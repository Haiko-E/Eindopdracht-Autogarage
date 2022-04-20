package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")
@JsonInclude(Include.NON_NULL)
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;

    private Integer totalPrice;
    private Boolean isPayed = false;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maintenance_id")
    @JsonManagedReference(value = "invoice-maintenance")
    private Maintenance maintenance;
}