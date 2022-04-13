//package com.autogarage.eindopdracht.Controllers;
//
//import com.autogarage.eindopdracht.EindopdrachtApplication;
//import com.autogarage.eindopdracht.Service.CustomerService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest
//@ContextConfiguration(classes={EindopdrachtApplication.class})
//class CustomerControllerTest {
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    CustomerService customerService;
//
//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    void testGetAllCustomers() throws Exception {
////        List<CustomerDTO> a = new ArrayList<CustomerDTO>();
////        CustomerDTO kees = new CustomerDTO();
////        a.add(kees);
////        given(customerService.findAllCustomers()).willReturn(a);
//        mockMvc.perform(get("/customers"))
//                .andExpect(status().isOk());
//
//    }
//
//    @Test
//    void testGetCustomerById() {
//    }
//
//    @Test
//    void testGetCustomerCars() {
//    }
//
//    @Test
//    void testCreateCustomer() {
//    }
//
//    @Test
//    void testUpdateCustomer() {
//    }
//
//    @Test
//    void testDeleteCustomer() {
//    }
//}