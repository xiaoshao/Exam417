package ma;

import java.util.*;

public class IslandPerimeter {
    public static void main(String[] args) {
        System.out.println(new IslandPerimeter().islandPerimeter(new int[][]{{0,1}}));
    }

    public int islandPerimeter(int[][] grid) {
        Node firstNode = null;

        firstLoop:
        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    firstNode = new Node(i, j);
                    break firstLoop;
                }

            }
        }

        if (firstNode == null) {
            return 0;
        }

        Stack<Node> stack = new Stack<>();
        stack.add(firstNode);
        Set<Node> visited = new HashSet<>();
        visited.add(firstNode);
        int length = 0;
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            List<Node> neighbours = node.getNeighbours(visited, grid);
            length += node.getLength(grid);
            for (Node neighbour : neighbours) {
                stack.add(neighbour);
                visited.add(neighbour);
            }
        }

        return length;
    }

    public static class Node {
        int x;
        int y;

        int[][] neis = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }


        public List<Node> getNeighbours(Set<Node> visited, int[][] grid) {
            int maxR = grid.length;
            int maxC = grid[0].length;
            List<Node> neighbour = new ArrayList<>();
            for (int[] nei : neis) {
                int row = this.x + nei[0];
                int col = this.y + nei[1];

                if (row >= 0 && row < maxR && col >= 0 && col < maxC) {
                    Node o = new Node(row, col);
                    if(grid[row][col] == 1 && !visited.contains(o)){
                        neighbour.add(o);
                    }
                }
            }
            return neighbour;
        }

        public int getLength(int[][] grid) {
            int maxR = grid.length;
            int maxC = grid[0].length;

            int length = 4;

            for (int[] nei : neis) {
                int row = this.x + nei[0];
                int col = this.y + nei[1];
                if (row >= 0 && row < maxR && col >= 0 && col < maxC) {
                    if (grid[row][col] == 1) {
                        length = length - 1;
                    }
                }
            }
            return length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (x != node.x) return false;
            return y == node.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
