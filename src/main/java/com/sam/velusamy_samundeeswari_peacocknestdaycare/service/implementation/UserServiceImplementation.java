package com.sam.velusamy_samundeeswari_peacocknestdaycare.service.implementation;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.UserDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Role;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.RoleRepository;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.UserRepository;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setAddress(userDto.getAddress());
        user.setCity(userDto.getCity());
        user.setState(userDto.getState());
        user.setCountry(userDto.getCountry());
        user.setZip(userDto.getZip());


        //Determine the role based on registration criteria
        String roleName;
        if(userDto.isAdminRegistration()){
            roleName = "ROLE_ADMIN";
        }else{
            roleName= "ROLE_PARENT";
        }

        //Check if role already exists in database, otherwise create it
        Role role = roleRepository.findByName(roleName);
        if(role == null) {
            role = new Role();
            role.setName((roleName));
            roleRepository.save(role);
        }

        //Assign the role to the user
        user.setRoles((Collections.singletonList(role)));
        userRepository.save(user);
    }

    //Find user by email
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public boolean deleteParentByEmail(String email) {
        var user = userRepository.findByEmail(email);
        if (user != null) {
            user.getRoles().clear();  // Disassociate roles
            userRepository.delete(user);
            return true;
        }
        return false;
    }


    //Retrieve the list of users
    @Override
    public List<UserDto> findAllUsers() {
        List<User> users= userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    //getting user by id
    @Override
    public Optional<User> getUserById(Long parentId) {
        return Optional.empty();
    }

    //Convert user entity to UserDto
    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setAddress(user.getAddress());
        userDto.setCity(user.getCity());
        userDto.setState(user.getState());
        userDto.setCountry(user.getCountry());
        userDto.setZip(user.getZip());

        return userDto;
    }
}