import java.util.Scanner;

public class Chaos extends Classic {
//    private Player player1;
//    private Player player2;
//    private Player currentPlayer;
//    private Board board;
//    private Scanner scanner;

    public Chaos() {
//        player1 = new Player("Player 1", "Red");
//        player2 = new Player("Player 2", "Blue");
//        board = new Board();
//        scanner = new Scanner(System.in);

        super();
    }

    @Override
    public boolean isFull() {
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                if (board.getCell(i, j) == ' ')
                    return false;
            }
        }
        return true;
    }

    @Override
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

    @Override
    public boolean isWon() {
        return (horizontalWin() || verticalWin() || diagonalWin());
    }

    @Override
    public void switchTurn() {
        if (currentPlayer.getName().equals(player1.getName()))
            currentPlayer = player2;
        else
            currentPlayer = player1;
    }

    public void takeOver(int i, int j) {
        System.out.println("Choose a chip to take over!");
        char current = currentPlayer.getChip().getColour().charAt(0);
        if (board.getCell(i, j) != ' ' && board.getCell(i, j) != current) {
            System.out.println("Taken");
            board.setCell(i, j, current);
        } else {
            if(board.getCell(i, j) != ' ' && board.getCell(i, j) == current){
                System.out.println("Choose one of the opponent's chips");
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                takeOver(x, y);
            }
        }
    }

    public void removeChip(int i, int j) {
        if (board.getCell(i, j) != ' ') {
            int count = board.getRows() - i;
            System.out.println(count);
            for (int x = count; x > 0; x--) {
                char temp = board.getCell(x - 1, j);
                board.setCell(x, j, temp);
            }
            board.setCell(0, j, ' ');
        } else {
            System.out.println("Empty Cell, you wasted a power up!");
        }
    }

    public void startGame() {
        board.initializeBoard();
        System.out.println("CHAOS MODE");
        System.out.println("Player 1 Starts the game");
        currentPlayer = player1;
        boolean gameFinished = false;
        while (!gameFinished) {
            board.displayBoard();
            char current = currentPlayer.getChip().getColour().charAt(0);
            double x = Math.random();
            if (x > 0.7 && x < 0.85) {
                System.out.println("TAKE OVER!");
                System.out.println("Choose a Cell to take over (row,column)");
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                takeOver(r,c);
            } else if (x >0.85) {
                System.out.println("REMOVE A CHIP!");
                System.out.println("Choose a chip to remove (row,column)");
                int r = scanner.nextInt();
                int c = scanner.nextInt();
                removeChip(r,c);
            } else {
                System.out.println("Player " + current + "'s turn. Enter column (0-6):");
                int column = scanner.nextInt();
                makeMove(column);
                System.out.println("Move made");
            }
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
