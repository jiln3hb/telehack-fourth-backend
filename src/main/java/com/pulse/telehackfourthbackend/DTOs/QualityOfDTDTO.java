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
    private float failedHttpSessionsRate;
    private float avgDTSpeedFromClient;
    private float avgDTSpeedToClient;
    private float httpSessionTime;
    private String operator;
}