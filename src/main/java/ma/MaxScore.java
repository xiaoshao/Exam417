package ma;

import java.util.HashMap;
import java.util.Map;

//0-1 背包问题
public class MaxScore {

    public static void main(String[] args) {
        int a = 'a';
        System.out.println(a);
    }
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        Map<String, Integer> scoreMap = new HashMap<>();
        int[] wordNum = new int[26];

        for (char letter : letters) {
            wordNum[letter - 97] = wordNum[letter - 97] + 1;
        }

        for (String word : words) {
            scoreMap.put(word, calculateScore(word, score));
        }


        return DFS(0, words, wordNum, scoreMap, 0);
    }

    public int DFS(int index, String[] words, int[] wordNum, Map<String, Integer> scoreMap, int sum) {
        if (words.length == index) {
            return sum;
        }

        String word = words[index];
        int[] wordNumTemp = wordNum.clone();

        for (char c : word.toCharArray()) {
            if (wordNumTemp[c - 97] > 0) {
                wordNumTemp[c - 97] = wordNumTemp[c - 97] - 1;
            } else {
                return DFS(index + 1, words, wordNum, scoreMap, sum);
            }
        }

        return Integer.max(DFS(index + 1, words, wordNum, scoreMap, sum),
                DFS(index + 1, words, wordNumTemp, scoreMap, sum + scoreMap.get(word)));

    }

    private int calculateScore(String word, int[] score) {
        int sum = 0;
        for (char c : word.toCharArray()) {
            sum += score[c - 97];
        }
        return sum;
    }
}
