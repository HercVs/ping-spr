package gr.eduping.eduping.mapper;

import gr.eduping.eduping.dto.InstitutionReadOnlyDTO;
import gr.eduping.eduping.model.static_data.Institution;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstitutionMapper {

    public InstitutionReadOnlyDTO mapToInstitutionReadOnlyDTO(Institution institution) {
        InstitutionReadOnlyDTO institutionReadOnlyDTO = new InstitutionReadOnlyDTO();
        institutionReadOnlyDTO.setName(institution.getName());

        return institutionReadOnlyDTO;
    }
}
