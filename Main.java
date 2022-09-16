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

    }
}
