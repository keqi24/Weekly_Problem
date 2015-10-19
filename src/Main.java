import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2001
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
        int[][] graph = new int[100][100];
        int[] bigEdge = new int[100];
        int[] processed = new int[100];
        Set<Integer> letters = new LinkedHashSet<>();
        String line;
        char[] previous;
        {
            line = readString(reader);
            previous = line.toCharArray();
            for (int i = 0; i < previous.length; ++i) {
                int tmp = previous[i];
                letters.add(tmp);
            }
        }

        while ((line = readString(reader)) != null) {
            if (line.equals("#")) {
                break;
            }
            char[] charArray = line.toCharArray();
            for (int i = 0; i < charArray.length; ++i) {
                int tmp = charArray[i];
                letters.add(tmp);
            }

            for (int i=0; i<previous.length && i<charArray.length; ++i) {
                if (previous[i] != charArray[i]) {
                    int pre = previous[i];
                    int cur = charArray[i];
                    if (graph[pre][cur] != 1) {
                        graph[pre][cur] = 1;
                        bigEdge[cur]++;
                        break;
                    }
                }
            }

            previous = charArray;
        }

        int lettersSize = 0;
        while (lettersSize < letters.size()) {
            int printData = 90;
            for (int num : letters) {
                if (bigEdge[num] == 0 && processed[num] != 1) {
                    printData = num;
                    break;
                }
            }
//            if (printData < 0) {
//                break;
//            }
            processed[printData] = 1;
            System.out.print((char) (printData));

            for (int j : letters) {
                if (graph[printData][j] == 1) {
                    bigEdge[j]--;
                }
            }
            lettersSize++;
        }
    }


    public static void fillGraph(List<int[]> data, int start, int end, int index, int[][] graph, int[] bigEdge) {
        int previousIndex = start;
        int previous = -1;
        for (int i = start; i < end; ++i) {
            int[] array = data.get(i);
            if (array.length > index) {
                previousIndex = i;
                previous = array[index];
                break;
            }
        }
        if (previous == -1) {
            return;
        }
        for (int i = previousIndex + 1; i < end; ++i) {
            int[] array = data.get(i);
            if (array.length > index && array[index] != previous) {
                int current = array[index];
                if (graph[previous][current] != 1) {
                    graph[previous][current] = 1;
                    bigEdge[current]++;
                }
                fillGraph(data, previousIndex, i, index + 1, graph, bigEdge);
                previous = current;
                previousIndex = i;
            }
        }
        if (previousIndex < end - 1) {
            fillGraph(data, previousIndex, end, index + 1, graph, bigEdge);
        }
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