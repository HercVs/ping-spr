package gr.eduping.eduping.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DepartmentReadOnlyDTO {
    private Long id;
    private String department;
    private String city;
    private String school;
    private String institution;
}
