package com.codecool.articlemanager.articlemanager.repository;

import com.codecool.articlemanager.articlemanager.model.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {

    Optional<TagEntity> findByTag(String tag);
}
