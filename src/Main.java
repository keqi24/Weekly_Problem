import com.sun.tools.internal.xjc.reader.gbind.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by qux on 23/9/15.
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=78&page=show_problem&problem=2717
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
        int N = 0;
        int M = 0;
        int caze = 1;
        while (true) {
            N = readInt(reader);
            if (N == 0) {
                break;
            }

            String[] data = new String[N];
            for (int i=0; i<N; ++i) {
                data[i] = readString(reader);
            }

            HashMap<String, HashSet<String>> map = new HashMap<>();

            M = readInt(reader);
            for (int i=0; i<M; ++i) {
                String[] pair = readStringArray(reader);
                HashSet<String> bigSet = map.get(pair[0]);
                if (bigSet == null) {
                    bigSet = new HashSet<>();
                    map.put(pair[0], bigSet);
                }
                bigSet.add(pair[1]);
            }

            Iterator<Map.Entry<String, HashSet<String>>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, HashSet<String>> entry = iter.next();
                String key = entry.getKey();
                HashSet<String> set = entry.getValue();
                Iterator<String> setIter = set.iterator();
                HashSet<String> tmpSet = new HashSet<>();
                while (setIter.hasNext()) {
                }
            }


        }

    }

    public static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine().trim());
    }

    public static String readString(BufferedReader reader) throws IOException {
        return reader.readLine().trim();
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