package com.spring.crud.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.spring.crud.model.Ingredient;
import com.spring.crud.repository.IngredientRepository;
import com.spring.crud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;

	public IngredientServiceImpl(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	@Override
    public List<Ingredient> getAllIngredients() {
		List<Ingredient> ingredients = ingredientRepository.findAll();
				return ingredients.stream().map(empl -> empl).collect(Collectors.toList());
    }
	  
	@Override
    public void saveIngredient(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

	@Override
	public Ingredient getIngredientById(Integer id) {
		Optional<Ingredient> optional = ingredientRepository.findById(id);
		Ingredient ingredient = null;
		if (optional.isPresent()) {
			ingredient = optional.get();
		} else {
			throw new RuntimeException(" Ingredient not found for id :: " + id);
		}
		return ingredient;
	}

	@Override
	public void deleteIngredientById(Integer id) {
		this.ingredientRepository.deleteById(id);		
	}

	@Override
	public void removeSomeIngredient(Ingredient ingredient, Double removedAmount) {
		 double actualAmount = ingredient.getAmount();
	        ingredient.setAmount(actualAmount - removedAmount);
	        ingredientRepository.save(ingredient);
		
	}


	  
	  
	  
}
