package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.EntityNotFoundException;
import gr.eduping.eduping.dto.DepartmentReadOnlyDTO;
import gr.eduping.eduping.dto.InstitutionReadOnlyDTO;
import gr.eduping.eduping.dto.SchoolReadOnlyDTO;

import java.util.Set;

public interface IInstitutionService {
    Set<SchoolReadOnlyDTO> getInstitutionSchools(Long id) throws EntityNotFoundException;
    Set<DepartmentReadOnlyDTO> getSchoolDepartments(Long schoolId, Long institutionId)
            throws EntityNotFoundException, EntityInvalidArgumentsException;
    Set<InstitutionReadOnlyDTO> getCountryInstitutions(Long id) throws EntityNotFoundException;
}
