import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by qux on 17/9/15.
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=861&page=show_problem&problem=4705
 */
 class CArcheryPuzzle {

    private static final int MAX = 10000;

    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int T = readInt(reader);
        for (int castCount = 1; castCount <= T; ++castCount) {
            int[] NS = readIntArray(reader, 2);
            int N = NS[0];
            int S = NS[1];
            int[] P = readIntArray(reader, N);

            int[] min = new int[S+1];
            for (int i=1; i<S+1; ++i) {
                min[i] = MAX + 1;
            }
            min[0] = 0;
            ArrayList<Integer> result[] = new ArrayList[S+1];
            result[0] = new ArrayList<>();

            for (int i=1; i<=S; ++i) {
                int selectedJ = -1;
                int currentMin = MAX;
                for (int j=0; j<N; ++j) {
                    if ( (P[j] <= i) && (min[i - P[j]] <= currentMin)) {
                        currentMin = min[i-P[j]];
                        selectedJ = j;
                    }
                }

                if (selectedJ >= 0) {
                    min[i] = currentMin + 1;
                    ArrayList<Integer> resultI = new ArrayList<>();
                    resultI.add(P[selectedJ]);
                    resultI.addAll(result[i - P[selectedJ]]);
                    result[i] = resultI;
                }
            }


            StringBuilder sb = new StringBuilder("Case ");
            sb.append(castCount + ":");
            if (min[S] >= MAX) {
                sb.append(" impossible");
            } else {
                sb.append(" [");
                sb.append(min[S]);
                sb.append("]");
                ArrayList<Integer> resultS = result[S];
                int size = resultS.size();
                for (int i=0; i<size; ++i) {
                    sb.append(" "+ resultS.get(i));
                }
            }

            System.out.println(sb.toString());
        }
    }

    public static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    public static int[] readIntArray(BufferedReader reader, int count) throws IOException {
        int[] result = new int[count];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int i=0; i<count; ++i) {
            result[i] = Integer.parseInt(tokenizer.nextToken());
        }

        return result;
    }
}