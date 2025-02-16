package task2;

import java.util.List;

public class Sorting {
    static List<String> trace;

    static void record(String point) {
        trace.add(point);
    }
    private static void sortArrayByCountingMethod(int[] array) {
        record("A");
        if (array.length == 0){
            record("Empty");
            return;
        }

        int k = array[0];
        for (int i = 1; i < array.length; i++) {
            record("B");
            if (array[i] > k) {
                k = array[i];
                record("C");
            }
        }

        int[] tempArray = new int[k + 1];
        record("D");

        for (int value : array) {
            tempArray[value] += 1;
            record("E");
        }
        record("F");

//        for (int i = 0; i < tempArray.length; i++){
//            System.out.print(tempArray[i]);
//            System.out.print(" ");
//        }
//        System.out.println(" ");

        int b = 0;
        for (int i = 0; i < tempArray.length; i++){
            record("H");
            for (int j = 0; j < tempArray[i]; j++) {
                record("I");
                array[b] = i;
                b += 1;
            }
        }
        record("K");
        record("End");
    }

    public static void main(String[] args) {
        int[] numbers = {};
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
