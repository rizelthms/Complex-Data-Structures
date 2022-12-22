package week1;

import utility.Client;
import utility.Package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Week1 {

    static final String CLIENTS_FILE = "resources/Clients.csv";
    static final String PACKAGES_FILE = "resources/Packages.csv";
    static final String CSV_DELIMITER = ";";
    static final Scanner scan = new Scanner(System.in);
    static long startTime;
    static long endTime;

    public static void main(String[] args){
        LinkedList clients = readClientsFromCsv();
        LinkedList packages = readPackagesFromCsv();

        //Sort clients list
        System.out.print("SORTING CLIENTS: ");
        startTime = System.nanoTime();
        clients.sortByID();
        endTime = System.nanoTime();
        System.out.println("Duration in (ns): " + (double) (endTime - startTime));
        System.out.println();

        //Sort packages list
        System.out.print("SORTING PACKAGES: ");
        startTime = System.nanoTime();
        packages.sortByID();
        endTime = System.nanoTime();
        System.out.println("Duration in (ns): " + (double) (endTime - startTime));
        System.out.println();

        // This method reads the number provided using keyboard, scanning the package to search for
        System.out.print("Enter the utility.Package Id to search for:");
        int inPackageId = scan.nextInt();
        // Closing Scanner after the use
        scan.close();
        System.out.println();


        System.out.println("RECURSIVE LINEAR SEARCH IN CLIENT");
        //Starting time to calculate
        startTime = System.nanoTime();
        //Searching for utility.Package ID
        Package searchPackage = packages.linearSearch(inPackageId);
        if (searchPackage != null) {
            //If PackageID is found get client ID from it and then search for client details
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
        //Ending time to calculate
        endTime = System.nanoTime();
        System.out.println("Duration in (ns): " + (double) (endTime - startTime));
        System.out.println();


        System.out.println("ITERATIVE LINEAR SEARCH IN CLIENT");
        //Starting time to calculate
        startTime = System.nanoTime();
        //Searching for utility.Package ID
        searchPackage = packages.iterativeLinearSearch(inPackageId);
        if (searchPackage != null) {
            //If PackageID is found get client ID from it and then search for client details
            Client searchClient = clients.iterativeLinearSearch(searchPackage.client.id);
            if (searchClient != null) {
                System.out.println("utility.Package Id " + searchPackage.id + " is assigned to client " + searchClient.name
                        + " having id " + searchClient.id);
            } else {
                System.out.println("No utility.Client Found with id " + searchPackage.client.id);
            }
        } else {
            System.out.println("No utility.Package Found with id " + inPackageId);
        }
        //Ending time to calculate
        endTime = System.nanoTime();
        System.out.println("Duration in (ns): " + (double) (endTime - startTime));
        System.out.println();


        System.out.println("BINARY SEARCH");
        startTime = System.nanoTime();
        searchPackage = packages.binarySearch(inPackageId);
        if (searchPackage != null) {
            Client searchClient = clients.binarySearch(searchPackage.client.id);
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

    public static LinkedList readClientsFromCsv() {
        LinkedList clients = new LinkedList();
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTS_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = getRecordFromLine(line);
                Client myClient = Client.fromCsvLine(parts);
                clients.insert(myClient);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return clients;
    }

    public static LinkedList readPackagesFromCsv() {
        LinkedList packages = new LinkedList();
        try (BufferedReader br = new BufferedReader(new FileReader(PACKAGES_FILE))) {
            br.readLine(); // Skip the header
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = getRecordFromLine(line);
                Package myPackage = Package.fromCsvLine(parts);
                packages.insert(myPackage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return packages;
    }

    public static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(CSV_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }
}