package domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Employee.TABLE_NAME)
public class Employee extends Person {

    public static final String TABLE_NAME = "employee";

    @ManyToOne
    @JoinColumn(nullable = false)
    private Branch workPlace;

    @ManyToOne
    private Employee supervisor;

    public Employee() {
    }

    public Branch getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Branch workPlace) {
        this.workPlace = workPlace;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
}
