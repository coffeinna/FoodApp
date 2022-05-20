package com.spring.crud.service;

import java.util.List;

import com.spring.crud.model.Recipe;
import org.springframework.stereotype.Service;

import com.spring.crud.model.RecipeIngredient;

@Service
public interface RecipeService {

	List<Recipe> getAllRecipes();
	
	List<RecipeIngredient> getAllRecipeIngredients();

	void saveRecipe(Recipe recipe);

	Recipe getRecipeById(Integer id);

	void deleteRecipeById(Integer id);
	
	void addIngredient(Integer recipeId, RecipeIngredient recipeIngredient);
	
    RecipeIngredient getRecipeIngredient(Integer id);
	
	List<RecipeIngredient> getRecipeIngredients(Integer recipeId);
	
	void removeIngredient(RecipeIngredient recipeIngredient);
	
	boolean canICook(Recipe recipe);
	
	void cook(Recipe recipe);
}
