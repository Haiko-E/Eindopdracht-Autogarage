package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.CarDTO;
import com.autogarage.eindopdracht.DTO.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> findAllCustomers();
    CustomerDTO findCustomerById (Long id);
    List<CarDTO> findCustomerCars (Long id);
    CustomerDTO updateCustomer (CustomerDTO customerDTO, Long id);
    CustomerDTO deleteCustomer(Long id);
}
