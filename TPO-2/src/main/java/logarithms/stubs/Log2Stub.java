package logarithms.stubs;

import logarithms.Log2;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.E;

public class Log2Stub extends Log2 {
    private static final Map<Double, Double> resultMap = new HashMap<>();

    static {
        resultMap.put(0.1, -3.3219);
        resultMap.put(0.2, -2.3219);
        resultMap.put(0.5, -1.0);

        resultMap.put(1.0, 0.0);
        resultMap.put(2.0, 1.0);
        resultMap.put(4.0, 2.0);
        resultMap.put(8.0, 3.0);
        resultMap.put(16.0, 4.0);
        resultMap.put(32.0, 5.0);
        resultMap.put(64.0, 6.0);
        resultMap.put(128.0, 7.0);
        resultMap.put(256.0, 8.0);
        resultMap.put(512.0, 9.0);
        resultMap.put(1024.0, 10.0);

        resultMap.put(E, 1.4427);
        resultMap.put(3.0, 1.585);
        resultMap.put(5.0, 2.3219);
        resultMap.put(10.0, 3.3219);
    }

    public double calculate(double alpha) {
        double res = resultMap.get(alpha);

        return res;
    }
}
