package repository.impl;

import base.repository.impl.BaseRepositoryImpl;
import domain.Branch;
import repository.AddressRepository;
import repository.BranchRepository;
import repository.EmployeeRepository;

import javax.persistence.EntityManager;

public class BranchRepositoryImpl extends BaseRepositoryImpl<Branch, Long>
        implements BranchRepository {

    private AddressRepository addressRepository;

    private EmployeeRepository employeeRepository;

    public BranchRepositoryImpl(EntityManager em, AddressRepository addressRepository, EmployeeRepository employeeRepository) {
        super(em);

        this.addressRepository = addressRepository;

        this.employeeRepository = employeeRepository;
    }

    @Override
    public Class<Branch> getClassType() {
        return Branch.class;
    }

    @Override
    public Branch createBranch() {

        Branch newBranch = new Branch();

        System.out.println("define branch Address: ");

        newBranch.setAddress(addressRepository.createAddress());

        newBranch.setManager(null);

        newBranch = save(newBranch);

        System.out.println("define branch manager: ");

        newBranch.setManager(employeeRepository.createEmployee(newBranch));

        newBranch = save(newBranch);

        return newBranch;
    }
}
