package com.pulse.telehackfourthbackend.entities.quality_params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.telehackfourthbackend.DTOs.QualityOfDTDTO;
import com.pulse.telehackfourthbackend.entities.Measure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "quality_of_dt")
public class QualityOfDT {

    @Id
    @SequenceGenerator(name = "QUALITY_OF_DT_ID_GEN", sequenceName = "QUALITY_OF_DT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_DT_ID_GEN")
    @Column(name = "quality_of_dt_id")
    private Long qualityOfDTId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "measure_id")
    @JsonIgnore
    private Measure measure;
    private float failedHttpSessionsRate;
    @Column(name = "avg_dt_speed_from_client")
    private float avgDTSpeedFromClient;
    @Column(name = "avg_dt_speed_to_client")
    private float avgDTSpeedToClient;
    private float httpSessionTime;
    private String operator;

    public QualityOfDT(Measure measure, float[] qosdt, String operator) {
        this.measure = measure;
        this.failedHttpSessionsRate = qosdt[0];
        this.avgDTSpeedFromClient = qosdt[1];
        this.avgDTSpeedToClient = qosdt[2];
        this.httpSessionTime = qosdt[3];
        this.operator = operator;
    }

    public QualityOfDT(Measure measure, QualityOfDTDTO qualityOfDTDTO) {
        this.measure = measure;
        this.failedHttpSessionsRate = qualityOfDTDTO.getFailedHttpSessionsRate();
        this.avgDTSpeedFromClient = qualityOfDTDTO.getAvgDTSpeedFromClient();
        this.avgDTSpeedToClient = qualityOfDTDTO.getAvgDTSpeedToClient();
        this.httpSessionTime = qualityOfDTDTO.getHttpSessionTime();
        this.operator = qualityOfDTDTO.getOperator();
    }
}
