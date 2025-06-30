package eterea.core.service.repository;

import eterea.core.service.model.Snapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotRepository extends JpaRepository<Snapshot, String> {
}
