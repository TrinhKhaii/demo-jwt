package web_service.repository;
/*
    Created by Trinh Khai
    Date: 25/05/2022
    Time: 20:03
*/

import org.springframework.data.jpa.repository.JpaRepository;
import web_service.model.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {
}
