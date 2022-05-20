package com.spring.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.crud.model.Ingredient;
import com.spring.crud.model.RecipeIngredient;


@Repository
public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, Integer> {

    @Query("SELECT t.ingredient FROM RecipeIngredient t WHERE t.recipe.id = ?1")
    List<Ingredient> findAllIngredients(Integer recipeId);

    @Query("SELECT t FROM RecipeIngredient t WHERE t.recipe.id = ?1")
    List<RecipeIngredient> findAll(Integer recipeId);

}
