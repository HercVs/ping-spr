package gr.eduping.eduping.repository;

import gr.eduping.eduping.model.static_data.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Long> {
    Set<Department> findAllBySchoolId(Long id);
    Set<Department> findAllByUsersId(Long id);
}
