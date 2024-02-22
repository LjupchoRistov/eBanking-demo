package com.example.racepulse.dto;

import com.example.racepulse.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsArticleDto {
    private Long id;
    @NotEmpty(message = "Title cannot be empty!")
    private String title;
    @NotEmpty(message = "Content cannot be empty!")
    private String content;
    @NotEmpty(message = "Photo url cannot be empty!")
    private String photoUrl;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private UserEntity createdBy;
}

