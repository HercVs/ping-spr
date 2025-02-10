package gr.eduping.eduping.repository;

import gr.eduping.eduping.model.static_data.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    @Query(value = "select co " +
            "from City ci, RegionalUnit ru, Region r, Country co " +
            "where ci.regionalUnit = ru " +
            "and ru.region = r " +
            "and r.country = co " +
            "and ci.id = ?1 ")
    Optional<Country> findByCityId(Long id);

    Optional<Country> findByName(String name);
}
