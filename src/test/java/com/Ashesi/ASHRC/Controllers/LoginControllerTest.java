package com.Ashesi.ASHRC.Controllers;

import com.Ashesi.ASHRC.Model.LoginDetails;
import com.Ashesi.ASHRC.Model.UserDetails;
import com.Ashesi.ASHRC.RepositoriesDAO.LoginRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.UserRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import javax.persistence.EntityManager;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class LoginControllerTest {

    public UserRepository userrepo = Mockito.mock(UserRepository.class);
    public LoginRepository loginrepo = Mockito.mock(LoginRepository.class);
    private EntityManager entityManager = Mockito.mock(EntityManager.class);
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
    @DisplayName("Test should pass if email exists")
    void emailExists() {
        UserDetails newUser = new UserDetails();
        newUser.setEmail("admin@ashesi");
        LoginController loginController = new LoginController(loginrepo,userrepo,entityManager);
        Assertions.assertFalse(loginController.emailExists("admin@ashesi"));
    }

    @Test
    @DisplayName("Test should pass if email doesn't exists")
    void emailNotExists() {
        UserDetails newUser = new UserDetails();
        newUser.setEmail("admin@ashesi");
        LoginController loginController = new LoginController(null,userrepo,null);
        Assertions.assertFalse(loginController.emailExists("Fakeadmin@ashesi"));
    }


    @Test
    @DisplayName("Test should pass if login successful")
    void loginSuccessful() {
        UserDetails newUser = new UserDetails();
        newUser.setEmail("admin@ashesi");
        newUser.setPassword("admin");
        LoginController loginController = new LoginController(loginrepo,userrepo,entityManager);
        LoginDetails newlogin = new LoginDetails();
        newlogin.setUsername("admin@ashesi");
        newlogin.setPassword("admin");
        loginController.validateLogin(null,newlogin,null);
    }

    @Test
    @DisplayName("Test should pass if login fails")
    void loginFail() {
        UserDetails newUser = new UserDetails();
        newUser.setEmail("admin@ashesi");
        newUser.setPassword("admin");
        LoginController loginController = new LoginController(loginrepo,userrepo,entityManager);
        LoginDetails newlogin = new LoginDetails();
        newlogin.setUsername("admin@ashesi");
        newlogin.setPassword("wrong password");
        loginController.validateLogin(null,newlogin,null);
        Assertions.assertEquals( "FAIL",outputStreamCaptor.toString().trim());
    }

    // check if all paths work

    @Test
    @DisplayName("Test should pass if method runs")
    void displayLoginPage() {
        LoginController loginController = new LoginController(null,userrepo,null);
        loginController.displayLoginPage(model);
        Assertions.assertEquals( "SUCCESS",outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("Test should pass if method runs")
    void displayErrorLoginPage() {
        LoginController loginController = new LoginController(null,userrepo,null);
        loginController.displayLoginPageError(model);
        Assertions.assertEquals( "SUCCESS",outputStreamCaptor.toString().trim());
    }





}