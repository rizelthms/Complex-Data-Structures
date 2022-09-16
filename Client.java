import java.util.List;

public class Client {
    public int id;
    public String name;
    public String initials;
    public int addressX;
    public int addressY;

    static Client fromCsvLine(List<String> parts) {
        Client c = new Client();
        c.id = Integer.parseInt(parts.get(0));
        c.name = parts.get(1);
        c.initials = parts.get(2);
        c.addressX = Integer.parseInt(parts.get(3));
        c.addressY = Integer.parseInt(parts.get(4));
        return c;
    }

    public String toString() {
        return String.format("<Client id=%d name=%s initials=%s addressX=%d addressY=%d>", id, name, initials, addressX, addressY);
    }
}