import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2001
 *
 * Topological sorting: https://en.wikipedia.org/wiki/Topological_sorting
 *
 *
 */
class W7_RareOrder {


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
        int caze = 0;
        String line = null;
        while ((line = readStringNoTrim(reader)) != null) {
            N = Integer.parseInt(line);

            ++caze;

            String[] data = new String[N];
            int[][] graph = new int[N][N];
            int[] bigEdge = new int[N];
            HashMap<String, Integer> indexMap = new HashMap<>();
            for (int i=0; i<N; ++i) {
                data[i] = readString(reader);
                indexMap.put(data[i], i);
            }

            M = readInt(reader);
            for (int i=0; i<M; ++i) {
                String[] pair = readStringArray(reader);
                if (graph[indexMap.get(pair[0])][indexMap.get(pair[1])] != 1) {
                    graph[indexMap.get(pair[0])][indexMap.get(pair[1])] = 1;
                    bigEdge[indexMap.get(pair[1])]++;
                }
            }

            int[] processed = new int[N];
            StringBuilder sb = new StringBuilder();
            sb.append("Case #").append(caze).append(": Dilbert should drink beverages in this order:");
            TreeSet<Integer> set = new TreeSet<>();
            for (int i=0; i<N; ++i) {
                if (bigEdge[i] == 0) {
                    processed[i] = 1;
                    set.add(i);
                }
            }
            while (!set.isEmpty()) {
                Integer first = set.first();
                set.remove(first);
                sb.append(" ");
                sb.append(data[first]);
                for (int i=0; i<N; ++i) {
                    if (graph[first][i] == 1) {
                        bigEdge[i]--;
                    }
                    if (processed[i] != 1 && bigEdge[i] == 0) {
                        processed[i] = 1;
                        set.add(i);
                    }
                }
            }
            sb.append(".");
            System.out.println(sb.toString());
            System.out.println();

            if (readStringNoTrim(reader) == null) {
                break;
            }
        }
    }

    //build graph using recursion, can't make sure is correct
    public static void fillGraph(List<int[]> data,  int start, int end, int index, int[][] graph, int[] bigEdge) {
        int previousIndex = start;
        int previous = -1;
        for (int i=start; i<end; ++i) {
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
        for (int i=previousIndex+1; i<end; ++i) {
            int [] array = data.get(i);
            if (array.length > index && array[index] != previous) {
                int current = array[index];
                if (graph[previous][current] != 1) {
                    graph[previous][current] = 1;
                    bigEdge[current]++;
                }
                fillGraph(data, previousIndex, i, index+1, graph, bigEdge);
                previous = current;
                previousIndex = i;
            }
        }
        if (previousIndex < end-1) {
            fillGraph(data, previousIndex, end, index+1, graph, bigEdge);
        }
    }


    public static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine().trim());
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