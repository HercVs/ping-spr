package gr.eduping.eduping.model.static_data;

import gr.eduping.eduping.model.PersonalDetails;
import jakarta.persistence.*;
import lombok.*;

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

    // TODO getters: getAll return Collections.unmodifiableSet
}
