package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Customer;
import com.autogarage.eindopdracht.Model.Maintenance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class InvoiceDTO {

    private Long id;

    private Integer totalPrice;
    private Boolean isPayed;

    private Maintenance maintenance;

    private Customer customer;
}
