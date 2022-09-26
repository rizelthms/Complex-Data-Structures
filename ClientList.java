import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ClientList {
    private List<Client> sortedClients;

    public ClientList(List<Client> clients) {
        this.sortedClients = clients;  // Sort by ID so we can do binary search
        sortedClients.sort(Comparator.comparing((c) -> c.id));
    }

    public Client linearSearch(int clientId) {
        for (Client c : sortedClients) {
            if (c.id == clientId) {
                return c;
            }
        }
        System.out.println("Client ID not found for linear search");
        return null;
    }

    public Client binarySearch(int clientId) {
        int low = 0;
        int high = sortedClients.size() - 1;

        while (low <= high) {
            int split = low  + ((high - low) / 2);
            if (sortedClients.get(split).id < clientId) {
                low = split + 1;
            } else if (sortedClients.get(split).id > clientId) {
                high = split - 1;
            } else if (sortedClients.get(split).id == clientId) {
                return sortedClients.get(split);
            }
        }

        return null;
    }
}
