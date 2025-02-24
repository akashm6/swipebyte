package com.swipebyte.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

import com.swipebyte.project.entity.*;

@Getter
@Setter
public class UserRegistrationDto {

    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String confirmPassword;
    private UserProfile userProfile;
    private HashSet<Project> swipedRightProjects = new HashSet<Project>();

}
