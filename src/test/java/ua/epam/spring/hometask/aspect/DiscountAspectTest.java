package ua.epam.spring.hometask.aspect;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.epam.spring.hometask.configuration.SpringConfig;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class DiscountAspectTest {

	@Autowired
    private DataSource dataSource;
	@Autowired
    private UserService userService;
	@Autowired
    private ApplicationContext ctx;
    
    private static boolean dbInitialized = false;


    @Before
    public void setUpDatabase() throws FileNotFoundException, SQLException {
        if(dbInitialized) {return;}
        Resource resource1 = new ClassPathResource("createUserTable.sql");
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.addScript(resource1);
        databasePopulator.populate(dataSource.getConnection());
        User user = new User();
        user.setFirstName("Adam");
        user.setLastName("Malysz");
        user.setEmail("Adam@Malysz.com");
        user.setId(1L);
        userService.save(user);
        dbInitialized = true;
    }

    @Test
    public void shouldReturnUserByEmail() throws Exception {
        //given
        String testedMail = "Adam@Malysz.com";

        //when
        User userOfEmail = userService.getUserByEmail(testedMail);

        //then
        assertNotNull(userOfEmail);
        assertEquals(userOfEmail.getEmail(), testedMail);
    }

    @Test
    public void shouldReturnAppropriateValuFromBaseAfterSave() throws Exception {
        //when
        User user = getDummyUser("SavedUser", 3L);

        //then
        assertFalse(userService.getAll().contains(user));
        userService.save(user);
        assertNotNull(userService.getUserByEmail(user.getEmail()));
        assertNotNull(userService.getById(user.getId()));
        assertTrue(userService.getAll().contains(user));
    }
    
    private User getDummyUser(Long id) {
        String firstName = "TestName";
        return getDummyUser(firstName, id);
    }

    private User getDummyUser(String firstName, Long id) {
        User user = new User();
        String lastName = "TestLastName";
        String email = "TestEmail@Email.com";
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setId(id);
        user.setEmail(email);
        return user;
    }
    
    @Test
    public void shouldReturnUserById() throws Exception {
        Long id = 1L;
        User user = userService.getById(id);
        assertNotNull(user);
        assertEquals(user.getId(), id);
    }

    @Test
    public void shouldUpdateUsers() throws Exception {
        User user = userService.getById(1L);
        user.setFirstName("AnotherFirstName");
        userService.save(user);
    }



    @Test
    public void shouldCreateAspect() {
        Assert.assertNotNull(ctx.getBean("discountAspect"));
    }

}
