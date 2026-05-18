package epn.edu.ec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import epn.edu.ec.repository.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    //is vip customer
    boolean isVipCustomer(Long customerId);

}
