import logarithms.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.mockito.Spy;
import trigonometry.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Double.NaN;
import static java.lang.Math.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockTest {
    @Spy
    private Sinus mockSin = Mockito.mock(Sinus.class);
    @Spy
    private Cosinus mockCos = Mockito.mock(Cosinus.class);
    @Spy
    private Tangent mockTan = Mockito.mock(Tangent.class);
    @Spy
    private Cotangent mockCot = Mockito.mock(Cotangent.class);
    @Spy
    private Secant mockSec = Mockito.mock(Secant.class);
    @Spy
    private Cosecant mockCsc = Mockito.mock(Cosecant.class);
    @Spy
    private Ln mockLn = Mockito.mock(Ln.class);
    @Spy
    private Log2 mockLog2 = Mockito.mock(Log2.class);
    @Spy
    private Log3 mockLog3 = Mockito.mock(Log3.class);
    @Spy
    private Log5 mockLog5 = Mockito.mock(Log5.class);

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

    @BeforeAll
    private static void clear() {
        CSVWriter.clearCsv("output.csv");
    }

    private final double epsilon = 0.005;
    private static final double specific = pow(E, (Math.log(5) / ((1 / Math.log(5)) + (1 / Math.log(2)) + 1)));

    static List<Double> provideTrigonometryData(){

        List<Double> data = new ArrayList<>(Arrays.asList(0.0, PI / 6, PI / 4, PI / 3, PI / 2,
                2 * PI / 3, 3 * PI / 4, 5 * PI / 6, PI,
                7 * PI / 6, 5 * PI / 4, 4 * PI / 3, 3 * PI / 2,
                5 * PI / 3, 7 * PI / 4, 11 * PI / 6, 2 * PI));

        double d = -2 * PI;
        while (d < 2 * PI){
            data.add(d);
            d += 0.1;
        }

        return data;
    }

    static List<Double> provideExtraTrigonometryData(){
        //для периодов
        List<Double> data = new ArrayList<>();
        double d = -20 * PI;
        while (d < -2 * PI){
            data.add(d);
            d += 0.1;
        }

        //для перегибов
        d = -4 * PI / 5;
        while (d > -20 * PI){
            data.add(d);
            d -= PI;
        }

        return data;
    }

    static List<Double> provideLogarithmData(){
            List<Double> data = new ArrayList<>(Arrays.asList(0.1, 0.2, 0.5, 1.0, specific, E, 10.0));

            int[] nums = {2, 3, 5};

            for (int n : nums){
                for (int j = 1; j < 11; j++){
                  data.add(pow(n, j));
                }
            }

            double d = 4;
            while (d < 100){
                data.add(d);
                d ++;
            }

            return data;
    }

    static List<Double> provideAllData(){
        List<Double> rareData = Stream.concat(provideTrigonometryData().stream(), provideLogarithmData().stream()).toList();
        List<Double> rawData = Stream.concat(provideExtraTrigonometryData().stream(), rareData.stream()).toList();
        List<Double> data = new ArrayList<>(rawData);

        Collections.sort(data);
        return data;
    }

    private void initializeSinMock(double alpha){
        when(mockSin.calculate(any(Double.class))).thenReturn(Math.sin(alpha));
    }

    private void initializeCosMock(double alpha){
        when(mockCos.calculate(any(Double.class), any(Sinus.class))).thenReturn(Math.cos(alpha));
    }

    private void initializeTanMock(double alpha){
        if (alpha % (PI/2) == 0 & alpha % (PI) != 0) {
            when(mockTan.calculate(any(Double.class), any(Sinus.class), any(Cosinus.class))).thenReturn(NaN);
        } else {
            when(mockTan.calculate(any(Double.class), any(Sinus.class), any(Cosinus.class))).thenReturn(Math.tan(alpha));
        }
    }

    private void initializeCotMock(double alpha){
        if (alpha % (PI) == 0) {
            when(mockCot.calculate(any(Double.class), any(Tangent.class))).thenReturn(NaN);
        } else {
            when(mockCot.calculate(any(Double.class), any(Tangent.class))).thenReturn(1 / Math.tan(alpha));
        }
    }

    private void initializeSecMock(double alpha){
        if (alpha % (PI/2) == 0 & alpha % (PI) != 0) {
            when(mockSec.calculate(any(Double.class), any(Cosinus.class))).thenReturn(NaN);
        } else {
            when(mockSec.calculate(any(Double.class), any(Cosinus.class))).thenReturn(1 / Math.cos(alpha));
        }
    }

    private void initializeCscMock(double alpha){
        if (alpha % (PI) == 0) {
            when(mockCsc.calculate(any(Double.class), any(Sinus.class))).thenReturn(NaN);
        } else {
            when(mockCsc.calculate(any(Double.class), any(Sinus.class))).thenReturn(1 / Math.sin(alpha));
        }
    }
    private void initializeLnMock(double alpha){
        when(mockLn.calculate(any(Double.class))).thenReturn(Math.log(alpha));
        when(mockLn.calculate(2)).thenReturn(Math.log(2));
        when(mockLn.calculate(3)).thenReturn(Math.log(3));
        when(mockLn.calculate(5)).thenReturn(Math.log(5));
    }

    private void initializeLog2Mock(double alpha){
        when(mockLog2.calculate(any(Double.class), any(Ln.class))).thenReturn(Math.log(alpha) / Math.log(2));
    }

    private void initializeLog3Mock(double alpha){
        when(mockLog3.calculate(any(Double.class), any(Ln.class))).thenReturn(Math.log(alpha) / Math.log(3));
    }

    private void initializeLog5Mock(double alpha){
        when(mockLog5.calculate(any(Double.class), any(Ln.class))).thenReturn(Math.log(alpha) / Math.log(5));
    }

    private void initializeAllMocks(double alpha){
        initializeSinMock(alpha);
        initializeCosMock(alpha);
        initializeTanMock(alpha);
        initializeCotMock(alpha);
        initializeSecMock(alpha);
        initializeCscMock(alpha);
        //
        initializeLnMock(alpha);
        initializeLog2Mock(alpha);
        initializeLog3Mock(alpha);
        initializeLog5Mock(alpha);
    }

    private void largeValuesCheck(double res, double alpha){
        double ipsilon = 0.000001;
        if (abs(res) > 1e9){
            assertEquals(realFunction(alpha), res, ipsilon * realFunction(alpha));
        } else {
            assertEquals(realFunction(alpha), res, epsilon);
        }
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    public void justSinusTest(double alpha) {
        double res1 = Math.sin(alpha);
        double res2 = sin.calculate(alpha);
        assertEquals(res1, res2, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideLogarithmData")
    public void justLnTest(double alpha) {
        double res1 = Math.log(alpha);
        double res2 = ln.calculate(alpha);
        assertEquals(res1, res2, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    void CosMustCallSinTest(double alpha) {
        initializeSinMock(alpha);
        double res1 = cos.calculate(alpha, mockSin);
        double res2 = cos.calculate(alpha, sin);

        assertEquals(Math.cos(alpha), res1, epsilon);
        assertEquals(Math.cos(alpha), res2, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    void CscMustCallSinTest(double alpha) {
        initializeSinMock(alpha);

        double res1 = csc.calculate(alpha, mockSin);
        double res2 = csc.calculate(alpha, sin);

        if (alpha % PI == 0) {
            assertTrue(Double.isNaN(res1));
            assertTrue(Double.isNaN(res2));
        } else {
            assertEquals(1 / Math.sin(alpha), res1, epsilon);
            assertEquals(1 / Math.sin(alpha), res2, epsilon);
        }
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    void TanMustCallSinAndCosTest(double alpha) {
        initializeSinMock(alpha);
        initializeCosMock(alpha);
        double res1 = tan.calculate(alpha, mockSin, mockCos);
        double res2 = tan.calculate(alpha, sin, cos);

        if (alpha % (PI/2) == 0 & alpha % (PI) != 0) {
            assertTrue(Double.isNaN(res1));
            assertTrue(Double.isNaN(res2));
        } else {
            assertEquals(Math.tan(alpha), res1, epsilon);
            assertEquals(Math.tan(alpha), res2, epsilon);
        }
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    void SecMustCallCosTest(double alpha) {
        initializeCosMock(alpha);
        double res1 = sec.calculate(alpha, mockCos);
        double res2 = sec.calculate(alpha, cos);

        if (alpha % (PI/2) == 0 & alpha % (PI) != 0) {
            assertTrue(Double.isNaN(res1));
            assertTrue(Double.isNaN(res2));
        } else {
            assertEquals(1 / Math.cos(alpha), res1, epsilon);
            assertEquals(1 / Math.cos(alpha), res2, epsilon);
        }
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    void CotMustCallTanTest(double alpha) {
        initializeTanMock(alpha);

        double res1 = cot.calculate(alpha, mockTan);
        double res2 = cot.calculate(alpha, tan);

        if (alpha % PI == 0) {
            assertTrue(Double.isNaN(res1));
            assertTrue(Double.isNaN(res2));
        } else {
            assertEquals(1 / Math.tan(alpha), res1, epsilon);
            assertEquals(1 / Math.tan(alpha), res2, epsilon);
        }
    }

    @ParameterizedTest
    @MethodSource("provideLogarithmData")
    void Log2MustCallLnTest(double alpha) {
        initializeLnMock(alpha);

        double res1 = log2.calculate(alpha, mockLn);
        double res2 = log2.calculate(alpha, ln);

        assertEquals(Math.log(alpha) / Math.log(2), res1, epsilon);
        assertEquals(Math.log(alpha) / Math.log(2), res2, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideLogarithmData")
    void Log3MustCallLnTest(double alpha) {
        initializeLnMock(alpha);

        double res1 = log3.calculate(alpha, mockLn);
        double res2 = log3.calculate(alpha, ln);

        assertEquals(Math.log(alpha) / Math.log(3), res1, epsilon);
        assertEquals(Math.log(alpha) / Math.log(3), res2, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideLogarithmData")
    void Log5MustCallLnTest(double alpha) {
        initializeLnMock(alpha);

        double res1 = log5.calculate(alpha, mockLn);
        double res2 = log5.calculate(alpha, ln);

        assertEquals(Math.log(alpha) / Math.log(5), res1, epsilon);
        assertEquals(Math.log(alpha) / Math.log(5), res2, epsilon);
    }

    public double realFunction(double alpha){
        double res;
        if (alpha <= 0){
            res = firstPart(alpha);
        } else {
            res = secondPart(alpha);
        }

        return res;
    }

    private double firstPart(double alpha){
        double sin = Math.sin(alpha);
        double cos = Math.cos(alpha);
        double tg = Math.tan(alpha);
        double cot = 1 / Math.tan(alpha);
        double sec = 1 / Math.cos(alpha);
        double csc = 1 / Math.sin(alpha);

        if (alpha % (PI/2) == 0 & alpha % (PI) != 0){
            tg = NaN;
            sec = NaN;
        }

        else if (alpha % PI == 0){
            cot = NaN;
            csc = NaN;
        }

        double first = (((pow((((pow(csc, 2)) - tg)-(pow(sec, 2))), 2)) - cot) + cos)- cos;
        double second = (pow((((pow(cot, 2))+(cos / cos)) * (pow(cot, 2))), 2))+(pow(csc, 2));
        double last = (sin+sin)-(((((sec * cot) + sin)- (csc - tg)) + sin)*(pow(sin, 3)));

        double result = ((first * second)+ sec)+ last;

        return result;
    }

    public double secondPart(double alpha){
        if (alpha == 1 || alpha == specific){
            return NaN;
        }

        double ln = Math.log(alpha);
        double log2 = Math.log(alpha) / Math.log(2);
        double log3 = Math.log(alpha) / Math.log(3);
        double log5 = Math.log(alpha) / Math.log(5);

        double numerator = (pow((log2 + log5), 3) - log3) * log2;
        double denominator = log5 + (ln - ((ln / log5) - log2));

        double res = numerator / denominator;

        return res;
    }

    @ParameterizedTest
    @MethodSource("provideAllData")
    public void testWithAllMocks(double alpha) throws IOException {
        MainFunction function = new MainFunction(mockSin, mockCos, mockTan, mockCot, mockSec, mockCsc,
                mockLn, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        assertEquals(realFunction(alpha), res, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    public void testWithoutSinMock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, mockCos, mockTan, mockCot, mockSec, mockCsc,
                mockLn, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        assertEquals(realFunction(alpha), res, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    public void testWithoutCosMock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, mockTan, mockCot, mockSec, mockCsc,
                mockLn, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        assertEquals(realFunction(alpha), res, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    public void testWithoutTanMock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, mockCot, mockSec, mockCsc,
                mockLn, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        assertEquals(realFunction(alpha), res, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    public void testWithoutCotMock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, cot, mockSec, mockCsc,
                mockLn, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        largeValuesCheck(res, alpha);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    public void testWithoutSecMock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, cot, sec, mockCsc,
                mockLn, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        largeValuesCheck(res, alpha);
    }

    @ParameterizedTest
    @MethodSource("provideTrigonometryData")
    public void testWithoutCscMock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, cot, sec, csc,
                mockLn, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        largeValuesCheck(res, alpha);
    }

    @ParameterizedTest
    @MethodSource("provideLogarithmData")
    public void testWithoutLnMock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, cot, sec, csc,
                ln, mockLog2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        //assertEquals(realFunction(alpha), res, epsilon);
        largeValuesCheck(res, alpha);
    }

    @ParameterizedTest
    @MethodSource("provideLogarithmData")
    public void testWithoutLog2Mock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, cot, sec, csc,
                ln, log2, mockLog3, mockLog5);

        initializeAllMocks(alpha);
        double res = function.count(alpha);
        assertEquals(realFunction(alpha), res, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideLogarithmData")
    public void testWithoutLog3Mock(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, cot, sec, csc,
                ln, log2, log3, mockLog5);

        initializeLog5Mock(alpha);
        double res = function.count(alpha);
        assertEquals(realFunction(alpha), res, epsilon);
    }

    @ParameterizedTest
    @MethodSource("provideAllData")
    public void testWithoutAnyMocks(double alpha) throws IOException {
        MainFunction function = new MainFunction(sin, cos, tan, cot, sec, csc,
                ln, log2, log3, log5);
        function.setWritingFlag(true);

        double res = function.count(alpha);
        largeValuesCheck(res, alpha);
    }
}
