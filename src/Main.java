import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=848
 * <p/>
 * Find the biggest distance from the N distances
 * From the biggest one to check if can fit the demand
 * if fit get the result
 * else biggest++ and check it again
 */
class Main {


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
            N = Integer.valueOf(nums[0]);
            M = Integer.valueOf(nums[1]);
            int[] cam = new int[N+1];
            for (int i=0; i<N+1; ++i) {
                cam[i] = readInt(reader);
            }

            int[][] dp = new int[N+1][M+1];
            for (int i=0; i<M+1; i++) {
                dp[0][i] = cam[0];
            }

            for (int i=0; i<N+1; i++) {
                dp[i][0] = getSum(cam, 0, i);
            }

            for (int n=1; n<N+1; ++n) {
                for (int m=1; m<M+1; ++m) {
                    int min = 100000;
                    for (int i=1; i<=n; ++i) {
                        int sum = getSum(cam, n-i+1, n);
                        int tmp = Math.max(dp[n-i][m-1], sum);
                        if (tmp < min) {
                            min = tmp;
                        }
                    }
                    dp[n][m] = min;
                }
            }

//            for (int n=0; n<N+1; n++) {
//                for (int m=0; m<M+1; m++) {
//                    System.out.println("dp[" + n + "][" + m + "]=" + dp[n][m]);
//                }
//            }

            System.out.println(dp[N][M]);
        }
    }

    public static void execute2() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line;
        int N;
        int M;
        while ( (line = readString(reader)) != null) {
            String[] nums = line.trim().split(" ");
            N = Integer.valueOf(nums[0]);
            M = Integer.valueOf(nums[1]);
            int[] cam = new int[N+1];
            for (int i=0; i<N+1; ++i) {
                cam[i] = readInt(reader);
            }

            int[][] dp = new int[N+1][M+1];
            for (int i=0; i<M+1; i++) {
                dp[0][i] = cam[0];
                System.out.println("dp[" + 0 + "][" + i + "]=" + dp[0][i]);
            }
            for (int n=1; n<N+1; ++n) {
                dp[n][0] = getSum(cam, 0, n);
                for (int m=1; m<M+1; ++m) {
                    if (n < m) {
                        dp[n][m] = dp[n][n];
                        System.out.println("dp[" + n + "][" + m + "]=" + dp[n][m]);
                        continue;
                    }
                    if (n == m) {
                        dp[n][m] = getMax(cam, 0, n);
                        System.out.println("dp[" + n + "][" + m + "]=" + dp[n][m]);
                        continue;
                    }
                    int min = Integer.MAX_VALUE;
                    for (int i=1; i<n; ++i) {
                        int sum = getSum(cam, n-i+1, n);
                        System.out.println("sum=" + sum);
                        int tmp = Math.max(dp[n-i][m-1], sum);
                        if (tmp < min) {
                            min = tmp;
                        }
                    }
                    dp[n][m] = min;
                    System.out.println("dp[" + n + "][" + m + "]=" + dp[n][m]);
                }
            }

            System.out.println(dp[N][M]);


        }
    }

    public static int getMax(int[] array, int start, int end) {
        int max = -1;
        for (int i=start; i<end+1; ++i) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int getSum(int[] array, int start, int end) {
        int sum = 0;
        for (int i=start; i<end+1; ++i) {
            sum += array[i];
        }
        return sum;
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