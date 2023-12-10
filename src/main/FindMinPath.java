package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FindMinPath {
    // TODO implement custom linkedList and custom list to remove
    // java.util.LinkedList and java.util.Lists

    public static void main(String[] args) {
        GraphWrapper gw = new GraphWrapper(true);
        GraphNode home = gw.getHome();
        home.priority = 0;

        MinPriorityQueue queue = new MinPriorityQueue(10); // Adjust size as needed
        queue.insert(home);

        GraphNode answer = null;

        while (!queue.isEmpty()) {
            GraphNode curr = queue.pullHighestPriorityElement();

            if (curr.isGoalNode()) {
                answer = curr;
                break;
            } else {
                // Check and process all possible directions
                processNeighbor(curr, curr.getNorth(), curr.getNorthWeight(), "North", queue);
                processNeighbor(curr, curr.getSouth(), curr.getSouthWeight(), "South", queue);
                processNeighbor(curr, curr.getWest(), curr.getWestWeight(), "West", queue);
                processNeighbor(curr, curr.getEast(), curr.getEastWeight(), "East", queue);
            }
        }

        if (answer != null) {
            List<String> directions = new LinkedList<>();
            while (answer.previousNode != null) {
                directions.add(0, answer.previousDirection); // Add to start of list
                answer = answer.previousNode;
            }

            // Write directions to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("answer.txt"))) {
                for (String direction : directions) {
                    writer.write(direction);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No path found from start to goal.");
        }
    }

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