package test;

import utility.Client;
import utility.DataSources;
import utility.Inputs;
import utility.Package;
import week3.AVLTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;
import org.testng.annotations.Test;


public class AVLTreeTest {
    public AVLTree loadTree(List<Object> items, DataSources source) {
        AVLTree tree = new AVLTree();

        switch (source) {
            case TESTCLIENTS:
            case CLIENTS:
                for (Object obj : items)
                    tree.insertElement(((Client) obj).id, obj);
                break;
            case TESTPACKAGES:
            case PACKAGES:
                for (Object obj : items)
                    tree.insertElement(((Package) obj).id, obj);
                break;
        }

        return tree;
    }

    @Test
    public void testBalancingSmallTrees() {    
        List<Object> clients = Inputs.readCSV(DataSources.TESTCLIENTS);
        List<Object> packages = Inputs.readCSV(DataSources.TESTPACKAGES);
        
        AVLTree clientTree = loadTree(clients, DataSources.TESTCLIENTS);
        AVLTree packageTree = loadTree(packages, DataSources.TESTPACKAGES);

        clientTree.print();
        assertTrue(clientTree.isBalanced(clientTree.getRootNode()));
        assertTrue(packageTree.isBalanced(packageTree.getRootNode()));
    }

    @Test
    public void testBalancingMainTrees() {
        List<Object> clients = Inputs.readCSV(DataSources.CLIENTS);
        List<Object> packages = Inputs.readCSV(DataSources.PACKAGES);
        
        AVLTree clientTree = loadTree(clients, DataSources.CLIENTS);
        AVLTree packageTree = loadTree(packages, DataSources.PACKAGES);

        clientTree.print();
        assertTrue(clientTree.isBalanced(clientTree.getRootNode()));
        assertTrue(packageTree.isBalanced(packageTree.getRootNode()));
    }

    @Test
    public void testLeftRotation() {
        AVLTree tree = new AVLTree();
        tree.insertElement(1, 1);
        tree.insertElement(2, 2);
        tree.insertElement(3, 3);

        ArrayList<Integer> values = new ArrayList<Integer>();

        List<Integer> expected = Arrays.asList(2, 1, -1, -1, 3, -1, -1);
        List<Integer> traversal = tree.preorder(tree.getRootNode(), values);

        assertTrue(tree.isBalanced(tree.getRootNode()));
        assertTrue(expected.equals(traversal));
    }

    @Test
    public void testRightLeftRotation() {
        AVLTree tree = new AVLTree();
        tree.insertElement(1, 1);
        tree.insertElement(3, 3);
        tree.insertElement(2, 2);

        ArrayList<Integer> values = new ArrayList<Integer>();

        List<Integer> expected = Arrays.asList(2, 1, -1, -1, 3, -1, -1);
        List<Integer> traversal = tree.preorder(tree.getRootNode(), values);

        assertTrue(tree.isBalanced(tree.getRootNode()));
        assertTrue(expected.equals(traversal));
    }

    @Test
    public void testRightRotation() {
        AVLTree tree = new AVLTree();
        tree.insertElement(3, 3);
        tree.insertElement(2, 2);
        tree.insertElement(1, 1);

        ArrayList<Integer> values = new ArrayList<Integer>();

        List<Integer> expected = Arrays.asList(2, 1, -1, -1, 3, -1, -1);
        List<Integer> traversal = tree.preorder(tree.getRootNode(), values);

        assertTrue(tree.isBalanced(tree.getRootNode()));
        assertTrue(expected.equals(traversal));
    }

    @Test
    public void testLeftRightRotation() {
        AVLTree tree = new AVLTree();
        tree.insertElement(3, 3);
        tree.insertElement(1, 1);
        tree.insertElement(2, 2);

        ArrayList<Integer> values = new ArrayList<Integer>();

        List<Integer> expected = Arrays.asList(2, 1, -1, -1, 3, -1, -1);
        List<Integer> traversal = tree.preorder(tree.getRootNode(), values);

        assertTrue(tree.isBalanced(tree.getRootNode()));
        assertTrue(expected.equals(traversal));
    }
}
