import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field();

        Game game = new Game(field,scanner);
        game.start();

    }
}
