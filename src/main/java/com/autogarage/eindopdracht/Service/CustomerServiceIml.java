package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.CarDTO;
import com.autogarage.eindopdracht.DTO.CustomerDTO;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Car;
import com.autogarage.eindopdracht.Model.Customer;
import com.autogarage.eindopdracht.Repository.CarRepo;
import com.autogarage.eindopdracht.Repository.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIml implements CustomerService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CarRepo carRepo;

    // CREATE
    @Override
    public CustomerDTO createCustomer(@Valid CustomerDTO customerDTO) {
        Customer newCustomer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerRepo.save(newCustomer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    // READ
    @Override
    public List<CustomerDTO> findAllCustomers() {
        List<Customer> customers =  customerRepo.findAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();

        for(Customer customer : customers) {
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        Optional<Customer> customer =  customerRepo.findById(id);
        if (customer.isPresent()){
            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            return customerDTO;
        } else {
            throw new RecordNotFoundException("customer not found");
        }
    }

    @Override
    public List<CarDTO> findCustomerCars(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        List<CarDTO> carDTOS = new ArrayList<>();
        if(customer.isPresent()) {
            List<Car> cars =  customer.get().getCars();
            for(Car car : cars){
                CarDTO carDTO = modelMapper.map(car, CarDTO.class);
                carDTOS.add(carDTO);
            }
        return carDTOS;
        } else {
            throw new RecordNotFoundException("no car found");
        }
    }

    // UPDATE
    @Override
    public CustomerDTO updateCustomer( CustomerDTO customerDTO, Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RecordNotFoundException("customer not found"));
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setStreet(customerDTO.getStreet());
        customer.setZipcode(customerDTO.getZipcode());
        customer.setEmail(customerDTO.getEmail());
        customer.setHouseNumber(customerDTO.getHouseNumber());
        customer.setCity(customerDTO.getCity());
        customer.setCars(customerDTO.getCars());
        customerRepo.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    // DELETE

    @Override
    public CustomerDTO deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepo.findById(id);
        if(customer.isPresent()){
            if(customer.get().getCars() != null){
                for (Car car : customer.get().getCars()){
                    Long carId = car.getId();
                    carRepo.deleteById(carId);
                }
            }
            CustomerDTO customerDTO = modelMapper.map(customer.get(), CustomerDTO.class);
            customerRepo.deleteById(id);
            return customerDTO;
        } else {
            throw new RecordNotFoundException("unable to find customer");
        }



    }
}
