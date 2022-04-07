package com.spring.crud.ingredient;

import java.util.List;

public interface IngredientService {

	List<Ingredient> getAllIngredients();

	void saveIngredient(Ingredient ingredient);

	Ingredient getIngredientById(Integer id);

	void deleteIngredientById(Integer id);
	
	void removeSomeIngredient(Ingredient ingredient, Double amount);
}
