package ua.epam.spring.hometask.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.util.DiscountStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

@Service("discountService")
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountStrategy discountStrategy;

    @Override
    public double getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        double birthday = discountStrategy.birthdayStrategy(user, event);
        double tenTickets = discountStrategy.tenTicketsStrategy(numberOfTickets);

        return birthday > tenTickets ? birthday : tenTickets;
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public DiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }
}
