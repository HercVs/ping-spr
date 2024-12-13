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
@Table(name = "field_categories")
public class FieldCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "fieldCategory")
    private Set<StudyField> studyFields = new HashSet<>();

    public Set<StudyField> getAllStudyFields() {
        if (studyFields == null) studyFields = new HashSet<>();
        return Collections.unmodifiableSet(studyFields);
    }
}
