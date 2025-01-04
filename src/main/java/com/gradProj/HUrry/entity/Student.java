package com.gradProj.HUrry.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student extends User{
    private String studentId;
}
