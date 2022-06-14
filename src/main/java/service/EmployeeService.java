package service;

import base.service.BaseService;
import domain.Branch;
import domain.Employee;

public interface EmployeeService extends BaseService<Employee, Long> {

    Employee createEmployee(Branch workPlace);

}
