package service;

import base.service.BaseService;
import domain.Customer;

public interface CustomerService extends BaseService<Customer, Long> {

    Customer createCustomer();

}
