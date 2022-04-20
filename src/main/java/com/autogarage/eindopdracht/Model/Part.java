package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parts")
@JsonInclude(Include.NON_NULL)
public class Part {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
    private Integer inStock;

    @OneToOne(targetEntity = MaintenanceItem.class, mappedBy = "part")
    @JsonBackReference(value = "maintenanceItem-part")
    private MaintenanceItem maintenanceItem;
}
