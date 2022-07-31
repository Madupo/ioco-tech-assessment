package com.ioco.iocoassessmentbackend.service;

import com.ioco.iocoassessmentbackend.model.Survivor;
import com.ioco.iocoassessmentbackend.repository.SurvivorRepository;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static com.ioco.iocoassessmentbackend.util.Constant.ROBOT_URL;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SurvivorServiceTest {
    @Autowired
    private SurvivorRepository survivorRepository;

    @Test
    void getAllSurvivors(){
        Survivor survivor = new Survivor(1L,"Emmanuel Madupo",29,"9208035482082",
                "Male","545121","89956",false,0);
        survivorRepository.save(survivor);
        Survivor firstResult = survivorRepository.findByIdentificationNumber("9208035482082");
        assertEquals(survivor.getName(), firstResult.getName());
        assertEquals(survivor.getAge(), firstResult.getAge());
    }


    @Test
    void updateSurvivorLocation(){
        Survivor survivor = new Survivor(1L,"Emmanuel Madupo",29,"9208035482082",
                "Male","154545","122345",false,0);
        survivorRepository.save(survivor);

        Survivor persistedSurvivor = survivorRepository.findByIdentificationNumber(survivor.getIdentificationNumber());
        assertNotNull(persistedSurvivor);
        persistedSurvivor.setLatitude("000000");
        persistedSurvivor.setLongitude("111111");
        assertNotEquals(persistedSurvivor.getLatitude(), survivor.getLatitude());
        assertNotEquals(persistedSurvivor.getLongitude(), survivor.getLongitude());
    }

    @Test
    void flagSurvivorAsInfectedLessThan3(){
        Survivor survivor = new Survivor(1L,"Emmanuel Madupo",29,"9208035482082",
                "Male","154545","122345",false,0);
        survivorRepository.save(survivor);

        Survivor persistedSurvivor = survivorRepository.findByIdentificationNumber(survivor.getIdentificationNumber());
        int currentComplaints = persistedSurvivor.getNumberOfComplaints();
        persistedSurvivor.setNumberOfComplaints(currentComplaints + 1);
        Survivor reportedSurvivor = survivorRepository.save(persistedSurvivor);
        assertNotEquals(reportedSurvivor.getNumberOfComplaints(), survivor.getNumberOfComplaints());
    }

    @Test
    public void whenPostJsonUsingHttpClient_thenCorrect()
            throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(ROBOT_URL);

        httpGet.setHeader("Accept", "application/json");
        httpGet.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = client.execute(httpGet);
        assertEquals(response.getStatusLine().getStatusCode(), 200);
        client.close();
    }
}
