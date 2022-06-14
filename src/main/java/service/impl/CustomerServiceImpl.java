package service.impl;

import base.service.impl.BaseServiceImpl;
import domain.Customer;
import repository.CustomerRepository;
import service.CustomerService;

public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long>
        implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {

        super(customerRepository);

        this.customerRepository = customerRepository;

    }

    @Override
    public Customer createCustomer() {

        Customer newCustomer = null;

        try {

            customerRepository.beginTransaction();

            newCustomer = customerRepository.createCustomer();

            customerRepository.commitTransaction();

        } catch (Exception e) {

            customerRepository.rollbackTransaction();

            e.getStackTrace();

            System.out.println(e.getMessage());

            return null;

        }

        return newCustomer;
    }
}
