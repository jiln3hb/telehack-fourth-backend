package com.pulse.telehackfourthbackend.repos;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfVoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityOfVoiceRepo extends JpaRepository<QualityOfVoice, Long> {
    // интерфейс, который является репозиторием для сущности QualityOfVoice
    Page<QualityOfVoice> findAll(Pageable pageable);
}
