package com.bridgelabz.employeepayroll;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NewIOFileApiTest {
    private static String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static String PLAY_WITH_NEW_IO_FILE = "TempPlayGround";
    /*
    @desc : playing around the all the methods of paths and files in java
    1. checking if file exist
    2. delete if already exist
    3. creating new file
    4. creating new directory
    5. listing all files inside directory
     */

    @Test
    public void givenPathWhenCheckedThenConfirm() throws IOException {
        // Check File Exists
        Path homePath = Paths.get(CURRENT_DIRECTORY);
        System.out.println(homePath);
        assertTrue(Files.exists(homePath));
        // Delete File and Check File Not Exist
        Path playPath = Paths.get(CURRENT_DIRECTORY + "/" + PLAY_WITH_NEW_IO_FILE);
        if (Files.exists(playPath)) {
            if (Files.isDirectory(playPath)) {
//                // List all files and subdirectories within the directory
                try {
                    Files.walk(playPath)
                            .sorted(Comparator.reverseOrder())
                            .map(Path::toFile)
                            .forEach(File::delete);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        assertTrue(Files.notExists(playPath));
        // Create Directory
        Files.createDirectory(playPath);
        assertTrue(Files.exists(playPath));
        // Create File
        IntStream.range(1, 10).forEach(cntr -> {
                    Path tempFile = Paths.get(playPath + "/temp" + cntr);
                    assertTrue(Files.notExists(tempFile));
                    try {
                        Files.createFile(tempFile);
                    } catch (IOException e) {
                    }
                    assertTrue(Files.exists(tempFile));
                }
        );

        //List Files , Directories as well all files with extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        System.out.println();
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        System.out.println();
        Files.newDirectoryStream(playPath , path->path.toFile().isFile() && path.toString().startsWith("temp")).forEach(System.out::println);
    }

    @Test
    public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException{
        try {
            Path dir = Paths.get(CURRENT_DIRECTORY + "/" + PLAY_WITH_NEW_IO_FILE);
            Files.list(dir).filter(Files::isRegularFile).forEach(System.out::println);
            assertEquals(9 , Files.list(dir).count());
            //if we want to watch the directory uncomment next line
            //new Java8WatchServiceExample(dir).processEvents();
        }catch (IOException ignored){}
    }

}
