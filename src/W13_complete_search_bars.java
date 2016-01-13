import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *  https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3886
 */
class W13_complete_search_bars {


    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        while (N > 0) {
            int sum = scanner.nextInt();
            int M = scanner.nextInt();
            int[] data = new int[M];
            for (int i=0; i<M; ++i) {
                data[i] = scanner.nextInt();
            }
            sort(data, M);
//            printArray(data);
            boolean result = false;
            for (int i=0; i<M; ++i) {
                if (check(sum, data, i, M)) {
                    result = true;
                    break;
                }
            }

            System.out.println(result ? "YES" : "NO");

            N--;
        }

    }

    private static boolean check(int sum, int[] data, int start, int size) {
        if (sum == 0) {
            return true;
        } else if (sum < 0) {
            return false;
        }
        if (start >= size) {
            return false;
        }

        for (int i=start; i<size; i++) {
            if (sum == data[i]) {
                return true;
            } else if (sum > data[i]) {
                if(check(sum-data[i], data, i+1, size)) {
                    return true;
                }
            }
        }
        return false;
    }


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