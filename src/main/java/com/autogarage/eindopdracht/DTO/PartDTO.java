package com.autogarage.eindopdracht.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PartDTO {

    private Long id;

    @NotNull(message = "name cant be null")
    @NotBlank(message = "name cant be blank")
    private String name;

    @NotNull(message = "price cant be null")
    private Integer price;

    @NotNull(message = "inStock cant be null")
    private Integer inStock;
}
