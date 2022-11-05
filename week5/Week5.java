package week5;

import utility.Client;
import week5.MapGraph;
import utility.Package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Week5 {
    static final String CLIENTS_FILE = "resources/Clients.csv";
    static final String PACKAGES_FILE = "resources/Packages.csv";
    static final String CSV_DELIMITER = ";";
    static final HashMap<Integer, Client> clientHashMap = new HashMap<Integer, Client>();
    static final HashMap<Integer, Package> packageHashMap = new HashMap<Integer, Package>();
    static final Scanner scan = new Scanner(System.in);

    // delivery center is at 375, 375
    static final MapGraph.Node DELIVERY_CENTER = new MapGraph.Node(375, 375);

    public static void main(String[] args) {

        // This method reads the number provided using keyboard
        System.out.print("Enter the Package Id to search a delivery path for (example: 13711):");
        int inPackageId = scan.nextInt();
        // Closing Scanner after the use
        scan.close();
        Package searchPackage  = null;

        List<Client> clients = readClientsFromCsv();
        for (Client c : clients) {
            //System.out.println(c.toString());
        }
        List<Package> packages = readPackagesFromCsv();
        for (Package p : packages) {
            //System.out.println(p.toString());
            if (p.id == inPackageId) {
                searchPackage = p;
            }
        }

        // Build map road graph
        MapGraph graph = createMapGraphNetwork(clients);

        System.out.println("Graph Search start and destination:");
        long startTime = System.nanoTime();
        MapGraph.Node start = graph.getNode(DELIVERY_CENTER);
        MapGraph.Node destination = graph.getNode(searchPackage.client.addressX, searchPackage.client.addressY);
        long endTime = System.nanoTime();
        System.out.println("Found start and destination nodes -> Duration in (ns) :" +  (double)(endTime - startTime));

        System.out.println("Graph Search start to destination path: " + start.toString() + "->" + destination.toString());
        startTime = System.nanoTime();
        List<MapGraph.Node> path = graph.findPath_BFS(start, destination);
        endTime = System.nanoTime();
        System.out.println("Found start to destination path -> Duration in (ns) :" +  (double)(endTime - startTime));
        System.out.println("The path is:");
        if (path != null) {
            for (MapGraph.Node node : path) {
                System.out.println("->" + node.toString());
            }
        } else {
            System.out.println("-> No path found!");
        }


    }
    public static List<Client> readClientsFromCsv() {
        List<Client> clients = new ArrayList<>();
        Client myClient ;
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTS_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = getRecordFromLine(line);
                myClient = Client.fromCsvLine(parts);
                clients.add(myClient);
                clientHashMap.put(myClient.id, myClient);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return clients;
    }

    public static List<Package> readPackagesFromCsv() {
        List<Package> packages = new ArrayList<>();
        Package myPackage;
        try (BufferedReader br = new BufferedReader(new FileReader(PACKAGES_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = getRecordFromLine(line);
                myPackage = Package.fromCsvLine(parts);
                packages.add(myPackage);
                myPackage.client = clientHashMap.get(myPackage.client.id);
                packageHashMap.put(myPackage.id,myPackage );
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return packages;
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

    public static MapGraph createMapGraphNetwork(List<Client> clients) {
        MapGraph graph = new MapGraph();
        graph.addNode(DELIVERY_CENTER);
        for (Client c: clients) {
            graph.addNode(new MapGraph.Node(c.addressX, c.addressY));
        }
        // Generate some made up road connections as edges
        // Spawn intersections at every distance x and y and connect with the rest of the nodes
        final int DISTANCE_X = 100;
        final int DISTANCE_Y = 120;
        final int INTERSECTIONS = 20;
        for (int i = 1; i < INTERSECTIONS; ++i) {
            graph.addNode(new MapGraph.Node(DELIVERY_CENTER.x + i * DISTANCE_X, DELIVERY_CENTER.y));
            graph.addNode(new MapGraph.Node(DELIVERY_CENTER.x - i * DISTANCE_X, DELIVERY_CENTER.y));
            graph.addNode(new MapGraph.Node(DELIVERY_CENTER.x, DELIVERY_CENTER.y + i * DISTANCE_Y));
            graph.addNode(new MapGraph.Node(DELIVERY_CENTER.x, DELIVERY_CENTER.y - i * DISTANCE_Y));
        }
        for (MapGraph.Node n1: graph.getNodes()) {
            for (MapGraph.Node n2: graph.getNodes()) {
                if (Math.abs(n1.x - n2.x) <= DISTANCE_X && Math.abs(n1.y - n2.y) <= DISTANCE_Y) {
                    n1.addEdge(n2);
                }
            }
        }
        return graph;
    }
}