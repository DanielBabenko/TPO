package logarithms;

public class Log5 {

    //private Ln ln = new Ln();
    public double calculate(double alpha, Ln ln) {
        double numerator = ln.calculate(alpha);
        double denominator = ln.calculate(5);

        double result = numerator;
        result /= denominator;
        return result;
    }
}
