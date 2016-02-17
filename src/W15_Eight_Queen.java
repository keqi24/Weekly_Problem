import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

/**
 *  https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=691
 */
class W15_Eight_Queen {

    private static final int N = 8;
    private static final int[] sData = new int[N];
    private static int sIndex = 1;

    static {
        for (int i=0; i<N; i++) {
            sData[i] =  -1;
        }
    }

    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int caze = scanner.nextInt();
        while (caze > 0) {
            caze--;
            int R = scanner.nextInt();
            int C = scanner.nextInt();

            System.out.println("SOLN       COLUMN");
            System.out.println(" #      1 2 3 4 5 6 7 8");
            System.out.println();

            sIndex = 1;
            for (int i=0; i<N; i++) {
                sData[i] =  -1;
            }
            sData[C-1] = R-1;

            findResult(0, R-1, C-1);
            if (caze > 0) {
                System.out.println();
            }
        }
    }

    public static void findResult(int curCol, int preRow, int preCol) {
        if (curCol == N) {
            printResult(sData);
        } else if (curCol == preCol){
            findResult(curCol + 1, preRow, preCol);
        } else {
            for (int i=0; i<N; i++) {
                if (isCanPosition(i, curCol) && !isCollision(i, curCol, preRow, preCol)) {
                    sData[curCol] = i;
                    findResult(curCol + 1, preRow, preCol);
                }
            }
        }
    }

    public static boolean isCanPosition(int row, int col) {
        for (int i=0; i<col; i++) {
            if ( sData[i] >= 0 && ((row == sData[i]) ||((Math.abs(row - sData[i]) == Math.abs(col - i))))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCollision(int row, int col, int preRow, int preCol) {
        if (row == preRow || (Math.abs(row - preRow) == Math.abs(col - preCol))) {
            return true;
        }
        return false;
    }

    public static void printResult(int[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<data.length; i++) {
            sb.append(" ");
            sb.append(data[i] + 1);
        }
        String result = String.format("%2d     %s", sIndex, sb.toString());
        System.out.println(result);
        sIndex++;
    }



    /***************************Util Method********************************/

    private static void sort(int[] array, int size) {
        for (int i=1; i<size; ++i) {
            int tmp = array[i];
            int j;
            for (j=i-1; j>=0 && array[j]<tmp; --j) {
                array[j+1] = array[j];
            }
            array[j+1] = tmp;
        }
    }

    public static void printArray(int[] data) {
        for (int i=0; i<data.length; ++i) {
            System.out.print(data[i] + ", ");
        }
        System.out.println();
    }

    public static int readInt(BufferedReader reader) throws IOException {
        return Integer.valueOf(reader.readLine());
    }

    public static String readString(BufferedReader reader) throws IOException {
        return reader.readLine();
    }

    public static String[] readStringArray(BufferedReader reader) throws IOException {
        return reader.readLine().trim().split(" ");
    }

    public static double readDouble(BufferedReader reader) throws IOException {
        return Double.parseDouble(reader.readLine().trim());
    }

    public static int[] readIntArray(BufferedReader reader, int count) throws IOException {
        int[] result = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; ++i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return result;
    }

    public static void readIntArray(BufferedReader reader, int[] array, int count) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < count; ++i) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    public static void printArray(double[] array, int start, int end) {
        for (int i = start; i < end; ++i) {
            System.out.println(array[i]);
        }
        System.out.println();
    }

}