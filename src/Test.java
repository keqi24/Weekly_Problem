import com.sun.tools.internal.xjc.reader.gbind.Graph;

/**
 * Created by Derek on 14/9/15.
 */
public class Test {
    public static void main(String[] args) {
    }


    public static long jiecheng(int num) {
        long result = 1;
        for (int i=2; i<num; ++i) {
            result = result * i;
        }
        return result;
    }

    public static void testGraph() {
        Graph graph = new Graph(7, false);
    }
}
