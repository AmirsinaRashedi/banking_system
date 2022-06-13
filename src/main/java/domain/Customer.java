package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer extends Person {

    public static final String TABLE_NAME = "customer";

    @ManyToOne
    private Address address;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Customer() {
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
