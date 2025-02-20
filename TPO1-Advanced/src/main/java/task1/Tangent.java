package task1;

import static java.lang.Math.*;

public class Tangent {
//    public static long factorial(int n) {
//        long fact = 1;
//        for (int i = 2; i <= n; i++) {
//            fact = fact * i;
//        }
//        return fact;
//    }

    public static double tangentTaylor (double alpha, int n){
        if (alpha % 90 == 0 & alpha % 180 != 0){
            throw new IllegalArgumentException("It is impossible to calculate tg(x) for angles " +
                    "like (π/2 + π*k, k ∈ Z)");
        }

        if (n < 0){
            throw new IllegalArgumentException("You cannot create a Taylor series without or with negative amount of elements");
        }
        //начинаем ряд с 0-го элемента

        alpha %= 180;
        if (alpha > 90){
            alpha -= 180;
        }

        alpha = alpha * PI / 180;

        double res = sinTaylor(alpha, n) / cosTaylor(alpha, n);
        return res;
    }

    public static double sinTaylor(double alpha, int n){
        double sinus = 0;
        double factorial = 1;
        for (int i = 0; i <= n; i++){
            double p = pow(-1, i);
//            double factorial = factorial(2*i + 1);
            double num = pow(alpha, 2*i + 1);
            factorial /= (2*i + 1);
            sinus += p * num * factorial;
            factorial /= (2*i + 2);
        }
        //System.out.println("Sin = "+sinus);
        return sinus;

//        double sinus = 0;
//        double factorial = 1;
//        for (int i = 0; i <= n; i++){
//            double p = pow(-1, i);
//
//        }
    }

    public static double cosTaylor(double alpha, int n){
        double cosinus = 0;
        double factorial = 1;
        for (int i = 0; i <= n; i++){
            double p = pow(-1, i);
            double num = pow(alpha, 2*i);
            cosinus += p * num * factorial;
            factorial /= (2*i + 1);
            factorial /= (2*i + 2);
        }
        //System.out.println("Cos = "+cosinus);
        return cosinus;
    }

    public static void check(double alpha, int n){
        double res = tangentTaylor(alpha, n);
        //res = Math.round(res * 1000.0) / 1000.0;
        System.out.println(res);

        res = tan(alpha * PI / 180);
        //res = Math.round(res * 1000.0) / 1000.0;
        System.out.println(res);
    }

    public static void main(String[] args) {
        check(89, 5);
    }
}
