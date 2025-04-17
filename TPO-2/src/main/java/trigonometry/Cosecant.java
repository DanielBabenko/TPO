package trigonometry;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Cosecant {
    //private Sinus sinus = new Sinus();
    public double calculate(double alpha, Sinus sinus) {
        if (alpha % (PI) == 0){
            return NaN;
        }

        double sin = sinus.calculate(alpha);

        double res = 1 / sin;
        return res;
    }
}