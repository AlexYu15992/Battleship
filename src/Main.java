import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field();


        field.putHorizontalShip(0, 0, 3);


        while (field.hasShips()) {
            field.printHidden();
            System.out.println("Введите строку для выстрела:");
            int row = scanner.nextInt() - 1;
            System.out.println("Введите столбец для выстрела:");
            int col = scanner.nextInt() - 1;
            field.shoot(row,col);
        }
        System.out.println("Победа");
    }
}
