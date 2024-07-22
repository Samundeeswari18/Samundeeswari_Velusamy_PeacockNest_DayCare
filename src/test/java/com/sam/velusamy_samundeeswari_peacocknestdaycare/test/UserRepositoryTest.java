package com.sam.velusamy_samundeeswari_peacocknestdaycare.test;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        // Create and save test data
        User user = new User();
        user.setEmail("john@example.com");
        user.setUserName("john_doe");
        userRepository.save(user);
    }

    @Test
    public void testFindByEmail() {
        User foundUser = userRepository.findByEmail("john@example.com");
        assertNotNull(foundUser, "User should not be null");
        assertEquals("john_doe", foundUser.getUserName(), "Username should match");
    }
}
