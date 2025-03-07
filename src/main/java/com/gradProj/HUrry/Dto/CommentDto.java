package com.gradProj.HUrry.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {

    private String body;
    private String createdByUserId;
    private LocalDateTime createdDate;
}
