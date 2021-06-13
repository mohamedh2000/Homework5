/**
 * Represents the position of a pixel.
 */
public class Position {
    private final int row;
    private final int column;

    /**
     * Creates a position.
     *
     * @param row
     * @param column
     */
    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the index of the row.
     *
     * @return the index of the row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the index of the column of this pixel/
     *
     * @return The index of the column.
     */
    public int getColumn() {
        return this.column;
    }

}
