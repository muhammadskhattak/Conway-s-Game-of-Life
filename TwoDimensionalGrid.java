/**
 * A two-dimensional field of cells. Each cell is held under the rules of Conway's Game
 * Of Life.
 * 
 * @author Muhammad Khattak Roll# 07
 * @version 2015-04-23 1.0
 */
public class TwoDimensionalGrid
{
    /*
     * class fields
     */
    /**
     * The delay time between screen updates.
     */
    public static final long DELAY = 100l;

    /**
     * The left-most bound of the grid.
     */
    public static final int LEFT_BOUND = 0;

    /**
     * The lowest bound of the grid.
     */
    public static final int LOWER_BOUND = 18;

    /**
     * The right-most bound of the grid.
     */
    public static final int RIGHT_BOUND = 50;

    /**
     * The highest bound of the grid.
     */
    public static final int UPPER_BOUND = 0;

    /*
     * instance fields
     */
    private Cell[][] cellGrid;

    /*
     * constructors
     */
    /**
     * Constructs a new grid and initializes all cells.
     */
    public TwoDimensionalGrid()
    {
        cellGrid = new Cell[LOWER_BOUND][RIGHT_BOUND];

        for (int columns = 0; columns < LOWER_BOUND; columns ++)
        {
            for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            {
                cellGrid[columns][rows] = new Cell(true);
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            System.out.print("\n");
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of constructor TwoDimensionalGrid()()

    /**
     * Constructs a new grid and intializes all cells with the specified
     * living cells.
     * 
     * @param columnsAlive the column coordinates of the living cell
     * @param rowsAlive the row coordinates of the living cell
     * @param numberOfCellsSet the number of pairs of coordinates given. Pairs should have the same index
     */
    public TwoDimensionalGrid(int[] columnsAlive, int[] rowsAlive, int numberOfCellsSet)
    {
        cellGrid = new Cell[LOWER_BOUND][RIGHT_BOUND];

        for (int columns = 0; columns < LOWER_BOUND; columns ++)
        {
            for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            {
                cellGrid[columns][rows] = new Cell();
                for (int cellsSet = 0; cellsSet < numberOfCellsSet; cellsSet ++)
                {
                    if (columns == columnsAlive[cellsSet] && rows == rowsAlive[cellsSet])
                    {
                        cellGrid[columns][rows] = new Cell(true);
                    } // end of if (columns == columnsAlive[cellsSet] && rows == rowsAlive[cellsSet])
                } // end of for (int cellsSet = 0; cellsSet < numberOfCellsSet; cellsSet ++)
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            System.out.print("\n");
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of constructor TwoDimensionalGrid(int[] columnsAlive, int[] rowsAlive, int numberOfCellsSet)

    /*
     * accessors
     */
    /**
     * Prints all the cells on the grid and their location and alive status.
     */
    public void printCells()
    {
        for (int columns = 0; columns < LOWER_BOUND; columns ++)
        {
            for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            {
                System.out.println("Columns: " + columns + "Row: " + rows + " " + cellGrid[columns][rows].isAlive());
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of accessor printCell()

    /*
     * mutators
     */
   
    /**
     * Checks the status of the cell by looking at its coordinates and applying the appropriate test.
     */
    public void checkCellStatus()
    {
        for (int columns = 0; columns < LOWER_BOUND; columns ++)
        {
            for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            {
                checkNeighbours(rows, columns);
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
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
                    }
                }
                catch (IndexOutOfBoundsException e)
                {
                    // do nothing
                }
            } // end of for (int y = indexY - 1; y == indexY; y++)
        } // end of for (int x = indexX - 1; x == indexX; x++)
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
     * Updates the screen to output the next cycle of cells.
     */
    public void update()
    {
        try
        {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException delayException)
        {
            System.out.println("Uh-oh, unexpected exception occured");
            System.out.println("This is probably the result of a unruly delay");
            System.out.println(delayException);
        } // end of catch (InterruptedException delayException)

        //checkCellStatus(); 

        checkCellStatus();
        
        System.out.print("\f");
        for (int columns = 0; columns < LOWER_BOUND; columns ++)
        {
            for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            {
                if (cellGrid[columns][rows].isToBeAlive())
                {
                    cellGrid[columns][rows].setAlive(true);
                }
                else
                {
                    cellGrid[columns][rows].setAlive(false);
                } // end of if (cellGrid[columns][rows].isToBeAlive())
            } // end of for (int rows = 0; rows < RIGHT_BOUND; rows ++)
            System.out.print("\n");
        } // end of for (int columns = 0; columns < LOWER_BOUND; columns ++)
    } // end of method update()

    /*
     * static methods
     */
    /**
     * Initializes the two-dimensional grid and loops the rule applying process 60 times.
     * Will begin with 3 "images"(a glider, 3 x 1 line and a beacon) to test the rule applying process.
     * 
     * @param arguments not necessary
     */
    public static void main(String[] arguments)
    {
        int[] firstImageTestColumns = {1, 1, 1,// horizontal line
            0, 1, 2, 2, 2, // glider
            14, 14, 15, 16, 17, 17}; // beacon
        int[] firstImageTestRows = {47, 48, 49, //horizontal line
            1, 2, 0, 1, 2, // glider
            0, 1, 0, 3, 2, 3}; // beacon

        TwoDimensionalGrid grid = new TwoDimensionalGrid(firstImageTestColumns, firstImageTestRows, 14);

        for (int loop = 0; loop < 60; loop ++)
        {
            grid.update();
        } // end of for (int loop = 0; loop < 30; loop ++)
    } // end of method main(String[] arguments)
} // end of class TwoDimensionalGrid
