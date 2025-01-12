package gr.eduping.eduping.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnnouncementInsertDTO {

    @NotEmpty(message = "Source url must not be empty")
    private String url;

    @NotNull(message = "Department id must not be empty")
    private Long departmentId;

    @NotEmpty(message = "Title must not be empty")
    private String title;

    @NotNull(message = "Date must not be empty")
    private LocalDate date;

    @NotEmpty(message = "Content must not be empty")
    private String content;
}
