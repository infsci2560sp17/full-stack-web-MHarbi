package edu.infsci2560.repositories;

import edu.infsci2560.models.Recipe;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long> {

    Recipe saveAndFlush(Recipe recipe);
    Recipe findById(Long id);
    @Transactional
    Long deleteById(Long id);
    Page<Recipe> findAll(Pageable pageable);
    List<Recipe> findByUserId(Long user_id);
    Recipe findByIdAndUserId(Long id, Long user_id);
}
