package com.sam.velusamy_samundeeswari_peacocknestdaycare.security;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Role;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //Constructs a CustomUserDetailsService with the specified UserRepository.
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by their email address.
     * @param email the email address of the user
     * @return UserDetails object representing the user with the given email
     * @throws UsernameNotFoundException if no user is found with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email);

        /// Returns a UserDetails object with the user's email, password, and authorities
        if(user!=null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),mapRolesToAuthorities(user.getRoles()));

        }
        else{
            // Throws exception if no user is found with the given email
            throw new UsernameNotFoundException("Invalid email or password.");

        }
    }

    /**
     * Maps a collection of Role entities to a collection of GrantedAuthority objects.
     * @param roles the collection of roles to be mapped
     * @return a collection of GrantedAuthority objects representing the user's authorities
     */
    private Collection< ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }

}