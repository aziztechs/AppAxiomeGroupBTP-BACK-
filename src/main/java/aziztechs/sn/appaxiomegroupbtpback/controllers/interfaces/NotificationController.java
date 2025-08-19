package aziztechs.sn.appaxiomegroupbtpback.controllers.interfaces;

import aziztechs.sn.appaxiomegroupbtpback.dto.requiest.NotificationRequestDTO;
import aziztechs.sn.appaxiomegroupbtpback.dto.response.NotificationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Notifications", description = "API pour la gestion des notifications")
@RequestMapping("/api/notifications")
public interface NotificationController {

    @Operation(summary = "Créer une nouvelle notification")
    @PostMapping
    ResponseEntity<NotificationResponseDTO> createNotification(@Valid @RequestBody NotificationRequestDTO notificationDTO);

    @Operation(summary = "Marquer une notification comme lue")
    @PatchMapping("/{id}/read")
    ResponseEntity<Void> markNotificationAsRead(@PathVariable Long id);

    @Operation(summary = "Marquer toutes les notifications d'un utilisateur comme lues")
    @PatchMapping("/utilisateur/{utilisateurId}/read-all")
    ResponseEntity<Void> markAllNotificationsAsRead(@PathVariable Long utilisateurId);

    @Operation(summary = "Récupérer une notification par son ID")
    @GetMapping("/{id}")
    ResponseEntity<NotificationResponseDTO> getNotificationById(@PathVariable Long id);

    @Operation(summary = "Récupérer les notifications non lues d'un utilisateur")
    @GetMapping("/utilisateur/{utilisateurId}/unread")
    ResponseEntity<List<NotificationResponseDTO>> getUnreadNotificationsByUtilisateur(@PathVariable Long utilisateurId);

    @Operation(summary = "Récupérer toutes les notifications d'un utilisateur")
    @GetMapping("/utilisateur/{utilisateurId}")
    ResponseEntity<List<NotificationResponseDTO>> getAllNotificationsByUtilisateur(@PathVariable Long utilisateurId);

    @Operation(summary = "Compter les notifications non lues d'un utilisateur")
    @GetMapping("/utilisateur/{utilisateurId}/count-unread")
    ResponseEntity<Map<String, Long>> countUnreadNotifications(@PathVariable Long utilisateurId);
}

