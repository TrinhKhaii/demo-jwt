package web_service.repository;
/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 09:32
*/

import org.springframework.data.jpa.repository.JpaRepository;
import web_service.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);
}
