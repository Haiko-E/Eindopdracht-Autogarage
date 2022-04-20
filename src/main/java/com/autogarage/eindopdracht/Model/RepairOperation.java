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
@Table(name = "repairOperations")
@JsonInclude(Include.NON_NULL)
public class RepairOperation {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;

    @OneToOne(targetEntity = MaintenanceItem.class, mappedBy = "repairOperation")
    @JsonBackReference(value = "maintenanceItem-repairOperation")
    private MaintenanceItem maintenanceItem;

}
