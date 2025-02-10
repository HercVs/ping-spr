package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.CountryReadOnlyDTO;
import gr.eduping.eduping.mapper.CountryMapper;
import gr.eduping.eduping.repository.CountryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService implements ICountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Transactional
    public Set<CountryReadOnlyDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::mapToCountryReadOnlyDTO)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Long getCountryId(String countryName) throws EntityNotFoundException {
        return countryRepository.findByName(countryName)
                .orElseThrow(() -> new EntityNotFoundException("Country", "Country with name: " + countryName +
                        " not found"))
                .getId();
    }

}
