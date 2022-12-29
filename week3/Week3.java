package week3;

import utility.Client;
import utility.Package;
import utility.Inputs;
import utility.DataSources;

import java.util.List;
import java.util.Scanner;

public class Week3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVLTree clientTree = new AVLTree();
        AVLTree packageTree = new AVLTree();

        List<Object> clients = Inputs.readCSV(DataSources.CLIENTS);
        List<Object> packages = Inputs.readCSV(DataSources.PACKAGES);

        for (Object obj : clients) {
            clientTree.insertElement(((Client) obj).id, obj);
        }
        for (Object obj : packages) {
            packageTree.insertElement(((Package) obj).id, obj);
        }

        int userOption = -1;
        while (userOption != 0) {
            System.out.println("\nMenu:\n");
            System.out.println("1. Search client AVLTree by ID");
            System.out.println("2. Search package AVLTree by ID");
            System.out.println("3. Print client AVLTree");
            System.out.println("4. Print package AVLTree");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            userOption = sc.nextInt();
            switch (userOption) {
                case 1 -> {
                    System.out.println("Enter Client ID to search");
                    clientTree.searchElement(sc.nextInt());
                }
                case 2 -> {
                    System.out.println("Enter Package ID to search");
                    packageTree.searchElement(sc.nextInt());
                }
                case 3 -> {
                    System.out.println("----- Client Tree ----- \n");
                    clientTree.print();
                    System.out.println("\n-----------------------");
                }
                case 4 -> {
                    System.out.println("----- Package Tree ----- \n");
                    clientTree.print();
                    System.out.println("\n-----------------------");
                }
                default -> {
                }
            }
        }

        sc.close();
    }
}
