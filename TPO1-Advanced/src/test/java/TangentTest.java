import org.junit.jupiter.api.Test;

import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;
import static task1.Tangent.*;

public class TangentTest {
    private static final double DELTA = 0.001;

    @Test
    void testZeroDegrees() {
        assertEquals(0.0, tangentTaylor(0, 10), DELTA);
    }

    @Test
    void testSmallAngle() {
        assertEquals(Math.tan(10 * PI / 180), tangentTaylor(10, 10), DELTA);
    }

    @Test
    void testRandomAcuteAngle() {
        assertEquals(Math.tan(52 * PI / 180), tangentTaylor(52, 10), DELTA);
    }

    @Test
    void testLargeAngle() {
        assertEquals(Math.tan(80 * PI / 180), tangentTaylor(80, 10), DELTA);
    }

    @Test
    void testObtuseAngle() {
        double expected = Math.tan(120 * PI / 180);
        assertEquals(expected, tangentTaylor(120, 10), DELTA);
    }

    @Test
    void testNegativeAngle() {
        assertEquals(Math.tan(-33 * PI / 180), tangentTaylor(-33, 10), DELTA);
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
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
    }

    @Test
    void testTangentTaylor270Degrees() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    tangentTaylor(270, 10);
                }
        );

        String expectedMessage = "It is impossible to calculate tg(x) for angles like (π/2 + π*k, k ∈ Z)";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage, "Exception message is incorrect");
    }

    @Test
    void testTangentTaylorMultipleOf180() {
        assertEquals(0.0, tangentTaylor(180, 10), DELTA);
        assertEquals(0.0, tangentTaylor(360, 10), DELTA);
        assertEquals(0.0, tangentTaylor(540, 10), DELTA);
    }


    @Test
    void testTangentTaylorDifferentN() {
        assertEquals(Math.tan(30 * PI / 180), tangentTaylor(30, 2), DELTA);
        assertEquals(Math.tan(30 * PI / 180), tangentTaylor(30, 5), DELTA);
        assertEquals(Math.tan(30 * PI / 180), tangentTaylor(30, 15), DELTA);
    }
}

