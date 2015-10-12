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
        int caze = 0;
        while (true) {
            N = readInt(reader);

            ++caze;

            HashMap<String, Integer> indexMap = new HashMap<>();
            String[] data = new String[N];
            String[] result = new String[N];
            for (int i=0; i<N; ++i) {
                data[i] = readString(reader);
                indexMap.put(data[i], i);
            }

            Map<String, Set<String>> biggerMap = new HashMap<>();
            Map<String, Set<String>> smallerMap = new HashMap<>();
            for (int i=0; i<N; ++i) {
                biggerMap.put(data[i], new HashSet<String>());
                smallerMap.put(data[i], new HashSet<String>());
            }

            M = readInt(reader);
            for (int i=0; i<M; ++i) {
                String[] pair = readStringArray(reader);
                Set<String> bigSet = biggerMap.get(pair[0]);
                bigSet.add(pair[1]);

                Set<String> smallSet = smallerMap.get(pair[1]);
                smallSet.add(pair[0]);
            }

            Set<String> bigFinishedSet = new HashSet<>();
            Set<String> smallFinishedSet = new HashSet<>();

            for (String str : data) {
                fillSet(str, biggerMap, bigFinishedSet);
                fillSet(str, smallerMap, smallFinishedSet);
            }

            for (int i=0; i<N; ++i) {
                System.out.println(data[i] + ":" + biggerMap.get(data[i]).toString());
                System.out.println(data[i] + ":" + smallerMap.get(data[i]).toString());
            }

            Set<String> bigSet;
            Set<String> smallSet;
            for (int i=0; i<N; ++i) {
                int resultIndex = 0;
                String dataStr = data[i];
                int dataIndex = indexMap.get(dataStr);
                String tmpStr;
                bigSet = biggerMap.get(dataStr);
                smallSet = smallerMap.get(dataStr);
                for (int j=0; j<dataIndex; ++j) {
                    tmpStr =  data[j];
                    if (bigSet.contains(tmpStr) || smallSet.contains(tmpStr)) {
                        continue;
                    }

                }
                for (int j=dataIndex+1; j<N; ++j) {
                    tmpStr = data[j];
                    if (bigSet.contains(tmpStr) || smallSet.contains(tmpStr)) {
                        continue;
                    }
                    bigSet.add(tmpStr);
                    bigSet.addAll(biggerMap.get(tmpStr));
                }
            }

            System.out.println("------------------------------");
            for (int i=0; i<N; ++i) {
                System.out.println(data[i] + ":" + biggerMap.get(data[i]).toString());
                System.out.println(data[i] + ":" + smallerMap.get(data[i]).toString());
            }
            System.out.println("------------------------------");
            for (int i=0; i<N; ++i) {
                result[smallerMap.get(data[i]).size()] = data[i];
            }


            for (int i=0; i<N; ++i) {
                String dataStr = data[i];
                int dataIndex = indexMap.get(dataStr);
                int resultIndex = 0;
                for (int j=0; j<dataIndex; ++j) {
                    if (biggerMap.get(dataStr).contains(data[j])) {
                        continue;
                    }
                    ++resultIndex;
                }
                for (int j=dataIndex+1; j<N; ++j) {
                    if (smallerMap.get(dataStr).contains(data[j])) {
                        ++resultIndex;
                    }
                }

                result[resultIndex] = dataStr;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Case #").append(caze).append(": Dilbert should drink beverages in this order:");
            for (int i=0; i<N; ++i) {
                sb.append(" ");
                sb.append(result[i]);
            }
            sb.append(".");
            System.out.println(sb.toString());

            if (readStringNoTrim(reader) == null) {
                break;
            }
        }

    }

    //Case #1: Dilbert should drink beverages in this order: beer wine vodka.
    private static Set<String> fillSet(String key, Map<String, Set<String>> map, Set<String> finishedSet) {
        Set<String> mainSet = map.get(key);
        if (finishedSet.contains(key)) {
            return mainSet;
        }
        if (mainSet.size() == 0) {
            finishedSet.add(key);
            return mainSet;
        }

        Set<String> tmpSet = new HashSet<>();
        for (String str : mainSet) {
            tmpSet.addAll(fillSet(str, map, finishedSet));
        }
        mainSet.addAll(tmpSet);

        finishedSet.add(key);
        return mainSet;
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