package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.EmployeeDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Employee;
import com.autogarage.eindopdracht.Repository.EmployeeRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceIml implements EmployeeService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        Employee newEmployee = modelMapper.map(employeeDTO, Employee.class);
        Employee savedEmployee = employeeRepo.save(newEmployee);
        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO findByEmployeeId(Long id) {
        Optional<Employee> employee =  employeeRepo.findById(id);
        if (employee.isPresent()){
            EmployeeDTO employeeDTO = modelMapper.map(employee.get(), EmployeeDTO.class);
            return employeeDTO;
        } else {
            throw new RecordNotFoundException("employee not found");
        }
    }

    @Override
    public List<EmployeeDTO> findAllEmployees() {
        List<Employee> employees =  employeeRepo.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for(Employee employee : employees) {
            EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    // UPDATE
    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, Long id) {
        Employee employee = employeeRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("employee not found"));
        employee.setUsername(employeeDTO.getUsername());
        employee.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        employee.setEnabled(employeeDTO.getEnabled());
        employee.setLastname(employeeDTO.getLastname());
        employee.setRole(employeeDTO.getRole().toString());
        employeeRepo.save(employee);
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if(employee.isPresent()){
            EmployeeDTO employeeDTO = modelMapper.map(employee.get(), EmployeeDTO.class);
            employeeRepo.deleteById(id);
            return employeeDTO;
        } else {
            throw new RecordNotFoundException("unable to find employee");
        }
    }
}
