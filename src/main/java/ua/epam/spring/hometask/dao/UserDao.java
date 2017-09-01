package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import java.util.Collection;

public interface UserDao {
    Collection<User> getAll();

    User save(User object);

    void remove(User object);
}
