public class AI {

    // AI's move using Minimax
    public static void computerMove() {
        int bestMoveScore = Integer.MIN_VALUE;
        int bestRow = -1, bestCol = -1;

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (Grid.grid[r][c] == ' ') {  // If cell is empty, try move
                    Grid.grid[r][c] = 'O'; // AI tester hvert tomt felt og placerer 'O' der midlertidigt.
                    int moveScore = minimax(false);  // Call Minimax hvor false betyder, at det nu er spillerens tur.
                    Grid.grid[r][c] = ' ';  // Undo move efter at have fået en score for trækket
                    if (moveScore > bestMoveScore) {  // AI vælger det træk med den højeste score og udfører det.
                        bestMoveScore = moveScore;
                        bestRow = r;
                        bestCol = c;
                    }
                }
            }
        }
        Grid.grid[bestRow][bestCol] = 'O';  // AI places 'O' in the best spot
    }

    // Minimax algorithm for AI decision-making
    private static int minimax(boolean isAITurn) {
        if (Rules.isWinner('X')) return -1;  // Player X wins
        if (Rules.isWinner('O')) return 1;   // AI wins
        if (Rules.isDraw()) return 0;        // Tie

        // AI skal maksimere, spilleren skal minimere
        // AI forsøger at maksimere sin score (starter lavt, så den kan finde den bedste mulighed).
        int bestMoveScore = isAITurn ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (Grid.grid[r][c] == ' ') {  // If cell is empty, try move
                    Grid.grid[r][c] = isAITurn ? 'O' : 'X'; // Simulér trækket
                    int moveScore = minimax(!isAITurn);
                    Grid.grid[r][c] = ' ';  // Undo move
                    bestMoveScore = isAITurn ? Math.max(moveScore, bestMoveScore) : Math.min(moveScore, bestMoveScore);
                }
            }
        }
        return bestMoveScore;
    }
}
