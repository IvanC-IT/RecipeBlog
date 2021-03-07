package it.course.myrecipesc4.controller;


import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import it.course.myrecipesc4.entity.Category;
import it.course.myrecipesc4.payload.response.ApiResponse;
import it.course.myrecipesc4.repository.CategoryRepository;

@RestController
@Validated
public class CategoryController {
	
	@Autowired CategoryRepository categoryRepository;
	
	@GetMapping("public/get-category-menu/{categoryType}")
	public ResponseEntity<ApiResponse> getPostById(HttpServletRequest request, 
			@PathVariable  @Size(min=1,max=10) @NotBlank String categoryType){
		ApiResponse response = new ApiResponse(request);
		
		if (categoryType.equals("RICETTE")  || categoryType.equals("BENESSERE")) {
			List<Category> categories = categoryRepository.findByCategoryTypeOrderByCategoryIdAsc(categoryType);
			List<String> cNames2 = categories
				    .stream()
				    .map(c -> c.getCategoryName()).collect(Collectors.toList());
			response.setMessage(cNames2);
			return new ResponseEntity<ApiResponse>(response,HttpStatus.OK); 
		}
		
		response.setMessage("Category "+categoryType+" not found. Accepted params: [RICETTE,BENESSERE]");
		return new ResponseEntity<ApiResponse>(response,HttpStatus.BAD_REQUEST); 
	}
}
