package week2;

import utility.Client;
import utility.Package;
import week1.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Week2 {
    static HashMap<Integer, Client> clientHashMap;
    static HashMap<Integer, Package> packageHashMap;
    static final String CLIENTS_FILE = "CDS_code/resources/Clients.csv";
    static final String PACKAGES_FILE = "CDS_code/resources/Packages.csv";

    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);

        // This method reads the number provided using keyboard
        System.out.print("Enter the utility.Package Id to search for:");
        int inPackageId = scan.nextInt();
        // Closing Scanner after the use
        scan.close();

        //Read data into data structures
        LinkedList clients = Week1.readClientsFromCsv();
        LinkedList packages = Week1.readPackagesFromCsv();
        clientHashMap = readClientsHashMap();
        packageHashMap = readPackagesHashMap();
        System.out.println();


        System.out.println("LINEAR SEARCH IN CLIENT");
        long startTime = System.nanoTime();
        Package searchPackage = packages.linearSearch(inPackageId);
        if (searchPackage != null) {
            Client searchClient = clients.linearSearch(searchPackage.client.id);
            if (searchClient != null) {
                System.out.println("utility.Package Id " + searchPackage.id + " is assigned to client " + searchClient.name
                        + " having id " + searchClient.id);
            } else {
                System.out.println("No utility.Client Found with id " + searchPackage.client.id);
            }
        } else {
            System.out.println("No utility.Package Found with id " + inPackageId);
        }
        long endTime = System.nanoTime();
        System.out.println("Duration in (ns): " + (double) (endTime - startTime));
        System.out.println();


        System.out.println("HASHMAP SEARCH");
        startTime = System.nanoTime();
        searchPackage = packageHashMap.get(inPackageId);
        if (searchPackage != null) {
            Client searchClient = clientHashMap.get(searchPackage.client.id);
            if (searchClient != null) {
                System.out.println("utility.Package Id " + searchPackage.id + " is assigned to client " + searchClient.name
                        + " having id " + searchClient.id);
            } else {
                System.out.println("No utility.Client Found with id " + searchPackage.client.id);
            }
        } else {
            System.out.println("No utility.Package Found with id " + inPackageId);
        }
        endTime = System.nanoTime();
        System.out.println("Duration in (ns): " + (double) (endTime - startTime));
    }

    public static HashMap readClientsHashMap() {
        HashMap<Integer, Client> clientHashMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTS_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = Week1.getRecordFromLine(line);
                Client myClient = Client.fromCsvLine(parts);
                clientHashMap.put(myClient.id, myClient);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clientHashMap;
    }

    public static HashMap readPackagesHashMap(){
        HashMap<Integer, Package> packageHashMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PACKAGES_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = Week1.getRecordFromLine(line);
                Package myPackage = Package.fromCsvLine(parts);
                packageHashMap.put(myPackage.id, myPackage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return packageHashMap;
    }

}