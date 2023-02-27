package mpaxton.streams.streamstest.service;

import mpaxton.streams.streamstest.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ReactiveServiceInvoker {
    private static final String URI = "http://localhost:8080";
    private final WebClient webClient = WebClient.create(URI);

    public Mono<Client> invoke(String id) {
        final var uriSpec = webClient.get();
        return uriSpec.uri(String.format("/clients/%s", id)).retrieve().bodyToMono(Client.class);
    }
}
