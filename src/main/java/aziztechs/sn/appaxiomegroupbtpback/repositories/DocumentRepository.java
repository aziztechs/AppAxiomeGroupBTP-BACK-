package aziztechs.sn.appaxiomegroupbtpback.repositories;

import aziztechs.sn.appaxiomegroupbtpback.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByChantierId(Long chantierId);
    List<Document> findByType(String type);
    List<Document> findByDateUploadBetween(java.time.LocalDateTime start, java.time.LocalDateTime end);
}
