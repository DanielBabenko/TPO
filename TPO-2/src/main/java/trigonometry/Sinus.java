package trigonometry;

import static java.lang.Math.*;

public class Sinus {
    public double calculate(double alpha){
        int n = 175;

        while (alpha < -1000){
            alpha += 2*PI;
        }

        alpha %= (2 * PI);

        if (alpha > PI) {
            alpha -= (2*PI);
        }

       // alpha = alpha * PI / 180;
        double sinus = 0;
        double factorial = 1;

        if (alpha == 0 || abs(alpha) < 1e-10){
            return sinus;
        }

        for (int i = 0; i <= n; i++){
            double p = pow(-1, i);
            double num = pow(alpha, 2*i + 1);
            factorial /= (2*i + 1);
            sinus += p * num * factorial;
            factorial /= (2*i + 2);
        }

        return sinus;
    }
}
