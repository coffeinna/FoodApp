package com.spring.crud.model;

import javax.persistence.*;

@Entity
public class RecipeIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Double amount, Ingredient ingredient, Recipe recipe) {
        this.amount = amount;
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public RecipeIngredient(Integer id, Double amount, Ingredient ingredient, Recipe recipe) {
        this.id = id;
        this.amount = amount;
        this.ingredient = ingredient;
        this.recipe = recipe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return amount + " " + ingredient;
    }


}
