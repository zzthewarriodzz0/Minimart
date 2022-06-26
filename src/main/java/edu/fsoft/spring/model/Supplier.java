package edu.fsoft.spring.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "[supplier]")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String companyName;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String homepage;

}
