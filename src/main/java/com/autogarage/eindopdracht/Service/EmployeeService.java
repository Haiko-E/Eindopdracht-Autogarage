package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO findByEmployeeId(Long id);
    List<EmployeeDTO> findAllEmployees();
    EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id);
    EmployeeDTO deleteEmployee(Long id);
}
