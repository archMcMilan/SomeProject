package kiev.ua.controller;

import kiev.ua.domain.City;
import kiev.ua.domain.Population;
import kiev.ua.domain.dto.CityDto;
import kiev.ua.domain.dto.CityRequest;
import kiev.ua.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private StoreService storeService;

    @RequestMapping("/init")
    public void initByDefault() {
        storeService.addCity("1", 2000);
        storeService.addCity("2", 3000);
        storeService.addCity("3", 4000);
        storeService.addCity("4", 5000);
    }

    @PostMapping("/add")
    public CityDto addCity(@RequestBody CityRequest cityDto) {
        City city = storeService.addCity(cityDto.getName(), cityDto.getPeopleAmount());
        return CityDto.builder().name(city.getName()).build();
    }


    @GetMapping("/average")
    public List<CityDto> getAverageByCity(@RequestParam String cityName) {
        List<CityDto> result = new LinkedList<>();
        Map<City, BigDecimal> cityAveragePopulation = storeService.getCityAveragePopulation(cityName);
        System.out.println(cityAveragePopulation);
        for (Map.Entry<City, BigDecimal> entry : cityAveragePopulation.entrySet()) {
            result.add(CityDto.builder()
                           .name(entry.getKey().getName())
                           .averagePopulation(entry.getValue())
                           .build());
        }
        return result;
    }
}
