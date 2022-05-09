package com.Ashesi.ASHRC.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class IncidentDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int incident_ID;
    private Date date;
    private String periodOfIncident;
    private String incidentDetails;
    private String evidence;
    private String incidentLocation;
    private String reporterGender;

    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleDetails role_; //FK
    private boolean isWhistleBlower;
    private boolean isReport;

    @ManyToOne
    @JoinColumn(name="status")
    private IncidentStatus incidentStatus;

    @ManyToOne
    @JoinColumn(name="respondent_ID")
    private RespondentDetails respondent; //FK
    private String requestedServices;

    @ManyToOne
    @JoinColumn(name="userId")
    private UserDetails user; //FK
}
