package trigonometry;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Tangent {
    //private Sinus sinus = new Sinus();
    //private Cosinus cosinus = new Cosinus();
    public double calculate(double alpha, Sinus sinus, Cosinus cosinus) {
        if (alpha % (PI/2) == 0 & alpha % (PI) != 0){
            return NaN;
        }
        double sin = sinus.calculate(alpha);
        double cos = cosinus.calculate(alpha, sinus);

        double res = sin / cos;
        return res;
    }
}
