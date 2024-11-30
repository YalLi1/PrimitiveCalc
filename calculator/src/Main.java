import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String inputFilePath = "C:\\Users\\New\\Desktop\\txt\\input.txt"; // Полный путь к файлу input.txt
        String outputFilePath = "C:\\Users\\New\\Desktop\\txt\\output.txt"; // Полный путь к файлу output.txt

        // Для хранения результатов
        StringBuilder outputBuilder = new StringBuilder();

        // Считываем ввод из файла input.txt
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String input;
            while ((input = reader.readLine()) != null) { // Читаем строки пока не достигнем конца файла
                String resultMessage = processInputLine(input);
                outputBuilder.append(input).append(" = ").append(resultMessage).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return; // Выходим, если не удалось прочитать файл
        }

        // Записываем результаты в файл output.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            writer.write(outputBuilder.toString()); // Записываем все результаты сразу
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    private static String processInputLine(String input) {
        try {
            // Разбиваем строку на части
            String[] parts = input.split(" ");
            if (parts.length != 3) {
                throw new IllegalArgumentException();
            }

            // Парсим числа и операцию
            double a = parseNumber(parts[0]);
            double b = parseNumber(parts[2]);
            String operation = parts[1];

            // Выполняем вычисление
            double result = performOperation(a, b, operation);
            return String.valueOf(result);

        } catch (NumberFormatException e) {
            return "Error! Not number";
        } catch (IllegalArgumentException e) {
            return "Operation Error!";
        } catch (ArithmeticException e) {
            return "Error! Division by zero";
        } catch (Exception e) {
            return "Unexpected Error!";
        }
    }

    private static double parseNumber(String str) throws NumberFormatException {
        return Double.parseDouble(str);
    }

    private static double performOperation(double a, double b, String operation) throws IllegalArgumentException, ArithmeticException {
        switch (operation) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new ArithmeticException();
                }
                return a / b;
            default:
                throw new IllegalArgumentException();
        }
    }
}
