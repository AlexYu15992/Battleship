import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field();

        field.setupShips();
        field.print();
        System.out.println();


        while (field.hasShips()) {
            field.printHidden();
            System.out.println("Введите строку для выстрела от 1 до 10:");
            int row = scanner.nextInt() - 1;
            System.out.println("Введите столбец для выстрела от 1 до 10:");
            int col = scanner.nextInt() - 1;
            field.shoot(row,col);
        }
        System.out.println("Победа");
    }
}
