package com.myProject.sport.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.sport.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsernameIgnoreCase(String username);  
    User findByUsername(String username);
}
