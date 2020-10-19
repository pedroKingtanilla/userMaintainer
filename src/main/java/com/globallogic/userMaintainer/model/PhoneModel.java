package com.globallogic.userMaintainer.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "Phones")
@Getter
@Setter
public class PhoneModel {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String numberPhone;
    @Column
    private String cityCode;
    @Column
    private String countryCode;
    @Column
    private int idUser;

}
