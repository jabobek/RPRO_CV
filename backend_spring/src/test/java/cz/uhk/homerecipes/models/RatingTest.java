package cz.uhk.homerecipes.models;

import cz.uhk.homerecipes.dao.RatingDAO;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class RatingTest {

    @Autowired
    private RatingDAO ratingDAO;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testCreateAndRetrieveRating() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@test.com");
        entityManager.persist(user);

        Recipe recipe = new Recipe();
        recipe.setName("Test Recipe");
        recipe.setUser(user);
        recipe.setSubtitle("test");
        recipe.setText("test");
        entityManager.persist(recipe);

        Rating rating = new Rating();
        rating.setUser(user);
        rating.setRecipe(recipe);
        rating.setRating(5);
        ratingDAO.createRating(rating);

        List<Rating> ratings = ratingDAO.getAllRatingsByRecipe(recipe.getId());

        // Verify
        assertEquals(1, ratings.size());
        assertEquals(5, ratings.get(0).getRating());
        assertEquals(user.getId(), ratings.get(0).getUser().getId());
        assertEquals(recipe.getId(), ratings.get(0).getRecipe().getId());
    }
}
