import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by qux on 23/9/15.
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=78&page=show_problem&problem=2717
 */
class Main2 {


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
        while ((line = readString(reader)) != null) {
            N = Integer.parseInt(line);

            ++caze;

            String[] data = new String[N];
            List<Integer> dataList = new LinkedList<>();
            HashMap<String, Integer> indexMap = new HashMap<>();
            for (int i=0; i<N; ++i) {
                data[i] = readString(reader);
                dataList.add(i);
                indexMap.put(data[i], i);
            }


            M = readInt(reader);
            HashMap<Integer, List<Integer>> bigMap = new LinkedHashMap<>(N);
            HashMap<Integer, List<Integer>> smallMap = new LinkedHashMap<>(N);
            for (int i=0; i<N; ++i) {
                bigMap.put(i, new LinkedList<Integer>());
                smallMap.put(i, new LinkedList<Integer>());
            }
            for (int i=0; i<M; ++i) {
                String[] pair = readStringArray(reader);
                bigMap.get(indexMap.get(pair[0])).add(indexMap.get(pair[1]));
                smallMap.get(indexMap.get(pair[1])).add(indexMap.get(pair[0]));
            }

//            for (int i=0; i<N; ++i) {
//                System.out.println(i + ":" + bigMap.get(i));
//                System.out.println(i + ":" + smallMap.get(i));
//            }

            List<Integer> resultList = new LinkedList<>();
            int[] proccedIndex = new int[N];
            fillLinkedList(-1, resultList, dataList, bigMap, smallMap, proccedIndex);


            StringBuilder sb = new StringBuilder();
            sb.append("Case #").append(caze).append(": Dilbert should drink beverages in this order:");
            for (int index : resultList) {
                sb.append(" ");
                sb.append(data[index]);
            }
            sb.append(".");
            System.out.println(sb.toString());

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