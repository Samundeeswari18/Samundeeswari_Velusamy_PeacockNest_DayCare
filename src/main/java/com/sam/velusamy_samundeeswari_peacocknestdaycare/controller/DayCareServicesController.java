package com.sam.velusamy_samundeeswari_peacocknestdaycare.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DayCareServicesController {



        @GetMapping("/partTimeService")
        public String partTimeCare() {
            return "partTimeService";
        }

        @GetMapping("/fullTimeService")
        public String fullTimeCare() {
            return "fullTimeService";
        }

        @GetMapping("/afterSchoolService")
        public String afterSchoolProgram() {
            return "afterSchoolService";
        }
    }


