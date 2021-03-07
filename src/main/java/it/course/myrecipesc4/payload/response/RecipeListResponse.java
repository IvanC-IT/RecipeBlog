package it.course.myrecipesc4.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RecipeListResponse {

	private byte[] image = null;
	private String recipeName;
	private String overview;
	private long comments;
	private double rating;
	private String difficult;
	private short preparation;
	private short calories;
	
	
	public RecipeListResponse(String recipeName, String overview, long comments, double rating, String difficult,
			short preparation, short calories) {
		super();
		this.recipeName = recipeName;
		this.overview = overview;
		this.comments = comments;
		this.rating = rating;
		this.difficult = difficult;
		this.preparation = preparation;
		this.calories = calories;
	}
	
	
	
}
