package week2;

import utility.Client;
import utility.ClientList;
import utility.Package;
import utility.PackageList;
import week1.Week1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Week2 {
    static final Map<Integer, Client> clientHashMap = new HashMap<>();
    static final Map<Integer, Package> packageHashMap = new HashMap<>();

    public static void main(String[] args) {
        final Scanner scan = new Scanner(System.in);

        // This method reads the number provided using keyboard
        System.out.print("Enter the utility.Package Id to search for:");
        int inPackageId = scan.nextInt();
        // Closing Scanner after the use
        scan.close();
        List<Client> clients = Week1.readClientsFromCsv();
        List<Package> packages = Week1.readPackagesFromCsv();
        ClientList clientList = new ClientList(clients);
        //int iterations = 100;
        PackageList packageList = new PackageList(packages);
        System.out.println();
        System.out.println("LINEAR SEARCH IN CLIENT");
        long startTime = System.nanoTime();
        Package searchPackage = packageList.linearSearch(inPackageId);
        if (searchPackage != null) {
            Client searchClient = clientList.linearSearch(searchPackage.client.id);
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
        System.out.println("Duration in (ns) :" + (double) (endTime - startTime));
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
        System.out.println("Duration in (ns) :" + (double) (endTime - startTime));
    }
}