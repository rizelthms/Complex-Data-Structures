package week4and5;

import utility.Inputs;
import utility.Client;
import utility.Package;
import utility.DataSources;
import utility.ClientWithPackageCount;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Week4 {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateParser = new SimpleDateFormat("d-MM-yyyy");
        Map <Client, List<Package>> clientPackageAdjacencyList = new HashMap<>();
        Set <Date> entryDates = new HashSet<>();

        List<Object> clients = Inputs.readCSV(DataSources.CLIENTS);
        List<Object> packages = Inputs.readCSV(DataSources.PACKAGES);

        for (Object obj : clients) {
            clientPackageAdjacencyList.put((Client) obj, new ArrayList<>());
        }
        for (Object obj : packages) {
            Package pkg = (Package) obj;
            entryDates.add(pkg.entryDate);
            List<Package> pkgs = clientPackageAdjacencyList.getOrDefault(pkg.client, new ArrayList<>());
            pkgs.add(pkg);
            clientPackageAdjacencyList.put(pkg.client, pkgs);
        }

        Date minDate = entryDates.stream().min(Comparator.naturalOrder()).orElseThrow();
        Date maxDate = entryDates.stream().max(Comparator.naturalOrder()).orElseThrow();
        System.out.println(minDate);
        System.out.println(maxDate);
        System.out.print("Provide the starting and ending dates between " + minDate
                + " and " + maxDate + " to find top 10 recipients.\nStarting Date: ");
        Date userMinDate = dateParser.parse(sc.next());
        System.out.print("Ending date: ");
        Date userMaxDate = dateParser.parse(sc.next());
        System.out.println("Top 10 recipients from " + userMinDate + " to " + userMaxDate + " are:");
        PriorityQueue<ClientWithPackageCount> pq = new PriorityQueue<>((client1, client2) -> client2.packageCount - client1.packageCount);

        for (Map.Entry<Client, List<Package>> entry : clientPackageAdjacencyList.entrySet()) {
            pq.add(new ClientWithPackageCount(entry.getKey(), entry.getValue().size()));
        }

        int recipientCount = 10;
        while (!pq.isEmpty() && recipientCount-- > 0) {
            System.out.println(pq.poll());
        }
    }
}

