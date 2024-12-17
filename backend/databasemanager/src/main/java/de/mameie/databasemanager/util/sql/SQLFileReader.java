package de.mameie.databasemanager.util.sql;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
/**
 * Utility class for reading SQL files.
 */
public class SQLFileReader {

    /**
     * Reads the content of an SQL file and returns it as a single String.
     *
     * @param filePath the path to the SQL file
     * @return the content of the SQL file as a String
     * @throws IOException if an I/O error occurs reading from the file or a malformed or unmappable byte sequence is read
     */
    public static String readSQLFile(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath)).collect(Collectors.joining("\n"));
    }
}
