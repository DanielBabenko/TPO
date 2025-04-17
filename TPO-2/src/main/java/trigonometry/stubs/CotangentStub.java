package trigonometry.stubs;

import trigonometry.Cotangent;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class CotangentStub extends Cotangent {
    private final double epsilon = 1e-9;
    private static final Map<Double, Double> resultMap = new HashMap<>();

    static {
        resultMap.put(0.0, NaN);
        resultMap.put(PI / 6, sqrt(3));
        resultMap.put(PI / 4, 1.0);
        resultMap.put(PI / 3, sqrt(3) / 3);
        resultMap.put(PI / 2, 0.0);
        resultMap.put(2 * PI / 3, -sqrt(3) / 3);
        resultMap.put(3 * PI / 4, -1.0);
        resultMap.put(5 * PI / 6, -sqrt(3));
        resultMap.put(PI, NaN);
        resultMap.put(7 * PI / 6, sqrt(3));
        resultMap.put(5 * PI / 4, 1.0);
        resultMap.put(4 * PI / 3, sqrt(3) / 3);
        resultMap.put(3 * PI / 2, 0.0);
        resultMap.put(5 * PI / 3, -sqrt(3) / 3);
        resultMap.put(7 * PI / 4, -1.0);
        resultMap.put(11 * PI / 6, -sqrt(3));
        resultMap.put(2 * PI, NaN);
    }

    public double calculate(double alpha) {
        alpha %= (2*PI);
        if (alpha < 0){
            alpha += 2*PI;
        }

        double res = 0;
        for (Map.Entry<Double, Double> entry : resultMap.entrySet()) {
            if (Math.abs(alpha - entry.getKey()) <= epsilon) {
                res = entry.getValue();
            }
        }

        if (Double.isNaN(res)){
            throw new IllegalArgumentException("There is no cot(α) for this α value!");
        }

        return res;
    }
}

