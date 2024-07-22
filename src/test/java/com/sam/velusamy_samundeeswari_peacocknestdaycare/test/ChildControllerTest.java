package com.sam.velusamy_samundeeswari_peacocknestdaycare.test;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.controller.ChildController;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Child;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.ChildService;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class ChildControllerTest {

    @InjectMocks
    private ChildController childController;

    @Mock
    private UserService userService;

    @Mock
    private ChildService childService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(childController).build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"parent1@example.com", "parent2@example.com", "nonexistent@example.com"})
    public void testGetParentInfo(String email) throws Exception {
        // Arrange
        if ("nonexistent@example.com".equals(email)) {
            when(userService.findUserByEmail(email)).thenReturn(null);
        } else {
            User user = new User();
            user.setEmail(email);
            Child child = new Child();
            child.setUser(user);
            when(userService.findUserByEmail(email)).thenReturn(user);
            when(childService.getChildrenByUser(user)).thenReturn(Collections.singletonList(child));
        }

        // Act & Assert
        mockMvc.perform(post("/childInfo")
                        .param("email", email))
                .andExpect(status().isOk())
                .andExpect(view().name("childInfoByParentEmail"))
                .andExpect(model().attributeExists("children"))
                .andExpect(model().attribute("email", email))
                .andExpect(model().attribute("children", hasSize(email.equals("nonexistent@example.com") ? 0 : 1)));
    }
}
