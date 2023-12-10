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

public class FindMinPath {

    public static void main(String[] args) {
        GraphWrapper gw = new GraphWrapper(true);
        GraphNode home = gw.getHome();
        home.priority = 0;

        MinPriorityQueue queue = new MinPriorityQueue(10);
        queue.insert(home);

        GraphNode answer = null;

        while (!queue.isEmpty()) {
            GraphNode curr = queue.pullHighestPriorityElement();

            if (curr.isGoalNode()) {
                answer = curr;
                break;
            } else {

                processNeighbor(curr, curr.getNorth(), curr.getNorthWeight(), "North", queue);
                processNeighbor(curr, curr.getSouth(), curr.getSouthWeight(), "South", queue);
                processNeighbor(curr, curr.getWest(), curr.getWestWeight(), "West", queue);
                processNeighbor(curr, curr.getEast(), curr.getEastWeight(), "East", queue);
            }
        }

        if (answer != null) {
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