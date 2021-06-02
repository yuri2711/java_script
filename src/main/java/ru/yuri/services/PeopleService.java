package ru.yuri.services;

import ru.yuri.model.People;
import ru.yuri.model.Role;

import java.util.List;

public interface PeopleService {
    List<People> index();
    People get(int id);
    void save(People people);
    boolean update(People people);
    boolean delete(int id);

    List<Role> getAllRoles();

    void saveRole(Role admin);

    Role getSingleRole(String rAdmin);

    People getOneUserByName(String name);
}
