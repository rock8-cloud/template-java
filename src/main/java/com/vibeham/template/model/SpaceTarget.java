package com.vibeham.template.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "space_target")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Column(name = "distance_light_years")
    private Double distanceLightYears;
}
