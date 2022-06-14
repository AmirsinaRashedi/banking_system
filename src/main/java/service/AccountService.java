package service;

import base.service.BaseService;
import domain.Account;
import domain.Customer;

public interface AccountService extends BaseService<Account, Long> {

    Account setUpAccount(Customer owner);

}
