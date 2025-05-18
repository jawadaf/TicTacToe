public class Grid {

    public static final char[][] grid = {  // The 3x3 game board
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    // Display the board in the console
    public static void displayGrid() {
        System.out.println("-------------");
        for (char[] row : Grid.grid) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " | ");
            }
            System.out.println("\n-------------");
        }
    }
}
