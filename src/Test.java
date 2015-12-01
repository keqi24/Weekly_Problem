import com.oracle.javafx.jmx.json.JSONException;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import com.sun.corba.se.impl.orbutil.graph.GraphImpl;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Derek on 14/9/15.
 */
public class Test {
    public static void main(String[] args) {

        final TestCondition test = new TestCondition();

        test.secondLock();
    }



    public static class TestCondition {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();


        public void secondLock() {
            lock.lock();
            try {
                testReLock();
                System.out.println("second lock");
            } finally {
                lock.unlock();
            }
        }

        public void testReLock() {
            lock.lock();
            try {
                System.out.println("first lock");

            } finally {
                lock.unlock();
            }
        }


        public void testTimeout() {
            lock.lock();
            try {
                System.out.println("start await");
                boolean result = condition.await(10, TimeUnit.SECONDS);
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void wakeup() {
            lock.lock();
            try {
                System.out.println("signalAll");
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }


    public static void testCrash() {

        try {
            throwIOExcetion();
            throwMyException();
        } catch (IOException e) {
//            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void throwIOExcetion() throws IOException {
        throw new IOException("IOException");
    }

    public static void throwMyException() throws MyException {
        throw new MyException();
    }

    public static class MyException extends Exception  {
        public MyException() {
            super("My Exception");
        }
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
