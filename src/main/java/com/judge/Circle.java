package com.judge;

public class Circle {

    public boolean judgeCircle(String moves) {
        char[] movesArray = moves.toCharArray();

        int x = 0; int y = 0;
        for (char item : movesArray) {
            switch (item) {
                case 'U': y+=1;break;
                case 'D': y-=1;break;
                case 'L': x-=1; break;
                case 'R': x+=1; break;
                default:
                    throw new RuntimeException("input incorrect");
            }
        }
        return x==0 && y ==0;
    }


}
