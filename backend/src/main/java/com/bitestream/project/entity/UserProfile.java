package com.bitestream.project.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String bio;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ElementCollection
    private List<String> favoritecuisines;

    /*
     * A single profile can have many reviews
     * mappedBy tells us what Review will use to categorize who made the review
     */

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userProfile")
    private List<Achievement> achievements;

}
