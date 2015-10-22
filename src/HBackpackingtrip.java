import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=848
 * <p/>
 * Find the biggest distance from the N distances
 * From the biggest one to check if can fit the demand
 * if fit get the result
 * else biggest++ and check it again
 */
class HBackpackingtrip {


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
        int N;
        int M;
        while ( (line = readString(reader)) != null) {
            String[] nums = line.trim().split(" ");
            N = Integer.valueOf(nums[0]) + 1;
            M = Integer.valueOf(nums[1]) + 1;
            int[] cam = new int[N];
            for (int i=0; i<N; ++i) {
                cam[i] = readInt(reader);
            }

            int biggest = cam[0];
            for (int i=1; i<N; ++i) {
                if (biggest < cam[i]) {
                    biggest = cam[i];
                }
            }
            while (true) {
                if (isFit(N, M, cam, biggest)) {
                    break;
                }
                biggest++;
            }
            System.out.println(biggest);
        }
    }

    public static boolean isFit(int N, int M, int[] cam, int testResult) {
        int index = 0;
        while (M > 0) {
            int tmpSum = 0;
            while (index < N) {
                tmpSum += cam[index];
                if (tmpSum <= testResult) {
                    index++;
                }
                if (tmpSum >= testResult) {
                    break;
                }
            }
            M--;
        }
        if (index >= N) {
            return true;
        }
        return false;
    }



    private static void printInt2Char(int[] array) {
        for (int i : array) {
            System.out.print((char) (i + 'A'));
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