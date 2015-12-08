import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=119
 */
class W11_DP {


    public static void main(String args[]) {
        try {
            execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static long[] power2 = new long[51];

    static {
        power2[0] = 1;
        for (int i=1; i<51; ++i) {
            power2[i] = 2 * power2[i-1];
        }
    }


    public static void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = readString(reader)) != null) {
            String[] strArray = line.trim().split(" +");
            System.out.println(core(strArray[0], strArray[1]));
        }
    }

    private static long core(String lessStr, String moreStr) {

        int moreSize = moreStr.length();
        int[] more = new int[moreSize];
        for (int i=0; i<moreSize; ++i) {
            more[i] = Character.getNumericValue(moreStr.charAt(i));
        }

        int lessSize = lessStr.length();
        int[] less = new int[moreSize];
        int index = 0;
        for (;index < moreSize-lessSize ; ++index) {
            less[index] = 0;
        }
        for (int i=0; i<lessSize; ++i, ++index) {
            less[index] = Character.getNumericValue(lessStr.charAt(i));
        }

        if (lessSize == moreSize) {
            return getMid(less, more, 0, lessSize);
        }

        long result = 0;
        result += getLessThanCurrent(more, 0, moreSize);

        for (int i=1; i<moreSize; ++i) {
            if (less[i] == 0) {
                long tmpResult =  power2[moreSize - i];
                result += tmpResult;
            } else {
                long tmpResult =  getMoreThanCurrent(less, i, moreSize);
                result += tmpResult;
                break;
            }
        }

        return result;
    }


    private static long getMid(int[] less, int[] more, int index, int size) {
        long result = 0;
        if (index == size-1) {
            if (less[index] <=4 && more[index] >= 4) {
                result += 1;
            }
            if (less[index] <=7 && more[index] >=7) {
                result += 1;
            }

            return result;
        }

        if (less[index] == more[index]) {
            if (less[index] == 4 || more[index] == 7) {
                return getMid(less, more, index+1, size);
            } else {
                return 0;
            }
        }

        if (less[index] < 4) {
            return getLessThanCurrent(more, index, size);
        } else if (less[index] == 4) {
            if (more[index] < 7) {
                return getLessThanCurrent(more, index+1, size);
            } else if (more[index] == 7) {
            }
        } else if (less[index] <7) {

        }


        if (less[index] ==4 && more[index] >= 4) {
            result += getMid(less, more, index+1, size);
        }
        if (less[index] <=7 && more[index] >=7) {
            result += getMid(less, more, index+1, size);
        }
        return result;
    }

    private static long getLessThanCurrent(int[] array, int startIndex, int endIndex) {
        long result = 0;
        int i = startIndex;
        if (i >= endIndex) {
            return 1;
        }
        if (array[i] < 4) {
            result += 0;
        } else if (array[i] == 4) {
            result += getLessThanCurrent(array, i+1, endIndex);
        } else if (array[i] < 7) {
            result += power2[endIndex - 1 - i];
        } else if (array[i] == 7) {
            result += power2[endIndex - 1 - i];
            result += getLessThanCurrent(array, i+1, endIndex);
        } else {
            result += power2[endIndex - i];
        }
        return result;
    }

    private static long getMoreThanCurrent(int[] array, int startIndex, int endIndex) {
        long result = 0;
        int i = startIndex;
        if (i >= endIndex) {
            return 1;
        }
        if (array[i] < 4) {
            result += power2[endIndex - i];
        } else if (array[i] == 4) {
            result += power2[endIndex - 1 - i];
            result += getMoreThanCurrent(array, i+1, endIndex);
        } else if (array[i] < 7) {
            result += power2[endIndex - 1 - i];
        } else if (array[i] == 7) {
            result += getMoreThanCurrent(array, i+1, endIndex);
        } else {
            result += 0;
        }
        return result;
    }



    private static class Record {
        public long result = 0;
        public boolean isLeadingZero = false;
    }


    public static int readInt(BufferedReader reader) throws IOException {
        return Integer.valueOf(reader.readLine());
    }

    public static String readString(BufferedReader reader) throws IOException {
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