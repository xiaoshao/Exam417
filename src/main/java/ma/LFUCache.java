package ma;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFUCache {

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(0);

//        ["put","put","get","put","get","get","put","get","get","get"]
//[[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
//        System.out.print("null ");
//
//        cache.put(1, 1);
//        System.out.print("null ");
//        cache.put(2, 2);
//        System.out.print("null ");
//        System.out.print(cache.get(1) + " ");
//        cache.put(3, 3);
//        System.out.print("null ");
//        System.out.print(cache.get(2) + " ");
//        System.out.print(cache.get(3) + " ");
//        cache.put(4, 4);
//        System.out.print("null ");
//        System.out.print(cache.get(1) + " ");
//        System.out.print(cache.get(3) + " ");
//        System.out.print(cache.get(4) + " ");
//
////        [null,null,null,1,null,-1,3,null,-1,3,4]
//        cache.put(3, 1);
//        System.out.print("null ");
//        cache.put(2, 1);
//        System.out.print("null ");
//        cache.put(2, 2);
//        System.out.print("null ");
//        cache.put(4, 4);
//        System.out.print("null ");
//
//        System.out.println(cache.get(2));
        cache.put(0, 0);
        System.out.println(cache.get(0));
    }

    private int capacity = 0;
    private PriorityQueue<Node> priorityQueue;
    private Map<Integer, Node> caches;
    private int operateFlag = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.priorityQueue = new PriorityQueue<>();
        this.caches = new HashMap<>();
    }

    public int get(int key) {
        if (this.caches.containsKey(key)) {
            Node value = this.caches.get(key);
            priorityQueue.remove(value);
            value.increase(++this.operateFlag);
            priorityQueue.add(value);
            return value.getValue();
        }
        return -1;
    }

    public void put(int key, int value) {
        int usedTimes = 1;
        if (this.caches.containsKey(key)) {
            Node removedObject = this.caches.get(key);
            usedTimes = removedObject.usedTimes + 1;
            priorityQueue.remove(removedObject);
        } else {
            if (this.capacity == priorityQueue.size()) {
                Node removeNode = priorityQueue.poll();
                if (removeNode != null){
                    caches.remove(removeNode.key);
                }
            }
        }

        Node node = new Node(key, value, usedTimes, ++this.operateFlag);

        priorityQueue.add(node);
        this.caches.put(key, node);
    }

    private static class Node implements Comparable<Node> {
        private int key;
        private int value;
        private int usedTimes;
        private int usedTime;

        public Node(int key, int value, int usedCount, int operateFlag) {
            this.key = key;
            this.value = value;
            this.usedTimes = usedCount;
            this.usedTime = operateFlag;
        }

        public void increase(int operateFlag) {
            this.usedTimes++;
            this.usedTime = operateFlag;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;

            Node node = (Node) o;

            if (getKey() != node.getKey()) return false;
            return getValue() == node.getValue();
        }

        @Override
        public int hashCode() {
            int result = getKey();
            result = 31 * result + getValue();
            return result;
        }

        @Override
        public int compareTo(Node o) {
            if (usedTimes == o.usedTimes) {
                return this.usedTime - o.usedTime > 0 ? 1 : -1;
            }
            return this.usedTimes - o.usedTimes;
        }
    }
}
