package it.course.myrecipesc4.entity;
// Generated 2 mar 2021, 16:59:05 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserRecipeImageId generated by hbm2java
 */
@Embeddable
public class UserRecipeImageId implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private long userId;
	private long recipeId;

	public UserRecipeImageId() {
	}

	public UserRecipeImageId(long userId, long recipeId) {
		this.userId = userId;
		this.recipeId = recipeId;
	}

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "recipe_id", nullable = false)
	public long getRecipeId() {
		return this.recipeId;
	}

	public void setRecipeId(long recipeId) {
		this.recipeId = recipeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRecipeImageId))
			return false;
		UserRecipeImageId castOther = (UserRecipeImageId) other;

		return (this.getUserId() == castOther.getUserId()) && (this.getRecipeId() == castOther.getRecipeId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getUserId();
		result = 37 * result + (int) this.getRecipeId();
		return result;
	}

}
