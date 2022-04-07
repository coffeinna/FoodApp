package com.spring.crud.recipe;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans. factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework. ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.crud.ingredient.Ingredient;
import com.spring.crud.ingredient.IngredientRepository;
import com.spring.crud.ingredient.IngredientServiceImpl;
import com.spring.crud.recipeingredient.RecipeIngredient;
import com.spring.crud.recipeingredient.RecipeIngredientRepository;
import com.spring.crud.unit.Unit;


@Controller
public class RecipeController {

	@Autowired
	private RecipeServiceImpl recipeServiceImpl;
	@Autowired
	private IngredientServiceImpl ingredientServiceImpl;
	
	
	@GetMapping("/recipes")
	public String listRecipes(Model model) {
		model.addAttribute("listRecipes", recipeServiceImpl.getAllRecipes());
		model.addAttribute("listRecipeIngredient", recipeServiceImpl.getAllRecipeIngredients());
		return "recipes";
	}
	@GetMapping("/newrecipe")
	public String newRecipeForm (Model model) {
		
		
		model.addAttribute("recipe", new Recipe());
		return "recipe_form";
	}
	
	@PostMapping("/recipes/save")
	public String saveRecipe(Recipe recipe) {
		
		recipeServiceImpl.saveRecipe(recipe);
		return "redirect:/recipes";
	}
	
	@GetMapping("/recipes/edit/{id}")
	public String showEditRecipeForm(@PathVariable(value = "id") Integer id,Model model) {
		model.addAttribute("recipe",recipeServiceImpl.getRecipeById(id));
		return "recipe_form";
	}
	
	@GetMapping("/recipes/delete/{id}")
	public String deleteRecipe(@PathVariable(value = "id") Integer id,Model model ){
		recipeServiceImpl.deleteRecipeById(id);
		return "redirect:/recipes";
	}
	
	@GetMapping("/recipes/editIngredients/{id}")
	public String showEditRecipeIngredientForm(@PathVariable(value = "id") Integer id,Model model) {
		RecipeIngredient recipeIngredient = new RecipeIngredient();
		Recipe recipe = recipeServiceImpl.getRecipeById(id);
		recipeIngredient.setRecipe(recipe);
		List<RecipeIngredient> recipeIngredients = recipeServiceImpl.getRecipeIngredients(recipe.getId());
		
		model.addAttribute("recipe",recipeServiceImpl.getRecipeById(id));
		model.addAttribute("listIngredients", ingredientServiceImpl.getAllIngredients());
        model.addAttribute("recipeIngredient", recipeIngredient);
        model.addAttribute("recipeIngredients", recipeIngredients);
		
		return "recipeingredient_form";
	}
	
	@PostMapping("/recipes/save/ingredients/{id}")
	public String saveRecipeIngredient(@PathVariable(value = "id") Integer id, @ModelAttribute RecipeIngredient recipeIngredient) {
		
		recipeServiceImpl.addIngredient(id, recipeIngredient);
		return "redirect:/recipes";
	}
	
	@GetMapping("/recipes/delete/ingredients/{id}")
	public String deleteRecipeIngredient(@PathVariable(value = "id") Integer id) {
		
		 RecipeIngredient ingredient = recipeServiceImpl.getRecipeIngredient(id);
	     Integer recipeId = ingredient.getRecipe().getId();
	     recipeServiceImpl.removeIngredient(ingredient);
	        
		return "/recipes/editIngredients/"+recipeId;
	}
	
	
	@GetMapping("/recipes/cook/{id}")
	 public String cook(@PathVariable(value = "id") Integer id,RedirectAttributes ra){
		Recipe recipe = recipeServiceImpl.getRecipeById(id);
		String messege=" ";
		if(recipeServiceImpl.canICook(recipe)) {
			
			messege="Smacznego!";
			ra.addFlashAttribute("messege",messege);
			recipeServiceImpl.cook(recipe);
			
		}else {
			
			messege="O nie! Nie masz wystarczająco składników!";
			ra.addFlashAttribute("messegeNOT",messege);
		}
		
		
		return "redirect:/recipes";
	
	}
}