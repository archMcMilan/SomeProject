package kiev.ua.repository;

import static org.junit.Assert.*;

import kiev.ua.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void shouldFindAllByName() {

        City city1 = new City("First");
        City city2 = new City("FirstLast");

        entityManager.persist(city1);
        entityManager.persist(city2);
        entityManager.flush();

        List<City> foundList = cityRepository.findAllByNameIgnoreCaseContaining(city1.getName());

        assertEquals(city1, foundList.get(0));
        assertEquals(city2, foundList.get(1));
    }
}