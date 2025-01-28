package gr.eduping.eduping.rest;

import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.DepartmentReadOnlyDTO;
import gr.eduping.eduping.dto.InstitutionReadOnlyDTO;
import gr.eduping.eduping.dto.SchoolReadOnlyDTO;
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
@RequestMapping("/api/institutions")
@RequiredArgsConstructor
public class InstitutionController {

    private final InstitutionService institutionService;

    // TODO more appropriate: /api/country/{countryId}/institutions
    // but /api/country endpoint is not gonna get used again
    @GetMapping("/country/{countryId}")
    public ResponseEntity<Set<InstitutionReadOnlyDTO>> getInstitutionsForCountry(@PathVariable Long countryId)
            throws EntityNotFoundException {

        Set<InstitutionReadOnlyDTO> institutionsReadOnlyDTO = institutionService.getCountryInstitutions(countryId);

        return new ResponseEntity<>(institutionsReadOnlyDTO, HttpStatus.OK);
    }

    @GetMapping("/{institutionId}/schools")
    public ResponseEntity<Set<SchoolReadOnlyDTO>> getInstitutionSchools(@PathVariable Long institutionId)
            throws EntityNotFoundException {

        Set<SchoolReadOnlyDTO> schoolReadOnlyDTOS = institutionService.getInstitutionSchools(institutionId);

        return new ResponseEntity<>(schoolReadOnlyDTOS, HttpStatus.OK);
    }

    @GetMapping("/{institutionId}/schools/{schoolId}/departments")
    public ResponseEntity<Set<DepartmentReadOnlyDTO>> getSchoolDepartments(@PathVariable Long institutionId,
                                                                           @PathVariable Long schoolId)
            throws EntityNotFoundException, EntityInvalidArgumentsException {

        Set<DepartmentReadOnlyDTO> departmentReadOnlyDTOS = institutionService.getSchoolDepartments(
                schoolId,
                institutionId
        );

        return new ResponseEntity<>(departmentReadOnlyDTOS, HttpStatus.OK);
    }
}
