package trigonometry;

import static java.lang.Math.*;

public class Cosinus {
    //private Sinus sinus = new Sinus();
    public double calculate(double alpha, Sinus sinus) {

        double sin = sinus.calculate(alpha);
        double squareCos = 1 - pow(sin, 2);

        alpha %= (2 * PI);
        //alpha = alpha * PI / 180;

        if (alpha < 0) {
            alpha += (2*PI);
        }

        double sign = 1;
        if (abs(alpha) >= PI/2 && abs(alpha) <= 3 * PI / 2) {
            sign = -1;
        }

        return sqrt(abs(squareCos)) * sign;
    }
}
