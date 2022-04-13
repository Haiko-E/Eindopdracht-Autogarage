package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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
