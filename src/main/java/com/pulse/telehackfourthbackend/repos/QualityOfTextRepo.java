package com.pulse.telehackfourthbackend.repos;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfText;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityOfTextRepo extends JpaRepository<QualityOfText, Long> {
    // интерфейс, который является репозиторием для сущности QualityOfText
    Page<QualityOfText> findAll(Pageable pageable);
}
