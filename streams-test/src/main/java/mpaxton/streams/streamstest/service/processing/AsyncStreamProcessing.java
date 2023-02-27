package mpaxton.streams.streamstest.service.processing;

import lombok.extern.slf4j.Slf4j;
import mpaxton.streams.streamstest.model.Client;
import mpaxton.streams.streamstest.service.BlockingServiceInvoker;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class AsyncStreamProcessing implements Processing {
    private final BlockingServiceInvoker serviceInvoker;

    public AsyncStreamProcessing(BlockingServiceInvoker serviceInvoker) {
        this.serviceInvoker = serviceInvoker;
    }

    @Override
    public void start() {
        final var startTime = System.nanoTime();
        final var futureRequests = getIds().stream()
                .map(id -> CompletableFuture.supplyAsync(() -> serviceInvoker.invoke(id)))
                .toList();

        final var totalPurchases = futureRequests.stream()
                .map(CompletableFuture::join)
                .mapToDouble(Client::purchases).sum();

        final var endTime = (System.nanoTime() - startTime) / 1_000_000;
        log.info("Async Stream | {}ms | Total: {}", endTime, totalPurchases);
    }

}
