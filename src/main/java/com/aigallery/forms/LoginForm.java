package com.aigallery.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginForm {

    @Email(message = "Invalid email address")
    @NotBlank (message ="Email required.")
    public String email;

    @NotBlank(message = "Password is required")
    public String password;
    
}
