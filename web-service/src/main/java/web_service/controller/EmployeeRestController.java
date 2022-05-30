package web_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web_service.model.Account;
import web_service.model.Employee;
import web_service.service.IAccountService;
import web_service.service.IEmployeeService;


/*
    Created by Trinh Khai
    Date: 25/05/2022
    Time: 20:07
*/
@RestController
@RequestMapping(value = "/api/employee")
@CrossOrigin("*")
public class EmployeeRestController {
    @Autowired
    private IEmployeeService iEmployeeService;




    @GetMapping(value = "")
    public ResponseEntity<Iterable<Employee>> findAll() {
        Iterable<Employee> employees = iEmployeeService.findAll();
        if (employees != null) {
            return ResponseEntity.ok().body(employees);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Employee> save(@RequestBody Employee employee) {
        iEmployeeService.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
