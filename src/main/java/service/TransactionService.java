package service;

import base.service.BaseService;
import domain.Transaction;

public interface TransactionService extends BaseService<Transaction, Long> {

    void doATransaction();

}
