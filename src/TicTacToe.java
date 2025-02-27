import java.util.Scanner;

public class TicTacToe {
    private static char[][] grid = {  // The 3x3 game board
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private static char currentPlayer = 'X';  // Start with Player X
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayGrid();  // Show the board
            if (currentPlayer == 'X') playerMove();
            else computerMove();  // AI makes a move

            if (isWinner(currentPlayer)) {
                displayGrid();
                System.out.println("Player " + currentPlayer + " wins! 🎉");
                break;
            }
            if (isDraw()) {
                displayGrid();
                System.out.println("It's a draw! 😐");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Switch turns
        }
    }

    // Player's move (human input)
    private static void playerMove() {
        int row, col;
        do {
            System.out.println("Enter row and column (0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (!isValidMove(row, col));
        grid[row][col] = 'X';
    }

    // AI's move using Minimax
    private static void computerMove() {
        int bestMoveScore = Integer.MIN_VALUE;
        int bestRow = -1, bestCol = -1;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c] == ' ') {  // If cell is empty, try move
                    grid[r][c] = 'O';
                    int moveScore = minimax(false);  // Call Minimax
                    grid[r][c] = ' ';  // Undo move
                    if (moveScore > bestMoveScore) {  // Pick the best move
                        bestMoveScore = moveScore;
                        bestRow = r;
                        bestCol = c;
                    }
                }
            }
        }
        grid[bestRow][bestCol] = 'O';  // AI places 'O' in the best spot
    }

    // Minimax algorithm for AI decision-making
    private static int minimax(boolean isAITurn) {
        if (isWinner('X')) return -1;  // Player X wins
        if (isWinner('O')) return 1;   // AI wins
        if (isDraw()) return 0;        // Tie

        int bestMoveScore = isAITurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c] == ' ') {  // If cell is empty, try move
                    grid[r][c] = isAITurn ? 'O' : 'X';
                    int moveScore = minimax(!isAITurn);
                    grid[r][c] = ' ';  // Undo move
                    bestMoveScore = isAITurn ? Math.max(moveScore, bestMoveScore) : Math.min(moveScore, bestMoveScore);
                }
            }
        }
        return bestMoveScore;
    }

    // Display the board in the console
    private static void displayGrid() {
        System.out.println("-------------");
        for (char[] row : grid) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Check if a move is valid
    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && grid[row][col] == ' ';
    }

    // Check for a winner
    private static boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if ((grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) ||  // Row win
                    (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player))  // Column win
                return true;
        }
        return (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) ||  // Diagonal \
                (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player);   // Diagonal /
    }



    // Check for a draw
    private static boolean isDraw() {
        for (char[] row : grid)
            for (char cell : row)
                if (cell == ' ') return false;  // If empty cell exists, game is not a draw
        return true;
    }
}
