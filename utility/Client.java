package utility;

import java.util.List;
import java.util.Objects;

public class Client {
    public int id;
    public String name;
    public String initials;
    public int addressX;
    public int addressY;

    public static Client fromCsvLine(List<String> parts) {
        Client c = new Client();
        c.id = Integer.parseInt(parts.get(0));
        c.name = parts.get(1);
        c.initials = parts.get(2);
        c.addressX = Integer.parseInt(parts.get(3));
        c.addressY = Integer.parseInt(parts.get(4));
        return c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return String.format("<Client id=%d name=%s initials=%s addressX=%d addressY=%d>", id, name, initials, addressX, addressY);
    }
}