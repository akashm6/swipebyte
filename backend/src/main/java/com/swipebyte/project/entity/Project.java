package com.swipebyte.project.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    private String last_updated;

    private String primaryLanguage;

    private Integer stargazerCount;

    @ElementCollection
    private List<String> topics;

    private String issuingUser;
}
