package gr.eduping.eduping.model.static_data;

import gr.eduping.eduping.model.PersonalDetails;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "country")
    private Set<Region> regions = new HashSet<>();

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "country")
    private Set<PersonalDetails> personalDetails = new HashSet<>();

    public Set<Region> getAllRegions() {
        if (regions == null) regions = new HashSet<>();
        return Collections.unmodifiableSet(regions);
    }

    public Set<PersonalDetails> getAllPersonalDetails() {
        if (personalDetails == null) personalDetails = new HashSet<>();
        return Collections.unmodifiableSet(personalDetails);
    }

    // TODO country-region-city data structures need refining. Regions maybe unnecessary, countries/cities maybe more fields
}
