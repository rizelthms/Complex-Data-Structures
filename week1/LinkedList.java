package week1;

import java.util.ArrayList;
import java.util.List;

public class LinkedList {
    private Node head;
    private Node tail;

    public <T> void insert(T data){
        Node newNode = new Node(data);

        if(head==null){
            head = newNode;
        }else{
            Node currentNode = head;
            while(currentNode.getNextNode()!=null){
                currentNode = currentNode.getNextNode();
            }
            currentNode.setNextNode(newNode);
        }
        tail = newNode;
    }

    public <T> void insertHead(T data){
        Node newNode = new Node(data);
        newNode.setNextNode(head);
        head = newNode;

        if(head.getNextNode()==null){
            tail=head;
        }
    }

    public <T> void insertAt(int index, T data){
        Node nodeToBeInserted = new Node(data);
        Node node = head;
        for(int i = 0; i< index -1; i++){
            node = node.getNextNode();
        }
        nodeToBeInserted.setNextNode(node.getNextNode());
        node.setNextNode(nodeToBeInserted);

        if(nodeToBeInserted.getNextNode()==null){
            tail=nodeToBeInserted;
        }
    }

    public void deleteNodeAt(int index){
        Node node = head;
        for(int i = 0; i< index -1; i++){
            node = node.getNextNode();
        }

        node.setNextNode(node.getNextNode().getNextNode());
        if(node.getNextNode()==null){
            tail = node;
        }
    }

    public <T> List<T> array(){
        if(head != null){
            List<T> temp = new ArrayList<>();
            Node currentNode = head;
            while(currentNode.getNextNode() != null){
                temp.add((T)currentNode.getData());
                //System.out.println(currentNode.getData());
                currentNode = currentNode.getNextNode();
            }
            temp.add((T)currentNode.getData());
            //System.out.println(currentNode.getData());

            return temp;
        }
        return null;
    }

    //Sort in ascending by ID
    public <T> void sortByID() {

        // Node current will point to head
        Node current = head, index = null;

        T temp;

        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.getNextNode();

                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them
                    if (current.getDataID() > index.getDataID()) {
                        temp = (T) current.getData();
                        current.setData(index.getData());
                        index.setData(temp);
                    }

                    index = index.getNextNode();
                }
                current = current.getNextNode();
            }
        }
    }

    //Iterative linear searching algorithm
    public <T> T iterativeLinearSearch(int id){
        Node current = head;
        while (current != null) {
            if (current.getDataID() == id)
                return (T) current.getData();
            current = current.getNextNode();
        }
        //data not found
        return null;
    }

    //Recursive linear searching algorithm
    public <T> T linearSearch(int id)
    {
        // Base case
        if (head == null)
            return null;

        //Check current node
        if (head.getDataID() == id)
            return (T) head.getData();

        //Search in rest of the list
        return linearSearch(head.getNextNode(), id);
    }

    //Recursive linear searching algorithm
    public <T> T linearSearch(Node head, int id)
    {
        // Base case
        if (head == null)
            return null;

        //Check current node
        if (head.getDataID() == id)
            return (T) head.getData();

        //Search in rest of the list
        return linearSearch(head.getNextNode(), id);
    }

    //Finds and returns the middle node
    static Node middleNode(Node start, Node last) {
        if (start == null) {
            return null;
        }

        Node slow = start;
        Node fast = start.getNextNode();

        while (fast != last) {
            fast = fast.getNextNode();
            if (fast != last) {
                slow = slow.getNextNode();
                fast = fast.getNextNode();
            }
        }
        return slow;
    }

    //Binary search algorithm
    public <T> T binarySearch(int id) {
        Node start = head;
        Node last = null;

        do {
            // Find the middle
            Node mid = middleNode(start, last);

            if (mid == null) {
                return null;
            }

            if (mid.getDataID() == id) {
                // If data is present at middle
                return (T) mid.getData();
            }

            else if (mid.getDataID() > id) {
                // If data is less than mid
                last = mid;
            } else {
                // If the data is more than mid
                start = mid.getNextNode();
            }
        } while (last == null || last != start);

        //data not found
        return null;
    }

}
