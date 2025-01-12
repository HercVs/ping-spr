package gr.eduping.eduping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementReadOnlyDTO {
    private String title;
    private String content;
    private LocalDate date;
    private Long departmentId;
    private String url;
}
