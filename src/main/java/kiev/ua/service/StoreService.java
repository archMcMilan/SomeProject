package kiev.ua.service;


import kiev.ua.domain.City;

import java.math.BigDecimal;
import java.util.Map;

public interface StoreService {

    City addCity(String name, long peopleAmount);

    City addCity(String name, long peopleAmount, int year);

    Map<City, BigDecimal> getCityAveragePopulation(String cityName);
}
