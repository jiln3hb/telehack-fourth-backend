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
    // класс, который реализует сущность, содержащую часть таблицы:
    // "Показатели качества услуг подвижной радиотелефонной связи в части голосового соединения"

    @Id
    @SequenceGenerator(name = "QUALITY_OF_VOICE_ID_GEN", sequenceName = "QUALITY_OF_VOICE_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_VOICE_ID_GEN")
    private Long qualityOfVoiceId;
    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "measure_id")
    @JsonIgnore
    private Measure measure;
    private double failedAttemptsToEstablishVoiceConnectionRate; // Доля неуспешных попыток установления голосового соединения  (Voice Service Non-Acessibility ) [%]
    private double failureVoiceConnectionsRate; // Доля обрывов голосовых соединений ( Voice Service Cut-off Ratio) [%]
    private double avgSpeechQuality; // Средняя разборчивость речи на соединение (Speech Quality on Call basis (MOS POLQA))
    private double voiceConnectionsWithLowSpeechQualityRate; //
    private String operator; // оператор связи, к которому относятся данные измерения

    public QualityOfVoice(Measure measure, double[] qosv, String operator) {
        this.measure = measure;
        this.failedAttemptsToEstablishVoiceConnectionRate = qosv[0];
        this.failureVoiceConnectionsRate = qosv[1];
        this.avgSpeechQuality = qosv[2];
        this.voiceConnectionsWithLowSpeechQualityRate = qosv[3];
        this.operator = operator;
    }

    public void setParams(Measure measure, QualityOfVoiceDTO qualityOfVoiceDTO) {
        this.measure = measure;
        this.failedAttemptsToEstablishVoiceConnectionRate = qualityOfVoiceDTO.getFailedAttemptsToEstablishVoiceConnectionRate();
        this.failureVoiceConnectionsRate = qualityOfVoiceDTO.getFailureVoiceConnectionsRate();
        this.avgSpeechQuality = qualityOfVoiceDTO.getAvgSpeechQuality();
        this.voiceConnectionsWithLowSpeechQualityRate = qualityOfVoiceDTO.getVoiceConnectionsWithLowSpeechQualityRate();
        this.operator = qualityOfVoiceDTO.getOperator();
    }
}