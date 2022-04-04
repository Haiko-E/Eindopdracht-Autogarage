package com.autogarage.eindopdracht.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "employees")
public class Employee {

        @Id
        @GeneratedValue
        private Long id;

        private String name;
        private String lastname;

        private String role;


}
