package ua.epam.spring.hometask.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.domain.Auditorium;

import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/web.xml")
public class AuditoriumServiceTest {

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void shouldReturnNumOfAuditoriums() {

        //when
        Set<Auditorium> givenAuditoriums = auditoriumService.getAll();

        //then
        assertEquals(givenAuditoriums.size(), 2);
    }

    @Test
    public void shouldReturnCorrectAuditoriumName() {

        //given
        String expectedName = "name1";

        //when
        Auditorium givenAuditorium = auditoriumService.getByName(expectedName);

        //then
        assertEquals(givenAuditorium.getName(), expectedName);
    }
}