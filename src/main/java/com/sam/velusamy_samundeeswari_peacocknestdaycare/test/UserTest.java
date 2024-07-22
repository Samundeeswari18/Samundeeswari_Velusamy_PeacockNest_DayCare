package com.sam.velusamy_samundeeswari_peacocknestdaycare.test;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import org.junit.Test;
//import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        User user = new User();
        user.setId(1L);
        user.setUserName("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("1234567890");
        user.setAddress("123 Main St");
        user.setCity("Springfield");
        user.setState("IL");
        user.setCountry("USA");
        user.setZip("62701");

//        assertEquals(1L, user.getId());
        assertEquals("john_doe", user.getUserName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("John", user.getFirstName());
        assertEquals("Doe", user.getLastName());
        assertEquals("1234567890", user.getPhone());
        assertEquals("123 Main St", user.getAddress());
        assertEquals("Springfield", user.getCity());
        assertEquals("IL", user.getState());
        assertEquals("USA", user.getCountry());
        assertEquals("62701", user.getZip());
    }

    @Test
    public void testToString() {
        User user = new User();
        user.setId(1L);
        user.setUserName("john_doe");
        user.setEmail("john@example.com");
        user.setPassword("password");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPhone("1234567890");
        user.setAddress("123 Main St");
        user.setCity("Springfield");
        user.setState("IL");
        user.setCountry("USA");
        user.setZip("62701");

        String expectedToString = "User{" +
                ", zip='62701'" +
                ", country='USA'" +
                ", state='IL'" +
                ", city='Springfield'" +
                ", address='123 Main St'" +
                ", phone='1234567890'" +
                ", lastName='Doe'" +
                ", firstName='John'" +
                ", password='password'" +
                ", email='john@example.com'" +
                ", userName='john_doe'" +
                ", id=1" +
                '}';
        assertEquals(expectedToString, user.toString());
    }
}
