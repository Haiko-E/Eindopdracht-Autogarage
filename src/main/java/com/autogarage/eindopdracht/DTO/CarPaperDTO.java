package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Car;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CarPaperDTO {

    private Long id;

    @JsonIgnore
    private byte[] carPapers;

    private Car car;
}
