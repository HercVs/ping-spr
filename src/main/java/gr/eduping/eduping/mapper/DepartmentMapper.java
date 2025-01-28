package gr.eduping.eduping.mapper;

import gr.eduping.eduping.dto.DepartmentReadOnlyDTO;
import gr.eduping.eduping.model.static_data.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentReadOnlyDTO mapToDepartmentReadOnlyDTO(Department department) {
        DepartmentReadOnlyDTO departmentReadOnlyDTO = new DepartmentReadOnlyDTO();
        departmentReadOnlyDTO.setDepartment(department.getName());
        departmentReadOnlyDTO.setCity(department.getCity().getName());
        departmentReadOnlyDTO.setSchool(department.getSchool().getName());
        departmentReadOnlyDTO.setInstitution(department.getSchool().getInstitution().getName());

        return departmentReadOnlyDTO;
    }
}
