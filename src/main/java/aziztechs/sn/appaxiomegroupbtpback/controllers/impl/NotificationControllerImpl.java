package aziztechs.sn.appaxiomegroupbtpback.controllers.impl;

import aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces.NotificationController;
import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.NotificationRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.NotificationResponseDTO;
import aziztechs.sn.appaxiomegroupbtpback.services.interfaces.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;

    @Override
    public ResponseEntity<NotificationResponseDTO> createNotification(NotificationRequestDTO notificationDTO) {
        NotificationResponseDTO createdNotification = notificationService.createNotification(notificationDTO);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> markNotificationAsRead(Long id) {
        notificationService.markNotificationAsRead(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> markAllNotificationsAsRead(Long utilisateurId) {
        notificationService.markAllNotificationsAsRead(utilisateurId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<NotificationResponseDTO> getNotificationById(Long id) {
        NotificationResponseDTO notification = notificationService.getNotificationById(id);
        return ResponseEntity.ok(notification);
    }

    @Override
    public ResponseEntity<List<NotificationResponseDTO>> getUnreadNotificationsByUtilisateur(Long utilisateurId) {
        List<NotificationResponseDTO> notifications = notificationService.getUnreadNotificationsByUtilisateur(utilisateurId);
        return ResponseEntity.ok(notifications);
    }

    @Override
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotificationsByUtilisateur(Long utilisateurId) {
        List<NotificationResponseDTO> notifications = notificationService.getAllNotificationsByUtilisateur(utilisateurId);
        return ResponseEntity.ok(notifications);
    }

    @Override
    public ResponseEntity<Map<String, Long>> countUnreadNotifications(Long utilisateurId) {
        long count = notificationService.countUnreadNotifications(utilisateurId);
        return ResponseEntity.ok(Map.of("count", count));
    }
}

