package week1;

import utility.Client;
import utility.Package;
import utility.Inputs;
import utility.DataSources;

import java.util.List;
import java.util.Scanner;

public class Week1 {
    static final Scanner scan = new Scanner(System.in);
    // Declare variables to store start and end times for measuring duration of operations
    static long startTime;
    static long endTime;

    public static void main(String[] args){
        // Read data into data structures
        List<Object> clientList = Inputs.readCSV(DataSources.CLIENTS);
        List<Object> packageList = Inputs.readCSV(DataSources.PACKAGES);
        LinkedList clients = new LinkedList();
        LinkedList packages = new LinkedList();

        // Iterate over the client list and insert each client object into the clients linked list
        for (Object obj : clientList) {
            Client client = (Client) obj;
            clients.insert(client);
        }
        // Iterate over the package list and insert each package object into the packages linked list
        for (Object obj : packageList) {
            Package pkg = (Package) obj;
            packages.insert(pkg);
        }

        // Sort the clients list by client ID
        System.out.print("SORTING CLIENTS: ");
        startTime = System.nanoTime();// Store the start time for measuring duration
        clients.sortByID();
        endTime = System.nanoTime();// Store the end time for measuring duration
        System.out.println("Duration in (ns): " + (double) (endTime - startTime));// Print the duration in nanoseconds
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
}