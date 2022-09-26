import java.io.BufferedReader;
import java.io.FileNotFoundException;
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




    public static void main(String[] args) {

        // This method reads the number provided using keyboard, scanning the package to search for

        System.out.print("Enter the Package Id to search for:");
        int inPackageId = scan.nextInt();
        // Closing Scanner after the use
        scan.close();

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

        PackageList packageList = new PackageList(packages);

        System.out.println();
        System.out.println("LINEAR SEARCH IN CLIENT");


        //Starting time to calculate
        long startTime = System.nanoTime();
        Package searchPackage;
        Client searchClient;
        //Searching for Package ID
        searchPackage = packageList.linearSearch(inPackageId);
        if (searchPackage != null)
        {
            //If PackageID is found get client Id from it and then search for client details
            searchClient = clientList.linearSearch(searchPackage.client.id);
            if(searchClient != null)
            {
                System.out.println("Package Id " + searchPackage.id  + " is assigned to a client named " + searchClient.name);
                System.out.println("Client ID no: " + searchClient.id);
            }
            else
            {
                System.out.println("No Client Found with id " + searchPackage.client.id);
            }
        }
        else
        {
            System.out.println("No Package Found with id " + inPackageId);
        }
        //Ending time to calculate
        long endTime = System.nanoTime();



        System.out.println("Duration in (ns) :" +  (double)(endTime - startTime));

        System.out.println();
        System.out.println("BINARY SEARCH");

        startTime = System.nanoTime();
        searchPackage = packageList.binarySearch(inPackageId);
        if (searchPackage != null)
        {
            searchClient = clientList.binarySearch(searchPackage.client.id);
            if(searchClient != null)
            {
                System.out.println("Package Id " + searchPackage.id  + " is assigned to a client named " + searchClient.name);
                System.out.println("Client ID no: " + searchClient.id);
            }
            else
            {
                System.out.println("No Client Found with id " + searchPackage.client.id);
            }
        }
        else
        {
            System.out.println("No Package Found with id " + inPackageId);
        }
        endTime = System.nanoTime();



        System.out.println("Duration in (ns) :" +  (double)(endTime - startTime));



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
}
