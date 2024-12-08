import java.util.Scanner;
public class TicTacToe {
    // The 3x3 game board
    static char[][] panelOfGame = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };
    static char currentPointer;
    static int presentPlayer;
    // Method to display the current Tic-Tac-Toe board
    public static void displayBoard() {
        System.out.println("+---+---+---+");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(panelOfGame[i][j] + " | ");
            }
            System.out.println("\n+---+---+---+");
        }
    }
    // Method to insert a move into the board
    public static boolean insertMove(int row, int col) {
        if (panelOfGame[row][col] == ' ') {
            panelOfGame[row][col] = currentPointer;
            return true;
        }
        return false;
    }
    // Helper method to check winning conditions
    public static boolean hasWinningCondition(char c) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if ((panelOfGame[i][0] == c && panelOfGame[i][1] == c && panelOfGame[i][2] == c) ||
                    (panelOfGame[0][i] == c && panelOfGame[1][i] == c && panelOfGame[2][i] == c)) {
                return true;
            }
        }
        return (panelOfGame[0][0] == c && panelOfGame[1][1] == c && panelOfGame[2][2] == c) ||
                (panelOfGame[0][2] == c && panelOfGame[1][1] == c && panelOfGame[2][0] == c);
    }
    // Method to check for a winner
    public static boolean checkWinner() {
        return hasWinningCondition(currentPointer);
    }
    // Method to swap players and pointers
    public static void swapPlayerAndPointer() {
        if (currentPointer == 'O') {
            currentPointer = 'X';
        } else {
            currentPointer = 'O';
        }
        if (presentPlayer == 1) {
            presentPlayer = 2;
        } else {
            presentPlayer = 1;
        }
    }
    // Main game logic
    public static void game() {
        Scanner scanner = new Scanner(System.in);
        // Player 1 chooses their pointer
        System.out.print("Player 1, choose your letter (O or X): ");
        currentPointer = scanner.next().toUpperCase().charAt(0);
        while (currentPointer != 'O' && currentPointer != 'X') {
            System.out.print("Invalid choice. Please choose either 'O' or 'X': ");
            currentPointer = scanner.next().toUpperCase().charAt(0);
        }
        presentPlayer = 1;
        // Display the empty board
        displayBoard();
        // Play for a maximum of 9 moves
        for (int moves = 0; moves < 9; moves++) {
            System.out.printf("Player %d (%c), enter your move (row col): ", presentPlayer, currentPointer);
            int row, col;
            try {
                row = scanner.nextInt();
                col = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter two numbers between 0 and 2.");
                scanner.nextLine(); // Clear invalid input
                moves--; // Retry the move
                continue;
            }
            // Check if the move is valid
            if (row < 0 || row > 2 || col < 0 || col > 2 || !insertMove(row, col)) {
                System.out.println("Invalid move or spot occupied. Try again.");
                moves--; // Retry the move
                continue;
            }
            // Display the updated board
            displayBoard();
            // Check if the current player wins
            if (checkWinner()) {
                System.out.printf("Player %d (%c) wins!\n", presentPlayer, currentPointer);
                scanner.close();
                return;
            }
            // Switch to the next player
            swapPlayerAndPointer();
        }
        // If no winner after 9 moves, it's a tie
        System.out.println("Game is a tie!");
        scanner.close();
    }
    // Main entry point
    public static void main(String[] args) {
        System.out.println("\t\tTIC TAC TOE GAME!");
        game();
    }
}
//OUTPUT
//TIC TAC TOE GAME!
//Player 1, choose your letter (O or X): X
//+---+---+---+
//        |   |   |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//Player 1 (X), enter your move (row col): 0 0
//        +---+---+---+
//        | X |   |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//Player 2 (O), enter your move (row col): 0 1
//        +---+---+---+
//        | X | O |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//Player 1 (X), enter your move (row col): 1 3
//Invalid move or spot occupied. Try again.
//Player 1 (X), enter your move (row col): 1 0
//        +---+---+---+
//        | X | O |   |
//        +---+---+---+
//        | X |   |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//Player 2 (O), enter your move (row col): 1 1
//        +---+---+---+
//        | X | O |   |
//        +---+---+---+
//        | X | O |   |
//        +---+---+---+
//        |   |   |   |
//        +---+---+---+
//Player 1 (X), enter your move (row col): 2 0
//        +---+---+---+
//        | X | O |   |
//        +---+---+---+
//        | X | O |   |
//        +---+---+---+
//        | X |   |   |
//        +---+---+---+
//Player 1 (X) wins!
//
//Process finished with exit code 0