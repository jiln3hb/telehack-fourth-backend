package com.pulse.telehackfourthbackend.entities.quality_params;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.telehackfourthbackend.DTOs.QualityOfDTDTO;
import com.pulse.telehackfourthbackend.DTOs.QualityOfVoiceDTO;
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
public class QualityOfVoice {

    @Id
    @SequenceGenerator(name = "QUALITY_OF_VOICE_ID_GEN", sequenceName = "QUALITY_OF_VOICE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_VOICE_ID_GEN")
    private Long qualityOfVoiceId;
    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "measure_id")
    @JsonIgnore
    private Measure measure;
    private float failedAttemptsToEstablishVoiceConnectionRate;
    private float failureVoiceConnectionsRate;
    private float avgSpeechQuality;
    private float voiceConnectionsWithLowSpeechQualityRate;
    private String operator;

    public QualityOfVoice(Measure measure, float[] qosv, String operator) {
        this.measure = measure;
        this.failedAttemptsToEstablishVoiceConnectionRate = qosv[0];
        this.failureVoiceConnectionsRate = qosv[1];
        this.avgSpeechQuality = qosv[2];
        this.voiceConnectionsWithLowSpeechQualityRate = qosv[3];
        this.operator = operator;
    }

    public QualityOfVoice(Measure measure, QualityOfVoiceDTO qualityOfVoiceDTO) {
        this.measure = measure;
        this.failedAttemptsToEstablishVoiceConnectionRate = qualityOfVoiceDTO.getFailedAttemptsToEstablishVoiceConnectionRate();
        this.failureVoiceConnectionsRate = qualityOfVoiceDTO.getFailureVoiceConnectionsRate();
        this.avgSpeechQuality = qualityOfVoiceDTO.getAvgSpeechQuality();
        this.voiceConnectionsWithLowSpeechQualityRate = qualityOfVoiceDTO.getVoiceConnectionsWithLowSpeechQualityRate();
        this.operator = qualityOfVoiceDTO.getOperator();
    }
}