package gr.eduping.eduping.rest;

import gr.eduping.eduping.core.exceptions.EntityInvalidArgumentsException;
import gr.eduping.eduping.core.exceptions.ValidationException;
import gr.eduping.eduping.dto.AnnouncementInsertDTO;
import gr.eduping.eduping.dto.AnnouncementReadOnlyDTO;
import gr.eduping.eduping.service.AnnouncementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for announcements related requests.
 * TODO announcements scraping to be implemented
 */
@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/insert")
    public ResponseEntity<AnnouncementReadOnlyDTO> insert(@Valid @RequestBody AnnouncementInsertDTO insertDTO,
                                         BindingResult bindingResult)
            throws ValidationException, EntityInvalidArgumentsException {

        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }

        AnnouncementReadOnlyDTO readOnlyDTO = announcementService.insertAnnouncement(insertDTO);
        return new ResponseEntity<>(readOnlyDTO, HttpStatus.CREATED);
    }
}
