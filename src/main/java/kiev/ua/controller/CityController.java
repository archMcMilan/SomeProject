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
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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


    @PostMapping
    public String getAverage(@RequestBody Population population) {
        System.out.println(population.getYear());
        return "Hi";
    }
}
