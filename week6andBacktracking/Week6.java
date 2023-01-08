package week6andBacktracking;


import utility.Client;
import utility.Package;
//import week4and5.MapGraph;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Week6 {

    static final String CLIENTS_FILE="resources/Clients.csv";

    static final String PACKAGES_FILE="resources/Packages.csv";

    static final String CSV_DELIMITER=";";

    static final HashMap<Integer, Client> clientHashMap=new HashMap<Integer, Client>();

    static final HashMap<Integer, Package> packageHashMap=new HashMap<Integer, Package>();

    static final Scanner scan=new Scanner(System.in);


    // delivery center is at 375, 375
    // static final DfsGraph.node DELIVERY_CENTER=new DfsGraph.node(375, 375);


    public static void main(String[] args) {

        // This method reads the number provided using keyboard
        System.out.print("Enter the Package Id to search a delivery path for (example: 13711):");
        int inPackageId=scan.nextInt();
        // Closing Scanner after the use
        scan.close();
        Package searchPackage=null;

        List<Client> clients=readClientsFromCsv();
        for (Client c : clients) {
            //System.out.println(c.toString());
        }
        List<Package> packages=readPackagesFromCsv();
        for (Package p : packages) {
            //System.out.println(p.toString());
            if (p.id == inPackageId) {
                searchPackage=p;
            }
        }

/*
        // Build map road graph
        DfsGraph graph=createDfsGraphNetwork(clients);

        System.out.println("Graph Search start and destination:");
        long startTime=System.nanoTime();
        DfsGraph.node start=graph.getNode(DELIVERY_CENTER);
        DfsGraph.Node destination=graph.getNode(searchPackage.client.addressX, searchPackage.client.addressY);
        long endTime=System.nanoTime();
        System.out.println("Found start and destination nodes -> Duration in (ns) :" + (double) (endTime - startTime));

        System.out.println("Graph Search start to destination path: " + start.toString() + "->" + destination.toString());
        startTime=System.nanoTime();
        List<DfsGraph.Node> path=graph.findPath_DFS(start, destination);
        endTime=System.nanoTime();
        System.out.println("Found start to destination path -> Duration in (ns) :" + (double) (endTime - startTime));
        System.out.println("The path is:");
        if (path != null) {
            for (DfsGraph.Node node : path) {
                System.out.println("->" + node.toString());
            }
        } else {
            System.out.println("-> No path found!");
        }
    }
*/
    }

    public static List<Client> readClientsFromCsv() {
        List<Client> clients=new ArrayList<>();
        Client myClient;
        try (BufferedReader br=new BufferedReader(new FileReader(CLIENTS_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line=br.readLine()) != null) {
                List<String> parts=getRecordFromLine(line);
                myClient=Client.fromCsvLine(parts);
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
        List<Package> packages=new ArrayList<>();
        Package myPackage;
        try (BufferedReader br=new BufferedReader(new FileReader(PACKAGES_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line=br.readLine()) != null) {
                List<String> parts=getRecordFromLine(line);
                myPackage=Package.fromCsvLine(parts);
                packages.add(myPackage);
                myPackage.client=clientHashMap.get(myPackage.client.id);
                packageHashMap.put(myPackage.id, myPackage);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return packages;
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values=new ArrayList<String>();
        try (Scanner rowScanner=new Scanner(line)) {
            rowScanner.useDelimiter(CSV_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    public static DfsGraph createDfsGraphNetwork(List<Client> clients) {
        DfsGraph graph=new DfsGraph();

        graph.DepthFirstSearch(0);
        return graph;
    }
}




