import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Created by qux on 23/9/15.
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=2017
 *
 */
 class DAddAgain {


    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] factorial = new int[13];
        factorial[0] = 1;
        for (int i=1; i<13; ++i) {
            factorial[i] = factorial[i-1] * i;
        }

        int N;
        int[] data = new int[12];
        int[] count = new int[10];
        while (true) {
            N = readInt(reader);
            if (N == 0) {
                break;
            } else if (N == 1) {
                System.out.println(readInt(reader));
                continue;
            }

            for (int i=0; i<10; ++i) {
                count[i] = 0;
            }
            readIntArray(reader, data, N);
            for (int i=0; i<N; ++i) {
                count[data[i]]++;
            }
            long times = factorial[N];
            for (int i=0; i<10; ++i) {
                times = times / factorial[count[i]];
            }

            long sum = 0;
            for (int i=0; i<N; ++i) {
                sum += data[i];
            }

            BigInteger result = BigInteger.valueOf(0);
            BigInteger ten = BigInteger.valueOf(10);
            BigInteger num = BigInteger.valueOf(times).multiply(BigInteger.valueOf(sum)).divide(BigInteger.valueOf(N));
            for (int i=0; i<N; ++i) {
                result = result.multiply(ten).add(num);
            }
            System.out.println(result);
        }
    }

    public static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine().trim());
    }

    public static int[] readIntArray(BufferedReader reader, int count) throws IOException {
        int[] result = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i=0; i<count; ++i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return result;
    }

    public static void readIntArray(BufferedReader reader, int[] array,  int count) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i=0; i<count; ++i) {
            array[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }
}