package logarithms.stubs;

import logarithms.Log3;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.E;

public class Log3Stub extends Log3 {
    private static final Map<Double, Double> resultMap = new HashMap<>();

    static {
        resultMap.put(0.1, -2.0959);
        resultMap.put(0.2, -1.465);
        resultMap.put(0.5, -0.6309);

        resultMap.put(1.0, 0.0);
        resultMap.put(3.0, 1.0);
        resultMap.put(9.0, 2.0);
        resultMap.put(27.0, 3.0);
        resultMap.put(81.0, 4.0);
        resultMap.put(243.0, 5.0);
        resultMap.put(729.0, 6.0);

        resultMap.put(2.0, 0.6309);
        resultMap.put(E, 0.9102);
        resultMap.put(5.0, 1.465);
        resultMap.put(10.0, 2.0959);
    }

    public double calculate(double alpha) {
        double res = resultMap.get(alpha);

        return res;
    }
}
