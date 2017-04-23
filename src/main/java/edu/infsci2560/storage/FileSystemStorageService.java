package edu.infsci2560.storage;

import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.ArrayList;

@Service
public class FileSystemStorageService implements StorageService {

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        
        File directory = new File(String.valueOf(this.rootLocation));
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public String store(MultipartFile[] files, String prefix) {
         String msg = "";
         ArrayList<String> filenames_succeed = new ArrayList<>();
         String current_file = "";
         ArrayList<MultipartFile> _files = new ArrayList<>(); // to be saved
         try {
            String[] fileFrags;
            String extension;
            int index = -1;
            File dir = this.rootLocation.toFile();
            // if(!dir.isDirectory()) throw new StorageException("wtf mate?");

            for(File f : dir.listFiles(new RegexFileFilter("^(" + prefix + "[-])"))) {

                fileFrags = f.getName().split("\\.");
                fileFrags = fileFrags[fileFrags.length-2].split("\\-");
                String i = fileFrags[fileFrags.length-1];
                if(index < Integer.parseInt(i))
                    index = Integer.parseInt(i);
            }

            for(MultipartFile file : files) {
                // I COMMENT THIS BECAUSE IN UPDATE, IMAGES NOT REQUIRED
                /*if (file.isEmpty()) {
                    msg += "Failed to store empty file " + file.getOriginalFilename() +"\n";
                }*/
                if(!file.isEmpty()) {
                    _files.add(file);
                }
            }

            if(msg == "") {
                for(MultipartFile file : _files) {
                    current_file = file.getOriginalFilename();
                    index++;
                    fileFrags = file.getOriginalFilename().split("\\.");
                    extension = fileFrags[fileFrags.length-1];
                    String filename = prefix + "-" + index +  "." + extension;
                    
                    Files.copy(file.getInputStream(), this.rootLocation.resolve(filename));
                    filenames_succeed.add(filename);
                    current_file = "";
                }
            }

        } catch (IOException e) {
            msg += "Failed to store file " + current_file + ": " + e.getMessage() +"\n";
        } finally {
            if(msg != "") {
                for(String filename : filenames_succeed) {
                     delete(filename);
                }
            }
            return msg;
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void delete(String filename) {
        try {
            Files.delete(this.rootLocation.resolve(filename));
        } catch (IOException e) {
            //deleting file failed
        }
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }

    @Override
    public ArrayList<String> lookup(String prefix) {

        ArrayList<String> filenames = new ArrayList<>();
        File dir = this.rootLocation.toFile();
        for(File f : dir.listFiles(new RegexFileFilter("^(" + prefix + "[-])"))) {
            filenames.add(f.getName());
        }

        return filenames;
    }
}
