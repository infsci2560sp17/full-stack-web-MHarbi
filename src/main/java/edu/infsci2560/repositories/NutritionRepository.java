package edu.infsci2560.repositories;

import edu.infsci2560.models.Nutrition;
import org.springframework.data.repository.PagingAndSortingRepository;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface NutritionRepository extends PagingAndSortingRepository<Nutrition, Long> {

    @Transactional
    Long deleteById(Long id);
    @Transactional
    Long deleteByRecipeId(Long recipe_id);
    Nutrition findByRecipeId(Long recipe_id);
    // @Modifying
    // @Query("update Nutrition n set n.calories = ?1, n.fat = ?2, n.carbs = ?3, n.protein = ?4, n.cholesterol = ?5, n.sodium = ?6 where n.recipe_id = ?7")
    // Long setNutritionInfoByRecipeId(float calories, float fat, float carbs, float protein, float cholesterol, float sodium, Long recipe_id);
}
