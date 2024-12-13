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

    // TODO getters: getAll return Collections.unmodifiableSet
}
