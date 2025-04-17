package logarithms.stubs;

import logarithms.Log5;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.E;

public class Log5Stub extends Log5 {
    private static final Map<Double, Double> resultMap = new HashMap<>();

    static {
        resultMap.put(0.1, -1.4307);
        resultMap.put(0.2, -1.0);
        resultMap.put(0.5, -0.4307);

        resultMap.put(1.0, 0.0);
        resultMap.put(5.0, 1.0);
        resultMap.put(25.0, 2.0);
        resultMap.put(125.0, 3.0);
        resultMap.put(625.0, 4.0);
        resultMap.put(3125.0, 5.0);

        resultMap.put(2.0, 0.4307);
        resultMap.put(E, 0.6213);
        resultMap.put(3.0, 0.6826);
        resultMap.put(10.0, 1.4307);
    }

    public double calculate(double alpha) {
        double res = resultMap.get(alpha);

        return res;
    }
}
