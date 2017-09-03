package ua.epam.spring.hometask.dao.daoImpl;

import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.AuditoriumDao;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AuditoriumDAOImpl implements AuditoriumDao {

    private Set<Auditorium> auditoriums = new HashSet<>();


    public AuditoriumDAOImpl() {
        setAuditoriums();
    }

    private void setAuditoriums() {
        Auditorium auditorium = new Auditorium();
        auditorium.setNumberOfSeats(1);
        auditorium.setName("name1");
        auditorium.setVipSeats(new HashSet<>(Arrays.asList(1L)));

        auditoriums.add(auditorium);

        auditorium = new Auditorium();
        auditorium.setNumberOfSeats(2);
        auditorium.setName("name2");
        auditorium.setVipSeats(new HashSet<>(Arrays.asList(1L, 2L)));

        auditoriums.add(auditorium);
    }

    @Override
    public Set<Auditorium> getAll() {
        return auditoriums;
    }

}
