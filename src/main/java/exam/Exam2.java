package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exam2 {

    public static void main(String[] args) {
        System.out.println(new Exam2().solu("2264712", 2));
    }

    public String solu(String input, int count) {
        if (input.length() <= count) {
            return "0";
        }

        char[] ars = input.toCharArray();

        List<Integer> lists = new ArrayList<>();
        for (int index = 0; index < ars.length; index++) {
            lists.add(Integer.parseInt(String.valueOf(ars[index])));
        }

        Collections.sort(lists);

        List<Integer> result = lists.subList(count, lists.size());

        StringBuilder sb = new StringBuilder();
        for (int index = ars.length - 1; index >= 0; index--) {
            if (result.contains(Integer.valueOf(String.valueOf(ars[index])))) {
                result.remove(Integer.valueOf(String.valueOf(ars[index])));
                sb.append(String.valueOf(ars[index]));
            }
        }

        return sb.reverse().toString();
    }


}
