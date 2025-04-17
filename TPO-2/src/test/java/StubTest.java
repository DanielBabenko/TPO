import logarithms.Ln;
import logarithms.Log2;
import logarithms.Log3;
import logarithms.Log5;
import logarithms.stubs.LnStub;
import logarithms.stubs.Log2Stub;
import logarithms.stubs.Log3Stub;
import logarithms.stubs.Log5Stub;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import trigonometry.*;
import trigonometry.stubs.*;

import java.io.IOException;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StubTest {

    private final double epsilon = 0.005;
    SinusStub sinStub = new SinusStub();
    Sinus sinus = new Sinus();
    CosinusStub cosStub = new CosinusStub();
    Cosinus cosinus = new Cosinus();
    TangentStub tanStub = new TangentStub();
    Tangent tangent = new Tangent();
    CotangentStub cotStub = new CotangentStub();
    Cotangent cotangent = new Cotangent();
    SecantStub secStub = new SecantStub();
    Secant secant = new Secant();
    CosecantStub cscStub = new CosecantStub();
    Cosecant cosecant = new Cosecant();
    LnStub lnStub = new LnStub();
    Ln ln = new Ln();
    Log2Stub log2Stub = new Log2Stub();
    Log2 log2 = new Log2();
    Log3Stub log3Stub = new Log3Stub();
    Log3 log3 = new Log3();
    Log5Stub log5Stub = new Log5Stub();
    Log5 log5 = new Log5();

    @BeforeAll
    private static void clear() {
        CSVWriter.clearCsv("output.csv");
    }

    void firstFunctionWork(MainFunction function) throws IOException {
        double a = -PI / 6;
        double b = -PI / 4;
        double c = -2 * PI / 3;

        double result1 = function.count(a);
        double result2 = function.count(b);
        double result3 = function.count(c);

        double expectedResult1 = 1813.8006;
        double expectedResult2 = 11.146447;
        double expectedResult3 = 25.00436;
        //System.out.println(result1 + " " + result2 + " " + result3);

        assertAll(
                "Testing All Stubs",
                () -> assertEquals(expectedResult1, result1, epsilon),
                () -> assertEquals(expectedResult2, result2, epsilon),
                () -> assertEquals(expectedResult3, result3, epsilon)
        );
    }

    void secondFunctionWork(MainFunction function) throws IOException {
        double a = 1;
        double b = 2;
        double c = 3;

        double result1 = function.count(a);
        double result2 = function.count(b);
        double result3 = function.count(c);

        double expectedResult1 = NaN;
        double expectedResult2 = 4.466355;
        double expectedResult3 = 9.6172109;
        //System.out.println(result1 + " " + result2 + " " + result3);

        assertAll(
                "Testing All Stubs",
                () -> assertEquals(expectedResult1, result1, epsilon),
                () -> assertEquals(expectedResult2, result2, epsilon),
                () -> assertEquals(expectedResult3, result3, epsilon)
        );
    }

    @Test
    public void testWithAllStubs() throws IOException {
        MainFunction function = new MainFunction(sinStub, cosStub, tanStub, cotStub, secStub, cscStub,
                lnStub, log2Stub, log3Stub,log5Stub);

        firstFunctionWork(function);
        secondFunctionWork(function);
    }

    @Test
    public void testWithoutSinStub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosStub, tanStub, cotStub, secStub, cscStub,
                lnStub, log2Stub, log3Stub,log5Stub);

        firstFunctionWork(function);
    }

    @Test
    public void testWithoutCosStub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tanStub, cotStub, secStub, cscStub,
                lnStub, log2Stub, log3Stub,log5Stub);

        firstFunctionWork(function);
    }

    @Test
    public void testWithoutTanStub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotStub, secStub, cscStub,
                lnStub, log2Stub, log3Stub,log5Stub);

        firstFunctionWork(function);
    }

    @Test
    public void testWithoutCotStub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotangent, secStub, cscStub,
                lnStub, log2Stub, log3Stub,log5Stub);

        firstFunctionWork(function);
    }

    @Test
    public void testWithoutSecStub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotangent, secant, cscStub,
                lnStub, log2Stub, log3Stub,log5Stub);

        firstFunctionWork(function);
    }

    @Test
    public void testWithoutCscStub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotangent, secant, cosecant,
                lnStub, log2Stub, log3Stub,log5Stub);

        firstFunctionWork(function);
    }

    @Test
    public void testWithoutLnStub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotangent, secant, cosecant,
                ln, log2Stub, log3Stub,log5Stub);

        secondFunctionWork(function);
    }

    @Test
    public void testWithoutLog2Stub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotangent, secant, cosecant,
                ln, log2, log3Stub,log5Stub);

        secondFunctionWork(function);
    }

    @Test
    public void testWithoutLog3Stub() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotangent, secant, cosecant,
                ln, log2, log3,log5Stub);

        secondFunctionWork(function);
    }

    @Test
    public void testWithoutStubs() throws IOException {
        MainFunction function = new MainFunction(sinus, cosinus, tangent, cotangent, secant, cosecant,
                ln, log2, log3,log5);

        firstFunctionWork(function);
        secondFunctionWork(function);
    }
}
