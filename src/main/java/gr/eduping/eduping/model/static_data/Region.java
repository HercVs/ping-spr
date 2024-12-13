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
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "region")
    private Set<City> cities = new HashSet<>();

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "region")
    private Set<PersonalDetails> personalDetails = new HashSet<>();

    public Set<City> getAllCities() {
        if (cities == null) cities = new HashSet<>();
        return Collections.unmodifiableSet(cities);
    }

    public Set<PersonalDetails> getAllPersonalDetails() {
        if (personalDetails == null) personalDetails = new HashSet<>();
        return Collections.unmodifiableSet(personalDetails);
    }
}
