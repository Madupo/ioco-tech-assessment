package com.ioco.iocoassessmentbackend.dataobject;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RobotResponse implements Serializable {
    private String model;
    private String serialNumber;
    private String manufacturedDate;
    private String category;
}
