package com.scmanage.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class ContactForm {

    @NotBlank(message= "name is required...")
    @Size(min=3,message="min 3 characters required")
    private String name;

    @Email(message = "email is required")
    @NotBlank(message ="Email required.")
    private String email;

    @NotBlank(message = "phone number is required")
    @Size(min=8,max=12,message="min 3 characters required")
    private String phoneNumber;

    @NotBlank(message = "Need Address here!")
    private String address;
    
    private String description;

    private String websiteLink;

    private String LinkedinLink;
    
    private String instagramLink;


    
    private boolean favorite;
    
    // @Annotation create karangr jo hamera file ko validate karega 
    //kyava  lidatekaregega resulition and size
    private MultipartFile contactImage;
    

}
