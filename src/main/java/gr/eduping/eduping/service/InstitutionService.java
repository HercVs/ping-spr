package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.DepartmentReadOnlyDTO;
import gr.eduping.eduping.dto.InstitutionReadOnlyDTO;
import gr.eduping.eduping.dto.SchoolReadOnlyDTO;
import gr.eduping.eduping.mapper.InstitutionMapper;
import gr.eduping.eduping.repository.CountryRepository;
import gr.eduping.eduping.repository.DepartmentRepository;
import gr.eduping.eduping.repository.InstitutionRepository;
import gr.eduping.eduping.repository.SchoolRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InstitutionService implements IInstitutionService {

    private final InstitutionRepository institutionRepository;
    private final SchoolRepository schoolRepository;
    private final DepartmentRepository departmentRepository;
    private final CountryRepository countryRepository;
    private final InstitutionMapper institutionMapper;

    @Override
    @Transactional
    public Set<SchoolReadOnlyDTO> getInstitutionSchools(Long id) throws EntityNotFoundException {

        if (institutionRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Institution", "Institution with id: " + id + " not found");
        }

        Set<SchoolReadOnlyDTO> schoolReadOnlyDTOS = schoolRepository.findAllByInstitutionId(id)
                .stream()
                .map(institutionMapper::mapToSchoolReadOnlyDTO)
                .collect(Collectors.toSet());

        if (schoolReadOnlyDTOS.isEmpty()) {
            throw new EntityNotFoundException("School", "No schools found for institution with id: " + id);
        }

        return schoolReadOnlyDTOS;
    }

    @Override
    @Transactional
    public Set<DepartmentReadOnlyDTO> getSchoolDepartments(Long schoolId, Long institutionId)
            throws EntityNotFoundException, EntityInvalidArgumentsException {

        if (institutionRepository.findById(institutionId).isEmpty()) {
            throw new EntityNotFoundException("Institution", "Institution with id: " + institutionId + " not found");
        }

        if (schoolRepository.findById(schoolId).isEmpty()) {
            throw new EntityNotFoundException("School", "School with id: " + schoolId + " not found");
        }

        if (!schoolRepository.findAllByInstitutionId(institutionId)
                .contains(schoolRepository.findById(schoolId).get())) {
            throw new EntityInvalidArgumentsException("School", "School with id: " + schoolId +
                    " doesn't belong to institution with id: " + institutionId);
        }

        Set<DepartmentReadOnlyDTO> departmentReadOnlyDTOS = departmentRepository.findAllBySchoolId(schoolId)
                .stream()
                .map(institutionMapper::mapToDepartmentReadOnlyDTO)
                .collect(Collectors.toSet());

        if (departmentReadOnlyDTOS.isEmpty()) {
            throw new EntityNotFoundException("Department", "No departments found for school with id: " + schoolId);
        }

        return departmentReadOnlyDTOS;
    }

    @Override
    @Transactional
    public Set<InstitutionReadOnlyDTO> getCountryInstitutions(Long id) throws EntityNotFoundException {

        if (countryRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Country", "Country with id: " + id + " not found");
        }

        Set<InstitutionReadOnlyDTO> institutionReadOnlyDTOS = institutionRepository.findAllByCountryId(id)
                .stream()
                .map(institutionMapper::mapToInstitutionReadOnlyDTO)
                .collect(Collectors.toSet());

        if (institutionReadOnlyDTOS.isEmpty()) {
            throw new EntityNotFoundException("Institution", "No institutions found for country with id: " + id);
        }

        return institutionReadOnlyDTOS;

    }
}
