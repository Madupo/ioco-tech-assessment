package com.ioco.iocoassessmentbackend.repository;

import com.ioco.iocoassessmentbackend.model.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("survivorRepository")
public interface SurvivorRepository extends JpaRepository<Survivor, Long> {
    Survivor findByIdentificationNumber(String identificationNumber);
}
