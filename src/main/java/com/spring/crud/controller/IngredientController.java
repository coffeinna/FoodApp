package com.spring.crud.controller;

import com.spring.crud.service.IngredientServiceImpl;
import com.spring.crud.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.crud.service.UnitServiceImpl;


@Controller
public class IngredientController {

    @Autowired
    private IngredientServiceImpl ingredientServiceImpl;

    @Autowired
    private UnitServiceImpl unitServiceImpl;

    public IngredientController() {
    }

    public IngredientController(IngredientServiceImpl ingredientServiceImpl) {
        this.ingredientServiceImpl = ingredientServiceImpl;
    }

    public IngredientController(IngredientServiceImpl ingredientServiceImpl, UnitServiceImpl unitServiceImpl) {
        this.ingredientServiceImpl = ingredientServiceImpl;
        this.unitServiceImpl = unitServiceImpl;
    }

    @GetMapping("/newingredient")
    public String newIngredientForm(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("listUnits", unitServiceImpl.getAllUnits());
        return "ingredient_form";
    }

    @PostMapping("/ingredients/save")
    public String saveIngredient(@ModelAttribute Ingredient ingredient) {
        ingredientServiceImpl.saveIngredient(ingredient);
        return "redirect:/ingredients";
    }

    @GetMapping("/ingredients")
    public String listIngredients(Model model) {
        model.addAttribute("listIngredients", ingredientServiceImpl.getAllIngredients());
        model.addAttribute("listUnits", unitServiceImpl.getAllUnits());
        return "ingredients";
    }

    @GetMapping("/ingredients/edit/{id}")
    public String showEditIngredientForm(@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("ingredient", ingredientServiceImpl.getIngredientById(id));
        model.addAttribute("listUnits", unitServiceImpl.getUnitById(id));
        return "ingredient_form";
    }

    @GetMapping("/ingredients/delete/{id}")
    public String deleteIngredient(@PathVariable(value = "id") Integer id, Model model) {
        this.ingredientServiceImpl.deleteIngredientById(id);
        return "redirect:/ingredients";
    }

}
