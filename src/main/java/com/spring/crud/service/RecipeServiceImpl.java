package com.spring.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.spring.crud.model.Recipe;
import com.spring.crud.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.crud.model.Ingredient;
import com.spring.crud.model.RecipeIngredient;
import com.spring.crud.repository.RecipeIngredientRepository;


@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
    private IngredientServiceImpl ingredientServiceImpl;
	
	@Autowired
    private RecipeIngredientRepository recipeIngredientRepository;
    
	@Override
	public List<Recipe> getAllRecipes() {
		return recipeRepository.findAll();
	}

	@Override
	public void saveRecipe(Recipe recipe) {
		recipeRepository.save(recipe);
		
	}

	@Override
	public Recipe getRecipeById(Integer id) {
		Optional<Recipe> optional = recipeRepository.findById(id);
		Recipe recipe = null;
		if (optional.isPresent()) {
			recipe = optional.get();
		} else {
			throw new RuntimeException(" Recipe not found for id :: " + id);
		}
		return recipe;
	}

	@Override
	public void deleteRecipeById(Integer id) {
		this.recipeRepository.deleteById(id);
		
	}

	/**/

	@Override
	public RecipeIngredient getRecipeIngredient(Integer id) {
		RecipeIngredient ingredient = recipeIngredientRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid RecipeIngredient id: " + id)
        );
        return ingredient;
		
	}

	@Override
	public void addIngredient(Integer recipeId, RecipeIngredient recipeIngredient) {
		 Recipe recipe = getRecipeById(recipeId);
	        recipeIngredient.setRecipe(recipe);
	        recipeIngredientRepository.save(recipeIngredient);
		
	}

	@Override
	public List<RecipeIngredient> getRecipeIngredients(Integer recipeId) {
		return recipeIngredientRepository.findAll(recipeId);
	}
	

	@Override
	public void removeIngredient(RecipeIngredient recipeIngredient) {
		recipeIngredientRepository.delete(recipeIngredient);
		
	}

	@Override
	public List<RecipeIngredient> getAllRecipeIngredients() {
		List<RecipeIngredient> recipeIngredients = recipeIngredientRepository.findAll();
		return recipeIngredients.stream().map(empl -> empl).collect(Collectors.toList());
	}

	@Override
	public boolean canICook(Recipe recipe) {
		 List<RecipeIngredient> recipeIngredients = getRecipeIngredients(recipe.getId());
		 
		for ( RecipeIngredient recipeIngredient :
             recipeIngredients) {
         Ingredient ingredient = ingredientServiceImpl.getIngredientById(recipeIngredient.getIngredient().getId());
         if (ingredient.getAmount() < recipeIngredient.getAmount()){
        	 recipe.setStatus(false);
             return false;
         }
     }
	recipe.setStatus(true);
    return true;
 }

	@Override
	public void cook(Recipe recipe) {
		assert this.canICook(recipe);
		List<RecipeIngredient> recipeIngredients = getRecipeIngredients(recipe.getId());
		for (RecipeIngredient recipeIngredient :
            recipeIngredients) {
        ingredientServiceImpl.removeSomeIngredient(recipeIngredient.getIngredient(), recipeIngredient.getAmount());
    }
	}

	

	
}
