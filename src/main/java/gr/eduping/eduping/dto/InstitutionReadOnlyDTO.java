package gr.eduping.eduping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InstitutionReadOnlyDTO {
    private String name;
    private String country;
}