package mpaxton.streams.streamstest.service.processing;

import lombok.extern.slf4j.Slf4j;
import mpaxton.streams.streamstest.model.Client;
import mpaxton.streams.streamstest.service.BlockingServiceInvoker;
import mpaxton.streams.streamstest.service.processing.Processing;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SequentialStreamProcessing implements Processing {
    private final BlockingServiceInvoker serviceInvoker;
    public SequentialStreamProcessing(BlockingServiceInvoker serviceInvoker) {
        this.serviceInvoker = serviceInvoker;
    }

    @Override
    public void start() {
       final var startTime = System.nanoTime();
        final var totalPurchases = getIds().stream()
                .map(serviceInvoker::invoke)
                .mapToDouble(Client::purchases).sum();

        final var endTime = (System.nanoTime() - startTime) / 1_000_000;
        log.info("Sequential Stream | {}ms | Total: {}", endTime, totalPurchases);
    }
}
