package com.autogarage.eindopdracht.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repairOperations")
public class RepairOperation {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;

}
