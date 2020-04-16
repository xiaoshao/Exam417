package ma;

import java.util.*;

// 632 最小区间， 优先队列，滑动窗口
public class SmallestRange {
    public static void main(String[] args) {

        List<List<Integer>> items = new ArrayList<>();

        List<Integer> item1 = Arrays.asList(new Integer[]{4, 10, 15, 24, 60});
        items.add(item1);

        List<Integer> item2 = Arrays.asList(new Integer[]{0, 9, 12, 20});
        items.add(item2);

        List<Integer> item3 = Arrays.asList(new Integer[]{5,18,22,30});
        items.add(item3);

        int[] result = new SmallestRange().smallestRange(items);

        System.out.println(result[0] + "  " + result[1]);
    }

    public int[] smallestRange(List<List<Integer>> nums) {

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::getValue));
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        for (int index = 0; index < nums.size(); index++) {
            Integer value = nums.get(index).get(0);
            queue.add(new Node(index, 0, value));
            minValue = Math.min(minValue, value);
            maxValue = Math.max(maxValue, value);
        }
        int[] res = new int[]{minValue, maxValue};

        while (!queue.isEmpty()) {
            Node item = queue.poll();

            if (item.getCol() + 1 == nums.get(item.getRow()).size()) {
                break;
            }

            int nextValue = nums.get(item.getRow()).get(item.getCol() + 1);

            queue.add(new Node(item.getRow(), item.getCol() + 1, nextValue));

            maxValue = Math.max(maxValue, nextValue);

            minValue = queue.peek().value;

            if ((maxValue - minValue) < (res[1] - res[0])) {
                res[0] = minValue;
                res[1] = maxValue;
            }
        }

        return res;
    }

    public static class Node {
        int row;
        int col;
        int value;

        public Node(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public int getValue() {
            return value;
        }
    }
}
