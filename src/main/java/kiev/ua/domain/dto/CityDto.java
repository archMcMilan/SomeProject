package kiev.ua.domain.dto;

import kiev.ua.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class CityDto {
    private String name;
}
