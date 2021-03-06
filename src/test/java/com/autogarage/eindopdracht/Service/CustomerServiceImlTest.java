package com.autogarage.eindopdracht.Service;

import com.autogarage.eindopdracht.DTO.CarDTO;
import com.autogarage.eindopdracht.DTO.CustomerDTO;
import com.autogarage.eindopdracht.EindopdrachtApplication;
import com.autogarage.eindopdracht.Exceptions.RecordNotFoundException;
import com.autogarage.eindopdracht.Model.Car;
import com.autogarage.eindopdracht.Model.Customer;
import com.autogarage.eindopdracht.Repository.CarRepo;
import com.autogarage.eindopdracht.Repository.CustomerRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes={EindopdrachtApplication.class})
class CustomerServiceImlTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepo customerRepo;

    @MockBean
    private CarRepo carRepo;

    @Mock
    CustomerDTO customerDTO;

    @Mock
    Customer customer;

    @Mock
    CarDTO carDTO;

    @Mock
    Car car;


    @BeforeEach
    void setUp() {
        Car car1 = new Car(1002L,"Seat", "Leon", "3-srp-wx", null, null, null);
        Car car2 = new Car(1003L,"Ford", "Focus", "97-rp-wx", null, null,null);
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);

        customer = new Customer(1001L, "harry", "henkst", "harry@henkst.com", "mooieweg", 6, "5435ws", "Oss", cars, null);
        customerDTO = new CustomerDTO(1001L, "harry", "henkst", "harry@henkst.com", "mooieweg", 6, "5435ws", "Oss", cars, null);
    }

    @Test
    void createCustomer() {
        Mockito
                .when(customerRepo.save(customer))
                .thenReturn(customer);

        CustomerDTO expected = customerDTO;

        CustomerDTO found = customerService.createCustomer(customerDTO);

        assertEquals(expected, found);
    }

    @Test
    void findAllCustomers() {
        Mockito
                .when(customerRepo.findAll())
                .thenReturn(List.of(customer));

        List<CustomerDTO> expected = new ArrayList<>();
        expected.add(customerDTO);

        List<CustomerDTO> found = customerService.findAllCustomers();

        assertEquals(expected, found);
    }

    @Test
    void testFindCustomerByIdAndReturnCustomerDTO() {
        Mockito
                .when(customerRepo.findById(customerDTO.getId()))
                .thenReturn(Optional.of(customer));



        Long id = 1001L;
        CustomerDTO expected = customerDTO;
        RecordNotFoundException expectedError = new RecordNotFoundException("customer not found");

        CustomerDTO found =  customerService.findCustomerById(id);


        assertEquals(expected, found);

    }

    @Test
    void findCustomerCars() {
        List<CarDTO> carDTOS = new ArrayList<>();
        CarDTO carDTO1 = new CarDTO(1002L,"Seat", "Leon", "3-srp-wx", null, null,null);
        CarDTO carDTO2 = new CarDTO(1003L,"Ford", "Focus", "97-rp-wx", null, null,null);
        carDTOS.add(carDTO1);
        carDTOS.add(carDTO2);



        Mockito
                .when(customerRepo.findById(customerDTO.getId()))
                .thenReturn(Optional.of(customer));

        Long id = 1001L;
        List<CarDTO> expected = carDTOS;

        List<CarDTO> found = customerService.findCustomerCars(1001L);


        assertEquals(expected.get(0).getLicensePlate(), found.get(0).getLicensePlate());
        assertEquals(expected.get(1).getLicensePlate(), found.get(1).getLicensePlate());

    }

    @Test
    void updateCustomer() {
        CustomerDTO verhuisdeCustomer = new CustomerDTO(1001L, "harry", "Jansen", "harry@henkst.com", "Brabantweg", 87, "1234WX", "Oss", null, null);

        Mockito
                .when(customerRepo.findById(customerDTO.getId()))
                .thenReturn(Optional.of(customer));

        CustomerDTO expected = verhuisdeCustomer;
        CustomerDTO found = customerService.updateCustomer(verhuisdeCustomer, customerDTO.getId());

        assertEquals(expected.getStreet(), found.getStreet());
        assertEquals(expected.getEmail(), found.getEmail());
        assertEquals(expected.getFirstname(), found.getFirstname());
        assertEquals(expected.getLastname(), found.getLastname());
        assertEquals(expected.getCity(), found.getCity());
        assertEquals(expected.getZipcode(), found.getZipcode());
        assertEquals(expected.getCars(), found.getCars());
    }

    @Test
    void deleteCustomer() {
        Mockito
                .when(customerRepo.findById(customerDTO.getId()))
                .thenReturn(Optional.of(customer));



        CustomerDTO expected = customerDTO;
        CustomerDTO found = customerService.deleteCustomer(customerDTO.getId());

        assertEquals(expected.getCars(), found.getCars());
    }
}