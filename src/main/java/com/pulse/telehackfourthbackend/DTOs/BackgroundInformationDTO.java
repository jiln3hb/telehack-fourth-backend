package com.pulse.telehackfourthbackend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BackgroundInformationDTO { // DTO для сущности BackgroundInformation
    private Long backgroundInformationId;
    private int quantityOfVoiceConnections;
    private int quantityOfVoiceSeq;
    private int quantityOfVoiceConnectionsWithLowIntelligibility;
    private int quantityOfSentSms;
    private int quantityOfConnectingToHttpServerAttempts;
    private int quantityOfHttpSessions;
    private String operator;
}