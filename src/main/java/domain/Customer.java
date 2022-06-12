package domain;

import base.domain.BaseEntity;

import java.util.Date;

public class Customer extends BaseEntity<Long> {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String phoneNumber;

    private String Ssn;

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSsn() {
        return Ssn;
    }

    public void setSsn(String ssn) {
        Ssn = ssn;
    }
}
