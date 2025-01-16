package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Recipe;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeDAOImpl implements RecipeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public RecipeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void createRecipe(Recipe recipe) {
        entityManager.persist(recipe);
    }

    @Override
    @Transactional
    public void updateRecipe(Recipe recipe) {
        entityManager.merge(recipe);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        TypedQuery<Recipe> query = entityManager.createQuery("FROM Recipe", Recipe.class);
        return query.getResultList();
    }

    @Override
    public List<Recipe> getAllRecipes(int userId, String searchText, String sortBy, boolean desc) {
        StringBuilder jpql = new StringBuilder("FROM Recipe e WHERE e.user.id = :userId");

        if (searchText != null && !searchText.trim().isEmpty()) {
            jpql.append(" AND (LOWER(e.name) LIKE LOWER(:searchText) OR LOWER(e.text) LIKE LOWER(:searchText) OR LOWER(e.subtitle) LIKE LOWER(:searchText))");
        }

        if (sortBy != null && !sortBy.trim().isEmpty()) {
            switch (sortBy) {
                case "name":
                    jpql.append(" ORDER BY e.name");
                    break;
                case "subtitle":
                    jpql.append(" ORDER BY e.subtitle");
                    break;
                case "imageBase64":
                    jpql.append(" ORDER BY e.imageBase64");
                    break;
                default:
                    jpql.append(" ORDER BY e.name");
            }
            if (desc) {
                jpql.append(" DESC");
            }
        } else {
            jpql.append(" ORDER BY e.name");
            if (desc) {
                jpql.append(" DESC");
            }
        }

        TypedQuery<Recipe> query = entityManager.createQuery(jpql.toString(), Recipe.class);
        query.setParameter("userId", userId);

        if (searchText != null && !searchText.trim().isEmpty()) {
            query.setParameter("searchText", "%" + searchText.trim() + "%");
        }

        return query.getResultList();
    }


    @Override
    public Recipe getRecipeById(int id) {
        return entityManager.find(Recipe.class, id);
    }

    @Override
    @Transactional
    public void removeRecipe(Recipe recipe) {
        entityManager.remove(recipe);
    }
}
