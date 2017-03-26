package edu.infsci2560.repositories;

import edu.infsci2560.models.User;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByEmail(String email);
    User findById(Long id);
    @Transactional
    Long deleteById(Long id);
}