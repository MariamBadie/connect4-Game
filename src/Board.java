public class Board {
    public static char[][] board;
    final private int rows = 6;
    final private int columns = 7;

    public Board() {
        board = new char[rows][columns];
    }

    public char getCell(int i, int j) {
        return board[i][j];
    }

    public int getRows() {
        return rows-1;
    }

    public int getColumns() {
        return columns-1;
    }

    public void initializeBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0;j<columns;j++)
                board[i][j] = ' ';
        }
    }
    public void setCell(int i, int j, char c){
        board[i][j] = c;
    }
    public void displayBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                char c = board[i][j];
                if (c != ' ') {
                    System.out.print("| " + c + " ");
                } else {
                    System.out.print("|   ");
                }
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }
}
