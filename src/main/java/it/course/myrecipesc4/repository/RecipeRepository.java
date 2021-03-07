package it.course.myrecipesc4.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.course.myrecipesc4.entity.Recipe;
import it.course.myrecipesc4.payload.response.RecipeListResponse;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>{

	Optional<Recipe> findByRecipeName(String recipeName);

	@Query(value="SELECT new it.course.myrecipesc4.payload.response.RecipeListResponse("
			//	+ "r.image,"
				+ "r.recipeName,"
				+ "r.overview,"
				+ "COUNT(c.commentId),"
				+ "COALESCE(ROUND(AVG(rat.rate),2),0),"  
				+ "r.difficulty,"
				+ "r.preparation,"
				+ "r.calories" 
				+ ")"
				+ " FROM Recipe r "
				+ "LEFT JOIN Comment c ON c.recipe = r AND c.approved = true "
				+ "LEFT JOIN Rating rat ON rat.recipe = r "
				+ "WHERE EXISTS (SELECT t.tagName FROM Tag t "
				+ "INNER JOIN t.recipes tr "
				+ "WHERE t.tagName = :tag ) "
				+ "GROUP BY r")
		Set<RecipeListResponse> findRecipeListResponse(String tag);

}
