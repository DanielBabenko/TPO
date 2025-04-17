package logarithms;

public class Log2 {

    //private Ln ln = new Ln();
    public double calculate(double alpha, Ln ln) {
        double numerator = ln.calculate(alpha);
        double denominator = ln.calculate(2);

        double result = numerator;
        result /= denominator;
        return result;
    }
}
