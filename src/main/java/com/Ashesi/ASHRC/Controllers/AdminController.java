package com.Ashesi.ASHRC.Controllers;


import com.Ashesi.ASHRC.Model.*;
import com.Ashesi.ASHRC.RepositoriesDAO.LoginRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.RespondentIncidentRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.RespondentRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.UserRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Required repositories to help implement needed methods
    @Autowired
    private EntityManager entityManager;

    @Autowired
    public UserRepository userRepo;

    @Autowired
    LoginRepository loginRepo;

    @Autowired
    RespondentRepository resRepo;

    @Autowired
    RespondentIncidentRepository RIrepo;


    // This method displays admin dashboard
    @GetMapping("/dashboard")
    public String displayDashboard(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<UserDetails> Emp = getAdmin((String)session.getAttribute("mail"));
        model.addAttribute("loggedInAdmin",Emp.get(0));

        Session thissession = entityManager.unwrap(Session.class);
        DashboardStats stats = new DashboardStats();
        Query query = thissession.createQuery("select count(*) from RespondentDetails");
        Long numRespondents = (Long)query.uniqueResult();


        Query query1 = thissession.createQuery("select count(*) from OffenderDetails");
        Long numOffenders = (Long) query1.uniqueResult();


        Query query2 = thissession.createQuery("select count(*) from IncidentDetails ");
        Long numIncidents = (Long) query2.uniqueResult();


        Query query3 = thissession.createQuery("select count(*) from UserDetails where role.roleId = 3"); //all students
        Long numStu = (Long) query3.uniqueResult();

        Query query4 = thissession.createQuery("select count(*) from UserDetails where role.roleId = 4"); // all faculty
        Long numUsers = (Long) query4.uniqueResult() + (Long) query3.uniqueResult();



        Query query5 = thissession.createQuery("select count(*) from RespondentDetails where gender = 'M' ");
        Long numMresp = (Long) query5.uniqueResult();

        Query query6 = thissession.createQuery("select count(*) from RespondentDetails where gender = 'F' ");
        Long numFresp = (Long) query6.uniqueResult();

        Query query7 = thissession.createQuery("select count(*) from IncidentDetails where isWhistleBlower = false ");
        Long numVictims = (Long) query7.uniqueResult();

        Query query8 = thissession.createQuery("select count(*) from IncidentDetails where isReport = true ");
        Long numReports = (Long) query8.uniqueResult();


        Query query9 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2021-12-31'"+" AND date < "+"'2022-02-01'");
        Long janCases = (Long) query9.uniqueResult();

        Query query10 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-01-31'"+" AND date < "+"'2022-03-01'");
        Long febCases = (Long) query10.uniqueResult();

        Query query11 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-02-28'"+" AND date < "+"'2022-04-01'");
        Long marCases = (Long) query11.uniqueResult();

        Query query12 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-03-31'"+" AND date < "+"'2022-05-01'");
        Long aprCases = (Long) query12.uniqueResult();

        Query query13 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-04-30'"+" AND date < "+"'2022-06-01'");
        Long mayCases = (Long) query13.uniqueResult();

        Query query14 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-05-31'"+" AND date < "+"'2022-07-01'");
        Long junCases = (Long) query14.uniqueResult();

        Query query15 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-06-30'"+" AND date < "+"'2022-08-01'");
        Long julCases = (Long) query15.uniqueResult();

        Query query16 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-07-31'"+" AND date < "+"'2022-09-01'");
        Long augCases = (Long) query16.uniqueResult();

        Query query17 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-08-31'"+" AND date < "+"'2022-10-01'");
        Long sepCases = (Long) query17.uniqueResult();

        Query query18 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-09-30'"+" AND date < "+"'2022-11-01'");
        Long octCases = (Long) query18.uniqueResult();

        Query query19 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-10-31'"+" AND date < "+"'2022-12-01'");
        Long novCases = (Long) query19.uniqueResult();

        Query query20 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-11-30'"+" AND date < "+"'2023-01-01'");
        Long decCases = (Long) query20.uniqueResult();



        stats.setNumRespondents(numRespondents);
        stats.setNumOffenders(numOffenders);
        stats.setNumIncidents(numIncidents);
        stats.setNumUsers(numUsers);
        stats.setNumStudents(numStu);
        stats.setMRespondents(numMresp);
        stats.setFRespondents(numFresp);
        stats.setVictims(numVictims);
        stats.setNumReports(numReports);

        stats.setJanCases(janCases);
        stats.setFebCases(febCases);
        stats.setMarCases(marCases);
        stats.setAprCases(aprCases);
        stats.setMayCases(mayCases);
        stats.setJunCases(junCases);
        stats.setJulCases(julCases);
        stats.setAugCases(augCases);
        stats.setSepCases(sepCases);
        stats.setOctCases(octCases);
        stats.setNovCases(novCases);
        stats.setDecCases(decCases);

        model.addAttribute("DashStats", stats);

        Query queryN = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 1 ");
        Long newlyReceived = (Long) queryN.uniqueResult();
        model.addAttribute("newlyReceived",newlyReceived);

        return "adminDashboard";
    }

    public List<UserDetails> getAdmin(String mail) {
        Session currentSession = entityManager.unwrap(Session.class);
        String hql = "FROM UserDetails Emp WHERE Emp.email = :email";
        Query query = currentSession.createQuery(hql);
        query.setParameter("email",mail);
        List<UserDetails> results = query.getResultList();

        return results;
    }

    // This method displays analytics/statistics page
    @GetMapping("/analytics")
    public String displayAnalyticsPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<UserDetails> Emp = getAdmin((String)session.getAttribute("mail"));
        model.addAttribute("loggedInAdmin",Emp.get(0));

        Session thissession = entityManager.unwrap(Session.class);
        DashboardStats stats = new DashboardStats();

        Query query9 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2021-12-31'"+" AND date < "+"'2022-02-01'");
        Long janCases = (Long) query9.uniqueResult();

        Query query10 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-01-31'"+" AND date < "+"'2022-03-01'");
        Long febCases = (Long) query10.uniqueResult();

        Query query11 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-02-28'"+" AND date < "+"'2022-04-01'");
        Long marCases = (Long) query11.uniqueResult();

        Query query12 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-03-31'"+" AND date < "+"'2022-05-01'");
        Long aprCases = (Long) query12.uniqueResult();

        Query query13 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-04-30'"+" AND date < "+"'2022-06-01'");
        Long mayCases = (Long) query13.uniqueResult();

        Query query14 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-05-31'"+" AND date < "+"'2022-07-01'");
        Long junCases = (Long) query14.uniqueResult();

        Query query15 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-06-30'"+" AND date < "+"'2022-08-01'");
        Long julCases = (Long) query15.uniqueResult();

        Query query16 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-07-31'"+" AND date < "+"'2022-09-01'");
        Long augCases = (Long) query16.uniqueResult();

        Query query17 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-08-31'"+" AND date < "+"'2022-10-01'");
        Long sepCases = (Long) query17.uniqueResult();

        Query query18 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-09-30'"+" AND date < "+"'2022-11-01'");
        Long octCases = (Long) query18.uniqueResult();

        Query query19 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-10-31'"+" AND date < "+"'2022-12-01'");
        Long novCases = (Long) query19.uniqueResult();

        Query query20 = thissession.createQuery( "select count(*) from IncidentDetails where date > "+"'2022-11-30'"+" AND date < "+"'2023-01-01'");
        Long decCases = (Long) query20.uniqueResult();

        Query query5 = thissession.createQuery("select count(*) from UserDetails where gender = 'M' and role.roleId = 3");
        Query query5_ = thissession.createQuery("select count(*) from UserDetails where gender = 'M' and role.roleId = 4");
        Long numMusers = (Long) query5.uniqueResult() +  (Long) query5_.uniqueResult();

        Query query3 = thissession.createQuery("select count(*) from UserDetails where role.roleId = 3"); //all students
        Query query4 = thissession.createQuery("select count(*) from UserDetails where role.roleId = 4"); // all faculty
        Long numUsers = (Long) query4.uniqueResult() + (Long) query3.uniqueResult();

        Query query1 = thissession.createQuery("select count(*) from OffenderDetails");
        Long numOffenders = (Long) query1.uniqueResult();

        Query query1_ = thissession.createQuery("select count(*) from OffenderDetails where gender='M'");
        Long numMoffenders = (Long) query1_.uniqueResult();

        Query query7 = thissession.createQuery("select count(*) from IncidentDetails where isWhistleBlower = false ");
        Long numVictims = (Long) query7.uniqueResult();

        Query query7_ = thissession.createQuery("SELECT count(*) from IncidentDetails where reporterGender='M'" );
        Long numMvictims = (Long) query7_.uniqueResult();



        stats.setJanCases(janCases);
        stats.setFebCases(febCases);
        stats.setMarCases(marCases);
        stats.setAprCases(aprCases);
        stats.setMayCases(mayCases);
        stats.setJunCases(junCases);
        stats.setJulCases(julCases);
        stats.setAugCases(augCases);
        stats.setSepCases(sepCases);
        stats.setOctCases(octCases);
        stats.setNovCases(novCases);
        stats.setDecCases(decCases);

        stats.setMUsers(numMusers);
        stats.setNumUsers(numUsers);
        stats.setMOffenders(numMoffenders);
        stats.setNumOffenders(numOffenders);
        stats.setVictims(numVictims);
        stats.setMVictims(numMvictims);

        model.addAttribute("DashStats", stats);

        Query queryN = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 1 ");
        Long newlyReceived = (Long) queryN.uniqueResult();
        model.addAttribute("newlyReceived",newlyReceived);

        return "adminAnalytics";
    }

    @GetMapping("/respondents")
    public String displayRespondentManagementPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<UserDetails> Emp = getAdmin((String)session.getAttribute("mail"));
        model.addAttribute("loggedInAdmin",Emp.get(0));

        Session thissession = entityManager.unwrap(Session.class);
        Query queryN = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 1 ");
        Long newlyReceived = (Long) queryN.uniqueResult();
        model.addAttribute("newlyReceived",newlyReceived);

        return "RespondentManagement";
    }

    // This method displays view more page
    @GetMapping("/viewMore")
    @Transactional
    public String viewRespondent(@RequestParam Integer respID,Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<UserDetails> Emp = getAdmin((String)session.getAttribute("mail"));
        model.addAttribute("loggedInAdmin",Emp.get(0));

        Optional<RespondentDetails> res = resRepo.findById(respID);
        RespondentDetails newRes = res.get();
        model.addAttribute("respondent",newRes);

        Session thissession = entityManager.unwrap(Session.class);

        String hql = "select count(*) FROM IncidentDetails where respondent.respondent_ID = :resId";
        Query query = thissession.createQuery(hql);
        query.setParameter("resId", respID);
        Long numRep = (Long) query.uniqueResult();

        String hql1 = "FROM IncidentDetails E WHERE E.respondent.respondent_ID = :resId";
        Query query1 = thissession.createQuery(hql1);
        query1.setParameter("resId", respID);
        List<IncidentDetails> results = query1.getResultList();


        Query queryN = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 1 ");
        Long newlyReceived = (Long) queryN.uniqueResult();
        model.addAttribute("newlyReceived",newlyReceived);

        model.addAttribute("cases",results);
        model.addAttribute("numReports",numRep);

        return "ViewMore";
    }

    @GetMapping("/settings")
    public String displaySettings(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<UserDetails> Emp = getAdmin((String)session.getAttribute("mail"));
        model.addAttribute("loggedInAdmin",Emp.get(0));

        Session thissession = entityManager.unwrap(Session.class);
        Query queryN = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 1 ");
        Long newlyReceived = (Long) queryN.uniqueResult();
        model.addAttribute("newlyReceived",newlyReceived);

        return "Settings";
    }

    @GetMapping("/incidentAnalysis")
    public String displayIncidentAnalysis(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<UserDetails> Emp = getAdmin((String)session.getAttribute("mail"));
        model.addAttribute("loggedInAdmin",Emp.get(0));
        Session thissession = entityManager.unwrap(Session.class);

        String hql1 = "FROM IncidentDetails E";
        Query query1 = thissession.createQuery(hql1);
        List<IncidentDetails> results = query1.getResultList();
        model.addAttribute("cases",results);


        Query query7 = thissession.createQuery("select count(*) from IncidentDetails where isReport = false ");
        Long numUnofficial = (Long) query7.uniqueResult();
        model.addAttribute("unofficial",numUnofficial);

        Query query8 = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 2 ");
        Long pendingReview = (Long) query8.uniqueResult();
        model.addAttribute("pendingReview",pendingReview);

        Query query9 = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 3 ");
        Long pendingAJC = (Long) query9.uniqueResult();
        model.addAttribute("pendingAJC",pendingAJC);

        Query query10 = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 4 ");
        Long adjudicatedAJC = (Long) query10.uniqueResult();
        model.addAttribute("adjudicatedAJC",adjudicatedAJC);

        Query queryN = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 1 ");
        Long newlyReceived = (Long) queryN.uniqueResult();
        model.addAttribute("newlyReceived",newlyReceived);

        return "incidentAnalysis";
    }

    @GetMapping("/reportProcessing")
    public String displayReportProcessing(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        List<UserDetails> Emp = getAdmin((String)session.getAttribute("mail"));
        model.addAttribute("loggedInAdmin",Emp.get(0));
        Session thissession = entityManager.unwrap(Session.class);

        Query queryN = thissession.createQuery("select count(*) from IncidentDetails where incidentStatus.reportStatus = 1 ");
        Long newlyReceived = (Long) queryN.uniqueResult();
        model.addAttribute("newlyReceived",newlyReceived);

        String hql1 = "FROM IncidentDetails E WHERE E.incidentStatus.reportStatus =1";
        Query query1 = thissession.createQuery(hql1);
        List<IncidentDetails> results = query1.getResultList();
        model.addAttribute("cases",results);

        return "reportProcessing";
    }

    @PostMapping("/processAssignment")
    @Transactional
    public String processAssignment(@RequestParam Integer resp, @RequestParam Integer caseId,Model model, HttpServletRequest request) {
        request.getSession(false);
        System.out.println("GOT HETRE: " + resp + "  " + caseId);
        Session thissession = entityManager.unwrap(Session.class);

        if(resp==1){
            String update = "update IncidentDetails set respondent.respondent_ID = 1 where incident_ID = :given_id ";
            String hql = "update IncidentDetails set incidentStatus.reportStatus = 2 where incident_ID = :given_id ";


            Query query0 = thissession.createQuery(update);
            query0.setParameter("given_id", caseId);
            query0.executeUpdate();

            Query query = thissession.createQuery(hql);
            query.setParameter("given_id", caseId);
            query.executeUpdate();

            String hql1 = "FROM IncidentDetails E WHERE E.incident_ID = :given_id";
            Query query1 = thissession.createQuery(hql1);
            query1.setParameter("given_id", caseId);
            List<IncidentDetails> results = query1.getResultList();

            String hql2 = "FROM RespondentDetails E WHERE E.respondent_ID = :given_id";
            Query query2 = thissession.createQuery(hql2);
            query2.setParameter("given_id", resp);
            List<RespondentDetails> results0 = query2.getResultList();

            RespondentIncident newAssignment = new RespondentIncident();
            newAssignment.setIncident(results.get(0));
            newAssignment.setRequestedServices(results.get(0).getRequestedServices());
            newAssignment.setRespondent(results0.get(0));
            RIrepo.save(newAssignment);
        }

        else if(resp==2){
            String update = "update IncidentDetails set respondent.respondent_ID = 2 where incident_ID = :given_id ";
            String hql = "update IncidentDetails set incidentStatus.reportStatus = 2 where incident_ID = :given_id ";


            Query query0 = thissession.createQuery(update);
            query0.setParameter("given_id", caseId);
            query0.executeUpdate();

            Query query = thissession.createQuery(hql);
            query.setParameter("given_id", caseId);
            query.executeUpdate();

            String hql1 = "FROM IncidentDetails E WHERE E.incident_ID = :given_id";
            Query query1 = thissession.createQuery(hql1);
            query1.setParameter("given_id", caseId);
            List<IncidentDetails> results = query1.getResultList();

            String hql2 = "FROM RespondentDetails E WHERE E.respondent_ID = :given_id";
            Query query2 = thissession.createQuery(hql2);
            query2.setParameter("given_id", resp);
            List<RespondentDetails> results0 = query2.getResultList();

            RespondentIncident newAssignment = new RespondentIncident();
            newAssignment.setIncident(results.get(0));
            newAssignment.setRequestedServices(results.get(0).getRequestedServices());
            newAssignment.setRespondent(results0.get(0));
            RIrepo.save(newAssignment);
        }

        else if(resp==3){
            String update = "update IncidentDetails set respondent.respondent_ID = 3 where incident_ID = :given_id ";
            String hql = "update IncidentDetails set incidentStatus.reportStatus = 2 where incident_ID = :given_id ";


            Query query0 = thissession.createQuery(update);
            query0.setParameter("given_id", caseId);
            query0.executeUpdate();

            Query query = thissession.createQuery(hql);
            query.setParameter("given_id", caseId);
            query.executeUpdate();

            String hql1 = "FROM IncidentDetails E WHERE E.incident_ID = :given_id";
            Query query1 = thissession.createQuery(hql1);
            query1.setParameter("given_id", caseId);
            List<IncidentDetails> results = query1.getResultList();

            String hql2 = "FROM RespondentDetails E WHERE E.respondent_ID = :given_id";
            Query query2 = thissession.createQuery(hql2);
            query2.setParameter("given_id", resp);
            List<RespondentDetails> results0 = query2.getResultList();

            RespondentIncident newAssignment = new RespondentIncident();
            newAssignment.setIncident(results.get(0));
            newAssignment.setRequestedServices(results.get(0).getRequestedServices());
            newAssignment.setRespondent(results0.get(0));
            RIrepo.save(newAssignment);


        }

        else if(resp==4){
            String update = "update IncidentDetails set respondent.respondent_ID = 4 where incident_ID = :given_id ";
            String hql = "update IncidentDetails set incidentStatus.reportStatus = 2 where incident_ID = :given_id ";


            Query query0 = thissession.createQuery(update);
            query0.setParameter("given_id", caseId);
            query0.executeUpdate();

            Query query = thissession.createQuery(hql);
            query.setParameter("given_id", caseId);
            query.executeUpdate();

            String hql1 = "FROM IncidentDetails E WHERE E.incident_ID = :given_id";
            Query query1 = thissession.createQuery(hql1);
            query1.setParameter("given_id", caseId);
            List<IncidentDetails> results = query1.getResultList();

            String hql2 = "FROM RespondentDetails E WHERE E.respondent_ID = :given_id";
            Query query2 = thissession.createQuery(hql2);
            query2.setParameter("given_id", resp);
            List<RespondentDetails> results0 = query2.getResultList();

            RespondentIncident newAssignment = new RespondentIncident();
            newAssignment.setIncident(results.get(0));
            newAssignment.setRequestedServices(results.get(0).getRequestedServices());
            newAssignment.setRespondent(results0.get(0));
            RIrepo.save(newAssignment);
        }

        else if(resp==5){
            String update = "update IncidentDetails set respondent.respondent_ID = 5 where incident_ID = :given_id ";
            String hql = "update IncidentDetails set incidentStatus.reportStatus = 2 where incident_ID = :given_id ";


            Query query0 = thissession.createQuery(update);
            query0.setParameter("given_id", caseId);
            query0.executeUpdate();

            Query query = thissession.createQuery(hql);
            query.setParameter("given_id", caseId);
            query.executeUpdate();

            String hql1 = "FROM IncidentDetails E WHERE E.incident_ID = :given_id";
            Query query1 = thissession.createQuery(hql1);
            query1.setParameter("given_id", caseId);
            List<IncidentDetails> results = query1.getResultList();

            String hql2 = "FROM RespondentDetails E WHERE E.respondent_ID = :given_id";
            Query query2 = thissession.createQuery(hql2);
            query2.setParameter("given_id", resp);
            List<RespondentDetails> results0 = query2.getResultList();

            RespondentIncident newAssignment = new RespondentIncident();
            newAssignment.setIncident(results.get(0));
            newAssignment.setRequestedServices(results.get(0).getRequestedServices());
            newAssignment.setRespondent(results0.get(0));
            RIrepo.save(newAssignment);
        }

        else if(resp==6){
            String update = "update IncidentDetails set respondent.respondent_ID = 6 where incident_ID = :given_id ";
            String hql = "update IncidentDetails set incidentStatus.reportStatus = 2 where incident_ID = :given_id ";


            Query query0 = thissession.createQuery(update);
            query0.setParameter("given_id", caseId);
            query0.executeUpdate();

            Query query = thissession.createQuery(hql);
            query.setParameter("given_id", caseId);
            query.executeUpdate();

            String hql1 = "FROM IncidentDetails E WHERE E.incident_ID = :given_id";
            Query query1 = thissession.createQuery(hql1);
            query1.setParameter("given_id", caseId);
            List<IncidentDetails> results = query1.getResultList();

            String hql2 = "FROM RespondentDetails E WHERE E.respondent_ID = :given_id";
            Query query2 = thissession.createQuery(hql2);
            query2.setParameter("given_id", resp);
            List<RespondentDetails> results0 = query2.getResultList();

            RespondentIncident newAssignment = new RespondentIncident();
            newAssignment.setIncident(results.get(0));
            newAssignment.setRequestedServices(results.get(0).getRequestedServices());
            newAssignment.setRespondent(results0.get(0));
            RIrepo.save(newAssignment);
        }



        return "redirect:/admin/reportProcessing";
    }

    // This is the logout method, to destroy the session of the currently logged in user
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "redirect:/ASHRCportal/login";
    }
}
