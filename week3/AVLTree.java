package week3;

import utility.Node;

class AVLTree {
    private Node rootNode;

    //Constructor to set null value to the rootNode
    public AVLTree() {
        rootNode = null;
    }

    // Create insertElement() to insert element to the AVL Tree
    public void insertElement(int element, Object obj) {
        rootNode = insertElement(element, rootNode, obj);
    }

    //Create getHeight() method to get the height of the AVL Tree
    private int getHeight(Node node) {
        return node == null ? -1 : node.height;
    }

    //Create insertElement() method to insert data in the AVL Tree recursively
    private Node insertElement(int element, Node node, Object obj) {
        //Check whether the node is null or not
        if (node == null)
            node = new Node(element, obj);
        else if (element < node.key) {
            node.leftChild = insertElement(element, node.leftChild, obj);
            if (getHeight(node.leftChild) - getHeight(node.rightChild) == 2)
                if (element < node.leftChild.key)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithLeftChild(node);
        } else if (element > node.key) {
            node.rightChild = insertElement(element, node.rightChild, obj);
            if (getHeight(node.rightChild) - getHeight(node.leftChild) == 2)
                if (element > node.rightChild.key)
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithRightChild(node);
        }
        node.height = Math.max(getHeight(node.leftChild), getHeight(node.rightChild)) + 1;
        return node;
    }

    // Create rotateWithLeftChild() method to perform rotation of binary tree node with left child
    private Node rotateWithLeftChild(Node node2) {
        Node node1 = node2.leftChild;
        node2.leftChild = node1.rightChild;
        node1.rightChild = node2;
        node2.height = Math.max(getHeight(node2.leftChild), getHeight(node2.rightChild)) + 1;
        node1.height = Math.max(getHeight(node1.leftChild), node2.height) + 1;
        return node1;
    }

    // Create rotateWithRightChild() method to perform rotation of binary tree node with right child
    private Node rotateWithRightChild(Node node1) {
        Node node2 = node1.rightChild;
        node1.rightChild = node2.leftChild;
        node2.leftChild = node1;
        node1.height = Math.max(getHeight(node1.leftChild), getHeight(node1.rightChild)) + 1;
        node2.height = Math.max(getHeight(node2.rightChild), node1.height) + 1;
        return node2;
    }

    //Create doubleWithLeftChild() method to perform double rotation of binary tree node. This method first rotate the left child with its right child, and after that, node3 with the new left child
    private Node doubleWithLeftChild(Node node3) {
        node3.leftChild = rotateWithRightChild(node3.leftChild);
        return rotateWithLeftChild(node3);
    }

    //Create doubleWithRightChild() method to perform double rotation of binary tree node. This method first rotate the right child with its left child and after that node1 with the new right child
    private Node doubleWithRightChild(Node node1) {
        node1.rightChild = rotateWithLeftChild(node1.rightChild);
        return rotateWithRightChild(node1);
    }

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

    private Node searchElement(Node head, int element) {
        if (head == null || head.key == element) return head;
        if (head.key > element) return searchElement(head.leftChild, element);
        return searchElement(head.rightChild, element);
    }
}