package aziztechs.sn.appaxiomegroupbtpback.services.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.NotificationRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {
    NotificationResponseDTO createNotification(NotificationRequestDTO notificationDTO);
    void markNotificationAsRead(Long id);
    void markAllNotificationsAsRead(Long utilisateurId);
    NotificationResponseDTO getNotificationById(Long id);
    List<NotificationResponseDTO> getUnreadNotificationsByUtilisateur(Long utilisateurId);
    List<NotificationResponseDTO> getAllNotificationsByUtilisateur(Long utilisateurId);
    long countUnreadNotifications(Long utilisateurId);
}
