import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriter {
    public static void writeCsv(String filename, double alpha, double res, boolean append) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, append))) {
            if (!append) {
                writer.println("X       function(x)");
            }

            try {
                writer.println(String.format("%.6f;%.6f", alpha, res));
            } catch (IllegalArgumentException e) {
                writer.println(String.format("%.6f;NaN", alpha));
            }
        }
    }

    public static void clearCsv(String filename){
        File file = new File(filename);
        if (file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
                writer.println("X       function(x)");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
