package gr.eduping.eduping.service;

import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.dto.AnnouncementInsertDTO;
import gr.eduping.eduping.dto.AnnouncementReadOnlyDTO;

public interface IAnnouncementService {

    AnnouncementReadOnlyDTO insertAnnouncement(AnnouncementInsertDTO announcementInsertDTO)
            throws EntityInvalidArgumentsException;
}
