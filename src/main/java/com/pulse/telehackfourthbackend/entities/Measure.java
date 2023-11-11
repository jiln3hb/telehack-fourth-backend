package com.pulse.telehackfourthbackend.entities;

import com.pulse.telehackfourthbackend.entities.quality_params.BackgroundInformation;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfDT;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfText;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfVoice;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

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

    @OneToMany(mappedBy = "measure")
    private List<BackgroundInformation> backgroundInformationList;
    @OneToMany(mappedBy = "measure")
    private List<QualityOfVoice> qualityOfVoiceList;
    @OneToMany(mappedBy = "measure")
    private List<QualityOfText> qualityOfTextList;
    @OneToMany(mappedBy = "measure")
    private List<QualityOfDT> qualityOfDTList;
}