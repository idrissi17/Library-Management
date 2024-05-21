package org.pj.library_management.service;


import org.pj.library_management.dao.entities.Customer;
import org.pj.library_management.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerManager {

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer addCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Boolean deleteCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer loginCustomer(String username, String password) {
        Customer customer =customerRepository.findByUsername(username);
        if (customer != null && customer.getPassword().equals(password)){
            return customer;
        }
        return null;
    }
}
