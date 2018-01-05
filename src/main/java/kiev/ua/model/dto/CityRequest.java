package kiev.ua.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityRequest {
    private int year;
    private String name;
    private long peopleAmount;
}
