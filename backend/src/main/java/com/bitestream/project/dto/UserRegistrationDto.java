package com.bitestream.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

import com.bitestream.project.entity.*;

@Getter
@Setter
public class UserRegistrationDto {

    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String location;
    private Integer numCoins = 0;
    private UserProfile userProfile;
    private HashSet<Restaurant> swipedRightRestaurants = new HashSet<Restaurant>();
    private HashSet<Restaurant> visitedRestaurants = new HashSet<Restaurant>();

}