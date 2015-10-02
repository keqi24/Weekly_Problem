/**
 * Created by Derek on 14/9/15.
 */
public class Test {
    public static void main(String[] args) {
        double time = 0.6905;

        time = (double)(Math.round(time*1000)/1000.0d);
        System.out.println(time);
    }


    public static long jiecheng(int num) {
        long result = 1;
        for (int i=2; i<num; ++i) {
            result = result * i;
        }
        return result;
    }
}
