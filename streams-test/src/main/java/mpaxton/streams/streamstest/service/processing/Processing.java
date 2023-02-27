package mpaxton.streams.streamstest.service.processing;

import java.util.List;

public interface Processing {
    default List<String> getIds() {
        return List.of(
                "C01", "C02", "C03", "C04", "C05", "C06", "C07", "C08", "C09", "C10",
                "C11", "C12", "C13", "C14", "C15", "C16", "C17", "C18", "C19", "C20");
    }

    void start();
}
