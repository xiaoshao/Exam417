package exam;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Exam1 {
    public static void main(String[] args) {
        System.out.println(new Exam1().count(2, new String[] {"2A"}));
    }


    public int count(int rows, String[] sells) {
        int num = rows * 2;

        HashMap<Integer, Set<String>> sold = new HashMap<>();

        for (String sell : sells) {
            String[] item = sell.split("");
            Integer row = Integer.valueOf(item[0]);

            if (sold.containsKey(row)) {
                sold.get(row).add(item[1]);
            } else {
                HashSet set = new HashSet();
                set.add(item[1]);
                sold.put(row, set);
            }
        }

        int removedCount = 0;
        for (Set<String> strings : sold.values()) {
            if(strings.contains("D") || strings.contains("E")) {
                removedCount ++;
            }

            if(strings.contains("B")) {
                removedCount ++;
            }else if(strings.contains("A") && strings.contains("C")) {
                removedCount ++;
            }
        }

        return num - removedCount;
    }
}
