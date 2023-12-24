package eterea.core.api.rest.service.extern;

import eterea.core.api.rest.exception.OrderNoteException;
import eterea.core.api.rest.kotlin.extern.OrderNote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class OrderNoteService {

    private final Environment environment;

    @Autowired
    public OrderNoteService(Environment environment) {
        this.environment = environment;
    }

    private String getUrl() {
        String ventaWebServer = environment.getProperty("app.venta-web-server");
        String ventaWebPort = environment.getProperty("app.venta-web-port");
        return "http://" + ventaWebServer + ":" + ventaWebPort + "/orderNote";
    }

    public OrderNote findByOrderNumberId(Long orderNumberId) {
        String url = this.getUrl() + "/" + orderNumberId;

        String username = "admin";
        String password = "admin";
        String authHeader = "Basic " + java.util.Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, authHeader);

        WebClient webClient = WebClient.builder()
                .defaultHeaders(header -> header.putAll(headers))
                .build();

        return webClient.get()
                .uri(url)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> response.bodyToMono(OrderNote.class)
                        .flatMap(error -> Mono.error(new OrderNoteException(orderNumberId))))
                .bodyToMono(OrderNote.class)
                .block();
    }

}
