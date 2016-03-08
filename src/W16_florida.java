import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *  https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=410
 */
class W16_florida {

    private static final int N = 1000;
    private static final char[][] sData = new char[N][N];
    private static final byte[][] sVisted = new byte[N][N];

    private static void resetData() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                sData[i][j] = 'L';
            }
        }
    }

    private static void resetVisited() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                sVisted[i][j] = 0;
            }
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int caze = readInt(reader);
        reader.readLine();
        while (caze > 0) {
            caze--;
            resetData();
            String line;
            int row = 0;
            while (true) {
                line = readString(reader);
                if (line==null || line.isEmpty()) {
                    break;
                }
                char firstC = line.charAt(0);
                if (firstC == 'L' || firstC == 'W') {
                    setData(row++, line);
                } else {
                    resetVisited();
                    String[] array = line.split(" ");
                    int first = Integer.valueOf(array[0]) - 1;
                    int second = Integer.valueOf(array[1]) - 1;
                    int result = calResult(first, second);
                    System.out.println(result);
                }
            }
            if (caze > 0) {
                System.out.println();
            }
        }
    }


    private static void setData(int row, String line) {
        char[] array = line.toCharArray();
        int size = array.length;
        System.arraycopy(array, 0, sData[row], 0, size);
    }

    private static int calResult(int row, int col) {
        int result = 0;
        List<Cell> cellQ = new LinkedList<>();
        Cell c = new Cell(row, col);
        cellQ.add(c);
        result++;
        sVisted[row][col] = 1;

        while (!cellQ.isEmpty()) {
            Cell current = cellQ.remove(0);
            result += check(cellQ, current.row-1, current.col-1) ? 1 : 0;
            result += check(cellQ, current.row-1, current.col) ? 1 : 0;
            result += check(cellQ, current.row-1, current.col+1) ? 1 : 0;
            result += check(cellQ, current.row, current.col-1) ? 1 : 0;
            result += check(cellQ, current.row, current.col+1) ? 1 : 0;
            result += check(cellQ, current.row+1, current.col-1) ? 1 : 0;
            result += check(cellQ, current.row+1, current.col) ? 1 : 0;
            result += check(cellQ, current.row+1, current.col+1) ? 1 : 0;
        }
        return result;
    }


    public static class Cell {
        public int row;
        public int col;

        public Cell(int r, int c) {
            row = r;
            col = c;
        }
    }

    public static boolean isWater(int row, int col) {
        if (row < 0 || col < 0) {
            return false;
        }
        return sData[row][col] == 'W';
    }

    public static boolean hasVisited(int row, int col) {
        if (row < 0 || col < 0) {
            return true;
        }
        return sVisted[row][col] == 1;
    }

    public static boolean check(List<Cell> cellQ, int row, int col) {
        if (isWater(row, col) && !hasVisited(row, col)) {
            cellQ.add(new Cell(row, col));
            sVisted[row][col] = 1;
            return true;
        }
        return false;
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