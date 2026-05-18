package epn.edu.ec.service;


import static java.util.stream.Collectors.toList;

import java.util.Comparator;

import epn.edu.ec.model.customer.CustomerResponse;
import org.springframework.stereotype.Service;
import epn.edu.ec.model.customer.CustomersResponse;
import epn.edu.ec.repository.CustomerRepository;
import epn.edu.ec.repository.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomersResponse getCustomers() {
        return new CustomersResponse(customerRepository.findAll().stream()
                .map(this::customerResponse)
                .sorted(Comparator.comparing(CustomerResponse::getName))
                .collect(toList()));
    }

    private CustomerResponse customerResponse(Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getMobileNumber(), customer.getType());
    }

    public boolean isVipCustomer(Long customerId) {
        return customerRepository.isVipCustomer(customerId);
    }
}


