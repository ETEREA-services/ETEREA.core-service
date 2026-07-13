package eterea.core.service.tool;

public interface Jsonifyable {

    default String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
