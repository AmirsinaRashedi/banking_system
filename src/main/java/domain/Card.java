package domain;

import base.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Card.TABLE_NAME)
public class Card extends BaseEntity<Long> {

    public static final String TABLE_NAME = "card";

    @Column(name = "card_number", unique = true)
    private String cardNumber;

    @Column(name = "cvv2")
    private Integer cvv2;

//    @Column(name = "expiration_date")
//    private Date expirationDate = null;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "primary_password")
    private Integer primaryPassword;

    @Column(name = "secondary_password")
    private String secondaryPassword;

    public Card() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCvv2() {
        return cvv2;
    }

    public void setCvv2(Integer cvv2) {
        this.cvv2 = cvv2;
    }

//    public Date getExpirationDate() {
//        return expirationDate;
//    }
//
//    public void setExpirationDate(Date expirationDate) {
//        this.expirationDate = expirationDate;
//    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getPrimaryPassword() {
        return primaryPassword;
    }

    public void setPrimaryPassword(Integer primaryPassword) {
        this.primaryPassword = primaryPassword;
    }

    public String getSecondaryPassword() {
        return secondaryPassword;
    }

    public void setSecondaryPassword(String secondaryPassword) {
        this.secondaryPassword = secondaryPassword;
    }
}
