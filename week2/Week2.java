package week2;

import utility.Client;
import utility.Package;
import utility.Inputs;
import utility.DataSources;
import week1.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Week2 {
    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);

        // This method reads the number provided using keyboard
        System.out.print("Enter the utility.Package Id to search for:");
        int inPackageId = scan.nextInt();
        // Closing Scanner after the use
        scan.close();

        // Read data into data structures
        List<Object> clientList = Inputs.readCSV(DataSources.CLIENTS);
        List<Object> packageList = Inputs.readCSV(DataSources.PACKAGES);
        LinkedList clients = new LinkedList();
        LinkedList packages = new LinkedList();
        HashMap<Integer, Client> clientHashMap = new HashMap<Integer, Client>();
        HashMap<Integer, Package> packageHashMap = new HashMap<Integer, Package>();

        for (Object obj : clientList) {
            Client client = (Client) obj;
            clients.insert(client);
            clientHashMap.put(client.id, client);
        }
        for (Object obj : packageList) {
            Package pkg = (Package) obj;
            packages.insert(pkg);
            packageHashMap.put(pkg.id, pkg);
        }

        clients.sortByID();
        packages.sortByID();
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
}