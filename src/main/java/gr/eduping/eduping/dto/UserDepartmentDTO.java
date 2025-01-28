package gr.eduping.eduping.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDepartmentDTO {
    @NotNull(message = "User id must not be null")
    private Long userId;

    @NotNull(message = "Department id must not be null")
    private Long departmentId;
}
