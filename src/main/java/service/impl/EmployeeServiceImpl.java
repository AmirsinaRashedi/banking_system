package service.impl;

import base.repository.BaseRepository;
import base.service.impl.BaseServiceImpl;
import domain.Branch;
import domain.Employee;
import repository.BranchRepository;
import repository.EmployeeRepository;
import service.EmployeeService;

import java.util.Scanner;

public class EmployeeServiceImpl extends BaseServiceImpl<Employee, Long>
        implements EmployeeService {

    private EmployeeRepository employeeRepository;

    private BranchRepository branchRepository;

    public EmployeeServiceImpl(BaseRepository repository, EmployeeRepository employeeRepository, BranchRepository branchRepository) {

        super(repository);

        this.employeeRepository = employeeRepository;

        this.branchRepository = branchRepository;

    }

    @Override
    public Employee createEmployee(Branch workPlace) {

        int branchCount = 0;

        for (Branch branch : branchRepository.findAll()) {

            System.out.println(++branchCount + "- " + branch.getAddress());

        }

        if (branchCount == 0) {
            System.out.println("no active branches available");

            return null;
        }

        Scanner intInput = new Scanner(System.in);

        System.out.println("choose the branch you want to work for");

        Long choice = intInput.nextLong();

        Employee newEmployee = null;

        if (choice > 0 && choice <= branchCount) {

            try {

                employeeRepository.beginTransaction();

                newEmployee = employeeRepository.createEmployee(branchRepository.findById(choice));

                employeeRepository.commitTransaction();

            } catch (Exception e) {

                employeeRepository.rollbackTransaction();

                e.getStackTrace();

                System.out.println(e.getMessage());

                return null;
            }

        }

        return newEmployee;
    }
}
