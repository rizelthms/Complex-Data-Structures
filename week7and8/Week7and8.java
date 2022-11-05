package week7and8;

import utility.Package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Week7and8 {
    static final String PACKAGES_FILE = "resources/Packages.csv";
    static final String CSV_DELIMITER = ";";


    public static void main(String[] args) {

        List<Package> packages = readPackagesFromCsv();

        System.out.println();


        try (Scanner scanner=new Scanner(System.in)) {

            while (true) {
                System.out.println("Enter the package ID: ");
                String regexPattern=scanner.nextLine();

                Pattern pattern=Pattern.compile(regexPattern);
                Matcher matcher=pattern.matcher(packages.toString());

                boolean found=false;

                while (matcher.find())
                {

                    System.out.println("The found package " + matcher.group() + " starting at index " + matcher.start()
                            + " and ending at index " + matcher.end());

                    found= true;

                }
                if (!found){
                    System.out.println("Package ID not found!");
                }
            }
        }
    }

    public static List<Package> readPackagesFromCsv() {
        List<Package> packages = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PACKAGES_FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                List<String> parts = getRecordFromLine(line);
                Package myPackage = Package.fromCsvLine(parts);
                packages.add(myPackage);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return packages;
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
