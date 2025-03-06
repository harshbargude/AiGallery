package com.aigallery.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aigallery.entities.Role;



@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    Optional<Role> findByRole(String string);

}
