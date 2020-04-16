package ma;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class RestoreIpAddress {

    public static void main(String[] args) {
        System.out.println(new RestoreIpAddress().restoreIpAddresses("0000"));
    }

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();

        List<String> res = new ArrayList<>();
        if (len < 4 || len > 12) {
            return res;
        }

        Deque<String> path = new ArrayDeque<>(4);
        List<String> result = new ArrayList<>();

        dfs(s, 0, 0, path, result);

        return result;

    }

    public void dfs(String str, int index, int start, Deque<String> path, List<String> result) {
        if (index == 4) {
            if (start == str.length()) {
                result.add(String.join(".", path));
            }
            return;
        }


        for (int position = 1; position <= 3; position++) {
            int res = judgeIsIp(str, start, start + position, index);
            if (res != -1) {
                path.addLast(res + "");
                dfs(str, index + 1, start + position, path, result);
                path.removeLast();
            }
        }
    }

    public int judgeIsIp(String s, int start, int end, int index) {
        int len = s.length();

        if (end > len) {
            return -1;
        }

        int last = len - end;
        if (last > (4 - index - 1) * 3 || last < (4 - index - 1)) {
            return -1;
        }


        String item = s.substring(start, end);

        if(item.length() > 1 && item.startsWith("0")){
            return -1;
        }

        int res = Integer.parseInt(item);

        if (res <= 255 && res >= 0) {
            return res;
        } else {
            return -1;
        }
    }

}
