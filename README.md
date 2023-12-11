# shortestPathJava

## Introduction

`shortestPathJava` is a custom implementation of Dijkstra's algorithm, a well-known algorithm for finding the shortest paths between nodes in a graph. This project is designed to demonstrate the efficient calculation of the shortest path in a graph using a custom implementation of a min-priority queue and a hash map.

## Implementation Details

- **Dijkstra's Algorithm**: At its core, the project utilizes Dijkstra's algorithm, which is famous for its efficiency in finding the shortest path in a weighted graph where the weights of edges are non-negative. This makes it ideal for various applications, such as network routing, geographical mapping, and in scenarios where it's crucial to find the most efficient path.

- **Min-Priority Queue**: The algorithm uses a custom min-priority queue to keep track of the vertices with the smallest distance from the source vertex that have not been processed yet. This queue is crucial for the efficiency of the algorithm as it allows quick access to the next vertex with the minimal total distance from the source.

- **Hash Map**: A hash map is employed to store and quickly retrieve the distance and path information of each vertex from the source. This allows the algorithm to efficiently update and access the shortest path information.

- **Custom Implementation**: Both the min-priority queue and the hash map are custom implemented. This provides a great opportunity to understand the underlying mechanics of these data structures and their role in the efficient execution of Dijkstra’s algorithm.

## Usage

To use `shortestPathJava`, follow these steps:

1. **Clone the Repository**:
