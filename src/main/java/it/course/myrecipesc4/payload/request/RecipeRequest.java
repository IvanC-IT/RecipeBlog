package it.course.myrecipesc4.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class RecipeRequest {
	
	private long chef;
	private String recipeName;
	private Short calories;
	private String difficulty;
	private String dose;
	private short preparation;
	private Short cooking;
	private String cost;
	private String notes;
	private String overview;
	private String content;
	private boolean healthy;
	
}
