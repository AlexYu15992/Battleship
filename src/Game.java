import java.util.Scanner;

public class Game {
    private Field field;
    private Scanner scanner;
    private int movesCount;

    public Game(Field field, Scanner scanner) {
        this.field = field;
        this.scanner = scanner;
    }

    public void start() {
        printRulesMessage();
        printWelcomeMessage();
        field.setupShips();
        field.print();

        while (field.hasShips()) {
            field.printHidden();
            makeMove();
        }

        System.out.println("Победа! Ходов сделано: " + movesCount);

    }


    private void printWelcomeMessage() {
        System.out.println("Добро пожаловать в Морской бой!");

    }

    private void printRulesMessage() {
        System.out.println("Правила: вводите строку и столбец от 1 до 10.");
    }




    private void makeMove() {

        int row = readCoordinate("Введите строку для выстрела от 1 до 10:");
        int col = readCoordinate("Введите столбец для выстрела от 1 до 10:");
        field.shoot(row, col);
        movesCount++;
    }

    

    private int readCoordinate(String message) {

        while (true) {
            System.out.println(message);
            if (!scanner.hasNextInt()) {
                System.out.println("Введите число");
                scanner.next();
                continue;
            }
            int number = scanner.nextInt();
            if (number < 1 || number > 10) {
                System.out.println("Введите число от 1 до 10");
                continue;
            }
            return number - 1;
        }
    }
}
