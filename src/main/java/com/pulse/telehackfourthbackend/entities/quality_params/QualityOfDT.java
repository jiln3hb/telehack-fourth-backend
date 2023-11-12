package com.pulse.telehackfourthbackend.entities.quality_params;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pulse.telehackfourthbackend.DTOs.QualityOfDTDTO;
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
@Table(name = "quality_of_dt")
public class QualityOfDT { // класс, который реализует сущность, содержащую часть таблицы "Показатели качества услуг связи по передаче данных"

    @Id
    @SequenceGenerator(name = "QUALITY_OF_DT_ID_GEN", sequenceName = "QUALITY_OF_DT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QUALITY_OF_DT_ID_GEN")
    @Column(name = "quality_of_dt_id")
    private Long qualityOfDTId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "measure_id")
    @JsonIgnore
    private Measure measure;
    private double failedHttpSessionsRate; // Доля неуспешных сессий по протоколу HTTP (HTTP Session Failure Ratio) [%]
    @Column(name = "avg_dt_speed_from_client")
    private double avgDTSpeedFromClient; // Среднее значение скорости передачи данных от абонента (HTTP UL Mean User Data Rate) [kbit/sec]
    @Column(name = "avg_dt_speed_to_client")
    private double avgDTSpeedToClient; // Среднее значение скорости передачи данных к абоненту (HTTP DL Mean User Data Rate) [kbit/sec]
    private double httpSessionTime; // Продолжительность успешной сессии (HTTP Session Time) [s]
    private String operator; // оператор связи, к которому относятся данные измерения

    public QualityOfDT(Measure measure, double[] qosdt, String operator) {
        this.measure = measure;
        this.failedHttpSessionsRate = qosdt[0];
        this.avgDTSpeedFromClient = qosdt[1];
        this.avgDTSpeedToClient = qosdt[2];
        this.httpSessionTime = qosdt[3];
        this.operator = operator;
    }

    public void setParams(Measure measure, QualityOfDTDTO qualityOfDTDTO) {
        this.measure = measure;
        this.failedHttpSessionsRate = qualityOfDTDTO.getFailedHttpSessionsRate();
        this.avgDTSpeedFromClient = qualityOfDTDTO.getAvgDTSpeedFromClient();
        this.avgDTSpeedToClient = qualityOfDTDTO.getAvgDTSpeedToClient();
        this.httpSessionTime = qualityOfDTDTO.getHttpSessionTime();
        this.operator = qualityOfDTDTO.getOperator();
    }
}
