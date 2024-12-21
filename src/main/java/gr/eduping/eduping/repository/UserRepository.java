package gr.eduping.eduping.repository;

import gr.eduping.eduping.core.enums.Occupation;
import gr.eduping.eduping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUuid(String uuid);

    // TODO Should be covered by specifications
//    List<User> findAllByDepartmentsId(Long id);
//    List<User> findAllByPersonalDetailsCityId(Long id);
//    List<User> findAllByPersonalDetailsCountryId(Long id);
//    List<User> findAllByDepartmentsIdAndPersonalDetailsOccupation(Long id, Occupation occupation);
}