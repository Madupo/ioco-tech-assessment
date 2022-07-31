package com.ioco.iocoassessmentbackend.dataobject;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FormattedRobotResponse {
    List<RobotResponse> flyingRobotList;
    List<RobotResponse> landRobotList;
}
