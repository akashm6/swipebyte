package com.swipebyte.project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    private Long id;
    private String username;
    private String email;
    private String password;

    public LoginDto(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

}
