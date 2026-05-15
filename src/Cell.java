enum Cell {
    EMPTY("⬜"),
    SHIP("\uD83D\uDEA2"),
    HIT("X"),
    MISS("•");

    private final String symbol;

    Cell(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
