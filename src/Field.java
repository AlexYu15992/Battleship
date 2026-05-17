class Field {
    Cell[][] field = new Cell[10][10];

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

    public void putShip(int row, int col) {
        if (!isInside(row,col)) {
            System.out.println("Неверные координаты");
            return;
        }
        if (field[row][col] != Cell.EMPTY || hasShipNear(row,col)) {
            System.out.println("Место занято или рядом корабль");
            return;
        }
        field[row][col] = Cell.SHIP;
    }

    public void putHorizontalShip(int row, int col, int length) {
        int lastCol = col + length - 1;
        if (length <= 0 ) {
            System.out.println("Неверная длина корабля");
            return;
        }
        if (!isInside(row,col)) {
            System.out.println("Начальная клетка вне поля");
            return;
        }
        if (!isInside(row, lastCol)) {
            System.out.println("Корабль не помещается");
            return;
        }
        for (int i = 0; i < length; i++) {
            if (field[row][col + i] != Cell.EMPTY) {
                System.out.println("Место занято");
                return;
            }
        }
        for (int i = 0; i < length; i++) {
            if (hasShipNear(row, col + i)) {
                System.out.println("Рядом уже есть корабль");
                return;
            }
        }
        for (int i = 0; i < length; i++) {
            field[row][col + i] = Cell.SHIP;
        }
    }

    public void putVerticalShip(int row, int col, int length) {
        int lastCol = row + length - 1;
        if (length <= 0 ) {
            System.out.println("Неверная длина корабля");
            return;
        }
        if (!isInside(row,col)) {
            System.out.println("Начальная клетка вне поля");
            return;
        }
        if (!isInside(row, lastCol)) {
            System.out.println("Корабль не помещается");
            return;
        }
        for (int i = 0; i < length; i++) {
            if (field[row + i][col] != Cell.EMPTY) {
                System.out.println("Место занято");
                return;
            }
        }
        for (int i = 0; i < length; i++) {
            if (hasShipNear(row + i, col)) {
                System.out.println("Рядом уже есть корабль");
                return;
            }
        }
        for (int i = 0; i < length; i++) {
            field[row + i][col] = Cell.SHIP;
        }
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
        putHorizontalShip(0, 0, 3);
        putVerticalShip(3, 5, 2);
        putShip(9, 9);
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
}
