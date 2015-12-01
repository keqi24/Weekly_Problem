import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=119
 */
class Main {


    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long[] power2 = new long[51];

    static {
        power2[0] = 1;
        for (int i=1; i<51; ++i) {
            power2[i] = 2 * power2[i-1];
        }
    }


    public static void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = readString(reader)) != null) {
            String[] strArray = line.trim().split(" +");


        }
    }

    private static void core(String lessStr, String moreStr) {
        int lessSize = lessStr.length();
        int[] less = new int[lessSize];
        for (int i=0; i<lessSize; ++i) {
            less[i] = Character.getNumericValue(lessStr.charAt(i));
        }

        int moreSize = moreStr.length();
        int[] more = new int[moreSize];
        for (int i=0; i<moreSize; ++i) {
            more[i] = Character.getNumericValue(moreStr.charAt(i));
        }

        int index = 0;


        Cell[] result = new Cell[moreSize];

        Cell cell = new Cell();
        result[0] = cell;


        while (index < lessSize) {
            int lessItem = less[lessSize-1-index];
            int moreItem = more[moreSize-1-index];

            Cell cell = new Cell();


            result[index] = cell;
            index++;
        }
    }

    private static Cell getCell(int lessItem, int moreItem, int index, Cell preCell) {
        
    }


    private static class Cell {
        public int small;
        public int mid;
        public int big;
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