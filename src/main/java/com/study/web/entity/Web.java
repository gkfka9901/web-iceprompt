package com.study.web.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Web {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private String content2;

}
