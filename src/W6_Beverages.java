import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=2001
 *
 * Method1 : Topological sorting: https://en.wikipedia.org/wiki/Topological_sorting
 *
 * Method2 : solve it directly
 *           scan the origin data from 0 to N
 *
 */
public class W6_Beverages {

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


    private static void fillLinkedList(Integer key, List<Integer> resultList, List<Integer> data, HashMap<Integer, List<Integer>> bigMap, HashMap<Integer, List<Integer>> smallMap, int[] proccedIndex) {
//        List<List<Integer>> tmpData = new LinkedList<>();
//        for (Integer dataIndex : data) {
//            smallMap.get(dataIndex).remove(key);
//            if (smallMap.get(dataIndex).size() == 0 && proccedIndex[dataIndex] != 1) {
//                if (dataIndex == 9) {
//                    System.out.println("9");
//                }
//                proccedIndex[dataIndex] = 1;
//                List<Integer> list = new LinkedList<>();
//                tmpData.add(list);
//                list.add(dataIndex);
//                fillLinkedList(dataIndex, list, bigMap.get(dataIndex), bigMap, smallMap, proccedIndex);
//            }
//        }
//
//        if (key == -1) {
//            System.out.println("-1");
//            for (List<Integer> list: tmpData) {
//                System.out.println(list);
//            }
//        }
//
//        while (true) {
//            int min = 1000;
//            List<Integer> minList = null;
//            for (List<Integer> list : tmpData) {
//                if (list.size() > 0 && list.get(0) < min) {
//                    minList = list;
//                    min = list.get(0);
//                }
//            }
//            if (minList != null) {
//                minList.remove((Integer)min);
//            }
//            if (min < 1000) {
//                System.out.println("add index:" + min);
//                resultList.add(min);
//            } else {
//                break;
//            }
//        }
        TreeSet<Integer> tmpList = new TreeSet<>();
        for (Integer index : data) {
            if (smallMap.get(index).size() == 0) {
                tmpList.add(index);
//                System.out.println("add index:" + index);
                proccedIndex[index] = 1;
            }
        }
        while (tmpList.size() > 0) {
            Integer resultData = tmpList.first();
            tmpList.remove(resultData);
            resultList.add(resultData);
            for (Integer bigIndex : bigMap.get(resultData)) {
                smallMap.get(bigIndex).remove(resultData);
                if (proccedIndex[bigIndex] != 1 && smallMap.get(bigIndex).size() == 0) {
                    proccedIndex[bigIndex] = 1;
//                    System.out.println("add index:" + bigIndex);
                    tmpList.add(bigIndex);
                }
            }
        }

    }

    /**
     * 10
     0 cachaca
     1 rum
     2 apple-juice
     3 tequila
     4 whiskey
     5 wine
     6 vodka
     7 beer
     8 martini
     9 gin
     11
     beer whiskey
     apple-juice gin
     rum cachaca
     vodka tequila
     apple-juice martini
     rum gin
     wine whiskey
     apple-juice beer
     beer rum
     wine vodka
     beer tequila

     0:[]
     0:[1]
     1:[0, 9]
     1:[7]
     2:[9, 8, 7]
     2:[]
     3:[]
     3:[6, 7]
     4:[]
     4:[7, 5]
     5:[4, 6]
     5:[]
     6:[3]
     6:[5]
     7:[4, 1, 3]
     7:[2]
     8:[]
     8:[2]
     9:[]
     9:[2, 1]
     */

    //Case #1: Dilbert should drink beverages in this order: beer wine vodka.


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
