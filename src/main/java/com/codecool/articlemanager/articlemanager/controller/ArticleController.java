package com.codecool.articlemanager.articlemanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/articles")
@CrossOrigin(origins = "*")
public class ArticleController {


}
