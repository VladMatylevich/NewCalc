import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine(); // Считываем выражение с консоли
        char action;
        String[] data;

// Определяем знак действия и разделяем операнды
        if (exp.contains(" + ")) {
            data = exp.split(" \\+ ");
            action = '+';
        } else if (exp.contains(" - ")) {
            data = exp.split(" - ");
            action = '-';
        } else if (exp.contains(" * ")) {
            data = exp.split(" \\* ");
            action = '*';
        } else if (exp.contains(" / ")) {
            data = exp.split(" / ");
            action = '/';
        } else {
            throw new Exception("Некорректный знак действия");
        }

// Проверяем условия для операций умножения и деления
        if (action == '*' || action == '/') {
            if (!data[0].contains("\"")) {
                throw new Exception("Первый операнд должен быть строкой, заключенной в кавычки");
            }
            if (!data[1].matches("\\d+")) {
                throw new Exception("Второй операнд должен быть числом");
            }
        }

// Удаляем кавычки из операндов
        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].replace("\"", "");
        }

// Проверяем длину операндов
        if (data[0].length() < 1 || data[0].length() > 10) {
            throw new Exception("Первый операнд должен быть строкой длиной от 1 до 10 символов");
        }

        if (data[1].length() < 1 || data[1].length() > 10) {
            throw new Exception("Второй операнд должен быть строкой длиной от 1 до 10 символов");
        }

// Выполняем действие в зависимости от знака
        if (action == '+') {
            printInQuotes(data[0] + data[1]);
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);
            String result = "";
            for (int i = 0; i < multiplier; i++) {
                result += data[0];
            }
            printInQuotes(result);
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);
            if (index == -1) {
                printInQuotes(data[0]);
            } else {
                String result = data[0].substring(0, index);
                result += data[0].substring(index + data[1].length());
                printInQuotes(result);
            }
        } else {
            int newLen = data[0].length() / Integer.parseInt(data[1]);
            String result = data[0].substring(0, newLen);
            printInQuotes(result);
        }
    }

    // Выводим результат в кавычках, с учетом ограничения на длину строки
    static void printInQuotes(String text) {
        if (text.length() > 40) {
            System.out.println("\"" + text.substring(0, 40) + "..." + "\"");
        } else {
            System.out.println("\"" + text + "\"");
        }
    }
}
