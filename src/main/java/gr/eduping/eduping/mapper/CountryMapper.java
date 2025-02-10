package gr.eduping.eduping.mapper;

import gr.eduping.eduping.dto.CountryReadOnlyDTO;
import gr.eduping.eduping.model.static_data.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {

    public CountryReadOnlyDTO mapToCountryReadOnlyDTO(Country country) {
        CountryReadOnlyDTO countryReadOnlyDTO = new CountryReadOnlyDTO();
        countryReadOnlyDTO.setId(country.getId());
        countryReadOnlyDTO.setName(country.getName());
        return  countryReadOnlyDTO;
    }
}
