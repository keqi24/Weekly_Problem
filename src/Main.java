import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=119
 */
class Main {


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
        long result = 0;

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




        Record current = new Record();
        current.isLeadingZero = less[0] == 0;
        for (int i=0; i<moreSize; ++i) {
            Record pre = current;
            current = new Record();
            current.result = pre.result;
            if (pre.isLeadingZero) {
                if (less[i] == 0) {
                    current.result += power2[moreSize - 1 -i];
                    if (more[i] < 4) {
                        current.result += 0;
                    } else if (more[i] == 4 ) {
                        current.result += getLessThanCurrent(more, i+1, moreSize);
                    } else if (more[i] < 7) {
                        current.result += power2[moreSize -1 -i];
                    } else if (more[i] == 7) {
                        current.result += power2[moreSize -1 -i];
                        current.result += getLessThanCurrent(more, i+1, moreSize);
                    } else {
                        current.result += power2[moreSize -i];
                    }
                    continue;
                } else {
                    current.isLeadingZero = false;
                    if (less[i] < 4 ) {
                        current.result += power2[moreSize -i];
                    } else if (less[i] == 4) {
                        current.result += power2[moreSize - 1 -i];
                        current.result += getMoreThanCurrent(less, i+1, moreSize);
                    } else if (less[i] < 7) {
                        current.result += power2[moreSize - 1 -i];
                    } else if (less[i] == 7) {
                        current.result += getMoreThanCurrent(less, i+1, moreSize);
                    } else if (less[i] > 7) {
                        current.result += 0;
                    }
                    break;
                }
            }
        }
        return current.result;
    }

//    private static long[] calcLessThanArray(int[] array) {
//        long[] lessThanArray = new long[array.length];
//        lessThanArray[array.length -1] = 1;
//        for (int i=array.length-1; i>0; --i) {
//            if (array[i] < 4) {
//                lessThanArray[i-1] = 0;
//            } else if (array[i] < 7) {
//                lessThanArray[i-1] = lessThanArray[i];
//            } else {
//                lessThanArray[i-1] = 2*lessThanArray[i];
//            }
//        }
//        return lessThanArray;
//    }
//
//    private static long[] calcMoreThanArray
//
    private static long getLessThanCurrent(int[] array, int startIndex, int endIndex) {
        long result = 0;
        for (int i=startIndex; i<endIndex; ++i) {
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
        }
        return result;
    }

    private static long getMoreThanCurrent(int[] array, int startIndex, int endIndex) {
        long result = 0;
        for (int i=startIndex; i<endIndex; ++i) {
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
        }
        return result;
    }



    private static class Record {
        public long result = 0;
        public boolean isLeadingZero = true;
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