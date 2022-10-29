package week4;

import utility.Client;
import utility.ClientWithPackageCount;
import utility.Package;
import week1.Week1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Week4 {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateParser = new SimpleDateFormat("d-MM-yyyy");
        Map <Client, List<Package>> clientPackageAdjacencyList = new HashMap<>();
        Set <Date> entryDates = new HashSet<>();
        for (Client client : Week1.readClientsFromCsv()) {
            clientPackageAdjacencyList.put(client, new ArrayList<>());
        }
        for (Package packageE : Week1.readPackagesFromCsv()) {
            entryDates.add(packageE.entryDate);
            List<Package> packages = clientPackageAdjacencyList.getOrDefault(packageE.client, new ArrayList<>());
            packages.add(packageE);
            clientPackageAdjacencyList.put(packageE.client, packages);
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
