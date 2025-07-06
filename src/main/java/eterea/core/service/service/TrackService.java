package eterea.core.service.service;

import eterea.core.service.exception.TrackException;
import eterea.core.service.model.Track;
import eterea.core.service.repository.TrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TrackService {

    private final TrackRepository repository;

    public TrackService(TrackRepository repository) {
        this.repository = repository;
    }

    public Track findByUuid(String trackUuid) {
        return repository.findByUuid(trackUuid).orElseThrow(() -> new TrackException(trackUuid));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Track startTracking(String descripcion) {
        var track = Track.builder()
                .uuid(UUID.randomUUID().toString())
                .descripcion(descripcion)
                .status(Track.Status.IN_PROGRESS)
                .build();
        return repository.save(track);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Track endTracking(Track track) {
        track.setStatus(Track.Status.COMPLETED);
        return repository.save(track);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Track failedTracking(Track track) {
        track.setStatus(Track.Status.FAILED);
        return repository.save(track);
    }

}
