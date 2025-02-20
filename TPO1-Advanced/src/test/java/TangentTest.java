import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;
import static task1.Tangent.*;

public class TangentTest {
    private static final double DELTA = 0.01;

    @Test
    void testZeroDegrees() {
        assertEquals(0.0, tangentTaylor(0, 10), DELTA);
    }

    @Test
    void testSmallAngle() {
        assertEquals(tan(10 * PI / 180), tangentTaylor(10, 10), DELTA);
    }

    @Test
    void testRandomAcuteAngle() {
        assertEquals(tan(52 * PI / 180), tangentTaylor(52, 10), DELTA);
    }

    @Test
    void testLargeAngle() {
        assertEquals(tan(80 * PI / 180), tangentTaylor(80, 10), DELTA);
    }

    @Test
    void testObtuseAngle() {
        double expected = tan(126 * PI / 180);
        assertEquals(expected, tangentTaylor(126, 10), DELTA);
    }

    @Test
    void testNegativeAngle() {
        assertEquals(tan(-33 * PI / 180), tangentTaylor(-33, 10), DELTA);
    }

    @Test
    void test90Degrees() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    tangentTaylor(90, 10);
                }
        );

        String expectedMessage = "It is impossible to calculate tg(x) for angles like (π/2 + π*k, k ∈ Z)";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void test270Degrees() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    tangentTaylor(270, 10);
                }
        );

        String expectedMessage = "It is impossible to calculate tg(x) for angles like (π/2 + π*k, k ∈ Z)";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testNegativeAmountOfSeries() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    tangentTaylor(52, -10);
                }
        );

        String expectedMessage = "You cannot create a Taylor series without or with negative amount of elements";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(doubles = {180, 360, 540, -360})
    void testMultipleOf180(double alpha) {
        assertEquals(0.0, tangentTaylor(alpha, 10), DELTA);
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 5, 15})
    void testDifferentN(int n) {
        assertEquals(tan(39.52 * PI / 180), tangentTaylor(39.52, n), DELTA);
    }


    void testTangentWithVaryingN(double alpha, double delta) {
        double expected = tan(alpha * PI / 180);
        int n = 0;
        double actual = tangentTaylor(alpha, n);

        while (abs(expected - actual) > delta && n <= 9) {
            n++;
            actual = tangentTaylor(alpha, n);
        }

        System.out.println(n);
        assertTrue(abs(expected - actual) <= delta,
                "Failed to achieve required accuracy for angle " + alpha + " with " + n + " series members");
    }

    @Test
    void testNear90Degrees() {
        testTangentWithVaryingN(89, DELTA);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 15, 52})
    void testNear90Degrees(int n) {
        assertEquals(tan(89 * PI / 180), tangentTaylor(89, n), DELTA);
    }
}

