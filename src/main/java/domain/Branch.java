package domain;

import base.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = Branch.TABLE_NAME)
public class Branch extends BaseEntity<Long> {

    public static final String TABLE_NAME = "bank_branch";

    @OneToOne
    @JoinColumn(nullable = false)
    private Employee manager;

    @OneToOne
    @JoinColumn(nullable = false)
    private Address address;

    public Branch() {
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
