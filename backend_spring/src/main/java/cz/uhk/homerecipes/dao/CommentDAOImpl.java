package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public CommentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Comment> getAllCommentsByRecipe(int recipeId) {
        return entityManager.createQuery(
                        "SELECT c FROM Comment c WHERE c.recipe.id = :recipeId ORDER BY date DESC", Comment.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    @Override
    @Transactional
    public void createComment(Comment comment) {
        entityManager.persist(comment);
    }
}
