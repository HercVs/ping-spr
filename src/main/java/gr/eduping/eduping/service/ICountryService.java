package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.CountryReadOnlyDTO;

import java.util.Set;

public interface ICountryService {
    Set<CountryReadOnlyDTO> getAllCountries();
    Long getCountryId(String countryName) throws EntityNotFoundException;
}
