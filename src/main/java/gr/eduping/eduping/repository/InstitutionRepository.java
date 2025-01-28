package gr.eduping.eduping.repository;

import gr.eduping.eduping.model.static_data.Country;
import gr.eduping.eduping.model.static_data.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query(value = "SELECT i " +
            "from Institution i, City ci, RegionalUnit ru, Region r, Country co " +
            "where i.city = ci " +
            "and ci.regionalUnit = ru " +
            "and ru.region = r " +
            "and r.country = co " +
            "and co.id = ?1")
    Set<Institution> findAllByCountryId(Long countryId);

    @Query(value = "SELECT i " +
            "from Institution i, City ci, RegionalUnit ru, Region r, Country co " +
            "where i.city = ci " +
            "and ci.regionalUnit = ru " +
            "and ru.region = r " +
            "and r.country = co " +
            "and co.name = ?1")
    Set<Institution> findAllByCountryName(String countryName);

    @Query(value = "SELECT co " +
            "from Institution i, City ci, RegionalUnit ru, Region r, Country co " +
            "where i.city = ci " +
            "and ci.regionalUnit = ru " +
            "and ru.region = r " +
            "and r.country = co " +
            "and co.id = ?1")
    Optional<Country> findInstitutionsCountry(Long institutionId);
}
