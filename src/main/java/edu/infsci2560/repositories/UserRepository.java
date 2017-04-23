package edu.infsci2560.repositories;

import edu.infsci2560.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    
    User saveAndFlush(User user);
    User findByEmail(String email);
    User findById(Long id);
    @Transactional
    Long deleteById(Long id);
}