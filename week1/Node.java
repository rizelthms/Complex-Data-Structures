package week1;

import utility.Client;
import utility.Package;

public class Node<T> {
    private T data;
    private Node nextNode;

    public Node(T data){
        this.data=data;
    }

    public T getData(){
        return data;
    }

    public int getDataID(){
        if(data.getClass()== Package.class){
            return ((Package) data).id;
        }else if(data.getClass()== Client.class){
            return ((Client) data).id;
        }
        return -1;
    }

    public void setData(T data){
        this.data=data;
    }

    public Node getNextNode(){
        return nextNode;
    }

    public void setNextNode(Node nextNode){
        this.nextNode=nextNode;
    }
}
