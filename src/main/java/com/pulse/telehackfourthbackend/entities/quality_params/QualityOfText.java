package com.pulse.telehackfourthbackend.entities.quality_params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.telehackfourthbackend.DTOs.QualityOfDTDTO;
import com.pulse.telehackfourthbackend.DTOs.QualityOfTextDTO;
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
public class QualityOfText {

    @Id
    @SequenceGenerator(name = "QUALITY_OF_TEXT_ID_GEN", sequenceName = "QUALITY_OF_TEXT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_TEXT_ID_GEN")
    private Long qualityOfTextId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "measure_id")
    @JsonIgnore
    private Measure measure;
    private float undeliveredSmsRate;
    private float avgSmsDeliveryTime;
    private String operator;

    public QualityOfText(Measure measure, float[] qost, String operator) {
        this.measure = measure;
        this.undeliveredSmsRate = qost[0];
        this.avgSmsDeliveryTime = qost[1];
        this.operator = operator;
    }

    public QualityOfText(Measure measure, QualityOfTextDTO qualityOfTextDTO) {
        this.measure = measure;
        this.undeliveredSmsRate = qualityOfTextDTO.getUndeliveredSmsRate();
        this.avgSmsDeliveryTime = qualityOfTextDTO.getAvgSmsDeliveryTime();
        this.operator = qualityOfTextDTO.getOperator();
    }
}
