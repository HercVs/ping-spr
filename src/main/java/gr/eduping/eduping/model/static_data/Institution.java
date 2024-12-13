package gr.eduping.eduping.model.static_data;

import gr.eduping.eduping.model.User;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToMany(mappedBy = "institutions")
    private Set<User> users = new HashSet<>();

    // TODO getters: getAll return Collections.unmodifiableSet
}
