package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Maintenance;
import com.autogarage.eindopdracht.Model.Part;
import com.autogarage.eindopdracht.Model.RepairOperation;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class MaintenanceItemDTO {

    private Long id;
    private Integer quantity;

    @JsonInclude(Include.NON_EMPTY)
    private RepairOperation repairOperation;

    @JsonInclude(Include.NON_EMPTY)
    private Part part;

    @NotNull(message = "Please add a maintenance")
    private Maintenance maintenance;
}
