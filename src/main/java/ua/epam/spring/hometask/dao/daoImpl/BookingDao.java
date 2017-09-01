package ua.epam.spring.hometask.dao.daoImpl;

import ua.epam.spring.hometask.domain.Ticket;

import java.util.HashSet;
import java.util.Set;

public class BookingDao implements ua.epam.spring.hometask.dao.BookingDao {

    private Set<Ticket> tickets = new HashSet<>();

    @Override
    public void addTickets(Set<Ticket> bookedTickets) {
        tickets.addAll(bookedTickets);
    }

    @Override
    public Set<Ticket> getTickets() {
        return tickets;
    }
}
