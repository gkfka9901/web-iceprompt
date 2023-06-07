package com.study.web.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.servlet.support.WebContentGenerator;

import java.util.List;

@Entity
@Table(name = "web")
@Data
public class Web {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "web", cascade = CascadeType.ALL)
    private List<WebContent> webContentList;

    // Getter, Setter, Constructors 등 생략

    public void addWebContent(WebContent webContent) {
        webContent.setWeb(this);
        webContentList.add(webContent);
    }

    public void removeWebContent(WebContent webContent) {
        webContent.setWeb(null);
        webContentList.remove(webContent);
    }
}
