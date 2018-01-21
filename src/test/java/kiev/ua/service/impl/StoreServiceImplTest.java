package kiev.ua.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import kiev.ua.MyTestsConfiguration;
import kiev.ua.domain.City;
import kiev.ua.domain.Population;
import kiev.ua.repository.CityRepository;
import kiev.ua.repository.PopulationRepository;
import kiev.ua.service.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@Import(MyTestsConfiguration.class)
public class StoreServiceImplTest {

    @MockBean
    private CityRepository cityRepository;
    @MockBean
    private PopulationRepository populationRepository;
    @Autowired
    private StoreService storeService;

    @Test
    public void getCityAveragePopulation() {
        String cityName = "First";
        City city = new City(cityName);
        Population population2018 = new Population(2018, 2000, city);
        Population population2017 = new Population(2017, 2400, city);
        Population population2016 = new Population(2016, 2500, city);
        List<Population> populationList = Arrays.asList(population2018, population2017, population2016);
        when(cityRepository.findAllByNameIgnoreCaseContaining(cityName)).thenReturn(Arrays.asList(city));
        when(populationRepository.findAllByCity(any(City.class))).thenReturn(populationList);

        assertEquals(BigDecimal.valueOf(230000, 2), storeService.getCityAveragePopulation(cityName).get(city));
    }

    @Test
    public void getCityAveragePopulationWhenNoCityWithSuchName() {
        when(cityRepository.findAllByNameIgnoreCaseContaining("First")).thenReturn(Collections.emptyList());
        assertEquals(0, storeService.getCityAveragePopulation("First").size());
    }

}