package com.autogarage.eindopdracht.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairOperationDTO {

    private Long id;

    @NotNull(message = "name cant be null")
    @NotBlank(message = "name cant be blank")
    private String name;

    @NotNull(message = "price cant be null")
    private Integer price;


}
