package it.course.myrecipesc4.controller;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.istack.NotNull;

import it.course.myrecipesc4.entity.Chef;
import it.course.myrecipesc4.entity.Recipe;
import it.course.myrecipesc4.entity.Tag;
import it.course.myrecipesc4.payload.request.RecipeRequest;
import it.course.myrecipesc4.payload.response.ApiResponse;
import it.course.myrecipesc4.payload.response.RecipeListResponse;
import it.course.myrecipesc4.repository.ChefRepository;
import it.course.myrecipesc4.repository.RecipeRepository;
import it.course.myrecipesc4.repository.TagRepository;
import it.course.myrecipesc4.repository.UserRepository;

@RestController
@Validated
public class RecipeController {
	
	@Autowired RecipeRepository recipeRepository;
	@Autowired ChefRepository chefRepository;
	@Autowired UserRepository userRepository;
	@Autowired TagRepository tagRepository;
	@PostMapping("private/add-update-recipe")
	public ResponseEntity<ApiResponse> addUpdateFilm(@RequestBody @Valid RecipeRequest recipeRequest, 
			HttpServletRequest request) {
		
		ApiResponse response = new ApiResponse(request);
		
		Optional<Chef> chefOpt = chefRepository.findById(recipeRequest.getChef());
		if(chefOpt.isEmpty()) {
			response.setMessage("No chef");
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK); 
		}
		
		Chef chef = chefOpt.get();

		Optional<Recipe> recipeOptional = recipeRepository.findByRecipeName(recipeRequest.getRecipeName());
		Recipe recipe;
		if (recipeOptional.isPresent()) {
			recipe = recipeOptional.get();
			recipe.setCalories(recipeRequest.getCalories());
			recipe.setChef(chef);
			recipe.setContent(recipeRequest.getContent());
			recipe.setCooking(recipeRequest.getCooking());
			recipe.setCost(recipeRequest.getCost());
			recipe.setDifficulty(recipeRequest.getDifficulty());
			recipe.setDose(recipeRequest.getDose());
			recipe.setHealthy(recipeRequest.isHealthy());			
			recipe.setNotes(recipeRequest.getNotes());
			recipe.setOverview(recipeRequest.getOverview());
			recipe.setPreparation(recipeRequest.getPreparation());
			recipe.setRecipeName(recipeRequest.getRecipeName());

			response.setMessage("Recipe "+recipe.getRecipeId()+" updated");
			
		} else {		
			recipe = new Recipe(						
					chef,
					recipeRequest.getRecipeName(),
					recipeRequest.getCalories(),
					recipeRequest.getDifficulty(),
					recipeRequest.getDose(),
					recipeRequest.getPreparation(),
					recipeRequest.getCooking(),
					recipeRequest.getCost(),
					recipeRequest.getNotes(),
					recipeRequest.getOverview(),
					recipeRequest.getContent(),						
					recipeRequest.isHealthy()
			);
								
			response.setMessage("New Recipe Added");
		}
		
		recipeRepository.save(recipe);
	
		return new ResponseEntity<ApiResponse>(response,HttpStatus.OK); 
	}
	@GetMapping("public/get-recipe-list")
	public ResponseEntity<ApiResponse> getRecipeList(HttpServletRequest request,
			@RequestParam @NotNull @NotEmpty String tagName) {
		
		ApiResponse response = new ApiResponse(request);
		
		Optional<Tag> tagOpt = tagRepository.findByTagName(tagName);
		if(tagOpt.isEmpty()) {
			response.setMessage("No tag " + tagName + " found.");
			return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND); 
		
		}
		
		Set<RecipeListResponse> recipeList = recipeRepository.findRecipeListResponse(tagName);

		response.setMessage(recipeList);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK); 
	}
}
