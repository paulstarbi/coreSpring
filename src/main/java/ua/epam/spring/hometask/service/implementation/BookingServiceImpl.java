package ua.epam.spring.hometask.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.daoImpl.BookingDaoImpl;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service("bookService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDaoImpl bookingDAO;
    @Autowired
    private DiscountService discountService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        Auditorium auditorium = event.getAuditoriums().get(dateTime);

        Set<Long> vipSeats = auditorium.getVipSeats();
        vipSeats.removeAll(seats);

        Set<Long> normalSeats = auditorium.getAllSeats();
        normalSeats.removeAll(auditorium.getVipSeats());
        normalSeats.retainAll(seats);

        double vipPrice = event.getBasePrice() * 2;
        double normalPrice = event.getBasePrice();

        if (event.getRating().equals(EventRating.HIGH)) {
            normalPrice *= 1.2;
            vipPrice *= 1.2;
        }

        double resultPrice = normalSeats.size() * normalPrice + vipSeats.size() * vipPrice;

        double discount = discountService.getDiscount(user, event, dateTime, auditorium.getAllSeats().size());
        if (discount > 0)
            resultPrice = resultPrice - (resultPrice*(discount/100));
        return resultPrice;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        Set<Ticket> ticketsToAdd = tickets.stream()
                .filter(i -> !Objects.isNull(i.getUser()))
                .collect(Collectors.toSet());
        bookingDAO.addTickets(ticketsToAdd);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return bookingDAO.getTickets().stream()
                .filter(i -> i.getEvent().equals(event))
                .filter(i -> i.getDateTime().equals(dateTime))
                .collect(Collectors.toSet());
    }

    public void setBookingDAO(BookingDaoImpl bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public void setDiscountService(DiscountServiceImpl discountService) {
        this.discountService = discountService;
    }

    public BookingDaoImpl getBookingDAO() {
        return bookingDAO;
    }

    public DiscountService getDiscountService() {
        return discountService;
    }

}
