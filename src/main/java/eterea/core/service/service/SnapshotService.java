package eterea.core.service.service;

import eterea.core.service.model.Snapshot;
import eterea.core.service.model.Track;
import eterea.core.service.repository.SnapshotRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SnapshotService {

    private final SnapshotRepository repository;

    public SnapshotService(SnapshotRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Snapshot add(Snapshot snapshot) {
        return repository.save(snapshot);
    }

}
