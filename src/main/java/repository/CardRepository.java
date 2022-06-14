package repository;

import base.repository.BaseRepository;
import domain.Account;
import domain.Card;

public interface CardRepository extends BaseRepository<Card, Long> {

    Card createCard(Account account);

    Card findByCardNumber(String cardNumber);

    void changePassword();

}
