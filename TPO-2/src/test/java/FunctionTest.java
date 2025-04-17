import logarithms.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import trigonometry.*;

import java.io.IOException;

import static java.lang.Double.NaN;
import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
    Sinus sin = new Sinus();
    Cosinus cos = new Cosinus();
    Tangent tan = new Tangent();
    Cotangent cot = new Cotangent();
    Secant sec = new Secant();
    Cosecant csc = new Cosecant();
    Ln ln = new Ln();
    Log2 log2 = new Log2();
    Log3 log3 = new Log3();
    Log5 log5 = new Log5();

    MainFunction function = new MainFunction(sin, cos, tan, cot, sec, csc, ln, log2, log3, log5);
    private final double epsilon = 0.001;

    @BeforeAll
    private static void clear() {
        CSVWriter.clearCsv("output.csv");
    }

    @Test
    public void testSinCosRelationship() {
        double sinValue = sin.calculate(PI / 24 + 52);
        double cosValue = cos.calculate(PI / 24 + 52, sin);

        assertEquals(1.0, sinValue * sinValue + cosValue * cosValue, epsilon);
    }

    @Test
    public void testPeriod() throws IOException {
        double x = -23.52;
        double res1 = function.count(x);
        double res2 = function.count(x+(2*PI));
        double res3 = function.count(x + (1*PI));
        //System.out.println(res1 + " " + res2 + " " + res3);
        assertEquals(res1, res2);
        assertNotEquals(res1, res3);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, 1.6909125123605662, -PI / 2, -2*PI, -52*PI})
    public void testBorders(double alpha) throws IOException {
        //Границы в первой части кратны PI / 2
        //Во второй - 1 и довольно специфичное значение
        double res = function.count(alpha);
        assertEquals(NaN, res);
    }

    @ParameterizedTest
    @CsvSource({
            "1000, 1473.8192",
            "1000000, 11343.022",
            "-1000, -0.23515575",
            "-10000, 1.098547056E6"
    })
    public void testWithLargeValues(double alpha, double expected) throws IOException {
        double res = function.count(alpha);
        assertEquals(expected, res, epsilon);
    }
}