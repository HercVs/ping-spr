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
@Table(name = "regional_units")
public class RegionalUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "regionalUnit")
    private Set<City> cities = new HashSet<>();

    public Set<City> getAllCities() {
        if (cities == null) cities = new HashSet<>();
        return Collections.unmodifiableSet(cities);
    }
}
