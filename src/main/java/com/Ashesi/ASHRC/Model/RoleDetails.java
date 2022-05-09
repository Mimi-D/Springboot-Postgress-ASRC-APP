package com.Ashesi.ASHRC.Model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class RoleDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int roleId; // 1 for admin, 2 for respondent, 3 for student, 4 for faculty
    private String role; //admin, respondent, student, faculty

    @OneToMany(mappedBy = "role")
    private List<UserDetails> employee;

    @OneToMany(mappedBy = "role_")
    private List<LoginDetails> login;

}
