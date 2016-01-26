import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=9
 */
class W14_The_Hamming_Distance {


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
            int N = scanner.nextInt();
            int H = scanner.nextInt();
            printResult("", N, H);
            if (caze != 0) {
                System.out.println();
            }
        }
    }

    public static void printResult(String pre, int N, int H) {
        if (H ==0) {
            System.out.println(pre + buildStr(N, 0));
            return;
        }
        if (N == H) {
            System.out.println(pre + buildStr(0, N));
            return;
        }
        for (int i=H; i<=N; ++i) {
            String leading = pre + buildStr(N-i, 1);
            printResult(leading, i-1, H-1);
        }
    }

    public static String buildStr(int zeroNum, int oneNum) {
        String str = "";
        for (int i=0; i< zeroNum; ++i) {
            str += "0";
        }
        for (int i=0;  i< oneNum; ++i) {
            str += "1";
        }
        return str;
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