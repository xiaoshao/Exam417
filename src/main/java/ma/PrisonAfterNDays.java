package ma;

public class PrisonAfterNDays {
    public static void main(String[] args) {
        int[] result = new PrisonAfterNDays().prisonAfterNDays(new int[]{0, 1, 0, 1, 1, 0, 0, 1}, 1);

        for (int item : result) {
            System.out.print(" " + item);
        }
    }

    public int[] prisonAfterNDays(int[] cells, int N) {
        int[] current = cells.clone();
        int[] result = new int[cells.length];
        result[0] = 0;
        result[cells.length - 1] = 0;

        for(int i = 0; i < N; i ++){
            for (int index = 1; index < cells.length - 1; index++) {
                if(current[index - 1] == current[index + 1]) {
                    result[index] = 1;
                }else {
                    result[index] = 0;
                }
            }
            current = result.clone();
        }

        return result;
    }
}
