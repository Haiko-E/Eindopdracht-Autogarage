package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "maintenances")
public class Maintenance {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonBackReference(value = "car-maintenance")
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "maintenance", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "maintenance-maintenanceItem")
    private List<MaintenanceItem> maintenanceItems;

    @OneToOne(mappedBy = "maintenance", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference(value = "invoice-maintenance")
    private Invoice invoice;

}
