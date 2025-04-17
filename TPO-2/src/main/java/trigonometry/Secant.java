package trigonometry;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Secant {
    //private Cosinus cosinus = new Cosinus();
    public double calculate(double alpha, Cosinus cosinus) {
        if (alpha % (PI/2) == 0 & alpha % (PI) != 0){
            return NaN;
        }

        double cos = cosinus.calculate(alpha, new Sinus());

        double res = 1 / cos;
        return res;
    }
}
