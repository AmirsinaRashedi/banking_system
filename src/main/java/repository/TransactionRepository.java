package repository;

import base.repository.BaseRepository;
import domain.Account;
import domain.Card;
import domain.Transaction;

import java.util.Date;

public interface TransactionRepository extends BaseRepository<Transaction, Long> {

    Transaction createTransaction(Account sender, Account receiver, int amount);

    void doATransaction();

    void validateOwner(Card card);

    void findTransaction();

    void findBySenderAccount(Account account);

    void findByDate(Date date);

    void findBySenderAccountAndDate(Account account, Date date);

}
