package com.example.racepulse.mapper;


import com.example.racepulse.dto.NewsArticleDto;

public class NewsArticleMapper {
    public static NewsArticle mapToNewsArticle(NewsArticleDto articleDto){

        return NewsArticle.builder()
                .id(articleDto.getId())
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .photoUrl(articleDto.getPhotoUrl())
                .createdOn(articleDto.getCreatedOn())
                .updatedOn(articleDto.getUpdatedOn())
                .createdBy(articleDto.getCreatedBy())
                .build();
    }

    public static NewsArticleDto mapToNewsArticleDto(NewsArticle article){

        return NewsArticleDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .photoUrl(article.getPhotoUrl())
                .createdOn(article.getCreatedOn())
                .updatedOn(article.getUpdatedOn())
                .createdBy(article.getCreatedBy())
                .build();
    }
}
