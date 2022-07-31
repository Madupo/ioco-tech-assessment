package com.ioco.iocoassessmentbackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "survivors")
public class Survivor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(unique = true)
    private String identificationNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String latitude;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private String longitude;

    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    private boolean infected;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
    private Integer numberOfComplaints;
}
