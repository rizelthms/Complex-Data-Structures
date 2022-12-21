package week3;

import utility.Client;
import utility.Package;
import week1.LinkedList;
import week1.Week1;

import java.util.List;
import java.util.Scanner;

public class Week3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVLTree clientTree = new AVLTree();
        AVLTree packageTree = new AVLTree();

        LinkedList clients = Week1.readClientsFromCsv();
        LinkedList packages = Week1.readPackagesFromCsv();
        List<Client> clientsArray = clients.array();
        List<Package> packagesArray = packages.array();

        for (Client client : clientsArray) {
            clientTree.insertElement(client.id, client);
        }
        for (Package packageElem : packagesArray) {
            packageTree.insertElement(packageElem.id, packageElem);
        }
        int userOption = 0;
        while (userOption != 3) {
            System.out.println("\nMenu:\n");
            System.out.println("1. Search client AVLTree by ID");
            System.out.println("2. Search package AVLTree by ID");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            userOption = sc.nextInt();
            switch (userOption) {
                case 1:
                    System.out.println("Enter Client ID to search");
                    clientTree.searchElement(sc.nextInt());
                    break;
                case 2:
                    System.out.println("Enter Package ID to search");
                    packageTree.searchElement(sc.nextInt());
                    break;
                default:
                    break;
            }
        }
    }
}
