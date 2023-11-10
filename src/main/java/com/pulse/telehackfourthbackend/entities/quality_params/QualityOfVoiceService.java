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
public class QualityOfVoiceService {

    @Id
    @SequenceGenerator(name = "QUALITY_OF_VOICE_ID_GEN", sequenceName = "QUALITY_OF_VOICE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_VOICE_ID_GEN")
    private Long qualityOfVoiceServiceId;
    private Long measureId;
    private float failedAttemptsToEstablishVoiceConnectionRate;
    private float failureVoiceConnectionsRate;
    private float avgSpeechQuality;
    private float voiceConnectionsWithLowSpeechQualityRate;
}
