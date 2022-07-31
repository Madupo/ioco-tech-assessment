package com.ioco.iocoassessmentbackend.util;

import com.ioco.iocoassessmentbackend.dataobject.FormattedRobotResponse;
import com.ioco.iocoassessmentbackend.dataobject.RobotResponse;

import java.util.List;
import java.util.stream.Collectors;

public class Common {
    public static FormattedRobotResponse formatRobotList(List<RobotResponse> robotResponseList){
        FormattedRobotResponse robotResponse = new FormattedRobotResponse();

        List<RobotResponse> flyingList = robotResponseList.stream()
                .filter(x -> "flying".equalsIgnoreCase(x.getCategory()))
                .collect(Collectors.toList());

        List<RobotResponse> landList = robotResponseList.stream()
                .filter(x -> "land".equalsIgnoreCase(x.getCategory()))
                .collect(Collectors.toList());

        robotResponse.setLandRobotList(landList);
        robotResponse.setFlyingRobotList(flyingList);
        return robotResponse;
    }
}
