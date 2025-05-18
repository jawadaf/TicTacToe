import java.util.Scanner;

public class Player {

    private static final Scanner scanner = new Scanner(System.in);

    // Player's move (human input)
    public static void playerMove() {
        int row, col;
        do {
            System.out.println("Enter row and column (0-2): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
        } while (!Rules.isValidMove(row, col));
        Grid.grid[row][col] = 'X';
    }
}
