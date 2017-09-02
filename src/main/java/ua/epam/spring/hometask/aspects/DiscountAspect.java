package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Aspect
@Component
public class DiscountAspect {

    private int total;
    private Map<User, Integer> users = new HashMap<>();
    private Map<Event, Container> eventsContainer = new HashMap<>();

    @After("execution(* ua.epam.spring.hometask.service.DiscountService.getDiscount(..))")
    public void discount(JoinPoint joinPoint) {
        User user = (User) joinPoint.getArgs()[0];
        if (users.containsKey(user)) users.replace(user, users.get(user) + 1);
        else users.put(user, 1);

        total++;

        System.out.println(user + " discount: " + users.get(user));
        System.out.println(user + " total discount: " + total);
    }

    @AfterReturning(pointcut = "execution(* ua.epam.spring.hometask.service.EventService.getByName(..))", returning = "retVal")
    public void accessedByName(Object retVal) {
        if (Objects.nonNull(retVal)) {
            Event event = (Event) retVal;
            if (eventsContainer.containsKey(event)) eventsContainer.get(event).incrementAccessedByName();
            else eventsContainer.put(event, new Container().incrementAccessedByName());

            System.out.println(event + " accessed by name: " + eventsContainer.get(event).getAccessedByName());
        }
    }

    @After("execution(* ua.epam.spring.hometask.service.BookingService.getTicketsPrice(..))")
    public void queriesPrice(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        Event event = (Event) arguments[0];

        if (Objects.nonNull(event)) {
            if (eventsContainer.containsKey(event)) eventsContainer.get(event).incrementQueriesPrice();
            else eventsContainer.put(event, new Container().incrementQueriesPrice());

            System.out.println(event + " queried by price: " + eventsContainer.get(event).getQueriesPrice());
        }
    }

    @AfterReturning("execution(* ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    public void bookedTickets(JoinPoint joinPoint) {
        @SuppressWarnings("unchecked")

        Set<Ticket> tickets = (Set<Ticket>) joinPoint.getArgs()[0];
        Event event = tickets.stream().findFirst().orElseThrow(IllegalArgumentException::new).getEvent();
        if (eventsContainer.containsKey(event)) eventsContainer.get(event).incrementAccessedByName();
        else eventsContainer.put(event, new Container().incrementBookedTickets());

        System.out.println(event + " booked tickets: " + eventsContainer.get(event).getBookedTickets());
    }

    static class Container {
        private int accessedByName;
        private int queriesPrice;
        private int bookedTickets;

        int getAccessedByName() {
            return accessedByName;
        }

        int getQueriesPrice() {
            return queriesPrice;
        }

        int getBookedTickets() {
            return bookedTickets;
        }

        Container incrementAccessedByName() {
            accessedByName++;
            return this;
        }

        Container incrementQueriesPrice() {
            queriesPrice++;
            return this;
        }

        Container incrementBookedTickets() {
            bookedTickets++;
            return this;
        }
    }
}
