package epn.edu.ec.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;


import epn.edu.ec.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import epn.edu.ec.repository.model.Customer;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setUp() {
        customer1 = Customer.builder()
                .id(1L)
                .name("Priscila Pazmiño")
                .mobileNumber("0960968760")
                .build();
        customer2 = Customer.builder()
                .id(2L)
                .name("Saul Tualombo")
                .mobileNumber("0958799248")
                .build();
    }
    @Test
    public void getCustomers_ShouldReturnAllCustomersSortedByName() {
        //ARRANGE
        List<Customer> customers = Arrays.asList(customer2, customer1);
        when(customerRepository.findAll()).thenReturn(customers);

        //ACT
        var response = customerService.getCustomers();

        //ASSERT
        assertNotNull(response);
        assertEquals(2, response.getCustomers().size());
        assertEquals("Priscila Pazmiño", response.getCustomers().get(0).getName());
        assertEquals("Saul Tualombo", response.getCustomers().get(1).getName());
    }
}