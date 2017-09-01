package ua.epam.spring.hometask.service.implementation;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.dao.daoImpl.EventDAOImpl;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public class EventServiceImpl implements EventService {

    private EventDao eventDAO;

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return eventDAO.getAll().stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return eventDAO.save(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        eventDAO.remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return eventDAO.getAll().stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return eventDAO.getAll();
    }

    public void setEventDAO(EventDAOImpl eventDAO) {
        this.eventDAO = eventDAO;
    }
}
