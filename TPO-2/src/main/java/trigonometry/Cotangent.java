package trigonometry;

import static java.lang.Double.NaN;
import static java.lang.Math.PI;

public class Cotangent {
    //private Tangent tangent = new Tangent();
    public double calculate(double alpha, Tangent tangent) {
        if (alpha % PI == 0){
            return NaN;
        }

        double tg = tangent.calculate(alpha, new Sinus(), new Cosinus());

        double res = 1 / tg;
        if (alpha % (PI/2) == 0 & alpha % (PI) != 0){
            res = 0;
        }
        return res;
    }
}
