package ua.epam.spring.hometask.dao.daoImpl;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;

import java.util.ArrayList;
import java.util.Collection;

public class EventDAOImpl implements EventDao {

    private final Collection<Event> events = new ArrayList<>();

    @Override
    public Event save(Event event) {
        events.add(event);
        return event;
    }

    @Override
    public void remove(Event event) {
        events.remove(event);
    }

    @Override
    public Collection<Event> getAll() {
        return events;
    }
}
