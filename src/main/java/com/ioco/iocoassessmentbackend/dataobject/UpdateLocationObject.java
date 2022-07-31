package com.ioco.iocoassessmentbackend.dataobject;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLocationObject implements Serializable {
    private String latitude;
    private String longitude;
    private String idNumber;
}
