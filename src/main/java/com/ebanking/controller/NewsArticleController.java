package com.ebanking.controller;

import com.example.racepulse.dto.NewsArticleDto;
import com.example.racepulse.models.Role;
import com.example.racepulse.models.UserEntity;
import com.example.racepulse.security.SecurityUtil;
import com.example.racepulse.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsArticleController {
    private final NewsArticleService newsArticleService;
    private final UserService userService;

    public NewsArticleController(NewsArticleService newsArticleService, UserService userService) {
        this.newsArticleService = newsArticleService;
        this.userService = userService;
    }

    @GetMapping("/news-articles")
    public String listNewsArticles(Model model) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userService.findByUsername(username);

        if (username == null)
            return "redirect:/login";

        List<NewsArticleDto> newsArticles = newsArticleService.all();
        List<Role> roles = user.getRoles().stream().toList();

        List<Role> temp = new ArrayList<>();
        boolean isAdmin = false;
        temp = roles.stream().filter(role -> role.getName().equals("ADMIN")).toList();
        if (!temp.isEmpty())
            isAdmin = true;

        temp = roles.stream().filter(role -> role.getName().equals("EDITOR")).toList();
        boolean isEditor = false;
        if (!temp.isEmpty())
            isEditor = true;

        boolean isValid = false;
        if (isAdmin|| isEditor)
            isValid = true;

        model.addAttribute("user", user);
        model.addAttribute("isEditor", isEditor);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("isValid", isValid);
        model.addAttribute("articles", newsArticles);
        model.addAttribute("name", username);

        return "news-articles-list";
    }

    @GetMapping("/news-articles/new")
    public String addNewsArticle(Model model) {
        NewsArticle newsArticle = new NewsArticle();
        model.addAttribute("article", newsArticle);

        return "news-articles-new";
    }

    @PostMapping("/news-articles/new")
    public String save(@Valid @ModelAttribute("article") NewsArticleDto newsArticleDto,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("article", newsArticleDto);
            return "news-articles-new";
        }

        newsArticleService.saveNewsArticle(newsArticleDto);
        return "redirect:/news-articles";
    }

    @GetMapping("/news-articles/edit")
    public String editNewsArticle(Model model) {
        NewsArticle newsArticle = new NewsArticle();
        model.addAttribute("article", newsArticle);

        return "news-articles-edit";
    }

    @GetMapping("/news-articles/delete")
    public String deleteNewsArticle(Model model) {
        NewsArticle newsArticle = new NewsArticle();
        model.addAttribute("article", newsArticle);

        return "news-articles-delete";
    }
}
