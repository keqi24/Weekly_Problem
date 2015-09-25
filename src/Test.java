/**
 * Created by Derek on 14/9/15.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Long.MAX_VALUE);
    }


    public static long jiecheng(int num) {
        long result = 1;
        for (int i=2; i<num; ++i) {
            result = result * i;
        }
        return result;
    }
}
