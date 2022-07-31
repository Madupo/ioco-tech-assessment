package com.ioco.iocoassessmentbackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ioco.iocoassessmentbackend.dataobject.RobotResponse;
import com.ioco.iocoassessmentbackend.service.SurvivorService;
import com.ioco.iocoassessmentbackend.dataobject.ComplainantInfo;
import com.ioco.iocoassessmentbackend.model.Survivor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class SurvivorControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SurvivorService survivorService;

    @Test
    void getAllSurvivors() throws Exception {
        List<Survivor> survivorList = new ArrayList<>();
        survivorList.add(new Survivor(1L,"Emmanuel Madupo",29,"9208035482082",
                "Male","5241564", "3223132",false,0));
        survivorList.add(new Survivor(1L,"Cindy Madupo",29,"8803255624085",
                "Female","5241564", "3223132",false,0));
        when(survivorService.findAll()).thenReturn(survivorList);

        mockMvc.perform(MockMvcRequestBuilders.get("/survivors/list")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

    @Test
    void flagSurvivorAsInfected() throws Exception {
        Survivor complainant = new Survivor(1L,"Cindy Madupo",21,"9208035482082",
                "Female","5241564", "3223132",false,0);
        when(survivorService.saveSurvivor(complainant)).thenReturn(complainant);
        when(survivorService.findSurvivorByIdentification(complainant.getIdentificationNumber())).thenReturn(complainant);
        ComplainantInfo complainantInfo = new ComplainantInfo(complainant.getIdentificationNumber());
        String body = stringifyObject(complainantInfo);
        when(survivorService.flagInfectedSurvivor(complainant)).thenReturn(complainant);
        mockMvc.perform(MockMvcRequestBuilders.post("/survivors/infected/flag/"+complainant.getIdentificationNumber().trim())
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andExpect(jsonPath("$", hasKey("message"))).andDo(print());
    }

    public String stringifyObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

    @Test
    void flagSurvivorAsInfectedNoProfile() throws Exception {
        Survivor complainant = new Survivor(1L,"Cindy Madupo",21,"9208035482082",
                "Female","5241564", "3223132",false,0);
        when(survivorService.saveSurvivor(complainant)).thenReturn(complainant);
        when(survivorService.findSurvivorByIdentification(complainant.getIdentificationNumber())).thenReturn(complainant);
        ComplainantInfo complainantInfo = new ComplainantInfo("12345");
        String body = stringifyObject(complainantInfo);
        when(survivorService.flagInfectedSurvivor(complainant)).thenReturn(complainant);
        mockMvc.perform(MockMvcRequestBuilders.post("/survivors/infected/flag/"+complainant.getIdentificationNumber().trim())
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andExpect(content().string("{\"message\":\"Complainant profile not found\"}"));
    }

    @Test
    void flagSurvivorAsInfectedAlreadyInfected() throws Exception {
        Survivor complainant = new Survivor(1L,"Cindy Madupo",21,"9208035482082",
                "Female","5241564", "3223132",false,3);
        when(survivorService.saveSurvivor(complainant)).thenReturn(complainant);
        when(survivorService.findSurvivorByIdentification(complainant.getIdentificationNumber())).thenReturn(complainant);
        ComplainantInfo complainantInfo = new ComplainantInfo("9208035482082");
        String body = stringifyObject(complainantInfo);
        when(survivorService.flagInfectedSurvivor(complainant)).thenReturn(complainant);
        mockMvc.perform(MockMvcRequestBuilders.post("/survivors/infected/flag/"+complainant.getIdentificationNumber().trim())
                .contentType(MediaType.APPLICATION_JSON)
                .content(body)
        ).andExpect(content().string("{\"message\":\"Infected survivor has already been flagged\"}"));
    }


    @Test
    void getAllRobots() throws Exception {
        List<RobotResponse> robotList = new ArrayList<>();
        robotList.add(new RobotResponse("MJTED","11CXV9PRE02WHG2","2022-09-11T15:29:35.6717753+00:00","Land"));
        robotList.add(new RobotResponse("N3XUC","JA62RQMPFP0R54I","2022-08-08T15:29:35.6717301+00:00","Flying"));
        when(survivorService.getAllRobots()).thenReturn(robotList);

        mockMvc.perform(MockMvcRequestBuilders.get("/survivors/robots/list")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasKey("flyingRobotList"))).andExpect(jsonPath("$", hasKey("landRobotList"))).andDo(print());
    }
}
