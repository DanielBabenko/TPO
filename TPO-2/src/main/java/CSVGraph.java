import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVGraph {

    public static void main(String[] args) throws IOException {

        String filePath = "output.csv"; // Укажите путь к вашему CSV файлу

        // Списки для хранения данных из CSV
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        // Чтение CSV файла
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Пропускаем строку заголовка

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";"); // Изменили разделитель на ";"
                try {
                    double x = Double.parseDouble(values[0].replace(",", ".")); // Заменяем запятую на точку
                    String yValue = values[1].trim(); // Обрезаем пробелы

                    double y;
                    if (yValue.equalsIgnoreCase("NaN")) {
                        y = Double.NaN; // Обрабатываем NaN
                    } else {
                        y = Double.parseDouble(yValue.replace(",", ".")); // Заменяем запятую на точку и парсим
                    }

                    xData.add(x);
                    yData.add(y);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка при парсинге чисел в строке: " + line);
                    // Обработка ошибки парсинга, например, пропуск строки
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.err.println("Неправильный формат строки в CSV: " + line);
                    // Обработка неверного формата строки, например, пропуск строки
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
            return; // Прерываем выполнение, если произошла ошибка чтения файла
        }

        // Преобразование List<Double> в массивы double[]
        double[] xArray = xData.stream().mapToDouble(Double::doubleValue).toArray();
        double[] yArray = yData.stream().mapToDouble(Double::doubleValue).toArray();

        // Создание графика с помощью XChart
        XYChart chart = QuickChart.getChart(
                "График из CSV файла", // Название графика
                "alpha", // Ось X
                "res", // Ось Y
                "Данные", // Название серии
                xArray, // Данные для оси X
                yArray  // Данные для оси Y
        );

        // Ограничение области видимости осей
        double xMin = -50;    // Минимальное значение для оси X
        double xMax = 50;    // Максимальное значение для оси X
        double yMin = -50;   // Минимальное значение для оси Y
        double yMax = 50;   // Максимальное значение для оси Y

        chart.getStyler().setXAxisMin(xMin);
        chart.getStyler().setXAxisMax(xMax);
        chart.getStyler().setYAxisMin(yMin);
        chart.getStyler().setYAxisMax(yMax);

        // Настройка отображения NaN значений
        chart.getStyler().setYAxisTicksVisible(true);  // Показывать деления на оси Y
        chart.getStyler().setChartBackgroundColor(java.awt.Color.WHITE); // Белый фон
        chart.getStyler().setPlotGridLinesVisible(false); //Скрыть сетку

        // Отображение графика
        new SwingWrapper<>(chart).displayChart();
    }
}