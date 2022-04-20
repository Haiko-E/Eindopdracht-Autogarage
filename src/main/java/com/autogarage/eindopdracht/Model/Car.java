package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
@JsonInclude(Include.NON_NULL)
public class Car extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    private String brand;
    private String type;
    private String licensePlate;

    @ManyToOne
    @JsonBackReference(value = "customer-car")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "car")
    @JsonManagedReference(value = "car-maintenance")
    private List<Maintenance> maintenances;


}
