package com.autogarage.eindopdracht.DTO;

import com.autogarage.eindopdracht.Enum.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeDTO {
    private Long id;

    @NotNull(message = "name cant be null")
    @NotBlank(message = "name cant be blank")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "password cant be null")
    @NotBlank(message = "password cant be blank")
    private String password;

    private Boolean enabled = true;

    @NotNull(message = "lastname cant be null")
    @NotBlank(message = "lastname cant be blank")
    private String lastname;

    @Enumerated
    private Role role;
}
