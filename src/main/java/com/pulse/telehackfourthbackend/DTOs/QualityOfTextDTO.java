package com.pulse.telehackfourthbackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualityOfTextDTO { // DTO для сущности QualityOfText
    private Long qualityOfTextId;
    private double undeliveredSmsRate;
    private double avgSmsDeliveryTime;
    private String operator;
}