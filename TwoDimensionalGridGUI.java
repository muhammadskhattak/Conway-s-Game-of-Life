import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * A two-dimensional field of cells. Each cell is held under the rules of Conway's Game
 * Of Life.
 * 
 * @author Muhammad Khattak Roll# 07
 * @version 2015-04-23 1.0
 */
public class TwoDimensionalGridGUI
{
    /*
     * instance fields
     */
    private CellGUI[][] cellGrid;
    private int leftBound;
    private int lowerBound;
    private int rightBound;
    private int upperBound;

    /*
     * constructors
     */
    /**
     * Constructs a new grid and initializes all cells.
     */
    public TwoDimensionalGridGUI()
    {
        leftBound = 0;
        lowerBound = 30;
        rightBound = 50;
        upperBound = 0;

        cellGrid = new CellGUI[lowerBound][rightBound];

        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                cellGrid[columns][rows] = new CellGUI();
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of constructor TwoDimensionalGrid()

    /**
     * Constructs a new grid and intializes all cells with the specified
     * living cells.
     * 
     * @param columnsAlive the column coordinates of the living cell
     * @param rowsAlive the row coordinates of the living cell
     * @param numberOfCellsSet the number of pairs of coordinates given. Pairs should have the same index
     */
    public TwoDimensionalGridGUI(int[] columnsAlive, int[] rowsAlive, int numberOfCellsSet)
    {
        leftBound = 0;
        lowerBound = 30;
        rightBound = 50;
        upperBound = 0;

        cellGrid = new CellGUI[lowerBound][rightBound];
        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                cellGrid[columns][rows] = new CellGUI();
                for (int cellsSet = 0; cellsSet < numberOfCellsSet; cellsSet ++)
                {
                    if (columns == columnsAlive[cellsSet] && rows == rowsAlive[cellsSet])
                    {
                        cellGrid[columns][rows] 
                        = new CellGUI(true);
                    } // end of if (columns == columnsAlive[cellsSet] && rows == rowsAlive[cellsSet])
                } // end of for (int cellsSet = 0; cellsSet < numberOfCellsSet; cellsSet ++)
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of constructor TwoDimensionalGrid(int[] columnsAlive, int[] rowsAlive, int numberOfCellsSet)

    /**
     * Constructs a grid with an amount of cells related to the size of the screen.
     * 
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @param squareSize the size of each grid representation
     */
    public TwoDimensionalGridGUI(int screenHeight, int screenWidth, int squareSize)
    {
        leftBound = 0;
        /*
        test to see if the screen is capable of being evenly divided by 
        the size of the squares, else it will remove one row and column in
        order to accomodate the screen.
         */
        if (screenHeight % squareSize == 0)
        {
            // subtract 2 from the lower bound to account for the frame and menu bar
            lowerBound = screenHeight / squareSize - 2;
            rightBound = screenWidth / squareSize;
        }
        else
        {
            // subtract 3 from the lower bound to account for the frame and menu bar and extra
            lowerBound = screenHeight / squareSize - 3;
            rightBound = screenWidth / squareSize - 1;
        } // end of if (screenHeight % GridGUI.SQUARE_SIZE == 0)
        upperBound = 0;

        cellGrid = new CellGUI[lowerBound][rightBound];

        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                cellGrid[columns][rows] = new CellGUI();
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of constructor TwoDimensionalGrid()() 

    /**
     * Constructs a new grid and intializes all cells with the specified
     * living cells.
     * 
     * @param columnsAlive the column coordinates of the living cell
     * @param rowsAlive the row coordinates of the living cell
     * @param numberOfPairs the number of row and column pairs given
     * @param cellRows the number of cells in a row
     * @param cellColumns the number of cells in a column
     */
    public TwoDimensionalGridGUI(int[] columnsAlive, int[] rowsAlive, int numberOfPairs, int cellColumns, int cellRows)
    {
        leftBound = 0;
        lowerBound = cellColumns;
        rightBound = cellRows;
        upperBound = 0;

        cellGrid = new CellGUI[lowerBound][rightBound];
        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                cellGrid[columns][rows] = new CellGUI();
                for (int cellsSet = 0; cellsSet < numberOfPairs; cellsSet ++)
                {
                    if (columns == columnsAlive[cellsSet] && rows == rowsAlive[cellsSet])
                    {
                        cellGrid[columns][rows] = new CellGUI(true);
                    } // end of if (columns == columnsAlive[cellsSet] && rows == rowsAlive[cellsSet])
                } // end of for (int cellsSet = 0; cellsSet < numberOfCellsSet; cellsSet ++)
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of constructor TwoDimensionalGrid(int[] columnsAlive, int[] rowsAlive ...
    
    /*
     * accessors
     */
    /**
     * Returns the specified cell.
     * 
     * @param column the column location of the specified cell
     * @param row the row location of the specified cell
     * 
     * @return cellGrid[column][row] the cell as specified by the parameters
     */
    public CellGUI getCell(int row, int column)
    {
        return cellGrid[column][row];
    } // end of accessor getCell(int row, int column)

    /**
     * Returns the lower bound of the grid.
     * 
     * @return lowerBound the lowest most bound of this grid
     */
    public int getLowerBound()
    {
        return lowerBound;
    } // end of accessor getLowerBound()

    /**
     * Retruns the right bound of the grid.
     * 
     * @return rightBound the right most bound of this grid
     */
    public int getRightBound()
    {
        return rightBound; 
    } // end of accessor getRightBound()

    /**
     * Prints all the cells on the grid and their location and alive status.
     */
    public void printCells()
    {
        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                System.out.println("Columns: " + columns + "Row: " + rows + " " + cellGrid[columns][rows].isAlive());
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of accessor printCell()

    /*
     * mutators
     */
    /**
     * Checks the status of a single cell.
     * 
     * @param row the row of the cell being checked
     * @param column the column of the cell being checked
     */
    public void checkCellStatus(int row, int column)
    {
        checkNeighbours(row, column);
    } // end of method checkCellStatus(Cell cell)

    /**
     * Checks the status of the cell by looking at its coordinates and applying the appropriate test.
     */
    public void checkAllCellStatus()
    {
        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                checkNeighbours(rows, columns);
            } // end of for (int rows = 0; rows < rightBound; rows ++)
        } // end of for (int columns = 0; columns < lowerBound; columns ++)
    } // end of method checkCellStatus()

    /**
     * Checks the surrounding cells of a specified cell.
     * 
     * @param row the row of the cell being checked
     * @param column the column of the cell being checked
     */
    public void checkNeighbours(int row, int column)
    {
        int counter = 0;
        //*************************************************
        //Following code was adapted from Harry Tang 2015-04-27
        for (int columnNeighbours = column - 1; columnNeighbours <= column + 1; columnNeighbours ++)
        {
            for (int rowNeighbours = row - 1; rowNeighbours <= row + 1; rowNeighbours ++)
            {
                try 
                {
                    if (cellGrid[columnNeighbours][rowNeighbours].isAlive()
                    && cellGrid[columnNeighbours][rowNeighbours] != cellGrid[column][row])
                    {
                        counter ++;
                    } // end of  if (cellGrid[columnNeighbours][rowNeighbours].isAlive() ...
                }
                catch (IndexOutOfBoundsException outOfBoundsException)
                {
                    // do nothing
                } // end of catch (IndexOutOfBoundsException outOfBoundsException)
            } // end of for (int rowNeighbours = row - 1; rowNeighbours <= row + 1; rowNeighbours ++)
        } // end of for (int columnNeighbours = column - 1; columnNeighbours <= column + 1; columnNeighbours ++)
        //*************************************************

        decisionOnCellState(counter, row, column);
    } // end of method checkNeighbours(int row, int column, boolean test1, boolean test2, boolean test3, boolean test4, boolean test5, boolean test6, boolean test7, boolean test8)

    /**
     * Applies the rules of the game of life.
     * 
     * @param counter the amount of living cells surrounding the current cell
     * @param row the row of the cell being checked
     * @param column the column of the cell being checked
     */
    public void decisionOnCellState(int counter, int row, int column)
    {
        if (cellGrid[column][row].isAlive())
        {
            if (counter < 2)
            {
                cellGrid[column][row].setToBeAlive(false);
            }
            else if (counter == 2 || counter == 3)
            {
                cellGrid[column][row].setToBeAlive(true);
            }
            else if (counter > 3)
            {
                cellGrid[column][row].setToBeAlive(false);
            } // end of if (counter < 2)
        }
        else
        {
            if (counter == 3)
            {
                cellGrid[column][row].setToBeAlive(true);
            } // end of if (counter == 3)
        } // end of if (cellGrid[column][row].isAlive())
    } // end of method decisionOnCellState(int counter)

    /**
     * Kills all cells.
     */
    public void killAllCells()
    {
        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                cellGrid[columns][rows].setToBeAlive(false);
                cellGrid[columns][rows].setAlive(false);
            } // end of for (int rows = 0; rows < rightBound; rows ++)
        } // end of for (int columns = 0; columns < lowerBound; columns ++)
    } // end of method killAllCells()

    /**
     * Updates the status of a single cell.
     * 
     * @param row the row of the cell being checked
     * @param column the column of the cell being checked
     */
    public void updateCell(int row, int column)
    {
        checkCellStatus(row, column);

        if (cellGrid[column][row].isToBeAlive())
        {
            cellGrid[column][row].setAlive(true);
        }
        else if (!cellGrid[column][row].isToBeAlive())
        {
            cellGrid[column][row].setAlive(false);
        } // end of if (cellGrid[columns][rows].isToBeAlive())
    } // end of updateCell(int row, int column)

    /**
     * Updates the screen to output the next cycle of cells.
     */
    public void updateAllCells()
    {
        checkAllCellStatus();

        for (int columns = 0; columns < lowerBound; columns ++)
        {
            for (int rows = 0; rows < rightBound; rows ++)
            {
                if (cellGrid[columns][rows].isToBeAlive())
                {
                    cellGrid[columns][rows].setAlive(true);
                }
                else
                {
                    cellGrid[columns][rows].setAlive(false);
                } // end of if (cellGrid[columns][rows].isToBeAlive())
            } // end of for (int rows = 0; rows < rightBound; rows ++)
        } // end of for (int columns = 0; columns < lowerBound; columns ++)
    } // end of method update()
} // end of class TwoDimensionalGridGUI