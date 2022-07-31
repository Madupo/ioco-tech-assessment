package com.ioco.iocoassessmentbackend.controller;

import com.ioco.iocoassessmentbackend.dataobject.ComplainantInfo;
import com.ioco.iocoassessmentbackend.dataobject.FormattedRobotResponse;
import com.ioco.iocoassessmentbackend.dataobject.RobotResponse;
import com.ioco.iocoassessmentbackend.dataobject.UpdateLocationObject;
import com.ioco.iocoassessmentbackend.model.Survivor;
import com.ioco.iocoassessmentbackend.service.SurvivorService;
import com.ioco.iocoassessmentbackend.util.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ioco.iocoassessmentbackend.util.Common.formatRobotList;

@RestController
@RequestMapping("/survivors")
public class SurvivorController {

    private final SurvivorService survivorService;
    
    public SurvivorController(SurvivorService survivorService){
        this.survivorService = survivorService;
    }

    @GetMapping("/list")
    ResponseEntity<List<Survivor>> getAllSurvivors() {
        return new ResponseEntity<>(survivorService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/infected/flag/{identificationNumber}")
    ResponseEntity<Message> flagInfectedSurvivor(@RequestBody ComplainantInfo complainantInfo, @PathVariable String identificationNumber) {
        try{
            if (complainantInfo == null || complainantInfo.getIdentificationNumber().isEmpty()){
                return new ResponseEntity<>(Message.create("Request body not provided"), HttpStatus.BAD_REQUEST);
            }
//            firstly, a complainant needs to be a valid user and not just a random bot so we check if user exists in the database
            Survivor complainant = survivorService.findSurvivorByIdentification(complainantInfo.getIdentificationNumber());
            if (complainant == null){
                return new ResponseEntity<>(Message.create("Complainant profile not found"), HttpStatus.NOT_FOUND);
            }

            Survivor flaggedSurvivor = survivorService.findSurvivorByIdentification(identificationNumber);
            if (flaggedSurvivor!= null){
                if (flaggedSurvivor.getNumberOfComplaints() >= 3){
//                return relevant error message as we have already had 3 survivors flag the infected survivor
                    return new ResponseEntity<>(Message.create("Infected survivor has already been flagged"), HttpStatus.OK);
                }
                flaggedSurvivor.setNumberOfComplaints(flaggedSurvivor.getNumberOfComplaints()+1);
                Survivor persistedFlag = survivorService.flagInfectedSurvivor(flaggedSurvivor);
                if (persistedFlag!=null){
//                assume the persisting was successful
                    return new ResponseEntity<>(Message.create("Thank you. Survivor has been flagged."), HttpStatus.OK);
                }
            }
        } catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(Message.create("Internal server error. Please contact administrator"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(Message.create("Internal server error. Please contact administrator"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("robots/list")
    ResponseEntity<FormattedRobotResponse> getAllRobots() {
        try{
            FormattedRobotResponse robotResponseList = new FormattedRobotResponse();
            List<RobotResponse> robotsFromDb = survivorService.getAllRobots();
            if (robotsFromDb.size() > 1){
                robotResponseList = formatRobotList(robotsFromDb);
            }

            return new ResponseEntity<>(robotResponseList, HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>(new FormattedRobotResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    opted against putting idNumber in the url as a parameter but in the object assuming the server would be https
    @PatchMapping("/update")
    ResponseEntity<Message> updateSurvivorLocation(@RequestBody UpdateLocationObject updateLocationObject) {
        try {
            if (updateLocationObject == null || updateLocationObject.getIdNumber().isEmpty()){
                return new ResponseEntity<>(Message.create("Request body not provided"), HttpStatus.BAD_REQUEST);
            }

            Survivor complainant = survivorService.findSurvivorByIdentification(updateLocationObject.getIdNumber());
            if (complainant == null){
                return new ResponseEntity<>(Message.create("Survivor profile not found"), HttpStatus.NOT_FOUND);
            }
            complainant.setLongitude(updateLocationObject.getLongitude());
            complainant.setLongitude(updateLocationObject.getLongitude());
            Survivor updatedSurvivor = survivorService.saveSurvivor(complainant);
            if (updatedSurvivor !=null){
                return new ResponseEntity<>(Message.create("User location updated"), HttpStatus.OK);
            }
            return new ResponseEntity<>(Message.create("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Message.create("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
