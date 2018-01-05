package kiev.ua.controller;

import kiev.ua.model.City;
import kiev.ua.model.YearToPeople;
import kiev.ua.model.dto.CityDto;
import kiev.ua.model.dto.CityRequest;
import kiev.ua.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/add")
    public CityDto addCity(@RequestBody CityRequest cityDto) {
        City city = cityService.addCity(cityDto.getName(), cityDto.getPeopleAmount());
        return CityDto.builder().name(city.getName()).yearToPeople(city.getYearToPeople()).build();
    }

    @PostMapping
    public String getYear(@RequestBody YearToPeople yearToPeople) {
        System.out.println(yearToPeople.getYear());
        return "Hi";
    }
}
