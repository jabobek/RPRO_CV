package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Tag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public TagDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void create(Tag tag) {
        entityManager.persist(tag);
    }

    @Override
    public List<Tag> findAllByUserId(int userId) {
        TypedQuery<Tag> query = entityManager.createQuery("FROM Tag t WHERE t.user.id = :userId", Tag.class);
        query.setParameter("userId", userId);

        return query.getResultList();
    }

    @Override
    public List<Tag> findAllByIdsAndUserId(List<Long> ids, int userId) {
        TypedQuery<Tag> query = entityManager.createQuery(
                "FROM Tag t WHERE t.id IN :ids AND t.user.id = :userId", Tag.class);
        query.setParameter("ids", ids);
        query.setParameter("userId", userId);

        return query.getResultList();
    }
}
