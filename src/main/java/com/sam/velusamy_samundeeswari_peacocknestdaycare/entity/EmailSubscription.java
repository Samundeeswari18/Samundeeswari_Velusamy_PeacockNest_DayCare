package com.sam.velusamy_samundeeswari_peacocknestdaycare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

//Lombok to reduce the boiler plate code
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailSubscription {

    //Primary key and auto increment
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Variables and properties to the table
    private String emailAddress;

    private LocalDate subscriptionDate;

    //automatically calculate the date when subscription happens
    @PrePersist
    protected void onCreate() {
        subscriptionDate = LocalDate.now();
    }
}
