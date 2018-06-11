package com.route;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public final class RouteResolver {

    private static final int NO_SUCH_ROUTE = -1;

    private Graph graph;

    private int maxStopsCount = 0;

    private RouteCountMode routeCountMode;

    private Stack<String> routeStack = new Stack<String>();

    private int maxDistance;

    private Node startingNode;

    private Node endingNode;

    private Map<String, Integer> routes;

    public RouteResolver(final Graph graph) {
        this.graph = graph;
    }

    public Map<String, Integer> getRoutes(final String startingNodeName,
            final String endingNodeName, final int maxStopsCount,
            final RouteCountMode routeCountMode, final int maxDistance) {
        startingNode = graph.getNode(startingNodeName);
        endingNode = graph.getNode(endingNodeName);
        this.maxStopsCount = maxStopsCount;
        this.routeCountMode = routeCountMode;
        this.maxDistance = maxDistance;
        this.routes = new HashMap<String, Integer>();

        final Set<Node> destinations = startingNode.getDestinations();

        for (final Node destination: destinations) {
            resolve(destination);
        }

        return Collections.unmodifiableMap(routes);
    }

    public String getDistance(final String route) {
        final int distance = calcDistance(route);

        if (NO_SUCH_ROUTE == distance) {
            return "NO SUCH ROUTE";
        } else {
            return String.valueOf(distance);
        }
    }

    private void resolve(final Node destNode) {
        routeStack.push(destNode.getName());
        final int stopsCnt = routeStack.size();

        if (stopsCnt > maxStopsCount) {
            routeStack.pop(); // backtrack
            return;
        }

        if (destNode.equals(endingNode)) { // found a route
            boolean match = false;

            switch (routeCountMode) {
                case EQUAL_STOP_COUNT:
                    if (maxStopsCount == stopsCnt) {
                        match = true;
                    }
                    break;
                case LESS_OR_EQUAL_STOP_COUNT:
                    if (stopsCnt <= maxStopsCount) {
                        match = true;
                    }
                    break;
                default:
                    throw new RuntimeException("Unspecified rounte count mode");
            }

            if (match) {
                final String route = getCurrentRoute();
                final int distance = calcDistance(route);

                if (distance > maxDistance) {
                    routeStack.pop(); // backtrack
                    return;
                } else {
                    routes.put(route, distance); // save the route
                }
            }
        }

        for (final Node destination: destNode.getDestinations()) {
            resolve(destination);
        }

        routeStack.pop(); // backtrack
    }

    private int calcDistance(final String route) {
        final String[] nodeNames = route.split("-");  //D4,F8, C-D-F

        int ret = 0;
        for (int i = 0; i < nodeNames.length; i++) {
            final String nodeName = nodeNames[i];
            final Node node = graph.getNode(nodeName);
            
            if (i + 1 < nodeNames.length) {
                final Node nextNode = graph.getNode(nodeNames[i + 1]);
                if (null == nextNode) {
                    return NO_SUCH_ROUTE;
                }

                final int distance = node.getDistance(nextNode);
                if (0 == distance) {
                    return NO_SUCH_ROUTE;
                }

                ret += distance;
            }
        }

        return ret;
    }

    private String getCurrentRoute() {
        final StringBuilder routeBuilder = new StringBuilder(
                startingNode.getName() + '-');
        for (int i = 0; i < routeStack.size(); i++) {
            final String nodeName = routeStack.elementAt(i);
            routeBuilder.append(nodeName);

            if (i < routeStack.size() - 1) {
                routeBuilder.append('-');
            }
        }

        final String ret = routeBuilder.toString();

        return ret;
    }

    public static enum RouteCountMode {

        LESS_OR_EQUAL_STOP_COUNT, EQUAL_STOP_COUNT;
    }
}
