package com.ioco.iocoassessmentbackend.interfaces;

import com.ioco.iocoassessmentbackend.dataobject.RobotResponse;
import com.ioco.iocoassessmentbackend.model.Survivor;

import java.io.IOException;
import java.util.List;

public interface SurvivorInterface {
    List<Survivor> findAll();
    Survivor flagInfectedSurvivor(Survivor survivor);
    Survivor findSurvivorByIdentification(String identificationNumber);
    Survivor saveSurvivor(Survivor survivor);
    List<RobotResponse> getAllRobots() throws IOException, ClassNotFoundException;
}
