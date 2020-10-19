package com.globallogic.userMaintainer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity(name = "Users")
public class UserModel {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, name = "name_user")
    private String name;
    @Column(nullable = false, name = "email", unique = true)
    private String email;
    @Column(nullable = false, name = "password")
    private String password;
    //@Column(nullable = false, name = "created")
    private String created;
    @Column(name = "modified")
    private String modified;
    @Column(name = "last_login")
    private String lastLogin;
    @Column
    private String token;
    @Column
    private boolean isactive;
/*
    @OneToMany(mappedBy = "userModel", cascade = CascadeType.ALL)
    private List<PhoneModel> phoneModelList = new ArrayList<>();

 */
}
