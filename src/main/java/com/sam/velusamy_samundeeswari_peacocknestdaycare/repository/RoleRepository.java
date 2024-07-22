package com.sam.velusamy_samundeeswari_peacocknestdaycare.repository;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
