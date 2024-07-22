package com.sam.velusamy_samundeeswari_peacocknestdaycare.service;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.UserDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface UserService {

    /**
     * Saves a new user based on the provided UserDto.
     *
     * @param userDto the data transfer object containing user details
     */
    void saveUser(UserDto userDto);


    /**
     * Retrieves a list of all users as UserDto objects.
     *
     * @return a list of UserDto objects
     */
    List<UserDto> findAllUsers();

    //Retrieve user by id
    Optional<User> getUserById(Long parentId);

    //Find the user by email
    User findUserByEmail(String email);

    //Delete a parent by email
    boolean deleteParentByEmail(String email);
}
