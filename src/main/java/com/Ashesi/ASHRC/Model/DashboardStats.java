package com.Ashesi.ASHRC.Model;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class DashboardStats {
    private Long numRespondents;
    private Long numOffenders;
    private Long numIncidents;
    private Long numUsers;
    private Long numStudents;
    private Long mRespondents; //number of male respondents
    private Long fRespondents; //number of female respondents
    private Long victims;
    private Long numReports;
    private Long janCases;
    private Long febCases;
    private Long marCases;
    private Long aprCases;
    private Long mayCases;
    private Long junCases;
    private Long julCases;
    private Long augCases;
    private Long sepCases;
    private Long octCases;
    private Long novCases;
    private Long decCases;

    private Long mVictims;
    private Long mOffenders;
    private Long mUsers;

}
