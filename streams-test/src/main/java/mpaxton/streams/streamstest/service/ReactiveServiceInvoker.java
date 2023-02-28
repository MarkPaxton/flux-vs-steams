package mpaxton.streams.streamstest.service;

import lombok.extern.slf4j.Slf4j;
import mpaxton.streams.streamstest.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ReactiveServiceInvoker {
    private static final String URI = "http://localhost:8080";
    private final WebClient webClient = WebClient.create(URI);

    public Mono<Client> invoke(String id) {
        log.info("Calling {}", id);
        return webClient.get().uri(String.format("/clients/%s", id)).retrieve().bodyToMono(Client.class);
    }
}
