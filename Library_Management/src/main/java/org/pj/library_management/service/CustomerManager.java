package org.pj.library_management.service;

import org.pj.library_management.dao.entities.Customer;

public interface CustomerManager {

    public Customer addCustomer(Customer customer);
    public Customer updateCustomer(Customer customer);
    public Boolean deleteCustomer(Customer customer);
    public Customer loginCustomer(String username,String password);

}
