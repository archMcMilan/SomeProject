package kiev.ua.model.dto;

import kiev.ua.model.YearToPeople;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class CityDto {
    private String name;
    private List<YearToPeople> yearToPeople;
}
