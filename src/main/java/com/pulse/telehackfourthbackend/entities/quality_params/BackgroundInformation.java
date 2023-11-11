package com.pulse.telehackfourthbackend.entities.quality_params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.telehackfourthbackend.entities.Measure;
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
    private Long backgroundInformationId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_id")
    @JsonIgnore
    private Measure measure;
    private int quantityOfVoiceConnections;
    private int quantityOfVoiceSeq;
    private int quantityOfVoiceConnectionsWithLowIntelligibility;
    private int quantityOfSentSms;
    private int quantityOfConnectingToHttpServerAttempts;
    private int quantityOfHttpSessions;
    private String operator;
}