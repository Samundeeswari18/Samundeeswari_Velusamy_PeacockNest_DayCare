package com.sam.velusamy_samundeeswari_peacocknestdaycare.repository;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Child;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildRepository extends JpaRepository<Child, Long> {
    List<Child> findByUser(User user);

}