package ua.epam.spring.hometask.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.dao.daoImpl.AuditoriumDAOImpl;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Set;

@Service("auditoriumService")
public class AuditoriumServiceImpl implements AuditoriumService {

    @Autowired
    private AuditoriumDao auditoriumDAO;

    @Nonnull
    @Override
    public Set<Auditorium> getAll() {
        return auditoriumDAO.getAll();
    }

    @Nullable
    @Override
    public Auditorium getByName(@Nonnull String name) {
        return auditoriumDAO.getAll().stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void setAuditoriumDAO(AuditoriumDAOImpl auditoriumDAO) {
        this.auditoriumDAO = auditoriumDAO;
    }
}
