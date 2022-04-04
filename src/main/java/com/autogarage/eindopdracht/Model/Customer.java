package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String street;
    private Integer houseNumber;
    private String zipcode;
    private String city;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Car> cars;




}
