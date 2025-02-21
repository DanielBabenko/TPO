package task2;

import java.util.List;

public class Sorting {

    private static List<String> trace;

    private static void record(String point) {
        trace.add(point);
    }

    public static void sortArrayByCountingMethod(int[] array) {
//        record("A");
        if (array.length == 0) {
//            record("Empty");
            return;
        }

        int k = array[0];
//        int cPoint = 0;
//        int bPoint = 0;
        int minimum = 0;
//        int nPoint = 0;
        for (int i = 1; i < array.length; i++) {
//            bPoint++;
            if (array[i] > k) {
                k = array[i];
//                cPoint++;
            } else if (array[i] < minimum && array[i] < 0) {
                minimum = array[i];
//                nPoint++;
            }
        }
//        record("B"+bPoint);
//        record("C"+cPoint);
//        record("Negative"+nPoint);
//        record("D");

        k -= minimum;
//        record("SHIFT"+(-minimum));

        int[] tempArray = new int[k + 1];

//        int ePoint = 0;
        for (int value : array) {
//            ePoint++;
            tempArray[value - minimum] += 1;
        }
//        record("E"+ePoint);
//        record("G");

        int b = 0;
//        int hPoint = 0;
//        int iPoint = 0;
//        int jPoint = 0;
        for (int i = 0; i < tempArray.length; i++){
            //hPoint++;
            for (int j = 0; j < tempArray[i]; j++) {
                //iPoint++;
                array[b] = i + minimum;
                b += 1;
            }
//            if (tempArray[i] == 0) {
//                jPoint++;
//            }
        }
//        record("H"+hPoint);
//        record("I"+iPoint);
//        record("J"+jPoint);
//        record("K");
    }

    public static void main(String[] args) {
        int[] numbers = {1, 52, 67, 34, 90, 2, 45, -52, 39, 52, -20, 0};
        for (int i = 0; i < numbers.length; i++){
            System.out.print(numbers[i]);
            System.out.print(" ");
        }

        System.out.println(" ");
        sortArrayByCountingMethod(numbers);

        for (int i = 0; i < numbers.length; i++){
            System.out.print(numbers[i]);
            System.out.print(" ");
        }
    }
}
