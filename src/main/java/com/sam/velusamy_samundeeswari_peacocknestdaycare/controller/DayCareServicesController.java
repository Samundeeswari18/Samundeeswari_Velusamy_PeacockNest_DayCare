package com.sam.velusamy_samundeeswari_peacocknestdaycare.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DayCareServicesController {


        //    Displays the PartTime Services page.
        @GetMapping("/partTimeService")
        public String partTimeCare() {
            return "partTimeService";
        }

        //Displays the FullTime Services page.
        @GetMapping("/fullTimeService")
        public String fullTimeCare() {
            return "fullTimeService";
        }

        //    Displays the afterSchool Services page.
        @GetMapping("/afterSchoolService")
        public String afterSchoolProgram() {
            return "afterSchoolService";
        }
    }


