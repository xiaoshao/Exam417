package ma;

import java.util.*;

/**
 * leecode 1023
 */
public class ShortestBridge {

    public static void main(String[] args) {
        System.out.println(new ShortestBridge().shortestBridge(new int[][]{{0, 1}, {1, 0}}));
    }

    public int shortestBridge(int[][] A) {

        int R = A.length;
        int C = A[0].length;

        int[][] colors = getComponents(A);

        Queue<Node> queue = new LinkedList();
        Set<Integer> target = new HashSet();

        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                if (colors[r][c] == 1) {
                    queue.add(new Node(r, c, 0));
                } else if (colors[r][c] == 2) {
                    target.add(r * C + c);
                }
            }

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (target.contains(node.r * C + node.c))
                return node.depth - 1;
            for (int nei : neighbours(A, node.r, node.c)) {
                int nr = nei / C, nc = nei % C;
                if (colors[nr][nc] != 1) {
                    queue.add(new Node(nr, nc, node.depth + 1));
                    colors[nr][nc] = 1;
                }
            }
        }

        throw null;
    }

    private int[][] getComponents(int[][] A) {
        int R = A.length;
        int C = A[0].length;
        int t = 0;
        int[][] colors = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (colors[i][j] == 0 && A[i][j] == 1) {
                    Stack<Integer> stack = new Stack<>();

                    stack.push(i * C + j);
                    colors[i][j] = ++t;
                    while (!stack.isEmpty()) {
                        int node = stack.pop();

                        int r = node / C;
                        int c = node % C;

                        for (int nei : neighbours(A, r, c)) {
                            int nr = nei / C;
                            int nc = nei % C;

                            if (A[nr][nc] == 1 && colors[nr][nc] == 0) {
                                colors[nr][nc] = t;
                                stack.push(nr * C + nc);
                            }
                        }
                    }
                }

            }
        }
        return colors;
    }

    private List<Integer> neighbours(int[][] A, int r, int c) {
        int R = A.length, C = A[0].length;
        List<Integer> ans = new ArrayList();
        if (0 <= r - 1) ans.add((r - 1) * R + c);
        if (0 <= c - 1) ans.add(r * R + (c - 1));
        if (r + 1 < R) ans.add((r + 1) * R + c);
        if (c + 1 < C) ans.add(r * R + (c + 1));
        return ans;
    }


    public static class Node {
        int r;
        int c;
        int depth;

        public Node(int row, int col, int dep) {
            this.r = row;
            this.c = col;
            this.depth = dep;
        }
    }
}
