package gr.eduping.eduping.model.static_data;

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
    private Set<RegionalUnit> regionalUnits = new HashSet<>();

    public Set<RegionalUnit> getAllCities() {
        if (regionalUnits == null) regionalUnits = new HashSet<>();
        return Collections.unmodifiableSet(regionalUnits);
    }
}
