package com.codecool.articlemanager.articlemanager.repository;

import com.codecool.articlemanager.articlemanager.model.ArticleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, Long> {

    List<ArticleEntity> findAll();
}
