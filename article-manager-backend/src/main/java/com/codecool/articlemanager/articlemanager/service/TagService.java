package com.codecool.articlemanager.articlemanager.service;

import com.codecool.articlemanager.articlemanager.model.entity.TagEntity;
import com.codecool.articlemanager.articlemanager.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<TagEntity> saveMultipleStringTags(List<String> stringTags) {
        List<TagEntity> transformedTags = new ArrayList<>();
        for(String tag : stringTags) {
            Optional<TagEntity> optionalTagEntity = tagRepository.findByTag(tag);
            if(optionalTagEntity.isEmpty()) {
                TagEntity savedTag = tagRepository.save(TagEntity.builder().tag(tag).build());
                transformedTags.add(savedTag);
            } else {
                transformedTags.add(optionalTagEntity.get());
            }
        }
        return transformedTags;
    }
}
