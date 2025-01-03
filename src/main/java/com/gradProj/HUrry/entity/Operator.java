package com.gradProj.HUrry.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Operator extends User{
    private String companyName;
}
