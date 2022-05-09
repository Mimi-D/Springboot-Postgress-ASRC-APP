package com.Ashesi.ASHRC.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class IncidentStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int reportStatus; //Received=1, pending Review=2, Pending AJC case=3, Ajudicated=4
    private String status;


}
