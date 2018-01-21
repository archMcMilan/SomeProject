package kiev.ua.service.impl;

import kiev.ua.domain.City;
import kiev.ua.domain.Population;
import kiev.ua.repository.CityRepository;
import kiev.ua.repository.PopulationRepository;
import kiev.ua.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PopulationRepository populationRepository;

    @Override
    @Transactional
    public City addCity(String name, long peopleAmount) {
        City city = new City().builder()
            .name(name)
            .build();
        cityRepository.save(city);
        populationRepository.save(new Population(LocalDateTime.now().getYear(), peopleAmount, city));
        return city;
    }

    @Override
    @Transactional
    public City addCity(String name, long peopleAmount, int year) {
        City city = City.builder()
            .name(name)
            .build();
        cityRepository.save(city);
        populationRepository.save(new Population(year, peopleAmount, city));
        return city;
    }

    @Override
    public Map<City, BigDecimal> getCityAveragePopulation(String cityName) {
        Map<City, BigDecimal> averagePopulationPerCity = new HashMap<>();
        List<City> cities = cityRepository.findAllByNameIgnoreCaseContaining(cityName);
        for (City city: cities) {
            List<Population> populationsByCity = populationRepository.findAllByCity(city);
            Optional<Long> cityPopulationSum = populationsByCity.stream()
                .map(Population::getPeopleAmount)
                .reduce(Long::sum);
            if (cityPopulationSum.isPresent()) {
                BigDecimal averagePeopleAmount =
                    BigDecimal.valueOf(cityPopulationSum.map(o -> o.doubleValue() / populationsByCity.size())
                                           .get()
                                           .doubleValue() / populationsByCity.size()).setScale(2);

                averagePopulationPerCity.put(city, averagePeopleAmount);
            }
        }
        return averagePopulationPerCity;
    }
}
