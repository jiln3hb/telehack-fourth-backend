package com.pulse.telehackfourthbackend.repos;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.entities.quality_params.QualityOfDT;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualityOfDTRepo extends JpaRepository<QualityOfDT, Long> {
    Page<QualityOfDT> findAll(Pageable pageable);
}
