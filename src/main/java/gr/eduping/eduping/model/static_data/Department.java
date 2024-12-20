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
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "institution_id", nullable = false)
    private Institution institution;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @Getter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "departments")
    private Set<User> users = new HashSet<>();

    public Set<User> getAllUsers() {
        if (users == null) users = new HashSet<>();
        return Collections.unmodifiableSet(users);
    }
}