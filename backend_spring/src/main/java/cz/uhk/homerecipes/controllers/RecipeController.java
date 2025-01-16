package cz.uhk.homerecipes.controllers;

import cz.uhk.homerecipes.dao.RecipeDAO;
import cz.uhk.homerecipes.dao.TagDAO;
import cz.uhk.homerecipes.models.Recipe;
import cz.uhk.homerecipes.models.Tag;
import cz.uhk.homerecipes.models.UserRole;
import cz.uhk.homerecipes.payload.request.RecipeCreateRequest;
import cz.uhk.homerecipes.repository.UserRepository;
import cz.uhk.homerecipes.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeDAO dao;
    private final UserRepository userRepository;
    private final TagDAO tagDAO;

    @Autowired
    public RecipeController(RecipeDAO recipeDAO, UserRepository userRepository, TagDAO tagDAO) {
        this.dao = recipeDAO;
        this.userRepository = userRepository;
        this.tagDAO = tagDAO;
    }


    @PostMapping("/")
    public ResponseEntity<?> createRecipe(@Valid @RequestBody RecipeCreateRequest recipeCreateRequest) {
        Recipe recipe = new Recipe();
        recipe.setName(recipeCreateRequest.getName());
        recipe.setSubtitle(recipeCreateRequest.getSubtitle());
        recipe.setText(recipeCreateRequest.getText());
        recipe.setUser(UserUtils.getCurrentUser(userRepository));
        recipe.setImageBase64(recipeCreateRequest.getImageBase64());

        List<Tag> tags = tagDAO.findAllByIdsAndUserId(recipeCreateRequest.getTagIds(), UserUtils.getCurrentUser(userRepository).getId());
        recipe.setTags(new HashSet<>(tags));

        dao.createRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@PathVariable int id, @Valid @RequestBody RecipeCreateRequest recipeCreateRequest) {
        Recipe recipe = dao.getRecipeById(id);

        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }

        if (!recipe.getUser().equals(UserUtils.getCurrentUser(userRepository))) {
            return ResponseEntity.status(403).body("Access denied");
        }

        recipe.setName(recipeCreateRequest.getName());
        recipe.setText(recipeCreateRequest.getText());
        if (recipeCreateRequest.getImageBase64() != null) {
            recipe.setImageBase64(recipeCreateRequest.getImageBase64());
        }

        List<Tag> tags = tagDAO.findAllByIdsAndUserId(recipeCreateRequest.getTagIds(), UserUtils.getCurrentUser(userRepository).getId());
        recipe.setTags(new HashSet<>(tags));

        dao.updateRecipe(recipe);
        return ResponseEntity.ok(recipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipe(@PathVariable int id) {
        Recipe recipe = dao.getRecipeById(id);

        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }

        if (recipe.getUser().getId() != UserUtils.getCurrentUser(userRepository).getId()) {
            return ResponseEntity.status(403).body("Access denied");
        }

        dao.removeRecipe(recipe);
        return ResponseEntity.ok("Removed");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipe(@PathVariable int id) {
        Recipe recipe = dao.getRecipeById(id);

        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }

        if (recipe.getUser().getId() != UserUtils.getCurrentUser(userRepository).getId()) {
            return ResponseEntity.status(403).body("Access denied");
        }

        return ResponseEntity.ok(recipe);
    }


    @GetMapping("/my")
    public ResponseEntity<?> getAllMyRecipes(
            @RequestParam(required = false) String searchText,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "false") boolean desc) {
        List<Recipe> recipes = dao.getAllRecipes(
                UserUtils.getCurrentUser(userRepository).getId(),
                searchText,
                sortBy,
                desc
        );
        return ResponseEntity.ok(recipes);
    }


    @GetMapping("/")
    public ResponseEntity<?> getAllRecipes() {
        List<Recipe> recipes = dao.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }
}
