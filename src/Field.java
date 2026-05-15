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
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j].getSymbol());
            }
            System.out.println();
        }
    }

    public void printHidden() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (field[i][j] == Cell.SHIP) {
                    System.out.print(Cell.EMPTY.getSymbol() + " ");
                } else {
                    System.out.print(field[i][j].getSymbol() + " ");
                }
            } System.out.println();
        }
    }

    public void putShip(int row, int col) {
        if (row < 0 || col < 0 || row >= field.length || col >= field[row].length) {
            System.out.println("Неверные координаты");
            return;
        }
        if (field[row][col] != Cell.EMPTY) {
            System.out.println("Место занято");
            return;
        }
        field[row][col] = Cell.SHIP;
    }

    public void putHorizontalShip(int row, int col, int length) {
        if (length <= 0 || row < 0 || col < 0 || row >= field.length || col + length > field[row].length) {
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
            field[row][col + i] = Cell.SHIP;
        }
    }

    public void putVerticalShip(int row, int col, int length) {
        if (length <= 0 || row < 0 || col < 0 || row >= field.length || col >= field[row].length || row + length > field.length) {
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
            field[row + i][col] = Cell.SHIP;
        }
    }

    public void shoot(int row, int col) {
        if (row < 0 || col < 0 || row >= field.length || col >= field[row].length) {
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
}
