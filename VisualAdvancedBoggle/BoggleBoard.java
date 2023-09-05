package VisualAdvancedBoggle;

/**
 * Represents the Boggle game board.
 */
public class BoggleBoard {

    /**
     * The size of the Boggle grid.
     */
    private final int size;

    /**
     * Characters assigned to each grid position.
     */
    private final char[][] board;

    /**
     * Constructs a BoggleBoard with the specified size.
     *
     * @param size The size of the Boggle grid to create (number of rows and columns).
     */
    public BoggleBoard(int size) {
        this.size = size;
        this.board = new char[size][size];
    }

    /**
     * Initializes the Boggle board by assigning letters from a string to each grid position.
     * Letters should be assigned from left to right, top to bottom.
     *
     * @param letters A string of letters, one for each grid position.
     */
    public void initalizeBoard(String letters) {
        int letterIndex = 0;
        for (int i = 0; i < numRows(); i++) {
            for (int j = 0; j < numCols(); j++) {
                this.board[i][j] = letters.charAt(letterIndex);
                letterIndex++;
            }
        }
    }

    /**
     * Generates a formatted string representation of the Boggle board,
     * facilitating easy scanning for words.
     *
     * @return A formatted string representing the Boggle board for display.
     */
    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                boardString.append(this.board[row][col]).append(" ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

    /**
     * Retrieves the number of rows in the Boggle board.
     * @return The number of rows in the grid.
     */
    public int numRows() {
        return this.size;
    }

    /**
     * Retrieves the number of columns in the Boggle board (assuming a square grid).
     * @return The number of columns in the grid.
     */
    public int numCols() {
        return this.size;
    }

    /**
     * Retrieves the character at a specified grid position.
     * @param row The row index of the grid position.
     * @param col The column index of the grid position.
     * @return The character located at the specified grid position.
     */
    public char getCharAt(int row, int col) {
        return this.board[row][col];
    }
}
