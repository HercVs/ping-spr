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
@Table(name = "study_fields")
public class StudyField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Getter(AccessLevel.PRIVATE)
    @ManyToMany(mappedBy = "studyFields")
    private Set<User> users = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "field_category_id")
    private FieldCategory fieldCategory;

    // TODO getters: getAll return Collections.unmodifiableSet
}
