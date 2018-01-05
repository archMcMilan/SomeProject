package kiev.ua.service.impl;

import kiev.ua.model.City;
import kiev.ua.model.YearToPeople;
import kiev.ua.service.CityService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class CityServiceImpl implements CityService {

    @Override
    public City addCity(String name, long peopleAmount) {
        City city = new City().builder()
                    .name(name)
                    .yearToPeople(Arrays.asList(new YearToPeople(0,LocalDateTime.now().getYear(), peopleAmount)))
            .build();
        return city;
    }
}
