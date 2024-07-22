package com.sam.velusamy_samundeeswari_peacocknestdaycare.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

//Lombok to reduce the boiler plate code
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class ContactMessage {

    //Primary key and auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Variables and properties to the table
    private String fullName;
    private String email;
    private String message;
    private LocalDate date;
    private long phone;

}


