package edu.infsci2560.repositories;

import edu.infsci2560.models.Comment;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    
    Comment findById(Long id);
    @Transactional
    Long deleteById(Long id);
}
