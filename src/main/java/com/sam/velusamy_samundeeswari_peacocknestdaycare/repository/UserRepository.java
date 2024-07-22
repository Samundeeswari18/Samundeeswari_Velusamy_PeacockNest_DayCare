package com.sam.velusamy_samundeeswari_peacocknestdaycare.repository;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}