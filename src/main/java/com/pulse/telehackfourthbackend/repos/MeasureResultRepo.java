package com.pulse.telehackfourthbackend.repos;

import com.pulse.telehackfourthbackend.entities.Measure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureResultRepo extends JpaRepository<Measure, Long> {
    Page<Measure> findAll(Pageable pageable);
}