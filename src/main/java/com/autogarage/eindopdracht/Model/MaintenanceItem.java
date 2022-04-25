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
@Table(name = "maintenance_items")
@JsonInclude(Include.NON_NULL)
public class MaintenanceItem {

    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    @ManyToOne
    @JsonBackReference(value = "maintenanceItem-repairOperation")
    @JoinColumn(name = "repair_operation_id")
    private RepairOperation repairOperation;

    @ManyToOne
    @JsonBackReference(value = "maintenanceItem-part")
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JsonBackReference(value = "maintenance-maintenanceItem")
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

}
