package com.osk.recruitmentsystemapi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    private String requirements;
    private String benefits;
    private String salary;
    private String contract;
    private String jobType;
    private String location;
}
