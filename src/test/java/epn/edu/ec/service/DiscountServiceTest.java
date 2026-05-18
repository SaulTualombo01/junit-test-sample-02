package epn.edu.ec.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import epn.edu.ec.repository.model.Customer;
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class DiscountServiceTest {
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private DiscountService discountService;

    private Customer customer1;

    @BeforeEach
    public void setUp() {
        customer1 = Customer.builder()
                .id(1L)
                .name ("Priscila Pazmiño")
                .mobileNumber("0960968760")
                .type("VIP")
                .build();
    }
    @Test
    public void shouldApplyVolumeDiscountWhenQuantityGreaterThan10() {
        // total = 200, quantity = 11 => descuento 15% = 30
        double total = 200.0;
        int quantity = 11;
        double discount = discountService.calculateDiscount(total, quantity, customer1.getId());
        assertEquals(30.0, discount, 0.0001);
    }

    @Test
    public void shouldApplyVolumeDiscountWhenTheCustomerIsVip() {
        // total = 200, quantity = 11 => descuento 15% = 30
        double total = 550.0;
        int quantity = 10;
        lenient().when(customerService.isVipCustomer(customer1.getId())).thenReturn(true);
        double discount = discountService.calculateDiscount(total, quantity,customer1.getId());
        assertEquals(55.0, discount, 0.0001);
    }

    @Test
    public void TotalShouldntHaveANegativeValue() {
        double total = -100.0;
        int quantity = 5;
       assertThrows(IllegalArgumentException.class, () -> discountService.calculateDiscount(total, quantity, customer1.getId()));
    }

    @Test
    public void QuantityShouldntHaveAZeroOrNegativeValue() {
        double total = 100.0;
        int quantity = -10;
       assertThrows(IllegalArgumentException.class, () -> discountService.calculateDiscount(total, quantity, customer1.getId()));
    }

    @Test
    public void ifTheCustomerNotCheckAnythingConditions() {
        double total = 100.0;
        int quantity = 5;
        lenient().when(customerService.isVipCustomer(customer1.getId())).thenReturn(false);
        double discount = discountService.calculateDiscount(total, quantity, customer1.getId());
        assertEquals(0.0, discount, 0.0001);
    }

}
