package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "maintenance_items")
@JsonInclude(Include.NON_NULL)
public class MaintenanceItem {

    @Id
    @GeneratedValue
    private Long id;

    private Integer quantity;

    @OneToOne(targetEntity =RepairOperation.class, cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "maintenanceItem-repairOperation")
    @JoinColumn(name = "repair_operation_id")
    private RepairOperation repairOperation;

    @OneToOne(targetEntity =Part.class, cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "maintenanceItem-part")
    @JoinColumn(name = "part_id")
    private Part part;

    @ManyToOne
    @JsonBackReference(value = "maintenance-maintenanceItem")
    @JoinColumn(name = "maintenance_id")
    private Maintenance maintenance;

}
