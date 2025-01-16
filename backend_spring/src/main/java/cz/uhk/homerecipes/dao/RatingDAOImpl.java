package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RatingDAOImpl implements RatingDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RatingDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Rating> getAllRatingsByRecipe(int recipeId) {
        return entityManager.createQuery(
                        "SELECT r FROM Rating r WHERE r.recipe.id = :recipeId ORDER BY id DESC", Rating.class)
                .setParameter("recipeId", recipeId)
                .getResultList();
    }

    @Override
    @Transactional
    public void createRating(Rating rating) {
        entityManager.persist(rating);
    }
}
