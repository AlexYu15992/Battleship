import java.util.Scanner;

public class Game {
    private Field field;
    private Scanner scanner;

    public Game(Field field, Scanner scanner) {
        this.field = field;
        this.scanner = scanner;
    }

    public void start() {
        field.setupShips();

        while (field.hasShips()) {
            field.printHidden();
            makeMove();
        }
        System.out.println("Победа");
    }

    private void makeMove() {

            System.out.println("Введите строку для выстрела от 1 до 10:");
            int row = scanner.nextInt() - 1;
            System.out.println("Введите столбец для выстрела от 1 до 10:");
            int col = scanner.nextInt() - 1;
            field.shoot(row,col);


    }
}
