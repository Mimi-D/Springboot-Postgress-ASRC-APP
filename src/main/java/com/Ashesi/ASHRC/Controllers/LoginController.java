package com.Ashesi.ASHRC.Controllers;

import com.Ashesi.ASHRC.Model.LoginDetails;
import com.Ashesi.ASHRC.Model.UserDetails;
import com.Ashesi.ASHRC.RepositoriesDAO.LoginRepository;
import com.Ashesi.ASHRC.RepositoriesDAO.UserRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


// This is the login controller to handle login operations
@Controller
@RequestMapping("/ASHRCportal")

@AllArgsConstructor
public class LoginController {

    // Required repositories to help implement needed methods
    @Autowired
    public LoginRepository loginrepo;

    @Autowired
    public UserRepository userrepo;

    @Autowired
    private EntityManager entityManager;


    // This method allows a new login and displays the login page
    @GetMapping("/login")
    public String displayLoginPage(Model model) {
        LoginDetails newLogin = new LoginDetails();
        model.addAttribute("login_details", newLogin);
        System.out.println("SUCCESS");
        return "loginPage";
    }

    @GetMapping("/loginError")
    public String displayLoginPageError(Model model) {
        LoginDetails newLogin = new LoginDetails();
        model.addAttribute("login_details", newLogin);
        System.out.println("SUCCESS");
        return "ErrorLoginPage";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model){
        return "ForgotPassword";
    }


    // This method authenticates login details provided by users
    @PostMapping("/loginVerify")
    public String validateLogin(Model model,LoginDetails login_details,HttpServletRequest request) {
        Session currentSession = entityManager.unwrap(Session.class);
        BCrypt passwordEncoder = new BCrypt();
        // parse through employee details table in database to obtain record of user with specified username

        if(emailExists(login_details.getUsername())){
            String hql = "FROM UserDetails E WHERE E.email = :userName";
            Query query = currentSession.createQuery(hql);
            query.setParameter("userName", login_details.getUsername());
            List<UserDetails> results = query.getResultList();
            boolean ver = passwordEncoder.checkpw(login_details.getPassword(),results.get(0).getPassword());
            // if result is obtained call home page for employee. if not redirect to login
            if (ver) {
                HttpSession session=request.getSession();
                session.setAttribute("mail",results.get(0).getEmail());
                model.addAttribute("loggedInAdmin",results.get(0));
                System.out.println("SUCCESS");
                return "redirect:/admin/dashboard";}

            else{ System.out.println("FAIL"); return "redirect:/ASHRCportal/loginError";}

        }
        // If login fails redirect to login page
        System.out.println("FAIL");
        return "redirect:/ASHRCportal/loginError";
    }

    public boolean emailExists(String email){
        List<UserDetails> users =userrepo.findAll();
        for(UserDetails user:users){
            if(user.getEmail().equals(email)){return true;}
        }
        return false;
    }



}
