package com.pulse.telehackfourthbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Measure {

    @Id
    @SequenceGenerator(name = "MEASURE_ID_GEN", sequenceName = "MEASURE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEASURE_ID_GEN")
    private Long measureId;
    private String federalDistrict;
    private String placeOfMeasure;
    private LocalDate startDate;
    private LocalDate endDate;
    private String operator;
}