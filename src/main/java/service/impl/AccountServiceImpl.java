package service.impl;

import base.repository.BaseRepository;
import base.service.impl.BaseServiceImpl;
import domain.Account;
import domain.Customer;
import repository.AccountRepository;
import service.AccountService;

public class AccountServiceImpl extends BaseServiceImpl<Account, Long>
        implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(BaseRepository repository, AccountRepository accountRepository) {
        super(repository);
        this.accountRepository = accountRepository;
    }

    @Override
    public Account setUpAccount(Customer owner) {

        Account newAccount = null;

        try {

            accountRepository.beginTransaction();

            newAccount = accountRepository.setUpAccount(owner);

            accountRepository.commitTransaction();

        } catch (Exception e) {

            accountRepository.rollbackTransaction();

            e.getStackTrace();

            System.out.println(e.getMessage());

            return null;
        }

        return newAccount;
    }
}
