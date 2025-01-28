package gr.eduping.eduping.repository;

import gr.eduping.eduping.model.static_data.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SchoolRepository extends JpaRepository<School, Long> {
    Set<School> findAllByInstitutionId(Long id);
    Set<School> findAllByInstitutionName(String name);
}
