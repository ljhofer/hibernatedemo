package perscholas.database.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import perscholas.database.entity.Actor;

public class ActorDAOTest {

    private static ActorDAO actorDAO;

    @BeforeAll
    public static void setUp() {
        actorDAO = new ActorDAO();
    }

    @Test
    public void getActorTest() {

        // given
        Actor expected = new Actor();
        expected.setId((1));
        expected.setFirstName("Issa");
        expected.setLastName("Rae");

        // when
        Actor actual = actorDAO.findById(1);

        // then
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
    }

    @Test
    public void getAnotherActorTest() {

        // given
        Actor expected = new Actor();
        expected.setId((3));
        expected.setFirstName("Amy");
        expected.setLastName("Poehler");

        // when
        Actor actual = actorDAO.findById(3);

        // then
        Assertions.assertEquals(expected.getId(), actual.getId());
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(expected.getLastName(), actual.getLastName());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void insert() {

        // given
        Actor expected = new Actor();
        expected.setFirstName("Ilana");
        expected.setLastName("Glazer");
        expected.setAge(38);

        // when
        expected = actorDAO.save(expected);
        Actor actual = actorDAO.findById(expected.getId());

        // then
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(expected.getLastName(), actual.getLastName());

    }

    @ParameterizedTest
    @CsvSource({"3,Amy,Poehler", "13,Ilana,Glazer"})
    void testJUnit5CsvParameters(int id, String firstName, String lastName) {
        Actor expected = new Actor();
        expected.setId(id);
        expected.setFirstName(firstName);
        expected.setLastName(lastName);

        Actor actual = actorDAO.findById(id);

        Assertions.assertEquals(expected, actual);

    }



}
