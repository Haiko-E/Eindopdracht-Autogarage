package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Auditable;
import com.autogarage.eindopdracht.Model.Car;
import com.autogarage.eindopdracht.Model.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO extends Auditable<String> {
    private Long id;

    @NotNull(message = "maintenance detail cant be null")
    private String maintenanceDetails;

    private String description;

    @NotNull(message = "date cant be null")
    private Date date;


    private Customer customer;


    private Car car;
}
