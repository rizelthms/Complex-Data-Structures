package week7and8;

import utility.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Week8 {
    static final String CLIENTS_FILE="resources/Clients.csv";

    static final String CSV_DELIMITER=";";


    public static void main(String[] args) {

        String regexPattern=null;

        List<Client> clients=readClientsFromCsv();

        System.out.println();


        try (Scanner scanner=new Scanner(System.in)) {

            while (true) {
                System.out.println("Enter CLIENT ID: ");
                regexPattern=scanner.nextLine();

                Pattern pattern=Pattern.compile(regexPattern);
                Matcher matcher=pattern.matcher(clients.toString());

                boolean matched=false;

                while (matcher.find()) {

                    Pattern.compile("[2-4][3-4][5-9][0-9][0-9][1-9]").matcher(regexPattern).matches();
                    matched=true;

                }
                if (!matched) {
                    System.out.println("CLIENT ID not found!");
                }
            }
        }
    }
        public static List<Client> readClientsFromCsv() {
            List<Client> clients = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(CLIENTS_FILE))) {
                br.readLine();
                String line;
                while ((line = br.readLine()) != null) {
                    List<String> parts = getRecordFromLine(line);
                    Client myClient = Client.fromCsvLine(parts);
                    clients.add(myClient);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return clients;
        }

        private static List<String> getRecordFromLine(String line) {
            List<String> values = new ArrayList<>();
            try (Scanner rowScanner = new Scanner(line)) {
                rowScanner.useDelimiter(CSV_DELIMITER);
                while (rowScanner.hasNext()) {
                    values.add(rowScanner.next());
                }
            }
            return values;
    }
}
