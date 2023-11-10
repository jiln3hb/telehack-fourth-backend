package com.pulse.telehackfourthbackend.entities.quality_params;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quality_of_dt_service")
public class QualityOfDTService {

    @Id
    @SequenceGenerator(name = "QUALITY_OF_DT_ID_GEN", sequenceName = "QUALITY_OF_DT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_DT_ID_GEN")
    @Column(name = "quality_of_dt_service_id")
    private Long qualityOfDTServiceId;
    private Long measureId;
    private float failedHttpSessionsRate;
    @Column(name = "avg_dt_speed_from_client")
    private float avgDTSpeedFromClient;
    @Column(name = "avg_dt_speed_to_client")
    private float avgDTSpeedToClient;
    private float httpSessionTime;
}