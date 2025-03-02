package com.aigallery.entities;

import com.aigallery.helpers.Providers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String name;
    private String password;
    private String email;
    private String profilePicture;
    private String role;
    private boolean enabled;

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;

}
