package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Comment;

import java.util.List;

public interface CommentDAO {
    List<Comment> getAllCommentsByRecipe(int recipeId);

    void createComment(Comment comment);
}
