package cz.uhk.homerecipes.dao;

import cz.uhk.homerecipes.models.Tag;

import java.util.List;

public interface TagDAO {

    void create(Tag tag);
    List<Tag> findAllByUserId(int userId);

    public List<Tag> findAllByIdsAndUserId(List<Long> ids, int userId);
}
