package gr.eduping.eduping.model.static_data;

import gr.eduping.eduping.model.User;
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
@Table(name = "institutions")
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "institution")
    private Set<Department> departments = new HashSet<>();

    public Set<Department> getAllDepartments() {
        if (departments == null) departments = new HashSet<>();
        return Collections.unmodifiableSet(departments);
    }
}
