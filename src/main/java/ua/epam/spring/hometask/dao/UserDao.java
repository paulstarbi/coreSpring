package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface UserDao {
    User getById(@Nonnull Long id);

    Collection<User> getAll();

    User save(User object);

    void remove(User object);

    User getUserByEmail(@Nonnull String email);
}
