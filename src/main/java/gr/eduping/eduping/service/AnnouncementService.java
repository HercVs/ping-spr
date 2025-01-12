package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.dto.AnnouncementInsertDTO;
import gr.eduping.eduping.dto.AnnouncementReadOnlyDTO;
import gr.eduping.eduping.mapper.AnnouncementMapper;
import gr.eduping.eduping.model.Announcement;
import gr.eduping.eduping.repository.AnnouncementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnnouncementService implements IAnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final AnnouncementMapper announcementMapper;

    @Override
    @Transactional
    public AnnouncementReadOnlyDTO insertAnnouncement(AnnouncementInsertDTO announcementInsertDTO)
            throws EntityInvalidArgumentsException {

        Announcement announcement = announcementMapper.mapToAnnouncementEntity(announcementInsertDTO);
        Announcement savedAnnouncement = announcementRepository.save(announcement);

        return announcementMapper.mapToAnnouncementReadOnlyDTO(savedAnnouncement);
    }
}
