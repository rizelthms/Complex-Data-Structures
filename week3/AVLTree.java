package week3;

import utility.Node;

import java.util.ArrayList;

public class AVLTree {
    private Node rootNode;

    //Default constructor to set null value to the rootNode
    public AVLTree() {
        rootNode = null;
    }

    //Create insertElement() to insert element to the AVL Tree
    public void insertElement(int element, Object obj) {
        rootNode = insertElement(element, rootNode, obj);
    }

    //Getter for the root node of the tree
    public Node getRootNode() {
        return rootNode;
    }

    //Create getHeight() method to get the height of the AVL Tree
    private int getHeight(Node node) {
        return node == null ? -1 : node.height;
    }

    //Return the balance at the current node
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.leftChild) - getHeight(node.rightChild);
    }

    //Print using preorder traversal
    public void print() {
        ArrayList<Integer> items = new ArrayList<Integer>();
        System.out.println(preorder(rootNode, items));
    }

    //Preorder traversal as array
    public ArrayList<Integer> preorder(Node node, ArrayList<Integer> arr) {
        if (node != null) {
            arr.add(node.key);
            preorder(node.leftChild, arr);
            preorder(node.rightChild, arr);
        } else {
            arr.add(-1);
        }

        return arr;
    }

    //Check if tree is balanced below a specific given node, "current"
    public boolean isBalanced(Node current) {
        boolean isRightBalanced = true;
        boolean isLeftBalanced = true;

        int lheight = 0;
        int rheight = 0;

        if (current.rightChild != null) {
            isRightBalanced = isBalanced(current.rightChild);
            rheight = getHeight(current.rightChild);
        }

        if (current.leftChild != null) {
            isLeftBalanced = isBalanced(current.leftChild);
            lheight = getHeight(current.leftChild);
        }

        boolean isRootBalanced = Math.abs(lheight - rheight) <= 1;
        // System.out.println("----- Node: " + current.key + " -----");
        // System.out.println("Rheight: " + rheight);
        // System.out.println("Lheight: " + lheight);
        // System.out.println(Math.abs(lheight - rheight));

        if (isLeftBalanced && isRightBalanced && isRootBalanced) {
            return true;
        }

        return false;
    }

    //Create insertElement() method to insert data in the AVL Tree recursively
    private Node insertElement(int element, Node node, Object obj) {
        if (node == null)
            node = new Node(element, obj);
        else if (element < node.key) {
            node.leftChild = insertElement(element, node.leftChild, obj);

            if (Math.abs(getBalance(node)) > 1)
                if (element < node.leftChild.key)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithLeftChild(node);
        } else if (element > node.key) {
            node.rightChild = insertElement(element, node.rightChild, obj);

            if (Math.abs(getBalance(node)) > 1)
                if (element > node.rightChild.key)
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithRightChild(node);
        }

        node.height = Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        return node;
    }

    //Create rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private Node rotateWithLeftChild(Node node2) {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.height = Math.max(getHeight(node2.leftChild), getHeight(node2.rightChild)) + 1;
        node1.height = Math.max(getHeight(node1.leftChild), node2.height) + 1;
        return node1;
    }

    //Create rotateWithRightChild() method to perform rotation of binary tree node with right child
    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.height = Math.max(getHeight(node1.leftChild), getHeight(node1.rightChild)) + 1;
        node2.height = Math.max(getHeight(node2.rightChild), node1.height) + 1;
        return node2;
    }

    /*
     Create doubleWithLeftChild() method to perform double rotation of binary tree node.
     This method first rotate the left child with its right child, and after that, node3 with the new left child
     */
    private Node doubleWithLeftChild(Node node) {
        node.leftChild = rotateWithRightChild(node.leftChild);
        return rotateWithLeftChild(node);
    }

    /*
     Create doubleWithRightChild() method to perform double rotation of binary tree
     node. This method first rotate the right child with its left child and after that node1 with the new right child.
     */
    private Node doubleWithRightChild(Node node) {
        node.rightChild = rotateWithLeftChild(node.rightChild);
        return rotateWithRightChild(node);
    }

    //Search for provided element in the tree
    public void searchElement(int element) {
        long startingTime = System.nanoTime();
        Node node = searchElement(rootNode, element);
        long endingTime = System.nanoTime();
        if (node == null) {
            System.out.println("Element not found");
        } else {
            System.out.println(node);
        }
        System.out.println("Time taken to search: " + (endingTime - startingTime) + "ns.");
    }

    //Search for the provided element in a specific tree from the given root
    private Node searchElement(Node head, int element) {
        if (head == null || head.key == element) return head;
        if (head.key > element) return searchElement(head.leftChild, element);
        return searchElement(head.rightChild, element);
    }
}