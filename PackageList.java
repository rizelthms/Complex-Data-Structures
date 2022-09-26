import java.util.Comparator;
import java.util.List;

public class PackageList {
    private List<Package> sortedPackages;

    public PackageList(List<Package> packages) {
        this.sortedPackages = packages;  // Sort by ID so we can do binary search
        sortedPackages.sort(Comparator.comparing((c) -> c.id));
    }

    public Package linearSearch(int packageId) {
        for (Package p : sortedPackages) {
            if (p.id == packageId) {
                return p;
            }
        }
        System.out.println("Package ID not found for linear search");
        return null;
    }

    public Package binarySearch(int packageId) {
        int low = 0;
        int high = sortedPackages.size() - 1;

        while (low <= high) {
            int split = low  + ((high - low) / 2);
            if (sortedPackages.get(split).id < packageId) {
                low = split + 1;
            } else if (sortedPackages.get(split).id > packageId) {
                high = split - 1;
            } else if (sortedPackages.get(split).id == packageId) {
                //return Optional.of(sortedPackages.get(split));
                return sortedPackages.get(split);
            }
        }

        return null;
    }
}
