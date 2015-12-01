import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=119
 */
class W10_Bitmap {


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
        while ((line = readString(reader)) != null) {
            String[] strArray = line.split(" +");
            long N = Long.valueOf(strArray[0]);
            long L = Long.valueOf(strArray[1]);
            long U = Long.valueOf(strArray[2]);

            System.out.println(getResult(N, L, U));

        }
    }


    public static long getResult(long N, long L, long U) {
        long result = 0;
        boolean ignoreL = false;
        boolean ignoreU = false;
        for (long i=31; i>=0; --i) {
            long mask = 1L << i;

            long bitResult = 0;
            long maskN = N & mask;
            long maskL = L & mask;
            long mastU = U & mask;

            if (mastU == 0 && !ignoreU) {
                bitResult = 0;
            } else if (maskL == mask && !ignoreL){
                bitResult = mask;
            } else {
                if (maskN == 0) {
                    bitResult = mask;
                    ignoreL = true;
                } else {
                    bitResult = 0;
                    ignoreU = true;
                }
            }

            result = result | bitResult;
        }
        return result;
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