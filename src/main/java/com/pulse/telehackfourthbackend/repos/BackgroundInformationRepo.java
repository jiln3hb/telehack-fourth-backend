package com.pulse.telehackfourthbackend.repos;

import com.pulse.telehackfourthbackend.entities.Measure;
import com.pulse.telehackfourthbackend.entities.quality_params.BackgroundInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundInformationRepo extends JpaRepository<BackgroundInformation, Long> {
    Page<BackgroundInformation> findAll(Pageable pageable);
}
