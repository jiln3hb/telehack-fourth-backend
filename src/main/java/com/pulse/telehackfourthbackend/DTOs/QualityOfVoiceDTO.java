package com.pulse.telehackfourthbackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualityOfVoiceDTO {
    private float failedAttemptsToEstablishVoiceConnectionRate;
    private float failureVoiceConnectionsRate;
    private float avgSpeechQuality;
    private float voiceConnectionsWithLowSpeechQualityRate;
    private String operator;
}