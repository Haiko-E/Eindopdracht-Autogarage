package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@JsonInclude(Include.NON_NULL)
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

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "customer-car")
    private List<Car> cars;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customer-invoice")
    private List<Invoice> invoices;





}
