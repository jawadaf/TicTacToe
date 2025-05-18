public class TicTacToe {

    private static char currentPlayer = 'X';  // Start with Player X

    public static void main(String[] args) {
        while (true) {
            Grid.displayGrid();  // Show the board
            if (currentPlayer == 'X') Player.playerMove();
            else AI.computerMove();  // AI makes a move

            if (Rules.isWinner(currentPlayer)) {
                Grid.displayGrid();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }
            if (Rules.isDraw()) {
                Grid.displayGrid();
                System.out.println("It's a draw!");
                break;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';  // Switch turns (Ternary operator)
        }
    }
}
