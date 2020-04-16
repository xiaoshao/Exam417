package ma;

import java.util.ArrayList;
import java.util.List;

public class CamelMatch2 {

    public static void main(String[] args) {
        System.out.println(new CamelMatch2().camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"}, "FB"));
    }

    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> result = new ArrayList<>();
        for (String query : queries) {
            result.add(match(query, pattern));
        }

        return result;
    }

    private Boolean match(String query, String pattern) {

        int j = 0;
        int k = 0;

        for (j = 0; j < query.length(); j++) {
            if (isUpperCase(query.charAt(j))) {
                if (k < pattern.length() && pattern.charAt(k) == query.charAt(j)) {
                    k++;
                } else {
                    return false;
                }
            } else {
                if (k < pattern.length() && query.charAt(j) == pattern.charAt(k)) {
                    k++;
                }
            }
        }

        return k == pattern.length() && query.length() == j;
    }

    private boolean isUpperCase(char item) {
        return item >= 'A' && item <= 'Z';
    }
}
