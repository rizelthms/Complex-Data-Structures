import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Package {
    public int id;
    public int length;
    public int breadth;
    public int height;
    public double weight;
    public Date entryDate;
    public Client client;
    // public int clientId;

    static Package fromCsvLine(List<String> parts) {

        Package p = new Package();
        p.id = Integer.parseInt(parts.get(0));
        p.length = Integer.parseInt(parts.get(1));
        p.breadth = Integer.parseInt(parts.get(2));
        p.height = Integer.parseInt(parts.get(3));
        p.weight = Double.parseDouble(parts.get(4));
        SimpleDateFormat fmt = new SimpleDateFormat("d-mm-yyyy");
        try {
            p.entryDate = fmt.parse(parts.get(5));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        p.client = new Client();
        p.client.id = Integer.parseInt(parts.get(6));
        return p;
    }

    public String toString() {
        return String.format("<Package id=%d length=%d breadth=%d height=%d weight=%f entryDate=%tD clientId=%d>",
                id, length, breadth, height, weight, entryDate, this.client.id);
    }
}
