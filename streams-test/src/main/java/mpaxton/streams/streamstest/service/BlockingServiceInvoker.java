package mpaxton.streams.streamstest.service;

import mpaxton.streams.streamstest.model.Client;
import org.springframework.stereotype.Service;

@Service
public class BlockingServiceInvoker {
    ReactiveServiceInvoker reactiveServiceInvoker;

    public BlockingServiceInvoker(ReactiveServiceInvoker reactiveServiceInvoker) {
        this.reactiveServiceInvoker = reactiveServiceInvoker;
    }

    public Client invoke(String id) {
        return reactiveServiceInvoker.invoke(id).block();
    }
}
