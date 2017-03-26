package edu.infsci2560.repositories;

import edu.infsci2560.models.Recipe;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;

public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long> {

    Recipe findById(Long id);
    @Transactional
    Long deleteById(Long id);
}
