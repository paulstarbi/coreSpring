package ua.epam.spring.hometask.dao.daoImpl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class UserDAOImpl implements UserDao {

    private final Collection<User> users = new ArrayList<>();

    @Override
    public Collection<User> getAll() {
        return users;
    }

    @Override
    public User save(User object) {
        users.add(object);
        return object;
    }

    @Override
    public void remove(User object) {
        users.remove(object);
    }
}
