package com.ioco.iocoassessmentbackend.dataobject;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ComplainantInfo implements Serializable {
    private String identificationNumber;
}
