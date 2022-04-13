package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Auditable;
import com.autogarage.eindopdracht.Model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO extends Auditable<String> {

    private Long id;

    @NotNull(message = "brand cant be null")
    @NotBlank(message = "brand cant be blank")
    private String brand;

    @NotNull(message = "type cant be null")
    @NotBlank(message = "type cant be blank")
    private String type;

    @NotNull(message = "licenseplate cant be null")
    @NotBlank(message = "licenseplate cant be blank")
    private String licensePlate;

    private Customer customer;


}
