package com.pulse.telehackfourthbackend.entities;

import com.pulse.telehackfourthbackend.DTOs.OverallDTO;
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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Measure { // класс, реализующий сущность, которая содержит в себе данные об измерении, и ссылается на все параметры качества

    @Id
    @SequenceGenerator(name = "MEASURE_ID_GEN", sequenceName = "MEASURE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEASURE_ID_GEN")
    private Long measureId;
    private String federalDistrict;
    private String placeOfMeasure;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "measure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BackgroundInformation> backgroundInformationList;
    @OneToMany(mappedBy = "measure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QualityOfVoice> qualityOfVoiceList;
    @OneToMany(mappedBy = "measure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QualityOfText> qualityOfTextList;
    @OneToMany(mappedBy = "measure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QualityOfDT> qualityOfDTList;

    public Measure() {
        this.backgroundInformationList = new ArrayList<>();
        this.qualityOfVoiceList = new ArrayList<>();
        this.qualityOfTextList = new ArrayList<>();
        this.qualityOfDTList = new ArrayList<>();
    }

    public Measure(OverallDTO overallDTO) {
        this.federalDistrict = overallDTO.getFederalDistrict();
        this.placeOfMeasure = overallDTO.getPlaceOfMeasure();
        this.startDate = overallDTO.getStartDate();
        this.endDate = overallDTO.getEndDate();
    }

    public void setParams(OverallDTO overallDTO) {
        this.federalDistrict = overallDTO.getFederalDistrict();
        this.placeOfMeasure = overallDTO.getPlaceOfMeasure();
        this.startDate = overallDTO.getStartDate();
        this.endDate = overallDTO.getEndDate();
    }

    public void addBackgroundInformation(BackgroundInformation backgroundInformation) {
        backgroundInformationList.add(backgroundInformation);
    }

    public void addQualityOfVoice(QualityOfVoice qualityOfVoice) {
        qualityOfVoiceList.add(qualityOfVoice);
    }

    public void addQualityOfText(QualityOfText qualityOfText) {
        qualityOfTextList.add(qualityOfText);
    }

    public void addQualityOfDT(QualityOfDT qualityOfDT) {
        qualityOfDTList.add(qualityOfDT);
    }
}