package gr.eduping.eduping.model;

import gr.eduping.eduping.model.static_data.Department;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "announcements")
public class Announcement extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_url")
    private String url;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private String content;

    @Getter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "announcement")
    private Set<Attachment> attachments = new HashSet<>();

    public Set<Attachment> getAllAttachments() {
        if (attachments == null) attachments = new HashSet<>();
        return Collections.unmodifiableSet(attachments);
    }
}
