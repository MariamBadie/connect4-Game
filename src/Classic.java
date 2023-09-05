import java.util.Scanner;

public class Classic implements Mode {
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public Board board;
    public Scanner scanner;

    public Classic() {
        player1 = new Player("Player 1", "Red");
        player2 = new Player("Player 2", "Blue");
        board = new Board();
        scanner = new Scanner(System.in);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isFull() {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (board.getCell(i, j) == ' ')
                    return false;
            }
        }
        return true;
    }

    public void makeMove(int i) {
        System.out.println("make move");
        if (i < 0 || i > board.getColumns()) {
            System.out.println("Invalid Column Try Again");
            int x = scanner.nextInt();
            makeMove(x);
        }
        char c = currentPlayer.getChip().getColour().charAt(0);
        int j = board.getRows();
        System.out.println("lol");
        while (board.getCell(j, i) != ' ') {
            System.out.println("Lmao");
            j--;
        }
        if (j >= 0) {
            System.out.println("Alo");
            board.setCell(j, i, c);
            if (isWon()) {
                System.out.println(currentPlayer.getName() + "Wins");
                //currentPlayer = null;
            }
//            switchTurn();
        } else {
            System.out.println("Column Full! Try Again");
//            makeMove();
        }
    }


    public boolean isWon() {
        return (horizontalWin() || verticalWin() || diagonalWin());
    }

    public boolean verticalWin() {
        char current = currentPlayer.getChip().getColour().charAt(0);
        for (int column = 0; column < board.getColumns(); column++) {
            int count = 0;
            for (int row = 0; row < board.getRows(); row++) {
                if (current == board.getCell(row, column))
                    count++;
                else
                    count = 0;
            }
            if (count >= 3)
                return true;
        }
        return false;
    }

    //    public boolean horizontalWin() {
//        char current = currentPlayer.getChip().getColour().charAt(0);
//        for (int row = 0; row < board.getRows(); row++) {
//            int count = 0;
//            for (int column = 0; column < board.getColumns(); column++) {
//                if (current == board.getCell(row, column))
//                    count++;
//                else
//                    count = 0;
//            }
//            if (count >= 3) {
//                System.out.println(count);
//                return true;
//            }
//        }
//        return false;
//    }
    public boolean horizontalWin() {
        char current = currentPlayer.getChip().getColour().charAt(0);
        for (int row = 0; row <= board.getRows(); row++) {
            for (int col = 0; col <= board.getColumns() - 4; col++) {
                if (board.getCell(row, col) == current &&
                        board.getCell(row, col + 1) == current &&
                        board.getCell(row, col + 2)== current &&
                        board.getCell(row, col + 3) == current) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean diagonalWin() {
        char current = currentPlayer.getChip().getColour().charAt(0);
        for (int i = 0; i <= board.getRows() - 3; i++) {
            for (int j = 0; j <= board.getColumns() - 4; j++) {
                if (board.getCell(i, j) == current &&
                        current == board.getCell(i + 1, j + 1) &&
                        current == board.getCell(i + 2, j + 2) &&
                        current == board.getCell(i + 3, j + 3)) {
                    return true;
                }
            }
        }
        for (int i = 0; i <= board.getRows() - 3; i++) {
            for (int j = board.getColumns() - 1; j >= 3; j--) {
                if (board.getCell(i, j) == current &&
                        current == board.getCell(i + 1, j - 1) &&
                        current == board.getCell(i + 2, j - 2) &&
                        current == board.getCell(i + 3, j - 3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void switchTurn() {
        if (currentPlayer.getName().equals(player1.getName())) {
            currentPlayer = player2;
        } else
            currentPlayer = player1;
    }

    public void startGame() {
        board.initializeBoard();
        System.out.println("Classic Connect Four Game - Two Players");
        System.out.println("Player 1 Starts the game");
        currentPlayer = player1;
        boolean gameFinished = false;
        while (!gameFinished) {
            board.displayBoard();
            char current = currentPlayer.getChip().getColour().charAt(0);
            System.out.println("Player " + current + "'s turn. Enter column (0-6):");
            int column = scanner.nextInt();
            makeMove(column);
            System.out.println("Move made");
            if (isWon()) {
                gameFinished = true;
                System.out.println("Player " + current + " wins!");
            } else if (isFull()) {
                gameFinished = true;
                System.out.println("The game is a draw!");
            } else {
                switchTurn();
                System.out.println("Turn Switched");
            }
        }
        board.displayBoard();
        scanner.close();
    }
}

