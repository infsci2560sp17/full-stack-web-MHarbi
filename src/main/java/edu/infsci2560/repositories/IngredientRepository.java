package edu.infsci2560.repositories;

import edu.infsci2560.models.Ingredient;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import java.util.List;

public interface IngredientRepository extends PagingAndSortingRepository<Ingredient, Long> {

    @Transactional
    Long deleteById(Long id);
    @Transactional
    List<Ingredient> deleteByRecipeId(Long recipe_id);
}
