import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=136
 * <p/>
 * Topological sorting: https://en.wikipedia.org/wiki/Topological_sorting
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

        boolean[][] graph = new boolean[26][26];
        int[] incoming = new int[26];
        boolean[] visited = new boolean[26];
        boolean[] data = new boolean[26];

        String previous = readString(reader);
        for (int i=0; i<previous.length(); ++i) {
            data[previous.charAt(i) - 'A'] = true;
        }

        String line;
        while (!(line = readString(reader)).equals("#")) {

            for (int i=0; i<line.length(); ++i) {
                data[line.charAt(i) - 'A'] = true;
            }

            int min = previous.length() < line.length() ? previous.length() : line.length();
            for (int i=0; i<min; ++i) {
                if (previous.charAt(i) != line.charAt(i)) {
                    if (!graph[previous.charAt(i) - 'A'][line.charAt(i) - 'A']) {
                        graph[previous.charAt(i) - 'A'][line.charAt(i) - 'A'] = true;
                        incoming[line.charAt(i) - 'A']++;
                    }
                    break;
                }
            }

            previous = line;
        }


        int count = 0;
        for (int i=0; i<26; i++) {
            if (data[i]) {
                count++;
            }
        }
        List<Character> result = new ArrayList<>();
        while (count>0) {
            for (int i=0; i<26; i++) {
                if (data[i] && incoming[i] == 0 && !visited[i]) {
                    result.add((char)(i + 'A'));
                    visited[i] = true;

                    for (int j=0; j<26; ++j) {
                        if (data[i]) {
                            if (graph[i][j]) {
                                incoming[j]--;
                            }
                        }
                    }
                    count--;
                    break;
                }
            }
        }

        for (char c : result) {
            System.out.print(c);
        }
        System.out.println();

    }

    private static void printInt2Char(int[] array) {
        for (int i : array) {
            System.out.print((char) (i + 'A'));
        }
        System.out.println();
    }

    public static String readString(BufferedReader reader) throws IOException {
        return reader.readLine().trim();
    }

    public static String readStringNoTrim(BufferedReader reader) throws IOException {
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