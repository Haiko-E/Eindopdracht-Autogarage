package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import javax.persistence.*;
import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@Table(name = "appointments")
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
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
