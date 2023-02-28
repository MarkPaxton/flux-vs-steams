package mpaxton.streams.streamstest.service.processing;

import lombok.extern.slf4j.Slf4j;
import mpaxton.streams.streamstest.model.Client;
import mpaxton.streams.streamstest.service.BlockingServiceInvoker;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class AsyncStreamExecutorProcessing implements Processing {

    private final BlockingServiceInvoker serviceInvoker;
    private final ExecutorService executorService = Executors.newFixedThreadPool(100);

    public AsyncStreamExecutorProcessing(BlockingServiceInvoker serviceInvoker) {
        this.serviceInvoker = serviceInvoker;
    }

    @Override
    public void start() {
        final var startTime = System.nanoTime();

        final var futureRequests = getIds().stream()
                .map(id -> CompletableFuture.supplyAsync(() -> serviceInvoker.invoke(id), executorService))
                .toList();
        final var totalPurchases = futureRequests.stream()
                .map(CompletableFuture::join)
                .mapToDouble(Client::purchases).sum();

        final var endTime = (System.nanoTime() - startTime) / 1_000_000;
        log.info("Async(100) Stream | {}ms | Total: {}", endTime, totalPurchases);
        executorService.shutdown();
    }
}
