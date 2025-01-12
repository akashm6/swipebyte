package com.swipebyte.project.entity;

import java.util.List;
import java.util.Set;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    @ManyToMany
    @JoinTable(name = "swiped_right", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    private Set<Restaurant> swipedRightRestaurants;

    @ManyToMany
    @JoinTable(name = "visited_restaurants", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
    private Set<Restaurant> visitedRestaurants;
    /*
     * user_id restaurant_id
     * 1 1
     * 1 2
     * 2 1
     * 2 3
     */

    private String username;

    private String first_name;

    private String last_name;

    private String email;

    private String password;

    private String location;

    private Integer numcoins;

    public Integer getNumAchievements() {

        return profile.getAchievements().size();
    }

    public List<Achievement> getAchievements() {

        return profile.getAchievements();
    }

}
