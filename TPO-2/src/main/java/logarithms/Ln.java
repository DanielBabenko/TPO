package logarithms;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Ln {
    public double calculate(double alpha) {
        int n = 10000;

        double ln = 0;

        while (alpha >= 2){
            alpha /= 1.5;
            ln += calculate(1.5);
        }

        alpha = alpha - 1;

        //только если |alpha - 1| < 1
        for (int i = 0; i <= n; i++){
            double p = pow(-1, i);
            double num = pow(alpha, i + 1);
            double divider = i + 1;

            double member = p * num;
            member /= divider;
            ln += member;
        }

        return ln;
    }
}
