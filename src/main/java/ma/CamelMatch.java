package ma;

import java.util.ArrayList;
import java.util.List;

/**
 * leecode 934
 */
public class CamelMatch {

    public static void main(String[] args) {
        System.out.println(new CamelMatch().camelMatch(new String[]{"FaceBBook"}, "FB"));
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>(queries.length);
        for (String query : queries) {
            String other = getOther(query, pattern);
            if (other.equals("")) {
                res.add(false);
            } else {
                res.add(other.toLowerCase().equals(other));
            }
        }
        return res;
    }

    private static String getOther(String query, String pattern) {
        int index = 0;
        StringBuilder sb = new StringBuilder("a"); // 避免两个串相等时返回""
        for (int i = 0; i < pattern.length(); i++) {
            char t = pattern.charAt(i);
            int index2 = query.indexOf(t, index);
            if (index2 < 0) {
                return "";
            }
            sb.append(query, index, index2);
            index = index2 + 1;
        }
        sb.append(query.substring(index));
        return sb.toString();
    }

}
