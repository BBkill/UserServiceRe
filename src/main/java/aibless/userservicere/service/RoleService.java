package aibless.userservicere.service;

import aibless.userservicere.model.Role;
import aibless.userservicere.model.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoleService{

    List<Role> getRoles();
    Optional<Role> findById(Integer id);
    void  save (Role role);
    void remove(Integer id);
    Role findByName(String name);

}
