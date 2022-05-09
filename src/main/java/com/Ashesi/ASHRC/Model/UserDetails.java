package com.Ashesi.ASHRC.Model;


import lombok.Data;

import javax.persistence.*;

@Entity
// Contains details of users
@Data public class UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int userId;
    private String fName;
    private String lName;
    private String yearGroup;
    private String department;
    private String password;
    private String email;
    private String gender;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;
    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleDetails role; // set default role to 1 which is regular user
}
