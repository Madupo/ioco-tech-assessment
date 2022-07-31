package com.ioco.iocoassessmentbackend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ioco.iocoassessmentbackend.dataobject.RobotResponse;
import com.ioco.iocoassessmentbackend.interfaces.SurvivorInterface;
import com.ioco.iocoassessmentbackend.model.Survivor;
import com.ioco.iocoassessmentbackend.repository.SurvivorRepository;
import com.ioco.iocoassessmentbackend.util.HttpHelper;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.ioco.iocoassessmentbackend.util.Constant.ROBOT_URL;

@Service
public class SurvivorService implements SurvivorInterface {
    @Autowired
    private SurvivorRepository survivorRepository;

    @Override
    public List<Survivor> findAll(){
        return survivorRepository.findAll();
    }
    @Override
    public Survivor flagInfectedSurvivor(Survivor survivor){
        if (survivor.getNumberOfComplaints() == 3 && !survivor.isInfected()){
            survivor.setInfected(true);
        }
        return survivorRepository.save(survivor);
    }
    @Override
    public Survivor findSurvivorByIdentification(String identificationNumber){
        return survivorRepository.findByIdentificationNumber(identificationNumber);
    }
    @Override
    public Survivor saveSurvivor(Survivor survivor){
        return survivorRepository.save(survivor);
    }

    @Override
    public List<RobotResponse> getAllRobots() throws IOException{
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            HttpHelper helper = new HttpHelper();
            HttpEntity response = helper.performGet(ROBOT_URL);
            ObjectMapper mapper = new ObjectMapper();
            String result = EntityUtils.toString(response);
            return mapper.readValue(result, new TypeReference<List<RobotResponse>>(){});
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            client.close();
        }
        return new ArrayList<>();
    }

}
