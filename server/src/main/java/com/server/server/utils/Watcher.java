package com.server.server.utils;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.server.server.controller.question.ReadQuestion;

@SuppressWarnings("unchecked")

public class Watcher {

    private final WatchService watcher;
    private final Path dir;

    /**
     * Creates a WatchService and registers the given directory
     */
    public Watcher(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher, ENTRY_CREATE);
        this.dir = dir;
    }

    /**
     * Process all events for the key queued to the watcher.
     */
    public void processEvents() {
        for (;;) {

            // wait for key to be signaled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                final WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                // The filename is the context of the event.
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path filename = ev.context();

                // Verify that the new file is a text file.
                try {
                    Path child = dir.resolve(filename);
                    if (!Files.probeContentType(child).equals("text/plain")) {
                        System.err.format("Just recieved a new QUESTION in the folder QUESTION !\n", filename);

                        Thread.sleep(1000);
                        new ReadQuestion().readQuestion(filename.toString());

                        continue;
                    }
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Reset the key -- this step is critical if you want to receive
            // further watch events. If the key is no longer valid, the directory
            // is inaccessible so exit the loop.
            boolean valid = key.reset();
            if (!valid)
                break;
        }
    }
}
