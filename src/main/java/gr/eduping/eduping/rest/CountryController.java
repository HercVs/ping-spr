package gr.eduping.eduping.rest;

import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.CountryReadOnlyDTO;
import gr.eduping.eduping.dto.InstitutionReadOnlyDTO;
import gr.eduping.eduping.service.CountryService;
import gr.eduping.eduping.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final InstitutionService institutionService;
    private final CountryService countryService;

    @GetMapping("")
    public ResponseEntity<Set<CountryReadOnlyDTO>> getAllCountries() {

        Set<CountryReadOnlyDTO> countries = countryService.getAllCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<Set<InstitutionReadOnlyDTO>> getInstitutionsForCountry(@PathVariable Long countryId)
            throws EntityNotFoundException {

        Set<InstitutionReadOnlyDTO> institutionsReadOnlyDTO = institutionService.getCountryInstitutions(countryId);

        return new ResponseEntity<>(institutionsReadOnlyDTO, HttpStatus.OK);
    }
}
