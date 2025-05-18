public class Rules {

    // Check if a move is valid
    public static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && Grid.grid[row][col] == ' ';
    }

    // Check for a winner
    public static boolean isWinner(char player) {
        for (int i = 0; i < 3; i++) {
            if ((Grid.grid[i][0] == player && Grid.grid[i][1] == player && Grid.grid[i][2] == player) ||  // Row win
                    (Grid.grid[0][i] == player && Grid.grid[1][i] == player && Grid.grid[2][i] == player))  // Column win
                return true;
        }
        return (Grid.grid[0][0] == player && Grid.grid[1][1] == player && Grid.grid[2][2] == player) ||  // Diagonal \
                (Grid.grid[0][2] == player && Grid.grid[1][1] == player && Grid.grid[2][0] == player);   // Diagonal /
    }

    // Check for a draw
    public static boolean isDraw() {
        for (char[] row : Grid.grid)
            for (char cell : row)
                if (cell == ' ') return false;  // If empty cell exists, game is not a draw
        return true;
    }
}
