package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Car;
import com.autogarage.eindopdracht.Model.Invoice;
import com.autogarage.eindopdracht.Model.MaintenanceItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MaintenanceDTO {

    private Long id;

    @NotNull(message = "car cant be null")
    private Car car;

    private List<MaintenanceItem> maintenanceItems;

    private Invoice invoice;



}
