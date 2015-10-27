import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.impl.orbutil.graph.GraphImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Derek on 14/9/15.
 */
public class Test {
    public static void main(String[] args) {

        HashSet<Long> set = new HashSet<>();
        int collisionCount = 0;
        double data = Math.pow(2, 32);
        System.out.println(data);
        for (double i=0; i<100000000; i++) {
            long tmp = UUID.randomUUID().getMostSignificantBits();
            if (set.contains(tmp)) {
                collisionCount++;
            } else {
                set.add(tmp);
            }
        }

        System.out.println("collision:" + collisionCount);

    }


    public static long jiecheng(int num) {
        long result = 1;
        for (int i=2; i<num; ++i) {
            result = result * i;
        }
        return result;
    }

    public static void testGraph() {
        Graph graph = new GraphImpl();
    }
}
