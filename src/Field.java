import java.util.Random;

class Field {
    Cell[][] field = new Cell[10][10];
    private Random random = new Random();
    public Field() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                field[i][j] = Cell.EMPTY;
            }
        }
    }

    public void print() {
        System.out.print("    ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    public void printHidden() {
        System.out.print("    ");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
        for (int i = 0; i < field.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == Cell.SHIP) {
                    System.out.print(Cell.EMPTY.getSymbol() + " ");
                } else {
                    System.out.print(field[i][j].getSymbol() + " ");
                }
            }
            System.out.println();
        }
    }

    public boolean putShip(int row, int col) {
        if (!isInside(row,col)) {
            System.out.println("Неверные координаты");
            return false;
        }
        if (field[row][col] != Cell.EMPTY || hasShipNear(row,col)) {
            System.out.println("Место занято или рядом корабль");
            return false;
        }
        field[row][col] = Cell.SHIP;
        return true;
    }

    public boolean putHorizontalShip(int row, int col, int length) {
        int lastCol = col + length - 1;
        if (length <= 0 ) {
            System.out.println("Неверная длина корабля");
            return false;
        }
        if (!isInside(row,col)) {
            System.out.println("Начальная клетка вне поля");
            return false;
        }
        if (!isInside(row, lastCol)) {
            System.out.println("Корабль не помещается");
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (field[row][col + i] != Cell.EMPTY) {
                System.out.println("Место занято");
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            if (hasShipNear(row, col + i)) {
                System.out.println("Рядом уже есть корабль");
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            field[row][col + i] = Cell.SHIP;

        }
        return true;
    }

    public boolean putVerticalShip(int row, int col, int length) {
        int lastRow = row + length - 1;
        if (length <= 0 ) {
            System.out.println("Неверная длина корабля");
            return false;
        }
        if (!isInside(row,col)) {
            System.out.println("Начальная клетка вне поля");
            return false;
        }
        if (!isInside(lastRow, col)) {
            System.out.println("Корабль не помещается");
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (field[row + i][col] != Cell.EMPTY) {
                System.out.println("Место занято");
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            if (hasShipNear(row + i, col)) {
                System.out.println("Рядом уже есть корабль");
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            field[row + i][col] = Cell.SHIP;

        }
        return true;
    }

    public void shoot(int row, int col) {
        if (!isInside(row,col)) {
            System.out.println("Неверные координаты");
            return;
        }
        if (field[row][col] == Cell.SHIP) {
            field[row][col] = Cell.HIT;
            System.out.println("Попал!");
            return;
        } else if (field[row][col] == Cell.EMPTY) {
            field[row][col] = Cell.MISS;
            System.out.println("Мимо");
            return;
        } else  {
            System.out.println("Сюда уже стреляли");
            return;
        }
    }

    public boolean hasShips() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == Cell.SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setupShips() {
        putRandomShip(4);

        putRandomShip(3);
        putRandomShip(3);

        putRandomShip(2);
        putRandomShip(2);
        putRandomShip(2);

        putRandomShip(1);
        putRandomShip(1);
        putRandomShip(1);
        putRandomShip(1);
    }

    private boolean isInside(int row, int col) {
        if (row < 0 || col < 0 || row >= field.length || col >= field[row].length) {
            return false;
        }
        return true;
    }
    private boolean hasShipNear(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (isInside(i,j) && field[i][j] == Cell.SHIP) {
                    return true;
                }
            }
        } return false;
    }
    private void putRandomShip(int length) {
        boolean shipPlaced = false;

        while (!shipPlaced) {
            int randomRow = random.nextInt(10);
            int randomCol = random.nextInt(10);
            boolean horizontal = random.nextBoolean();
            if (horizontal) {
                shipPlaced = putHorizontalShip(randomRow, randomCol, length);
            } else {
                shipPlaced = putVerticalShip(randomRow, randomCol, length);
            }
        }
    }
}
