package edu.infsci2560.repositories;

import edu.infsci2560.models.Nutrition;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NutritionRepository extends PagingAndSortingRepository<Nutrition, Long> {}
