package com.pulse.telehackfourthbackend.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualityOfDTDTO {
    private double failedHttpSessionsRate;
    private double avgDTSpeedFromClient;
    private double avgDTSpeedToClient;
    private double httpSessionTime;
    private String operator;
}