package com.pulse.telehackfourthbackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualityOfTextDTO {
    private float undeliveredSmsRate;
    private float avgSmsDeliveryTime;
    private String operator;
}