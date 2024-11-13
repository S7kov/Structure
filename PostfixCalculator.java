import java.util.Stack;
import java.util.Scanner;

public class PostfixCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение в постфиксной нотации (разделите пробелами):");
        String input = scanner.nextLine();
        
        try {
            double result = evaluatePostfix(input.split(" "));
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static double evaluatePostfix(String[] tokens) {
        Stack<Double> stack = new Stack<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                // Извлекаем два верхних элемента стека
                double b = stack.pop();
                double a = stack.pop();
                double result = performOperation(a, b, token);
                stack.push(result);
            } else {
                // Преобразуем токен в число и добавляем в стек
                stack.push(Double.parseDouble(token));
            }
        }

        return stack.pop(); // Возвращаем результат
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private static double performOperation(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Деление на ноль!");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        }
    }
}