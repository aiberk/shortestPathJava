/**
 * Abraham Iberkleid
 * aiberkleid@brandeis.edu
 * December 9, 2023
 * PA3
 * Bugs: There are no known bugs. 
 */
package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implements Dijkstra's algorithm to find the shortest path in a graph and
 * writes the path to a file.
 */
public class FindMinPath {

    public static void main(String[] args) {
        GraphWrapper gw = new GraphWrapper(true);
        GraphNode home = gw.getHome();
        MinPriorityQueue queue = initializeQueue(home);

        GraphNode answer = processGraph(queue);

        if (answer != null) {
            writeDirectionsToFile(answer);
        } else {
            System.out.println("No path found from start to goal.");
        }
    }

    /**
     * Initializes the priority queue with the home node.
     * Because of the nature of the program using a MinHeap with Dijkstra's
     * algorithm, the directions are
     * acquired in reverse order. A linked list is used to store the directions
     * and then the directions are written to a file in the correct order.
     *
     * @param home The starting node of the graph.
     * @return Initialized MinPriorityQueue with the home node.
     */
    private static MinPriorityQueue initializeQueue(GraphNode home) {
        home.priority = 0;
        MinPriorityQueue queue = new MinPriorityQueue(10);
        queue.insert(home);
        return queue;
    }

    /**
     * Processes the graph to find the shortest path to the goal node using
     * Dijkstra's algorithm.
     *
     * @param queue The priority queue used for processing the graph.
     * @return The goal node if found, otherwise null.
     */
    private static GraphNode processGraph(MinPriorityQueue queue) {
        GraphNode answer = null;
        while (!queue.isEmpty()) {
            GraphNode curr = queue.pullHighestPriorityElement();
            if (curr.isGoalNode()) {
                answer = curr;
                break;
            } else {
                processAllNeighbors(curr, queue);
            }
        }
        return answer;
    }

    /**
     * Processes all possible neighbors (North, South, West, East) of the current
     * node.
     *
     * @param curr  The current node being processed.
     * @param queue The priority queue used for processing the graph.
     */
    private static void processAllNeighbors(GraphNode curr, MinPriorityQueue queue) {
        processNeighbor(curr, curr.getNorth(), curr.getNorthWeight(), "North", queue);
        processNeighbor(curr, curr.getSouth(), curr.getSouthWeight(), "South", queue);
        processNeighbor(curr, curr.getWest(), curr.getWestWeight(), "West", queue);
        processNeighbor(curr, curr.getEast(), curr.getEastWeight(), "East", queue);
    }

    /**
     * Writes the path directions to a file named "answer.txt".
     *
     * @param answer The goal node from which the path is traced back to the start
     *               node.
     */
    private static void writeDirectionsToFile(GraphNode answer) {
        CustomLinkedList directions = new CustomLinkedList();
        while (answer.previousNode != null) {
            directions.addFirst(answer.previousDirection);
            answer = answer.previousNode;
        }
        String[] directionsArray = directions.toArray();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("answer.txt"))) {
            for (String direction : directionsArray) {
                writer.write(direction);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Processes a neighbor node during the path finding process. Updates the
     * neighbor's
     * priority and previous node/direction if necessary.
     *
     * @param current   The current node being processed.
     * @param neighbor  The neighbor node to be processed.
     * @param weight    The weight of the edge to the neighbor.
     * @param direction The direction from the current node to the neighbor.
     * @param queue     The priority queue used for path finding.
     */
    private static void processNeighbor(GraphNode current, GraphNode neighbor, int weight, String direction,
            MinPriorityQueue queue) {
        if (neighbor != null) {
            int newPriority = current.priority + weight;
            if (!queue.indexMap.hasKey(neighbor)) {
                neighbor.priority = newPriority;
                neighbor.previousNode = current;
                neighbor.previousDirection = direction;
                queue.insert(neighbor);
            } else if (newPriority < neighbor.priority) {
                neighbor.priority = newPriority;
                neighbor.previousNode = current;
                neighbor.previousDirection = direction;
                queue.rebalance(neighbor);
            }
        }
    }

}