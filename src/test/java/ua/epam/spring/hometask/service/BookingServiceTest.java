package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.configuration.SpringConfig;
import ua.epam.spring.hometask.domain.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Test
    public void shouldReturnCorrectPrice() {

        //given
        double basePrice = 2.2;
        List<Long> seats = Arrays.asList(1L, 2L, 3L);
        long vipSeat = 3L;

        LocalDateTime givenTime = LocalDateTime.now();

        Set<Long> givenSeats = new HashSet<>(seats);

        Event givenEvent = new Event();
        givenEvent.setBasePrice(basePrice);
        givenEvent.setRating(EventRating.HIGH);

        NavigableSet<LocalDateTime> dates = new TreeSet<>(Arrays.asList(givenTime));

        givenEvent.setAirDates(dates);

        Set<Long> vipSeats = new HashSet<>(Arrays.asList(vipSeat));

        Auditorium givenAuditorium = new Auditorium();
        givenAuditorium.setVipSeats(vipSeats);
        givenAuditorium.setNumberOfSeats(seats.size());

        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(givenTime, givenAuditorium);

        givenEvent.setAuditoriums(auditoriums);

        double expectedPrice = 1.2 * ((basePrice * 1) + (basePrice * 2));

        //when
        Double givenPrice = bookingService.getTicketsPrice(givenEvent, givenTime, new User(), givenSeats);

        //then
        assertTrue(givenPrice.equals(expectedPrice));
    }

    @Test
    public void shouldReturnCorrectEvent() {

        //given
        Set<Ticket> allTickets = new HashSet<>();

        Event givenEvent = new Event();
        LocalDateTime givenTimeForEvent = LocalDateTime.now();

        allTickets.add(new Ticket(new User(), givenEvent, givenTimeForEvent, 1));
        allTickets.add(new Ticket(new User(), givenEvent, givenTimeForEvent, 2));

        //when
        bookingService.bookTickets(allTickets);
        Set<Ticket> givenTickets = bookingService.getPurchasedTicketsForEvent(givenEvent, givenTimeForEvent);

        //then
        givenTickets.forEach(i -> {
            assertEquals(i.getEvent(), givenEvent);
            assertEquals(i.getDateTime(), givenTimeForEvent);
        });
    }

}