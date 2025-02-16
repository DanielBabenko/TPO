import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SortingTest {

    private List<String> trace;

    private void record(String point) {
        trace.add(point);
    }

    private void sortArrayByCountingMethod(int[] array) {
        record("A");

        //Найдём максимальное число в массиве
        if (array.length == 0) {
            record("Empty");
            return;
        }

        int k = array[0];
        int cPoint = 0;
        int bPoint = 0;
        for (int i = 1; i < array.length; i++) {
            bPoint++;
            if (array[i] > k) {
                k = array[i];
                cPoint++;
            }
        }
        record("B"+bPoint);
        record("C"+cPoint);
        record("D");

        int[] tempArray = new int[k + 1];

        int ePoint = 0;
        int fPoint = 0;
        for (int value : array) {
            ePoint++;
            tempArray[value] += 1;
            fPoint++;
        }
        record("E"+ePoint);
        record("F"+fPoint);
        record("G");


        int b = 0;
        int hPoint = 0;
        int iPoint = 0;
        int jPoint = 0;
        for (int i = 0; i < tempArray.length; i++){
            hPoint++;
            for (int j = 0; j < tempArray[i]; j++) {
                iPoint++;
                array[b] = i;
                b += 1;
            }
            if (tempArray[i] == 0) {
                jPoint++;
            }
        }
        record("H"+hPoint);
        record("I"+iPoint);
        record("J"+jPoint);
        record("K");
    }

    @Test
    void testEmptyArray() {
        int[] array = {};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "Empty");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void testSingleElementArray() {
        int[] array = {5};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B0", "C0", "D", "E1", "F1", "G", "H6", "I1", "J5", "K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{5}, array);
    }

    @Test
    void testVariousPositiveElements() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B7", "C3", "D", "E8", "F8", "G", "H10", "I8", "J3", "K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 9}, array);
    }

//    @Test
//    void testRepeatingElements() {
//        int[] array = {2, 2, 2, 2, 2};
//        trace = new ArrayList<>();
//        sortArrayByCountingMethod(array);
//        List<String> expectedTrace = Arrays.asList("A", "B", "D", "E", "F", "E", "F", "E", "F", "E", "F", "E", "F", "G", "H", "I", "I", "J", "J", "I", "J", "J", "J", "J", "K");
//        assertEquals(expectedTrace, trace);
//        assertArrayEquals(new int[]{2, 2, 2, 2, 2}, array);
//    }
//
//    @Test
//    void testMaxIsFirstElement() {
//        int[] array = {10, 1, 2, 3, 4};
//        trace = new ArrayList<>();
//        sortArrayByCountingMethod(array);
//        List<String> expectedTrace = Arrays.asList("A", "B", "D", "E", "F", "E", "F", "E", "F", "E", "F", "E", "F", "G", "H", "I", "I", "J", "J", "I", "J", "J", "J", "J", "J", "J", "J", "J", "J", "J", "J", "K");
//                assertEquals(expectedTrace, trace);
//        assertArrayEquals(new int[]{1, 2, 3, 4, 10}, array);
//    }
//
//    @Test
//    void testMaxIsLastElement() {
//        int[] array = {1, 2, 3, 4, 10};
//        trace = new ArrayList<>();
//        sortArrayByCountingMethod(array);
//        List<String> expectedTrace = Arrays.asList("A", "B", "B", "B", "B", "C", "D", "E", "F", "E", "F", "E", "F", "E", "F", "E", "F", "G", "H", "I", "I", "J", "J", "I", "J", "J", "J", "J", "J", "J", "J", "J", "J", "J", "J", "K");
//        assertEquals(expectedTrace, trace);
//        assertArrayEquals(new int[]{1, 2, 3, 4, 10}, array);
//    }
//
//    @Test
//    void testZerosAndPositiveNumbers() {
//        int[] array = {0, 1, 2, 0, 3, 0};
//        trace = new ArrayList<>();
//        sortArrayByCountingMethod(array);
//        List<String> expectedTrace = Arrays.asList("A", "B", "D", "E", "F", "E", "F", "E", "F", "E", "F", "E", "F", "E", "F", "G", "H", "I", "I", "J", "J", "J", "I", "J", "K");
//        assertEquals(expectedTrace, trace);
//        assertArrayEquals(new int[]{0, 0, 0, 1, 2, 3}, array);
//    }
//
//    // Helper method to print the trace
//    private void printTrace(List<String> trace) {
//        System.out.println("Trace: " + String.join(", ", trace));
//    }
//
//    // Helper method to print array
//    private void printArray(int[] array) {
//        System.out.println("Array: " + Arrays.toString(array));
//    }
}
