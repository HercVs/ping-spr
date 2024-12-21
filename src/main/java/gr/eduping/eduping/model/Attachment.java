package gr.eduping.eduping.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String savedName;
    private String filePath;
    private String contentType;
    private String extension;

    @ManyToOne
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;
}
