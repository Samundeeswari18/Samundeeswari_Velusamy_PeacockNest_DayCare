package com.sam.velusamy_samundeeswari_peacocknestdaycare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailSubscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String emailAddress;

    private LocalDate subscriptionDate;

    @PrePersist
    protected void onCreate() {
        subscriptionDate = LocalDate.now();
    }
}
