package com.route;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class Graph {

    private Map<String, Node> nodes = new HashMap<String, Node>();

    public Graph(final String description) {
        
        final String[] pairs = description.trim().split(",");  //CD4, DF8
        
        for (int i = 0; i < pairs.length; i++) {
            final String pair = pairs[i].trim(); // i.e. AB5
            final String srcNodeName = pair.substring(0, 1);
            final String destNodeName = pair.substring(1, 2);

            final Node srcNode = initNode(srcNodeName);
            final Node destNode = initNode(destNodeName);

            final int distance = Integer.parseInt(pair.substring(2));
            srcNode.addDestination(destNode, distance);  //D4,F8, C-D-F 
        }
    }

    private Node initNode(final String nodeName) {
        Node ret = nodes.get(nodeName);
        if (null == ret) {
            ret = new Node();
            ret.setName(nodeName);
            nodes.put(nodeName, ret);
        }

        return ret;
    }

    Node getNode(final String nodeName) {
        return nodes.get(nodeName);
    }

    public int getNodesCount() {
        return nodes.size();
    }
}



final class Node {

    private String name;

    private Map<Node, Integer> nodes = new HashMap<Node, Integer>();

    public int getDistance(final Node node) {
        final Integer ret = nodes.get(node);

        if (null == ret) {
            return 0;
        }

        return ret;
    }

    public Set<Node> getDestinations() {
        return nodes.keySet();
    }

    public void addDestination(final Node node, final int distance) {
        nodes.put(node, distance);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node[" + "name=" + name + ']';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if ((this.name == null) ? (other.name != null)
                : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
}
