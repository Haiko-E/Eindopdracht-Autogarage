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
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Integer price;
    private Integer inStock;
}
