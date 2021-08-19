package com.codecool.articlemanager.articlemanager.repository;

import com.codecool.articlemanager.articlemanager.model.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    List<CommentEntity> findAllByArticleId(Long id);

}
