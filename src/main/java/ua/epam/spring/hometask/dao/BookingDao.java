package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Ticket;

import java.util.Set;

public interface BookingDao {
    void addTickets(Set<Ticket> bookedTickets);

    Set<Ticket> getTickets();
}
