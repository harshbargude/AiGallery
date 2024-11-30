package com.scmanage.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class UserForm {

    @NotBlank(message = "User name is required..")
    @Size(min=3 ,message = "Min 3 characters are required")
    public String name;

    @Email(message = "Email is required")
    @NotBlank(message ="Email required.")
    public String email;

    @NotBlank(message = "Password is required")
    @Size(min=6 ,message = "Min 6 characters are required in password!")
    public String password;

    @NotBlank(message = "about is required!")
    public String about;

    @Size(min=8, max = 12 ,message = "Invalid phone Number")
    public String phoneNumber;

}
