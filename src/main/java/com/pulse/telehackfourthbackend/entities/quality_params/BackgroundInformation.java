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
public class BackgroundInformation { // класс, который реализует сущность, содержащую часть таблицы "Справочная информация"

    @Id
    @SequenceGenerator(name = "BACKGROUND_ID_GEN", sequenceName = "BACKGROUND_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BACKGROUND_ID_GEN")
    private Long backgroundInformationId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "measure_id")
    @JsonIgnore
    private Measure measure;
    private int quantityOfVoiceConnections; // Общее количество тестовых голосовых соединений
    private int quantityOfVoiceSeq; // Общее количество голосовых последовательностей в оцениваемых соединениях (POLQA)
    private int quantityOfVoiceConnectionsWithLowIntelligibility; // Количество голосовых соединений с низкой разборчивостью (Negative MOS samples Count, MOS POLQA<2,6)[%]
    private int quantityOfSentSms; // Общее количество отправленных SMS - сообщений
    private int quantityOfConnectingToHttpServerAttempts; // Общее количество попыток соединений с сервером передачи данных HTTP (Загрузка файлов)
    private int quantityOfHttpSessions; // Общее количество тестовых сессий по протоколу HTTP (Web-browsing)
    private String operator; // оператор связи, к которому относятся данные измерения

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

    public void setParams(Measure measure, BackgroundInformationDTO backgroundInformationDTO) {
        this.measure = measure;
        this.quantityOfVoiceConnections = backgroundInformationDTO.getQuantityOfVoiceConnections();
        this.quantityOfVoiceSeq = backgroundInformationDTO.getQuantityOfVoiceSeq();
        this.quantityOfVoiceConnectionsWithLowIntelligibility = backgroundInformationDTO.getQuantityOfVoiceConnectionsWithLowIntelligibility();
        this.quantityOfSentSms = backgroundInformationDTO.getQuantityOfSentSms();
        this.quantityOfConnectingToHttpServerAttempts = backgroundInformationDTO.getQuantityOfConnectingToHttpServerAttempts();
        this.operator = backgroundInformationDTO.getOperator();
    }
}