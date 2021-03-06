package it.course.myrecipesc4.entity;
// Generated 2 mar 2021, 16:59:05 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tag generated by hbm2java
 */
@Entity
@Table(name = "tag", catalog = "myrecipesc4")
public class Tag implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private long tagId;
	private Category category;
	private String tagName;
	private Set<Recipe> recipes = new HashSet<Recipe>(0);

	public Tag() {
	}

	public Tag(long tagId, Category category, String tagName) {
		this.tagId = tagId;
		this.category = category;
		this.tagName = tagName;
	}

	public Tag(long tagId, Category category, String tagName, Set<Recipe> recipes) {
		this.tagId = tagId;
		this.category = category;
		this.tagName = tagName;
		this.recipes = recipes;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tag_id", unique = true, nullable = false)
	public long getTagId() {
		return this.tagId;
	}

	public void setTagId(long tagId) {
		this.tagId = tagId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_category_id", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "tag_name", nullable = false, length = 45)
	public String getTagName() {
		return this.tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "recipe_has_tag", catalog = "myrecipesc4", joinColumns = {
			@JoinColumn(name = "tag_tag_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "recipe_recipe_id", nullable = false, updatable = false) })
	public Set<Recipe> getRecipes() {
		return this.recipes;
	}

	public void setRecipes(Set<Recipe> recipes) {
		this.recipes = recipes;
	}

}
