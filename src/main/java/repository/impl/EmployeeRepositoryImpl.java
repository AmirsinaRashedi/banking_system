package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Branch;
import domain.Employee;
import repository.EmployeeRepository;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class EmployeeRepositoryImpl extends BaseRepositoryImpl<Employee, Long>
        implements EmployeeRepository {

    public EmployeeRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public Class<Employee> getClassType() {
        return Employee.class;
    }

    @Override
    public Employee createEmployee(Branch workPlace) {

        Scanner stringInput = new Scanner(System.in);

        Scanner intInput = new Scanner(System.in);

        Employee newEmployee = new Employee();

        System.out.print("enter first_name: ");
        newEmployee.setFirstName(stringInput.nextLine());

        System.out.print("enter last_name: ");
        newEmployee.setLastName(stringInput.nextLine());

        System.out.print("enter social_security_number: ");
        newEmployee.setSsn(stringInput.nextLine());

//        System.out.print("enter date_of_birth in order of (year , month , day) with a" +
//                " space between each number : ");
//        newEmployee.setDateOfBirth(new Date(intInput.nextInt(), intInput.nextInt() - 1, intInput.nextInt()));

        System.out.print("enter phone_number: ");
        newEmployee.setPhoneNumber(stringInput.nextLine());

        newEmployee.setWorkPlace(workPlace);

        return save(newEmployee);
    }
}
