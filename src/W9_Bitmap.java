import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=119
 * just solve it using recursion
 */
class W9_Bitmap {


    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        line = readString(reader);
        while (!line.equals("#")) {

            String[] infoArray = line.split(" +");
            String format = infoArray[0].trim();
            int rows = Integer.valueOf(infoArray[1].trim());
            int columns = Integer.valueOf(infoArray[2].trim());

            line = readString(reader).trim();
            StringBuilder dataStr = new StringBuilder();
            while (!line.contains(" ") && !line.equals("#")) {
                dataStr.append(line);
                line = readString(reader).trim();
            }

            if (format.equals("B")) {
                convertFromB2D(dataStr.toString(), rows, columns);
            } else if (format.equals("D")) {
                convertFromD2B(dataStr.toString(), rows, columns);
            }
        }
    }

    private static void convertFromD2B(String dataStr, int rows, int columns) {
//        System.out.println("B " + rows + " " +columns);
        System.out.println(String.format("B%4d%4d", rows, columns));
        int data[][] = new int[rows][columns];

        int index = 0;

        buildB(dataStr, index, data, 0, rows, 0, columns);

        StringBuilder result = new StringBuilder();
        for (int i=0; i<rows; ++i) {
            for (int j=0; j<columns; ++j) {
                result.append(data[i][j]);
            }
        }

        printResult(result);
    }

    private static int buildB(String dataStr, int index, int[][] data, int startR, int endR, int startC, int endC) {

//        System.out.println("index:" + index + "["+ startR + ", " + endR + ", " + startC + ", " + endC + "]");
//        System.out.println(dataStr.substring(index, dataStr.length()));
        if (startR == endR || startC == endC) {
            return index;
        }

        char c = dataStr.charAt(index);
        index++;
        if (c == 'D') {
            int[][] dividedData = divideData(startR, endR, startC, endC);
            for (int i=0; i<4; ++i) {
                index = buildB(dataStr, index, data, dividedData[i][0], dividedData[i][1], dividedData[i][2], dividedData[i][3]);
            }
        } else {
            int value = c == '1' ? 1 : 0;
            for (int i=startR; i<endR; ++i) {
                for (int j=startC; j<endC; ++j) {
                    data[i][j] = value;
                }
            }
        }
        return index;
    }


    private static void convertFromB2D(String dataStr, int rows, int columns) throws IOException {
        System.out.println(String.format("D%4d%4d", rows, columns));
        int data[][] = new int[rows][columns];

        char[] dataChar = dataStr.toCharArray();
        int index = 0;
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                char c = dataChar[index];
                if (c == '1') {
                    data[i][j] = 1;
                } else {
                    data[i][j] = 0;
                }
                index++;
            }
        }

        StringBuilder result = new StringBuilder();
        buildD(data, 0, rows, 0, columns, result);
        printResult(result);
    }


    private static void buildD(int[][] data, int startR, int endR, int startC, int endC, StringBuilder result) {
        if (startR == endR || startC == endC) {
            return;
        }
        int first = data[startR][startC];
        boolean allSame = true;
        for (int i = startR; i < endR; ++i) {
            for (int j = startC; j < endC; ++j) {
                if (first != data[i][j]) {
                    allSame = false;
                    break;
                }
            }
        }

        if (allSame) {
            result.append(first);
        } else {
            result.append('D');
            int[][] dividedData = divideData(startR, endR, startC, endC);
            for (int i = 0; i < 4; ++i) {
                buildD(data, dividedData[i][0], dividedData[i][1], dividedData[i][2], dividedData[i][3], result);
            }
        }
    }

    private static int[][] divideData(int startR, int endR, int startC, int endC) {
        int rows = endR - startR;
        int columns = endC - startC;
        int divR = (int) Math.ceil(rows / 2.0);
        int divC = (int) Math.ceil(columns / 2.0);

        int[][] result = new int[4][4];
        result[0][0] = startR;
        result[0][1] = startR + divR;
        result[0][2] = startC;
        result[0][3] = startC + divC;

        result[1][0] = startR;
        result[1][1] = startR + divR;
        result[1][2] = startC + divC;
        result[1][3] = endC;

        result[2][0] = startR + divR;
        result[2][1] = endR;
        result[2][2] = startC;
        result[2][3] = startC + divC;

        result[3][0] = startR + divR;
        result[3][1] = endR;
        result[3][2] = startC + divC;
        result[3][3] = endC;

//        System.out.println("divide:" + startR + ", " + endR + ", " + startC + ", " + endC);
//        for (int i=0; i<4; ++i) {
//            System.out.println("result:" + Arrays.toString(result[i]));
//        }

        return result;
    }

    private static void printResult(StringBuilder sb) {
        int index = 0;
        while (index < sb.length()) {
            int end = (index + 50) > sb.length() ? sb.length() : (index + 50);
            System.out.println(sb.subSequence(index, end));
            index = end;
        }
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