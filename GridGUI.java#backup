import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.util.Scanner;
import java.awt.Shape;
import java.awt.Toolkit;

/**
 * A GUI representation of a two dimensional grid used to 
 * display Conway's Game of Life.
 * 
 * @author Muhammad Khattak Roll# 07
 * @version 2015-04-29 1.0
 */
public class GridGUI implements Runnable
{
    /*
     * class fields
     */
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * The delay time between screen updates.
     */
    public static final int DELAY = 20;

    private static final int SQUARE_SIZE = 10;

    /*
     * instance fields
     */
    private Double gridHeightDouble;
    private Double gridWidthDouble;
    private int gridHeight;
    private int gridWidth;
    private boolean continueUpdating;
    private boolean enableToggling;
    private TwoDimensionalGridGUI grid;
    private JFrame mainFrame;
    private RectanglePaint rectangles;
    private Thread updateThread;
    private boolean threadStart;
    private int xCoordinate;
    private int yCoordinate;

    /*
     * constructors
     */
    /**
     * Constructs a GUI representation of a grid and displays it.
     */
    public GridGUI()
    {
        gridHeightDouble = SCREEN_SIZE.getHeight();
        gridWidthDouble = SCREEN_SIZE.getWidth();

        gridHeight = gridHeightDouble.intValue();
        gridWidth = gridWidthDouble.intValue();

        xCoordinate = 0;
        yCoordinate = 0;

        continueUpdating = true;
        enableToggling = true;
        updateThread = new Thread(this);
        threadStart = true;

        grid = new TwoDimensionalGridGUI(gridHeight, gridWidth, SQUARE_SIZE);
        rectangles = new RectanglePaint();

        makeFrame();
    } // end of constructor GridGUI()

    /**
     * Constructs a GUI representation of a grid with the predertimined living cells.
     * 
     * @param rows the living rows in the grid
     * @param columns the living columns in the grid
     * @param cellRows the number of cells in a row
     * @param cellColumns the number of cells in a column
     */
    public GridGUI(int[] rows, int [] columns, int numberOfPairs, int cellRows, int cellColumns)
    {
        gridHeightDouble = 0.0;
        gridWidthDouble = 0.0;

        gridHeight = cellColumns * SQUARE_SIZE;
        gridWidth = cellRows * SQUARE_SIZE;

        xCoordinate = 0;
        yCoordinate = 0;

        continueUpdating = true;
        enableToggling = true;
        updateThread = new Thread(this);
        threadStart = true;

        grid = new TwoDimensionalGridGUI(columns, rows, numberOfPairs, cellColumns, cellRows);
        rectangles = new RectanglePaint();

        makeFrame();
    } // end of constructor GridGUI(int[] row, int [] columns, int cellRows, int cellColumns)

    /*
     * mutators
     */
    /*
     * Gives a graphical representation of the cells state.
     * 
     * @param cellDrawn the cell to be represented
     * @param xCoordinate the x coordinate for where to draw the rectangle
     * @param yCoordinate the y coordinate for where to draw the rectangle
     */
    private void drawCellState(CellGUI cellDrawn)
    {
        if (cellDrawn.isAlive())
        {
            rectangles.drawFullRectangle(Color.BLACK, xCoordinate - SQUARE_SIZE, yCoordinate - SQUARE_SIZE,
                SQUARE_SIZE, SQUARE_SIZE, false);
        }
        else
        {
            rectangles.drawFullRectangle(Color.WHITE, xCoordinate - SQUARE_SIZE, yCoordinate - SQUARE_SIZE,
                SQUARE_SIZE, SQUARE_SIZE, true);

            rectangles.drawOutlinedRectangle(Color.BLACK, xCoordinate - SQUARE_SIZE, yCoordinate - SQUARE_SIZE,
                SQUARE_SIZE, SQUARE_SIZE);
        } // end of if (cellDrawn.isAlive())
    } // end of method drawCellState(Cell cellDrawn)

    /*
     * Prepares the frame for which the content will be contained.
     */
    private void makeFrame()
    {
        mainFrame = new JFrame("Conway's Game of Life!");

        mainFrame.add(makeMenu(), BorderLayout.NORTH);

        mainFrame.getContentPane().add(rectangles);

        rectangles.addMouseListener(new GridInteraction());

        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        grid.updateAllCells();

        yCoordinate = SQUARE_SIZE;
        for (int columns = 0; columns < grid.getLowerBound(); columns ++)
        {
            xCoordinate = SQUARE_SIZE;
            for (int rows = 0; rows < grid.getRightBound(); rows ++)
            {
                drawCellState(grid.getCell(rows, columns));
                xCoordinate += SQUARE_SIZE;
            } // end of for (int rows = 0; rows < grid.RIGHT_BOUND; rows ++)
            yCoordinate += SQUARE_SIZE;
        } // end of for (int columns = 0; columns < grid.LOWER_BOUND; columns ++)

        mainFrame.pack();
        mainFrame.setVisible(true);
    } // end of method makeFrame()

    /*
     * Prepares a menu for the frame.
     * 
     * @return menuBar the menu bar to be used in the frame.
     */
    private JMenuBar makeMenu()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu simulationMenu = new JMenu("Simulation");

        menuBar.add(fileMenu);
        menuBar.add(simulationMenu);

        JMenuItem quitItem = new JMenuItem("Quit");
        JMenuItem startItem = new JMenuItem("Start");
        JMenuItem pauseItem = new JMenuItem("Pause");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");

        fileMenu.add(saveItem);
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        simulationMenu.add(startItem);
        simulationMenu.add(pauseItem);
        simulationMenu.add(resetItem);

        resetItem.addActionListener(new ActionListener()
            {
                /*
                 * Resets all cells to dead.
                 * 
                 * @param resetEvent the details of the event handled
                 */
                public void actionPerformed(ActionEvent resetEvent)
                {
                    if (enableToggling)
                    {
                        grid.killAllCells();

                        yCoordinate = SQUARE_SIZE;
                        for (int columns = 0; columns < grid.getLowerBound(); columns ++)
                        {
                            xCoordinate = SQUARE_SIZE;
                            for (int rows = 0; rows < grid.getRightBound(); rows ++)
                            {
                                drawCellState(grid.getCell(rows, columns));
                                xCoordinate += SQUARE_SIZE;
                            } // end of for (int rows = 0; rows < grid.RIGHT_BOUND; rows ++)
                            yCoordinate += SQUARE_SIZE;
                        } // end of for (int columns = 0; columns < grid.LOWER_BOUND; columns ++)
                    } // end of if (enableToggling)
                    else
                    {
                        continueUpdating = false;
                        enableToggling = true;
                        
                        grid.killAllCells();

                        yCoordinate = SQUARE_SIZE;
                        for (int columns = 0; columns < grid.getLowerBound(); columns ++)
                        {
                            xCoordinate = SQUARE_SIZE;
                            for (int rows = 0; rows < grid.getRightBound(); rows ++)
                            {
                                drawCellState(grid.getCell(rows, columns));
                                xCoordinate += SQUARE_SIZE;
                            } // end of for (int rows = 0; rows < grid.RIGHT_BOUND; rows ++)
                            yCoordinate += SQUARE_SIZE;
                        } // end of for (int columns = 0; columns < grid.LOWER_BOUND; columns ++)
                    }
                } // end of actionPerformed(ActionEvent resetEvent)
            }); // end of ActionListener() 

        pauseItem.addActionListener(new ActionListener()
            {
                /*
                 * Pauses the simulation.
                 * 
                 * @param pauseEvent the details of the event handled
                 */
                public void actionPerformed(ActionEvent pauseEvent)
                {
                    continueUpdating = false;
                    enableToggling = true;
                } // end of actionPerformed(ActionEvent pauseEvent)
            }); // end of ActionListener()

        startItem.addActionListener(new ActionListener()
            {
                /*
                 * Starts the simulation.
                 * 
                 * @param startEvent the details of the event handled
                 */
                public void actionPerformed(ActionEvent startEvent)
                {
                    enableToggling = false;
                    continueUpdating = true;

                    if (threadStart)
                    {
                        updateThread.start();
                        threadStart = false;
                    } // end of if (threadStart)
                } // end of actionPerformed(ActionEvent startEvent)
            }); // end of ActionListener()

        quitItem.addActionListener(new ActionListener()
            {
                /*
                 * Quits the program.
                 * 
                 * @param quitEvent the details of the event handled
                 */
                public void actionPerformed(ActionEvent quitEvent)
                {
                    System.exit(0);
                } // end of actionPerformed(ActionEvent quitEvent)
            }); // end of ActionListener()

        openItem.addActionListener(new ActionListener()
            {
                /*
                 * Presents an option for the user to open a file.
                 * 
                 * @param openEvent details of the event handled
                 */
                public void actionPerformed(ActionEvent openEvent)
                {
                    Thread openItemThread = new Thread(new OpenItems());
                    openItemThread.start();
                } // end of actionPerformed(ActionEvent openEvent)
            }); // end of ActionListener()

        saveItem.addActionListener(new ActionListener()
            {
                /*
                 * Presents an option for the user to save a file.
                 * 
                 * @param saveEvent details of the event handled
                 */
                public void actionPerformed(ActionEvent saveEvent)
                {
                    JFileChooser selectFileToSave = new JFileChooser(System.getProperty("user.dir"));
                    selectFileToSave.showOpenDialog(mainFrame);

                    File fileSaved = selectFileToSave.getSelectedFile();
                } // end of actionPerformed(ActionEvent saveEvent)
            }); // end of ActionListener()

        return menuBar;
    } // end of method makeMenu()

    /**
     * The method called when the thread is started. It will update the cells.
     */
    public void run()
    { 
        while (updateThread.isAlive())
        {
            while (continueUpdating)    
            {
                grid.updateAllCells();

                yCoordinate = SQUARE_SIZE;
                for (int columns = 0; columns < grid.getLowerBound(); columns ++)
                {
                    xCoordinate = SQUARE_SIZE;
                    for (int rows = 0; rows < grid.getRightBound(); rows ++)
                    {
                        drawCellState(grid.getCell(rows, columns));
                        xCoordinate += SQUARE_SIZE;
                    } // end of for (int rows = 0; rows < grid.RIGHT_BOUND; rows ++)
                    yCoordinate += SQUARE_SIZE;
                } // end of for (int columns = 0; columns < grid.LOWER_BOUND; columns ++)

                try
                {
                    Thread.sleep(DELAY);
                }
                catch (InterruptedException delayException)
                {
                    System.err.println("Uh-oh, could not delay!");
                } // end of catch (InterruptedException delayException)
            }  // end of while (continueUpdating)   

            while (!continueUpdating)
            {
                try
                {
                    Thread.sleep(DELAY);
                }
                catch (InterruptedException delayException)
                {
                    System.err.println("Uh-oh, could not delay!");
                } // end of catch (InterruptedException delayException
            } // end of while (!continueUpdating)
        } // end of while (threadExists)
    } // end of method run()

    /*
     * inner classes
     */
    /*
     * A class meant to handle mouse events from the rectangle images.
     */
    class GridInteraction implements MouseListener
    {
        /*
         * Toggles the alive state of the cell that was clicked.
         * 
         * @param mouseClickedEvent the details of the event handled
         */
        public void mouseClicked(MouseEvent mouseClickedEvent)
        {
            boolean continueChecking = true;

            if (enableToggling)
            {
                yCoordinate = SQUARE_SIZE;
                for (int columns = 0; columns < grid.getLowerBound() && continueChecking == true;
                columns ++)
                {
                    xCoordinate = SQUARE_SIZE;
                    for (int rows = 0; rows < grid.getRightBound() && continueChecking == true;
                    rows ++)
                    {
                        if (mouseClickedEvent.getPoint().getX() <= xCoordinate &&
                        mouseClickedEvent.getPoint().getY() <= yCoordinate)
                        {
                            if (grid.getCell(rows, columns).isAlive())
                            {
                                grid.getCell(rows, columns).setAlive(false);
                                drawCellState(grid.getCell(rows, columns));   
                                continueChecking = false;   
                            }
                            else if (!grid.getCell(rows, columns).isAlive())
                            {
                                grid.getCell(rows, columns).setAlive(true);
                                drawCellState(grid.getCell(rows, columns));
                                continueChecking = false; 
                            } // end of if (grid.getCell(rows, columns).isAlive())
                        }
                        else
                        {
                            xCoordinate += SQUARE_SIZE;
                        } // end of if (mouseClickedEvent.getPoint().getX() <= xCoordinate ...
                    } // end of for (rows = 0; rows < grid.LOWER_BOUND &&
                    yCoordinate += SQUARE_SIZE;
                } // end of for (columns = 0; columns < grid.LOWER_BOUND && ...
            } // end of if (enableToggling)
        } // end of method mouseClicked(MouseEvent mouseClickedEvent)

        /*
         * Not meant to do anything.
         * 
         * @param mouseEnteredEvent the details of the event handled
         */
        public void mouseEntered(MouseEvent mouseEnteredEvent)
        {
            // do nothing
        } // end of method mouseEntered(MouseEvent mouseEnteredEvent)

        /*
         * Not meant to do anything.
         * 
         * @param mouseExitedEvent the details of the event handled
         */
        public void mouseExited(MouseEvent mouseExitedEvent)
        {
            // do nothing
        } // end of method mouseExited(MouseEvent mouseExitedEvent)

        /*
         * Not meant to do anything.
         * 
         * @param mousePressedEvent the details of the event handled
         */
        public void mousePressed(MouseEvent mousePressedEvent)
        {
            // do nothing
        } // end of method mousePressed(MouseEvent mousePressedEvent)

        /*
         * Not meant to do anything.
         * 
         * @param mouseReleasedEvent the details of the event handled
         */
        public void mouseReleased(MouseEvent mouseReleasedEvent)
        {
            // do nothing
        } // end of method mouseReleased(MouseEvent mouseReleasedEvent)
    } // end of class GridInteraction

    // *********************************************************
    // code adapted from http://stackoverflow.com/questions/19803276/rectangle-not-drawing-on-bufferedimage
    // date accessed: 2015-04-29
    /*
     * A container capable of drawing rectangles upon an image.
     */
    class RectanglePaint extends JPanel
    {
        /*
         * instance fields
         */
        private BufferedImage canvas = new BufferedImage(gridWidth, 
                gridHeight, BufferedImage.TYPE_INT_ARGB);

        /*
         * mutators
         */
        /**
         * Paints a rectangle to the canvas.
         * 
         * @param context the graphical context of the component that
         * is being drawn on.
         */
        public void paintComponent(Graphics context)
        {
            super.paintComponent(context);
            context.drawImage(canvas, 0, 0, null);
        } // end of method paintComponent(Graphics context)

        /**
         * Draws a filled rectangle with the specified colour and at the specified location.
         * 
         * @param colour the colour of the rectangle
         * @param xCoordinate the x coordinate of the rectangle
         * @param yCoordinate the y coordinate of the rectangle
         * @param width the width of the rectangle
         * @param height the height of the rectangle
         */
        public void drawFullRectangle(Color colour, int xCoordinate, 
        int yCoordinate, int width, int height, boolean raisedOrNot) 
        {
            Graphics2D shapeDrawn = canvas.createGraphics();
            shapeDrawn.setColor(colour);
            shapeDrawn.fill3DRect(xCoordinate, yCoordinate, width, height, raisedOrNot);
            shapeDrawn.dispose();
            repaint();
        } // end of method drawFullRectangle(Color colour, int xCoordinate, ...

        /**
         * Draws the outline rectangle with the specified colour and at the specified location.
         * 
         * @param colour the colour of the rectangle
         * @param xCoordinate the x coordinate of the rectangle
         * @param yCoordinate the y coordinate of the rectangle
         * @param width the width of the rectangle
         * @param height the height of the rectangle
         */
        public void drawOutlinedRectangle(Color colour, int xCoordinate, 
        int yCoordinate, int width, int height) 
        {
            Graphics2D shapeDrawn = canvas.createGraphics();
            shapeDrawn.setColor(colour);
            shapeDrawn.drawRect(xCoordinate, yCoordinate, width, height);
            shapeDrawn.dispose();
            repaint();
        } // end of method drawOutlinedRectangle(Color colour, int xCoordinate ...
    } // end of class RectanglePaint
    // *********************************************************

    class OpenItems implements Runnable
    {
        /*
         * Prepares a new grid for presentation using a selected file. 
         */
        public void run()
        {
            Scanner fileStepper = null;
            int widthOfGrid = 0;
            int heightOfGrid = 0;
            JFileChooser selectFileToOpen = new JFileChooser(System.getProperty("user.dir"));
            selectFileToOpen.showOpenDialog(mainFrame);

            File fileOpened = selectFileToOpen.getSelectedFile();

            try
            {
                fileStepper = new Scanner(fileOpened);
            }
            catch (FileNotFoundException failedToOpenException)
            {
                System.out.println("Uh-oh, failed to read the selected file");
                System.out.println("This may be because the selected file does not exist");
                return;
            } // end of catch (FileNotFoundException failedToOpenException)

            // get the first 2 lines of the text file, which should be the dimensions
            // the width should have been stored first, and the height second
            for (int dimensionXAndY = 0; dimensionXAndY < 2; dimensionXAndY ++)
            {
                if (fileStepper.hasNextLine())
                {
                    fileStepper.nextLine();
                    String dimensions = fileStepper.next();

                    try 
                    {
                        if (dimensionXAndY == 0)
                        {
                            widthOfGrid = Integer.parseInt(dimensions);
                        }
                        else
                        {
                            heightOfGrid = Integer.parseInt(dimensions);
                        } // end of if (dimensionXAndY == 0)
                    }
                    catch (NumberFormatException conversionException)
                    {
                        System.out.println("Uh-oh, there was a problem converting the selected integer!");
                    } // end of catch (NumberFormatException conversionException)
                } // end of if (fileStepper.hasNextLine())
            } // end of for (int dimensionXAndY = 0; dimensionXAndY < 3; dimensionXAndY ++)

            int[] livingColumnCoordinates = new int[heightOfGrid / SQUARE_SIZE];
            int[] livingRowCoordinates = new int[widthOfGrid / SQUARE_SIZE];

            int columnPair = 0;
            int rowPair = 0;

            int numberOfPairs = 0;
            // get the coordinates of living cells as specified within the text file
            while (fileStepper.hasNextLine())
            {
                fileStepper.nextLine();
                for (int pair = 0; pair < 2; pair ++)
                {
                    try 
                    {
                        String currentCoordinateRowOrColumn = fileStepper.next();

                        if (fileStepper.hasNextLine())
                        {
                            if (pair == 0)
                            {
                                livingColumnCoordinates[columnPair] = Integer.parseInt(currentCoordinateRowOrColumn);
                            }
                            else
                            {
                                livingRowCoordinates[rowPair] = Integer.parseInt(currentCoordinateRowOrColumn);
                            } // end of if (dimensionXAndY == 0)
                        }
                        else
                        {
                            // test if this is the last line and last token
                            numberOfPairs = Integer.parseInt(currentCoordinateRowOrColumn);
                        } // end of if (fileStepper.hasNextLine())
                    }
                    catch (NumberFormatException conversionException)
                    {
                        System.out.println("Uh-oh, there was a problem converting the selected integer!");
                    } // end of catch (NumberFormatException conversionException)
                } // end of while (fileStepper.hasNext())
                columnPair ++;
                rowPair ++;
            } // end of while (fileStepper.hasNextLine())

            GridGUI newGridOpened = new GridGUI(livingRowCoordinates, livingColumnCoordinates, numberOfPairs,
                    widthOfGrid / SQUARE_SIZE, heightOfGrid / SQUARE_SIZE);
        } // end of method run()
    } // end of class OpenItems
    /*
     * static methods
     */
    /**
     * Makes a new grid display and runs the simulation of 
     * Conway's Game of Life.
     * 
     * @param argument not used.
     */
    public static void main(String[] arguments)
    {
        GridGUI gridGUI = new GridGUI();
    } // end of main(String[] arguments)
} // end of class GridGUI