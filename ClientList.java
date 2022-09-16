import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ClientList {
    private List<Client> sortedClients;

    public ClientList(List<Client> clients) {
        this.sortedClients = clients;  // Sort by ID so we can do binary search
        sortedClients.sort(Comparator.comparing((c) -> c.id));
    }

    public Optional<Client> linearSearch(int clientId) {
        for (Client c : sortedClients) {
            if (c.id == clientId) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    public Optional<Client> binarySearch(int clientId) {
        int low = 0;
        int high = sortedClients.size() - 1;

        while (low <= high) {
            int split = low  + ((high - low) / 2);
            if (sortedClients.get(split).id < clientId) {
                low = split + 1;
            } else if (sortedClients.get(split).id > clientId) {
                high = split - 1;
            } else if (sortedClients.get(split).id == clientId) {
                return Optional.of(sortedClients.get(split));
            }
        }

        return Optional.empty();
    }
}
