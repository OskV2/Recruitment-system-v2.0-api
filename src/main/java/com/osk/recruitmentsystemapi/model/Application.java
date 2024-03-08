package com.osk.recruitmentsystemapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "offer_id") // Name of the foreign key column in the Application table
    private Offer offer;

    private String name;

    private String surname;

    private String email;

    private String phoneNumber;

    private String githubLink;

    private String linkedInLink;

    private LocalDateTime interviewDate = null;

    private int status = 1;
}