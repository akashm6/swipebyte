package com.swipebyte.project.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    @ElementCollection
    private List<String> cuisines;

    @Transient
    private Double averageRating;

    private Integer visitCount;

    private String priceRange;
    private String description;
}
