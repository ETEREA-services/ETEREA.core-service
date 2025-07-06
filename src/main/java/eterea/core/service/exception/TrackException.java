package eterea.core.service.exception;

public class TrackException extends RuntimeException {

    public TrackException(String trackUuid) {
        super("Cannot find Track " + trackUuid);
    }

}
