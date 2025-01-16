package cz.uhk.homerecipes.controllers;

import cz.uhk.homerecipes.dao.CommentDAO;
import cz.uhk.homerecipes.dao.RecipeDAO;
import cz.uhk.homerecipes.models.Comment;
import cz.uhk.homerecipes.models.Recipe;
import cz.uhk.homerecipes.payload.request.CommentCreateRequest;
import cz.uhk.homerecipes.repository.UserRepository;
import cz.uhk.homerecipes.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentDAO dao;
    private final UserRepository userRepository;
    private final RecipeDAO recipeDAO;

    @Autowired
    CommentController(CommentDAO dao, UserRepository userRepository, RecipeDAO recipeDAO) {
        this.dao = dao;
        this.userRepository = userRepository;
        this.recipeDAO = recipeDAO;
    }


    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<?> getAllComments(@PathVariable int recipeId) {
        List<Comment> comments = dao.getAllCommentsByRecipe(recipeId);

        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        // Remove when all comments are public
        if (!comments.get(0).getRecipe().getUser().equals(UserUtils.getCurrentUser(userRepository))) {
            return ResponseEntity.status(403).body("Access denied");
        }

        return ResponseEntity.ok(comments);
    }

    @PostMapping("/recipe/{recipeId}")
    public ResponseEntity<?> addComment(@PathVariable int recipeId, @Valid @RequestBody CommentCreateRequest commentCreateRequest) {
        Comment comment = new Comment();
        Recipe recipe = recipeDAO.getRecipeById(recipeId);
        if (recipe == null) {
            return ResponseEntity.notFound().build();
        }
        comment.setRecipe(recipe);
        comment.setText(commentCreateRequest.getText());
        comment.setDate(LocalDateTime.now());
        comment.setUser(UserUtils.getCurrentUser(userRepository));

        dao.createComment(comment);
        return ResponseEntity.ok(comment);
    }
}
