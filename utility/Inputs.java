package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Inputs {
    // Constants for file names and CSV delimiter
    static final String CLIENTS_FILE = "CLIENTS.csv";
    static final String PACKAGES_FILE = "PACKAGES.csv";
    static final String CSV_DELIMITER = ";";

    // Reads a CSV file and returns a list of objects
    public static List<Object> readCSV(DataSources source) {
        // Construct the file path based on the DataSources enum value
        String filename = "/resources/" + source.toString() + ".csv";
        System.out.println("Reading from: " + filename);
        URL resource = Inputs.class.getResource(filename);
        List<Object> elements = new ArrayList<>();

        // Check if the file exists
        if (resource == null) {
            System.err.println("Warning: Could not find " + filename);
            return elements;
        }

        // Read the file and create objects from each line
        try (BufferedReader br = new BufferedReader(new FileReader(URLDecoder.decode(resource.getPath(), StandardCharsets.UTF_8)))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = getRecordFromLine(line);
                Object element = null;

                // Create the appropriate object based on the DataSources enum value
                switch (source) {
                    case CLIENTS -> element = Client.fromCsvLine(parts);
                    case PACKAGES -> element = Package.fromCsvLine(parts);
                    case TESTCLIENTS -> element = Client.fromCsvLine(parts);
                    case TESTPACKAGES -> element = Package.fromCsvLine(parts);
                    default -> {
                    }
                }
                // Add the object to the list
                elements.add(element);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Read " + elements.size() + " items from " + filename);
        return elements;
    }

    // Parses a single line from a CSV file and returns a list of values
    public static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            // Use the CSV delimiter to split the line into values
            rowScanner.useDelimiter(CSV_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}
