package gr.eduping.eduping.mapper;

import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.dto.AnnouncementInsertDTO;
import gr.eduping.eduping.dto.AnnouncementReadOnlyDTO;
import gr.eduping.eduping.model.Announcement;
import gr.eduping.eduping.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnnouncementMapper {

    private final DepartmentRepository departmentRepository;

    public Announcement mapToAnnouncementEntity(AnnouncementInsertDTO insertDTO) throws EntityInvalidArgumentsException {
        Announcement announcement = new Announcement();

        announcement.setUrl(insertDTO.getUrl());
        announcement.setDepartment(departmentRepository.findById(insertDTO.getDepartmentId())
                .orElseThrow(() -> new EntityInvalidArgumentsException("Announcement", "Department with id: "
                + insertDTO.getDepartmentId() + " doesn't exist")));
        announcement.setTitle(insertDTO.getTitle());
        announcement.setDate(insertDTO.getDate());
        announcement.setContent(insertDTO.getContent());

        return announcement;
    }

    public AnnouncementReadOnlyDTO mapToAnnouncementReadOnlyDTO(Announcement announcement) {
        AnnouncementReadOnlyDTO readOnlyDTO = new AnnouncementReadOnlyDTO();

        readOnlyDTO.setTitle(announcement.getTitle());
        readOnlyDTO.setContent(announcement.getContent());
        readOnlyDTO.setDate(announcement.getDate());
        readOnlyDTO.setDepartmentId(announcement.getDepartment().getId());
        readOnlyDTO.setUrl(announcement.getUrl());

        return readOnlyDTO;
    }
}
