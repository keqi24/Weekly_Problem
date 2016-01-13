import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2829
 *
 * notice:
 * This problem the input is not line by line, should using scanner to get input
 */
class W12_CommandoWar {


    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N;
        int caze = 1;
        while ((N = scanner.nextInt()) != 0) {
            int[] B = new int[N];
            int[] J = new int[N];

            for (int i=0; i<N; ++i) {
                B[i] = scanner.nextInt();
                J[i] = scanner.nextInt();
            }

            sort(B, J, N);

            int executeLeftTime = 0;
            int totalTime = 0;
            for (int i=N-1; i>=0; --i) {
                totalTime += B[i];
                int tmp = executeLeftTime - B[i];
                executeLeftTime = tmp > J[i] ? tmp : J[i];
            }

            totalTime += executeLeftTime;

            System.out.println("Case " + caze++ + ": " + totalTime);

        }
    }


    private static void sort(int[] B, int[] J, int size) {
        for (int i=1; i<size; ++i) {
            int tmpJ = J[i];
            int tmpB = B[i];
            int m;
            for (m=i-1; m>=0 && J[m]>tmpJ ; --m) {
                J[m+1] = J[m];
                B[m+1] = B[m];
            }
            J[m+1] = tmpJ;
            B[m+1] = tmpB;
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