package ua.epam.spring.hometask.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.dao.daoImpl.UserDAOImpl;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDAO;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDAO.getAll().stream()
                .filter(i -> i.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User save(@Nonnull User object) {
        return userDAO.save(object);
    }

    @Override
    public void remove(@Nonnull User object) {
        userDAO.remove(object);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return userDAO.getAll().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDAO.getAll();
    }

    public void setUserDAO(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }
}
