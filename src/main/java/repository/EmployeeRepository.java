package repository;

import base.repository.BaseRepository;
import domain.Branch;
import domain.Employee;

public interface EmployeeRepository extends BaseRepository<Employee, Long> {

    Employee createEmployee(Branch workPlace);
}
