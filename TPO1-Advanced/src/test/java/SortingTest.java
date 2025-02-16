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
        if (array.length == 0) {
            record("Empty");
            return;
        }

        int k = array[0];
        int cPoint = 0;
        int bPoint = 0;
        int minimum = 0;
        int nPoint = 0;
        for (int i = 1; i < array.length; i++) {
            bPoint++;
            if (array[i] > k) {
                k = array[i];
                cPoint++;
            } else if (array[i] < minimum && array[i] < 0) {
                minimum = array[i];
                nPoint++;
            }
        }
        record("B"+bPoint);
        record("C"+cPoint);
        record("Negative"+nPoint);
        record("D");

        k -= minimum;
        record("SHIFT"+(-minimum));

        int[] tempArray = new int[k + 1];

        int ePoint = 0;
        int fPoint = 0;
        for (int value : array) {
            ePoint++;
            tempArray[value - minimum] += 1;
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
                array[b] = i + minimum;
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
        List<String> expectedTrace = Arrays.asList("A", "B0", "C0", "Negative0", "D", "SHIFT0", "E1", "F1", "G", "H6", "I1", "J5", "K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{5}, array);
    }

    @Test
    void testVariousElements() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B7", "C3", "Negative0", "D", "SHIFT0", "E8", "F8", "G", "H10", "I8", "J3", "K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 9}, array);
    }

    @Test
    void testRepeatingElements() {
        int[] array = {52, 52, 52, 52, 52};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B4", "C0", "Negative0", "D", "SHIFT0", "E5", "F5", "G", "H53", "I5", "J52","K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{52, 52, 52, 52, 52}, array);
    }

    @Test
    void testMaxIsFirstElement() {
        int[] array = {10, 1, 2, 3, 4};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B4", "C0", "Negative0", "D", "SHIFT0", "E5", "F5", "G", "H11", "I5", "J6", "K");
                assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{1, 2, 3, 4, 10}, array);
    }

    @Test
    void testMaxIsLastElement() {
        int[] array = {1, 2, 3, 4, 10};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B4", "C4", "Negative0", "D", "SHIFT0", "E5", "F5", "G", "H11", "I5", "J6", "K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{1, 2, 3, 4, 10}, array);
    }

    @Test
    void testZerosAndPositiveNumbers() {
        int[] array = {0, 1, 2, 0, 3, 0};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B5", "C3", "Negative0", "D", "SHIFT0", "E6", "F6", "G", "H4", "I6", "J0", "K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{0, 0, 0, 1, 2, 3}, array);
    }

    @Test
    void testWithNegativeNumbers() {
        int[] array = {-3, -20, 19, 0, -1, 6};
        trace = new ArrayList<>();
        sortArrayByCountingMethod(array);
        List<String> expectedTrace = Arrays.asList("A", "B5", "C1", "Negative1", "D", "SHIFT20", "E6", "F6", "G", "H40", "I6", "J34", "K");
        assertEquals(expectedTrace, trace);
        assertArrayEquals(new int[]{-20, -3, -1, 0, 6, 19}, array);
    }
}
