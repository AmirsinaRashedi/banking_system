package repository;

import base.repository.BaseRepository;
import domain.Account;
import domain.Card;
import domain.Customer;

import java.util.List;

public interface AccountRepository extends BaseRepository<Account, Long> {

    Account setUpAccount(Customer owner);

    Account findByCard(Card card);

    List<Account> findByOwnerSsn(String Ssn);

}
