import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by qux on 23/9/15.
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=78&page=show_problem&problem=2717
 *
 * Thought 2, using the binary search
 * Just assume a result time, check if it ok. if ok binary search to smaller.
 */
class W5_PhysicsExperiment {


    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int caze = 1;
        int N = 0;
        double D = 0.0;
//        double[] data = new double[10001];

        while (true) {
            N = readInt(reader);
            if (N == 0) {
                break;
            }
            double[] data = new double[N];

            for (int i = 0; i < N; ++i) {
                data[i] = readDouble(reader);
            }
            D = readDouble(reader);

            if (N == 1) {
                System.out.printf("Case %d: %.3f\n", caze, 0f);
                caze++;
                continue;
            }

            Arrays.sort(data);

            int index = 1;
            double time = 0;
            double newPosition = data[0];
            while (index < N) {
                double distance = data[index] - newPosition;
                if (distance > D) {
                    double tmpTime = (distance - time - D) / 2.0d;
                    if (tmpTime >= 0.0) {
                        time += tmpTime;
                        newPosition += tmpTime;
                    }
                    newPosition += D;

                } else if (distance < D) {
                    newPosition += (distance + time) < D ? (distance + time) : D;
                } else {
                    newPosition += D;
                }
                ++index;
            }
            System.out.printf("Case %d: %.3f\n", caze, time);
            caze++;
        }
    }

    public static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine().trim());
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