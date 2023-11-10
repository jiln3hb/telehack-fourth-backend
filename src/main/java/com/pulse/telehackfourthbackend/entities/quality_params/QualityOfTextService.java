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
public class QualityOfTextService {

    @Id
    @SequenceGenerator(name = "QUALITY_OF_TEXT_ID_GEN", sequenceName = "QUALITY_OF_TEXT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_TEXT_ID_GEN")
    private Long qualityOfTextServiceId;
    private Long measureId;
    private float undeliveredSmsRate;
    private float avgSmsDeliveryTime;
}
