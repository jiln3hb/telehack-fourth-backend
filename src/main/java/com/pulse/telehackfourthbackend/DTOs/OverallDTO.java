package com.pulse.telehackfourthbackend.DTOs;

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
public class OverallDTO {
    private String federalDistrict;
    private String placeOfMeasure;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<BackgroundInformationDTO> backgroundInformationDTOList;
    private List<QualityOfVoiceDTO> qualityOfVoiceDTOList;
    private List<QualityOfTextDTO> qualityOfTextDTOList;
    private List<QualityOfDTDTO> qualityOfDTDTOList;
}