package web_service.service.impl;
/*
    Created by Trinh Khai
    Date: 26/05/2022
    Time: 09:34
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web_service.model.Role;
import web_service.repository.IRoleRepository;
import web_service.service.IRoleService;

import java.util.Optional;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private IRoleRepository iRoleRepository;

    @Override
    public Iterable<Role> findAll() {
        return null;
    }

    @Override
    public Optional<Role> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Role save(Role role) {
        return iRoleRepository.save(role);
    }
}
