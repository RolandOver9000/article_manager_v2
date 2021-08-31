package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.entity.TagEntity;
import com.codecool.articlemanager.articlemanager.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<TagEntity> saveMultipleStringTags(List<String> stringTags) {
        List<TagEntity> transformedTags = new ArrayList<TagEntity>();
        for(String tag : stringTags) {
            TagEntity savedTag = tagRepository.save(TagEntity.builder().tag(tag).build());
            transformedTags.add(savedTag);
        }
        return transformedTags;
    }
}
