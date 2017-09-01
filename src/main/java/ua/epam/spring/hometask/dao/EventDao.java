package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;

import java.util.Collection;

public interface EventDao {
    Event save(Event event);

    void remove(Event event);

    Collection<Event> getAll();
}
