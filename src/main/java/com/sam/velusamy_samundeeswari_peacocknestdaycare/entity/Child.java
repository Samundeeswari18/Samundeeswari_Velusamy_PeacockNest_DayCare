package com.sam.velusamy_samundeeswari_peacocknestdaycare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter         // Getters and Setters
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "child")
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long childId;
    private String childFirstName;
    private String childLastName;
    private LocalDate dob;
    private String gender;
    private String medicalInfo;
    private String photoUrl;


    @ManyToOne
    @JoinColumn(name = "parent_id",nullable = false)
    private  User user;


    // Calculates the age of the child based on the current date and the date of birth (dob).
    public int getAge() {
        return LocalDate.now().getYear() - dob.getYear();
    }

    // Sets the parent user for the child.
    public void setParent(User user) {
    }

    //ToString Method
    @Override
    public String toString() {
        return "Child{" +
                "childId=" + childId +
                ", childFirstName='" + childFirstName + '\'' +
                ", childLastName='" + childLastName + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", medicalInfo='" + medicalInfo + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
//                ", user=" + user +
                '}';
    }
}