package gr.eduping.eduping.repository;

import gr.eduping.eduping.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findAllByDepartmentId(Long id);
}
