package com.spring.crud.recipe;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.spring.crud.recipeingredient.RecipeIngredient;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	
	@Column(length=45, nullable= false, unique=true)
	private String name;
	
	@Column(columnDefinition = "TEXT")
    private String description;
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private List<RecipeIngredient> ingredients = new ArrayList<>();

	public Recipe() {
	}
	
	
	public Recipe(String name, String description, List<RecipeIngredient> ingredients) {
		this.name = name;
		this.description = description;
		this.ingredients = ingredients;
	}

	public Recipe(Integer id, String name, String description, List<RecipeIngredient> ingredients) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.ingredients = ingredients;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RecipeIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredient(List<RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
