package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car_papers")
@JsonInclude(Include.NON_NULL)
public class CarPaper {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @JsonIgnore
    private byte[] carPapers;

    @OneToOne
    @JoinColumn(name = "car_id")
    @JsonManagedReference(value = "car_paper-car")
    private Car car;
}
