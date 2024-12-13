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

    // TODO getters: getAll return Collections.unmodifiableSet
}
