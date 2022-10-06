package com.bulbul.unittest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    private UUID id;
    @NotBlank(message = "First name must not be null")
    private String firstname;
    @NotBlank(message = "Last name must not be null")
    private String lastname;
    @Email(message = "That's not a valid email")
    @NotBlank(message = "Email must not be null")
    private String email;
    @Email(message = "That's not a valid username")
    @NotBlank(message = "Username must not be null")
    private String username;
    @NotBlank(message = "Password must not be null")
    private String password;
    private String role;

    public UserDTO(String firstname, String lastname, String email, String username, String password, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
