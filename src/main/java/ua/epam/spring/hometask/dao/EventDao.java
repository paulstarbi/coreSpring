package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface EventDao {
    Event save(Event event);

    void remove(Event event);

    Event getById(@Nonnull Long id);

    Collection<Event> getAll();

    Event getByName(String name);
}
