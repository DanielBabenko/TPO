package task2;

public class Sorting {

    public static void sortArrayByCountingMethod(int[] array) {
        if (array.length == 0) {
            return;
        }

        int k = array[0];
        int minimum = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > k) {
                k = array[i];
            } else if (array[i] < minimum && array[i] < 0) {
                minimum = array[i];
            }
        }

        k -= minimum;

        int[] tempArray = new int[k + 1];

        for (int value : array) {
            tempArray[value - minimum] += 1;
        }

        int b = 0;
        for (int i = 0; i < tempArray.length; i++){;
            for (int j = 0; j < tempArray[i]; j++) {
                array[b] = i + minimum;
                b += 1;
            }
        }
    }


    public static void main(String[] args) {
        int[] numbers = {1, 52, 67, 34, 90, 2, 45, 52, 39, 52, -20, 0};
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
