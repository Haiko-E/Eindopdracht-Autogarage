package com.autogarage.eindopdracht.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
    private byte[] carPapers;
}
