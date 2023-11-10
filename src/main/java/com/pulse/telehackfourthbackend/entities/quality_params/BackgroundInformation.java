package com.pulse.telehackfourthbackend.entities.quality_params;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BackgroundInformation {

    @Id
    @SequenceGenerator(name = "BACKGROUND_ID_GEN", sequenceName = "BACKGROUND_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BACKGROUND_ID_GEN")
    private Long backgroundInfoId;
    private Long measureId;
    private int quantityOfVoiceConnections;
    private int quantityOfVoiceSeq;
    private int quantityOfVoiceConnectionsWithLowIntelligibility;
    private int quantityOfSentSms;
    private int quantityOfConnectingToHttpServerAttempts;
    private int quantityOfHttpSessions;
}