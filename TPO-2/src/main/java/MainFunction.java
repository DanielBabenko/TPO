import logarithms.*;
import trigonometry.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.Double.NaN;
import static java.lang.Math.E;
import static java.lang.Math.pow;

public class MainFunction {
    public Sinus sinus;
    public Cosinus cosinus;
    public Tangent tangent;
    public Cotangent cotangent;
    public Secant secant;
    public Cosecant cosecant;
    public Ln lnX;
    public Log2 log2X;
    public Log3 log3X;
    public Log5 log5X;

    private boolean writingFlag;

    public MainFunction(Sinus sinus, Cosinus cosinus, Tangent tangent, Cotangent cotangent, Secant secant, Cosecant cosecant, Ln lnX, Log2 log2X, Log3 log3X, Log5 log5X) {
        this.sinus = sinus;
        this.cosinus = cosinus;
        this.tangent = tangent;
        this.cotangent = cotangent;
        this.secant = secant;
        this.cosecant = cosecant;
        this.lnX = lnX;
        this.log2X = log2X;
        this.log3X = log3X;
        this.log5X = log5X;
        this.writingFlag = false;
    }

    public double count(double alpha) throws IOException {
        double res;
        if (alpha <= 0){
            res = firstPart(alpha);
        } else {
            res = secondPart(alpha);
        }

        if (writingFlag) {
            String filename = "output.csv";
            CSVWriter.writeCsv(filename, alpha, res, true);
        }

        return res;
    }

    public double firstPart(double alpha){

        double sin = sinus.calculate(alpha);
        double cos = cosinus.calculate(alpha, sinus);
        double tg = tangent.calculate(alpha, sinus, cosinus);
        double cot = cotangent.calculate(alpha, tangent);
        double sec = secant.calculate(alpha, cosinus);
        double csc = cosecant.calculate(alpha, sinus);

        double first = (((pow((((pow(csc, 2)) - tg)-(pow(sec, 2))), 2)) - cot) + cos)- cos;
        double second = (pow((((pow(cot, 2))+(cos / cos)) * (pow(cot, 2))), 2))+(pow(csc, 2));
        double last = (sin+sin)-(((((sec * cot) + sin)- (csc - tg)) + sin)*(pow(sin, 3)));

        double result = ((first * second)+ sec)+ last;

        return result;
    }

    public double secondPart(double alpha){
//        if (alpha == 1 || alpha == pow(E, (lnX.calculate(5) / (log5X.calculate(E, lnX) + log2X.calculate(E, lnX) + 1)))){
//            return NaN;
//        }
        if (alpha == 1 || alpha == 1.6909125123605662){
            return NaN;
        }

        double ln = lnX.calculate(alpha);
        double log2 = log2X.calculate(alpha, lnX);
        double log3 = log3X.calculate(alpha, lnX);
        double log5 = log5X.calculate(alpha, lnX);

        double numerator = (pow((log2 + log5), 3) - log3) * log2;
        double denominator = log5 + (ln - ((ln / log5) - log2));

        double res = numerator / denominator;

        return res;
    }

    public void setWritingFlag(boolean writingFlag) {
        this.writingFlag = writingFlag;
    }
}
