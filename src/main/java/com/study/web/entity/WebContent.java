package com.study.web.entity;

import com.study.web.entity.Web;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "web_content")
@Data
public class WebContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "web_id")
    private Web web;

    @Column(name = "web_content")
    private String content;

    // Getter, Setter, Constructors 등 생략
}
