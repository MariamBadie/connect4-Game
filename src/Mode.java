public interface Mode {
    boolean isFull();
    void makeMove(int i);
    boolean isWon();
    void switchTurn();
}
