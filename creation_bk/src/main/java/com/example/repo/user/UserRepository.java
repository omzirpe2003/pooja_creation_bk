package com.example.repo.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Model.authmodel.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    // 🔍 Existence checks (used during registration)
    boolean existsByUsername(String username);

    boolean existsByEmailId(String emailId);

    boolean existsByMobileNo(String mobileNo);

    // 🔎 Fetch user (used for login / profile)
    Optional<UserModel> findByUsername(String username);
    
    
    Optional<UserModel> findByEmailId(String emailId);
}
