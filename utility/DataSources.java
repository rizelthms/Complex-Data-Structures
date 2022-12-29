package utility;

// Enum to represent the different data sources that can be read from CSV files
public enum DataSources {
    // Data sources for clients and packages
    CLIENTS(Client.class),
    PACKAGES(Package.class),
    // Test data sources for clients and packages
    TESTCLIENTS(Client.class),
    TESTPACKAGES(Package.class);

    // The type of object that the data source represents (e.g. Client or Package)
    private final Class<?> type;

    // Constructor to store the type of object for the data source
    private DataSources(Class<?> type) {
        this.type = type;
    }

    // Getter method for the type of object for the data source
    public Class<?> getType(){
        return this.type;
    }
}