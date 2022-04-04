package com.autogarage.eindopdracht.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "appointments")
public class Appointment extends Auditable<String> {

    @Id
    @GeneratedValue
    private Long id;

    private String maintenanceDetails;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
