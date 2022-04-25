package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "repairOperation")
    @JsonManagedReference(value = "maintenanceItem-repairOperation")
    private List<MaintenanceItem> maintenanceItem;

}
