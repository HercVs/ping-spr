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
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "city")
    private Set<Institution> institutions = new HashSet<>();

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "city")
    private Set<PersonalDetails> personalDetails = new HashSet<>();

    public Set<Institution> getAllInstitutions() {
        if (institutions == null) institutions = new HashSet<>();
        return Collections.unmodifiableSet(institutions);
    }

    public Set<PersonalDetails> getAllPersonalDetails() {
        if (personalDetails == null) personalDetails = new HashSet<>();
        return Collections.unmodifiableSet(personalDetails);
    }
}
