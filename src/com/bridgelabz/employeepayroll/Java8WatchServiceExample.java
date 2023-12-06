package com.bridgelabz.employeepayroll;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/*
 * @desc : A class demonstrating the usage of WatchService in Java NIO.2 for monitoring file system changes.
 * This class provides methods for registering directories, scanning them, and processing watch events.
 */
public class Java8WatchServiceExample {

    // Static variables for directory paths
    private static String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static String PLAY_WITH_NEW_IO_FILE = "TempPlayGround";

    // WatchService and directory map variables
    private final WatchService watcher;
    private final Map<WatchKey, Path> dirWatchers;

    /*
     * @desc :Constructor initializing the WatchService and registering the provided directory.
     * @param :dir The initial directory to be registered.
     * @throws :IOException If an I/O error occurs.
     */
    public Java8WatchServiceExample(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        this.dirWatchers = new HashMap<WatchKey, Path>();
        scanAndRegisterDirectories(dir);
    }

    /*
     * @desc : Register a directory for watch events (create, delete, modify) and store it in the map.
     * @param dir The directory to be registered.
     * @throws IOException If an I/O error occurs.
     */
    private void registerDirWatchers(Path dir) throws IOException {
        WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        dirWatchers.put(key, dir);
    }

    /*
     * @desc : Recursively scan and register directories starting from the provided path.
     * @param start The starting path for directory scanning.
     * @throws IOException If an I/O error occurs.
     */
    private void scanAndRegisterDirectories(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                registerDirWatchers(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /*
     *@desc :  Process watch events continuously in an infinite loop.
     * @desc This method retrieves a WatchKey, processes watch events, and updates the dirWatchers map accordingly.
     * @params : no params
     * @return : no return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    void processEvents() {
        while (true) {
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }
            Path dir = dirWatchers.get(key);
            if (dir == null) continue;
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                Path name = ((WatchEvent<Path>) event).context();
                Path child = dir.resolve(name);
                System.out.format("%s: %s\n", event.kind().name(), child);

                if (kind == ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) {
                            scanAndRegisterDirectories(child);
                        }
                    } catch (IOException x) {
                        // Handle IOException
                    }
                } else if (kind.equals(ENTRY_DELETE)) {
                    if (Files.isDirectory(child)) {
                        dirWatchers.remove(key);
                    }
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                dirWatchers.remove(key);
                if (dirWatchers.isEmpty()) {
                    break;
                }
            }
        }
    }
}