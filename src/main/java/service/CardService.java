package service;

import base.service.BaseService;
import domain.Account;
import domain.Card;

public interface CardService extends BaseService<Card, Long> {

    Card createCard(Account account);

    void changePassword(Card card);

}
