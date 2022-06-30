package repository;

import base.repository.BaseRepository;
import domain.Customer;

import java.util.List;

public interface CustomerRepository extends BaseRepository<Customer, Long> {

    Customer createCustomer();

    Customer findBySsn(String Ssn);

    void setAccountRepository(AccountRepository accountRepository);

    List<Customer> customSearch();
}
