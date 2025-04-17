package logarithms.stubs;

import logarithms.Ln;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.*;

public class LnStub extends Ln {
    private static final Map<Double, Double> resultMap = new HashMap<>();

    static {
        resultMap.put(0.1, -2.3026);
        resultMap.put(0.2, -1.6094);
        resultMap.put(0.5, -0.6931);
        resultMap.put(1.0, 0.0);
        resultMap.put(2.0, 0.6931);
        resultMap.put(E, 1.0);
        resultMap.put(3.0, 1.0986);
        resultMap.put(5.0, 1.6094);
        resultMap.put(10.0, 2.3026);
    }

    public double calculate(double alpha) {
        double res = resultMap.get(alpha);

        return res;
    }
}
