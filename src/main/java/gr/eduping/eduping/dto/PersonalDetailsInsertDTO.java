package gr.eduping.eduping.dto;

import gr.eduping.eduping.core.enums.Gender;
import gr.eduping.eduping.core.enums.Occupation;
import gr.eduping.eduping.model.static_data.City;
import gr.eduping.eduping.model.static_data.Country;
import gr.eduping.eduping.model.static_data.Region;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalDetailsInsertDTO {

    @NotEmpty(message = "Firstname must not be empty")
    private String firstname;

    @NotEmpty(message = "Lastname must not be empty")
    private String lastname;

    @NotNull(message = "Gender must not be null")
    private Gender gender;

    @NotNull(message = "City must not be null")
    private City city;

    @NotNull(message = "Region must not be null")
    private Region region;

    @NotNull(message = "Country must not be null")
    private Country country;

    @NotNull(message = "Occupation must not be null")
    private Occupation occupation;
}
