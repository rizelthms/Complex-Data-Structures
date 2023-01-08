package week4and5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MapGraph {
    public static class Node {
        public int x;
        public int y;
        public List<Node> edges = new ArrayList<Node>();
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (o == null || !(o instanceof Node)) {
                return false;
            }
            Node r = (Node)o;
            return this.x == r.x && this.y == r.y;
        }
        @Override
        public String toString() {
            return "(" + x + ";" + y + ")";
        }
        public void addEdge(Node r) {
            if (edges.contains(r)) {
                return;
            }
            edges.add(r);
        }
    }
    private List<Node> nodes = new ArrayList<Node>();
    
    public MapGraph() {
    }

    public List<Node> getNodes() {
        return nodes;
    }
    public Node getNode(int x, int y) {
        return getNode(new Node(x, y));
    }
    public Node getNode(Node node) {
        for (Node n: nodes) {
            if (n.equals(node)) {
                return n;
            }
        }
        return null;
    }

    public Node addNode(Node node) {
        Node added = getNode(node);
        if (added == null) {
            nodes.add(node);
            added = node;
        }
        return added;
    }

    public List<Node> findPath_BFS(Node start, Node destination) {
        Node startNode = getNode(start);
        if (startNode == null) {
            return null;
        }
        // Track nodes we've seen
        List<Node> searchedNodes = new ArrayList<Node>();
        // Track current paths being searched / built
        List<LinkedList<Node>> searchingPaths = new ArrayList<LinkedList<Node>>();
        // Being with single path at start point
        LinkedList<Node> path = new LinkedList<Node>();
        path.add(startNode);
        searchingPaths.add(path);
        while(true) {
            // Build the next path iterations
            List<LinkedList<Node>> iteratedPaths = new ArrayList<LinkedList<Node>>();
            for (LinkedList<Node> p: searchingPaths) {
                // Stop when the last is the destination reached
                Node search = p.getLast();
                if (search.equals(destination)) {
                    return p;
                }
                // Mark node as seen
                searchedNodes.add(search);
                // Expand path with edges, skipping already seen nodes
                for (Node edge: search.edges) {
                    if (searchedNodes.contains(edge)) {
                        continue;
                    }
                    LinkedList<Node> nextPath = new LinkedList<Node>(p);
                    nextPath.add(edge);
                    iteratedPaths.add(nextPath);
                }
            }
            // Destination not found if nothing left to expand
            if (iteratedPaths.isEmpty()) {
                break;
            }
            searchingPaths = iteratedPaths;
        }
        return null;
    }
}
