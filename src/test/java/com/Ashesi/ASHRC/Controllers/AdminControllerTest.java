package com.Ashesi.ASHRC.Controllers;

import com.Ashesi.ASHRC.RepositoriesDAO.LoginRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.RespondentIncidentRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.RespondentRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {
    public UserRepository userrepo = Mockito.mock(UserRepository.class);
    public LoginRepository loginrepo = Mockito.mock(LoginRepository.class);
    private EntityManager entityManager = Mockito.mock(EntityManager.class);
    private RespondentRepository resRepo = Mockito.mock(RespondentRepository.class);
    private RespondentIncidentRepository RIrepo = Mockito.mock(RespondentIncidentRepository.class);
    public HttpServletRequest request  =  Mockito.mock(HttpServletRequest.class);
    private Model model = Mockito.mock(Model.class);

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }


    @Test
    void displayDashboard() {
    }

    @Test
    void getAdmin() {
    }

    @Test
    void displayAnalyticsPage() {
    }

    @Test
    void displayRespondentManagementPage() {
    }

    @Test
    void viewRespondent() {
    }

    @Test
    void displaySettings() {
        AdminController adminController = new AdminController(entityManager,userrepo,loginrepo,resRepo,RIrepo);
        adminController.displaySettings(model,request);
        Assertions.assertEquals( "SUCCESS",outputStreamCaptor.toString().trim());

    }

    @Test
    void displayIncidentAnalysis() {
    }

    @Test
    void logout() {

    }

}