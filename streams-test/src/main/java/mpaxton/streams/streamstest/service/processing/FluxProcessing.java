package mpaxton.streams.streamstest.service.processing;

import lombok.extern.slf4j.Slf4j;
import mpaxton.streams.streamstest.model.Client;
import mpaxton.streams.streamstest.service.ReactiveServiceInvoker;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class FluxProcessing implements Processing {
    private final ReactiveServiceInvoker serviceInvoker;

    public FluxProcessing() {
        this.serviceInvoker = new ReactiveServiceInvoker();
    }

    @Override
    public void start() {
       final var startTime = System.nanoTime();
        final var totalPurchases = Flux.fromIterable(getIds())
                .flatMap(serviceInvoker::invoke)
                .map(Client::purchases)
                .reduce(0.0, Double::sum)
                .block();

        final var endTime = (System.nanoTime() - startTime) / 1_000_000;
        log.info("Flux | {}ms | Total: {}", endTime, totalPurchases);
    }
}
