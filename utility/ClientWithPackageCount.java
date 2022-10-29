package utility;

public class ClientWithPackageCount {
    public Client client;
    public int packageCount;

    public ClientWithPackageCount(Client client, int packageCount) {
        this.client = client;
        this.packageCount = packageCount;
    }

    @Override
    public String toString() {
        return client.toString() + "Total No. of Packages:" + packageCount;
    }
}
