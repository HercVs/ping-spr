package gr.eduping.eduping.mapper;

import gr.eduping.eduping.dto.DepartmentReadOnlyDTO;
import gr.eduping.eduping.dto.InstitutionReadOnlyDTO;
import gr.eduping.eduping.dto.SchoolReadOnlyDTO;
import gr.eduping.eduping.model.static_data.Country;
import gr.eduping.eduping.model.static_data.Department;
import gr.eduping.eduping.model.static_data.Institution;
import gr.eduping.eduping.model.static_data.School;
import gr.eduping.eduping.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionMapper {

    private final CountryRepository countryRepository;

    public InstitutionReadOnlyDTO mapToInstitutionReadOnlyDTO(Institution institution) {
        InstitutionReadOnlyDTO institutionReadOnlyDTO = new InstitutionReadOnlyDTO();
        institutionReadOnlyDTO.setName(institution.getName());
        // TODO issue when trying to handle exception for below
        if (countryRepository.findByCityId(institution.getCity().getId()).isPresent()) {
            Country country = countryRepository.findByCityId(institution.getCity().getId()).get();
            institutionReadOnlyDTO.setCountry(country.getName());
        }
        return institutionReadOnlyDTO;
    }

    public SchoolReadOnlyDTO mapToSchoolReadOnlyDTO(School school) {
        SchoolReadOnlyDTO schoolReadOnlyDTO = new SchoolReadOnlyDTO();
        schoolReadOnlyDTO.setSchool(school.getName());
        schoolReadOnlyDTO.setInstitution(school.getInstitution().getName());
        return schoolReadOnlyDTO;
    }

    public DepartmentReadOnlyDTO mapToDepartmentReadOnlyDTO(Department department) {
        DepartmentReadOnlyDTO departmentReadOnlyDTO = new DepartmentReadOnlyDTO();
        departmentReadOnlyDTO.setDepartment(department.getName());
        departmentReadOnlyDTO.setCity(department.getCity().getName());
        departmentReadOnlyDTO.setSchool(department.getSchool().getName());
        departmentReadOnlyDTO.setInstitution(department.getSchool().getInstitution().getName());
        return departmentReadOnlyDTO;
    }
}
