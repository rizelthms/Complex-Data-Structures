import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static final String CLIENTS_FILE = "resources/Clients.csv";
    static final String PACKAGES_FILE = "resources/Packages.csv";
    static final String CSV_DELIMITER = ";";

    public static void main(String[] args) {
        List<Client> clients = readClientsFromCsv();
        for (Client c : clients) {
            //System.out.println(c.toString());
        }
        List<Package> packages = readPackagesFromCsv();
        for (Package p : packages) {
            //System.out.println(p.toString());
        }

        ArrayList<Integer> allIds = new ArrayList<Integer>();
        for (Client c : clients) {
            allIds.add(c.id);
        }

        ClientList clientList = new ClientList(clients);
        int iterations = 100;

        System.out.println("LINEAR SEARCH");
        ArrayList<Double> linearDurations = new ArrayList<Double>();

        for (int i = 0; i < iterations; ++i) {
            // Search for a random id from the set
            int randomId = allIds.get(new Random().nextInt(allIds.size()));
            long startTime = System.nanoTime();
            clientList.linearSearch(randomId);
            long endTime = System.nanoTime();
            linearDurations.add((double) (endTime - startTime));
        }

        double meanDuration = linearDurations.stream().mapToDouble(d -> d).average().orElse(0);
        double maxDuration = linearDurations.stream().mapToDouble(d -> d).max().orElse(0);
        System.out.println(String.format("Mean duration (ns): %.0f, Max duration (ns): %.0f", meanDuration, maxDuration));


        System.out.println("BINARY SEARCH");
        ArrayList<Double> binaryDurations = new ArrayList<Double>();

        for (int i=0; i < iterations; ++i) {

        // Search for a random id from the set
        int randomId = allIds.get(new Random().nextInt(allIds.size()));
        long startTime = System.nanoTime();
        clientList.binarySearch(randomId);
        long endTime = System.nanoTime();
        binaryDurations.add((double)(endTime - startTime));
        }

        meanDuration = binaryDurations.stream().mapToDouble(d -> d).average().orElse(0);
        maxDuration = binaryDurations.stream().mapToDouble(d -> d).max().orElse(0);
        System.out.println(String.format("Mean duration (ns): %.0f, Max duration (ns): %.0f", meanDuration, maxDuration));
        }

        public static List<Client> readClientsFromCsv() {
        List<Client> clients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTS_FILE))) {
        br.readLine(); // Skip the header
        String line;
        while ((line = br.readLine()) != null) {
        List<String> parts = getRecordFromLine(line);
        clients.add(Client.fromCsvLine(parts));
        }
        } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
        } catch (IOException e) {
        throw new RuntimeException(e);
        }

        return clients;
        }

        public static List<Package> readPackagesFromCsv() {
        List<Package> clients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(PACKAGES_FILE))) {
        br.readLine(); // Skip the header
        String line;
        while ((line = br.readLine()) != null) {
        List<String> parts = getRecordFromLine(line);
        clients.add(Package.fromCsvLine(parts));
        }
        } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
        } catch (IOException e) {
        throw new RuntimeException(e);
        }

        return clients;
        }

        private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
        rowScanner.useDelimiter(CSV_DELIMITER);
        while (rowScanner.hasNext()) {
        values.add(rowScanner.next());
        }
        }
        return values;
        }
        }

