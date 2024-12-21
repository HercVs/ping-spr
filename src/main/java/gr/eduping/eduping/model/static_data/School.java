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
@Table(name = "schools")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "school")
    private Set<Department> departments = new HashSet<>();

    public Set<Department> getAllDepartments() {
        if (departments == null) departments = new HashSet<>();
        return Collections.unmodifiableSet(departments);
    }
}
