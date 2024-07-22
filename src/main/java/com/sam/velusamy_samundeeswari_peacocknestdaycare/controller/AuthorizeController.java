package com.sam.velusamy_samundeeswari_peacocknestdaycare.controller;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.UserDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.User;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
public class AuthorizeController {

    private UserService userService;

    @Autowired
    public AuthorizeController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String loginForm()
    {
        return "login";
    }



    @GetMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";  // Ensure you have this template
    }

    @GetMapping("/parentDashboard")
    public String userDashboard() {
        return "parentDashboard";  // Ensure you have this template
    }


}