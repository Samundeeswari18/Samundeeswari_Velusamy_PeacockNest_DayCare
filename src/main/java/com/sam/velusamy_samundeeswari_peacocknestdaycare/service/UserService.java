package com.sam.velusamy_samundeeswari_peacocknestdaycare.service;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.UserDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {
    void saveUser(UserDto userDto);
    List<User> getAllUsers();
    List<UserDto> findAllUsers();

    Optional<User> getUserById(Long parentId);

    User findUserByEmail(String email);

    boolean deleteParentByEmail(String email);
}
