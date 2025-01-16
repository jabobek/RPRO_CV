package cz.uhk.homerecipes.controllers;

import cz.uhk.homerecipes.dao.RatingDAO;
import cz.uhk.homerecipes.dao.RecipeDAO;
import cz.uhk.homerecipes.models.Rating;
import cz.uhk.homerecipes.models.Recipe;
import cz.uhk.homerecipes.payload.request.RatingCreateRequest;
import cz.uhk.homerecipes.repository.UserRepository;
import cz.uhk.homerecipes.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingDAO dao;
    private final UserRepository userRepository;
    private final RecipeDAO recipeDAO;

    @Autowired
    RatingController(RatingDAO dao, UserRepository userRepository, RecipeDAO recipeDAO) {
        this.dao = dao;
        this.userRepository = userRepository;
        this.recipeDAO = recipeDAO;
    }


    @PostMapping("/recipe/{recipeId}")
    public ResponseEntity<?> addRating(@PathVariable int recipeId, @Valid @RequestBody RatingCreateRequest ratingCreateRequest) {
        try {
            Rating rating = new Rating();
            Recipe recipe = recipeDAO.getRecipeById(recipeId);
            if (recipe == null) {
                return ResponseEntity.notFound().build();
            }
            rating.setRecipe(recipe);
            rating.setRating(ratingCreateRequest.getRating());
            rating.setUser(UserUtils.getCurrentUser(userRepository));

            dao.createRating(rating);
            return ResponseEntity.ok(rating);
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("You have already rated this recipe.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred. Please try again later.");
        }
    }


    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<?> getAllCommentsByRecipe(@PathVariable int recipeId) {
        List<Rating> ratings = dao.getAllRatingsByRecipe(recipeId);

        if (ratings.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        int count = ratings.size();
        double average = ratings.stream()
                .mapToInt(Rating::getRating)
                .average()
                .orElse(0.0);

        Map<String, Object> response = new HashMap<>();
        response.put("average", average);
        response.put("count", count);

        return ResponseEntity.ok(response);
    }
}
