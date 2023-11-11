package com.pulse.telehackfourthbackend.entities.quality_params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.telehackfourthbackend.DTOs.BackgroundInformationDTO;
import com.pulse.telehackfourthbackend.DTOs.OverallDTO;
import com.pulse.telehackfourthbackend.entities.Measure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public BackgroundInformation(Measure measure, int[] backgr, String operator) {
        this.measure = measure;
        this.quantityOfVoiceConnections = backgr[0];
        this.quantityOfVoiceSeq = backgr[1];
        this.quantityOfVoiceConnectionsWithLowIntelligibility = backgr[2];
        this.quantityOfSentSms = backgr[3];
        this.quantityOfConnectingToHttpServerAttempts = backgr[4];
        this.quantityOfHttpSessions = backgr[5];
        this.operator = operator;
    }

    public BackgroundInformation(Measure measure, BackgroundInformationDTO backgroundInformationDTO) {
        this.measure = measure;
        this.quantityOfVoiceConnections = backgroundInformationDTO.getQuantityOfVoiceConnections();
        this.quantityOfVoiceSeq = backgroundInformationDTO.getQuantityOfVoiceSeq();
        this.quantityOfVoiceConnectionsWithLowIntelligibility = backgroundInformationDTO.getQuantityOfVoiceConnectionsWithLowIntelligibility();
        this.quantityOfSentSms = backgroundInformationDTO.getQuantityOfSentSms();
        this.quantityOfConnectingToHttpServerAttempts = backgroundInformationDTO.getQuantityOfConnectingToHttpServerAttempts();
        this.operator = backgroundInformationDTO.getOperator();
    }
}