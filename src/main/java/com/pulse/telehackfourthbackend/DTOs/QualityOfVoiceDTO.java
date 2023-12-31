package com.pulse.telehackfourthbackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QualityOfVoiceDTO { // DTO для сущности QualityOfVoice
    private Long qualityOfVoiceId;
    private double failedAttemptsToEstablishVoiceConnectionRate;
    private double failureVoiceConnectionsRate;
    private double avgSpeechQuality;
    private double voiceConnectionsWithLowSpeechQualityRate;
    private String operator;
}