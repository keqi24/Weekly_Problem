import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.impl.orbutil.graph.GraphImpl;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Derek on 14/9/15.
 */
public class Test {
    public static void main(String[] args) {
        char z = 'Z';
        System.out.println((int)z);

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
