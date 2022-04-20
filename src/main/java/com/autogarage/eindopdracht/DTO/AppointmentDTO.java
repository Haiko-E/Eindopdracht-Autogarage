package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Auditable;
import com.autogarage.eindopdracht.Model.Car;
import com.autogarage.eindopdracht.Model.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(Include.NON_NULL)
public class AppointmentDTO extends Auditable<String> {
    private Long id;

    @NotNull(message = "brand cant be null")
    @NotBlank(message = "brand cant be blank")
    private String maintenanceDetails;

    private String description;

    @NotNull(message = "date cant be null")
    private Date date;

    @NotNull(message = "customer cant be null")
    private Customer customer;

    @NotNull(message = "car cant be null")
    private Car car;
}
