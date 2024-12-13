package gr.eduping.eduping.model.static_data;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "field_categories")
public class FieldCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "fieldCategory")
    private Set<StudyField> studyFields = new HashSet<>();

    // TODO getters: getAll return Collections.unmodifiableSet
}
