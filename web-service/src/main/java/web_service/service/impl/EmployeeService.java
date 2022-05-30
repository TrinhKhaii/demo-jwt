package web_service.service.impl;
/*
    Created by Trinh Khai
    Date: 25/05/2022
    Time: 20:03
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web_service.model.Employee;
import web_service.repository.IEmployeeRepository;
import web_service.service.IEmployeeService;

import java.util.Optional;
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private IEmployeeRepository iEmployeeRepository;

    @Override
    public Iterable<Employee> findAll() {
        return iEmployeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return iEmployeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return iEmployeeRepository.save(employee);
    }
}
