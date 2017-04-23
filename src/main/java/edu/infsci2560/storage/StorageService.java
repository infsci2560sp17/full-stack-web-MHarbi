package edu.infsci2560.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.ArrayList;

public interface StorageService {

    void init();

    void store(MultipartFile file);

    String store(MultipartFile[] files, String prefix);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
    
    void delete(String filename);

    ArrayList<String> lookup(String prefix);

}