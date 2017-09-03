//package ua.epam.spring.hometask.service;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import ua.epam.spring.hometask.configuration.SpringConfig;
//import ua.epam.spring.hometask.domain.Event;
//
//import java.util.Arrays;
//import java.util.Collection;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringConfig.class)
//public class EventServiceTest {
//
//    @Autowired
//    private EventService eventService;
//    private Event expectedEvent;
//
//    private final Long expectedID = 1L;
//    private final String expectedName = "name";
//
//    private void setupEvent() {
//        expectedEvent = new Event();
//        expectedEvent.setId(expectedID);
//        expectedEvent.setName(expectedName);
//    }
//
//    @Test
//    public void shouldReturnCorrectUserByNameAfterSave() {
//
//        //given
//        setupEvent();
//
//        //when
//        eventService.save(expectedEvent);
//        String givenEmail = eventService.getByName(expectedName).getName();
//
//        //then
//        assertEquals(givenEmail, expectedName);
//    }
//
//    @Test
//    public void shouldReturnCorrectUserByIDAfterSave() {
//
//        //given
//        setupEvent();
//
//        //when
//        eventService.save(expectedEvent);
//        Long givenID = eventService.getById(expectedID).getId();
//
//        assertEquals(givenID, expectedID);
//    }
//
//    @Test
//    public void shouldContainUserAfterSave() {
//
//        //given
//        setupEvent();
//
//        //when
//        eventService.save(expectedEvent);
//        Collection<Event> givenCollection = eventService.getAll();
//
//        //then
//        assertTrue(givenCollection.containsAll(Arrays.asList(expectedEvent)));
//    }
//}