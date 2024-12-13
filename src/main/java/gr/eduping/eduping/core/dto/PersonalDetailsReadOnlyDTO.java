package gr.eduping.eduping.core.dto;

import gr.eduping.eduping.core.enums.Occupation;
import gr.eduping.eduping.model.static_data.City;
import gr.eduping.eduping.model.static_data.Country;
import gr.eduping.eduping.model.static_data.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonalDetailsReadOnlyDTO {
    private String firstname;
    private String lastname;
    private City city;
    private Region region;
    private Country country;
    private Occupation occupation;
}
