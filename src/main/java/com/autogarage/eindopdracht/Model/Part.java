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
@Table(name = "parts")
@JsonInclude(Include.NON_NULL)
public class Part {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
    private Integer inStock;

    @OneToMany(mappedBy = "part")
    @JsonManagedReference(value = "maintenanceItem-part")
    private List<MaintenanceItem> maintenanceItem;
}
