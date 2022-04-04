package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Model.Car;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class CustomerDTO {

    private Long id;

    @NotNull(message = "firstname cant be null")
    @NotBlank(message = "firstname cant be blank")
    private String firstname;

    @NotNull(message = "lastname cant be null")
    @NotBlank(message = "lastname cant be blank")
    private String lastname;

    @NotNull(message = "email cant be null")
    @NotBlank(message = "email cant be blank")
    @Email(message = "not a valid emailadres")
    private String email;

    @NotNull(message = "street cant be null")
    @NotBlank(message = "street cant be blank")
    private String street;

    @NotNull
    private Integer houseNumber;

    @NotNull(message = "zipcode cant be null")
    @NotBlank(message = "zipcode cant be blank")
    @Pattern(regexp = "^[1-9][0-9]{3} ?[a-z]{2}$" , flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid Zipcode") // regex dutch postal / zipcode
    private String zipcode;

    @NotNull(message = "city cant be null")
    @NotBlank(message = "city cant be blank")
    private String city;

    private List<Car> cars;


}
