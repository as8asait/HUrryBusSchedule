package com.gradProj.HUrry.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

    @Id
    private Long id;

    private String body;
    private String createdByUserId;
    private LocalDateTime createdDate;

}
