package ua.epam.spring.hometask.util;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class DiscountStrategy {

     public double birthdayStrategy(User user, Event event) {
        for (LocalDateTime i : event.getAirDates())
            if (user.getBirthday() != null && ChronoUnit.DAYS.between(user.getBirthday(), i) > 4L)
                return 5;
        return 0;
    }

    public double tenTicketsStrategy(long numberOfTickets) {
         long ticketsDiscount = numberOfTickets - 10;

         if (ticketsDiscount > 0) {
             return (ticketsDiscount/2.0)/numberOfTickets;
         }
         return 0;
    }
}
