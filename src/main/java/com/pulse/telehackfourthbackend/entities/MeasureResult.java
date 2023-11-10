package com.pulse.telehackfourthbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MeasureResult {

    @Id
    @SequenceGenerator(name = "MEASURE_ID_GEN", sequenceName = "MEASURE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEASURE_ID_GEN")
    private Long measureId;
    private String data;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "data: " + data + ", date: " + dateTime.toString();
    }
}
