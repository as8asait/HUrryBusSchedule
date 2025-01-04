package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int state_id;
    private String state_name;
}
