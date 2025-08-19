package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUtilisateurIdAndLuFalse(Long utilisateurId);
    List<Notification> findByUtilisateurIdOrderByDateDesc(Long utilisateurId);
    long countByUtilisateurIdAndLuFalse(Long utilisateurId);
}
