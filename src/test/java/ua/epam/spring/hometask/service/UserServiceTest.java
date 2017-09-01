package ua.epam.spring.hometask.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.domain.User;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/web.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private User expectedUser;

    private final Long expectedID = 1L;
    private final String expectedEmail = "email";

    @Before
    public void setUp() {
        expectedUser = new User();
        expectedUser.setEmail(expectedEmail);
        expectedUser.setId(expectedID);
    }

    @After
    public void cleanUsers() {
        userService.remove(expectedUser);
    }

    @Test
    public void shouldReturnCorrectUserByEmailAfterSave() {

        //when
        userService.save(expectedUser);
        String givenEmail = userService.getUserByEmail(expectedEmail).getEmail();

        //then
        assertEquals(givenEmail, expectedEmail);
    }

    @Test
    public void shouldReturnCorrectUserByIDAfterSave() {

        //when
        userService.save(expectedUser);
        Long givenID = userService.getById(expectedID).getId();

        assertEquals(givenID, expectedID);
    }

    @Test
    public void shouldContainUserAfterSave() {

        //when
        userService.save(expectedUser);
        Collection<User> givenCollection = userService.getAll();

        //then
        assertTrue(givenCollection.containsAll(Arrays.asList(expectedUser)));
    }

    @Test
    public void shouldReturnEmptyCollectionAfterRemove() {

        //when
        userService.save(expectedUser);
        userService.remove(expectedUser);

        //then
        assertTrue(userService.getAll().isEmpty());
    }

}