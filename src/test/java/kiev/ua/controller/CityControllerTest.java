package kiev.ua.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import kiev.ua.domain.City;
import kiev.ua.domain.dto.CityRequest;
import kiev.ua.service.StoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CityController.class)
public class CityControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StoreService storeService;

    @Test
    public void shouldReturnNameOfAddedCity() throws Exception {

        CityRequest cityDto = new CityRequest();
        cityDto.setName("Test");
        cityDto.setPeopleAmount(1000L);

        when(storeService.addCity(cityDto.getName(), cityDto.getPeopleAmount()))
            .thenReturn(new City(cityDto.getName()));

        mvc.perform(post("/city/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(convertObjectToJsonBytes(cityDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("name", is(cityDto.getName())));
    }


    private byte[] convertObjectToJsonBytes(Object object) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

}