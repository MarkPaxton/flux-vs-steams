package mpaxton.streams.streamstest;

import lombok.extern.slf4j.Slf4j;
import mpaxton.streams.streamstest.service.processing.Processing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@Slf4j
public class StreamsTestApplication implements CommandLineRunner {
    private final List<Processing> processingList;

    public StreamsTestApplication(List<Processing> processingList) {
        this.processingList = processingList;
    }

    public static void main(String[] args) {
        SpringApplication.run(StreamsTestApplication.class, args);
    }

    public void run(String... args) {
        log.info("Application ready with {} processors", Runtime.getRuntime().availableProcessors());
        for (var p : processingList) {
            try {
                p.start();
            } catch (Exception e) {
                log.error("Something went wrong: {}", e.getMessage());
            }
        }
        log.info("End");
    }
}
