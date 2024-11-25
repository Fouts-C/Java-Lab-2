package maze;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * MazeSolver is a backtracking version of MazeGame.
 * 
 * @author Mitch Parry
 * @author Willow Sapphire
 * @author Carson Fouts
 * @version 4/17
 */
public class MazeSolver
{
    /**
     * The height of game maps.
     */
    private final static int HEIGHT = 99;

    /**
     * The width of game maps.
     */
    private final static int WIDTH = 99;

    /**
     * The game map, as a 2D array of booleans.
     * True indicates the spot is blocked.
     */
    private boolean[][] wall;

    /**
     * Works like wall but holds visited spots.
     */
    private boolean[][] visited;

    /**
     * Constructor sets up the maps and the path list.
     * 
     * @param mazeFile name of the file containing the map.
     */
    public MazeSolver(String mazeFile)
    {
        loadMaze(mazeFile);
        visited = new boolean[HEIGHT][WIDTH];
    }

    /**
     * Loads the data from the maze file and creates the map
     * 2D array.
     *  
     * @param mazeFile the input maze file.
     */
    private void loadMaze(String mazeFile)
    {
        wall = new boolean[HEIGHT][WIDTH];
        Scanner mazeScanner;
        try
        {
            mazeScanner = new Scanner(new FileReader(mazeFile));
            for (int i = 0; i < HEIGHT; i++)
            {
                for (int j = 0; j < WIDTH; j++)
                {
                    if (mazeScanner.next().equals("1"))
                    {
                        wall[i][j] = true;
                    }
                }
            }
            mazeScanner.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + mazeFile);
        }
    }

    /**
     * Prints the map.
     */
    public void printMap()
    {
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                if (wall[i][j])
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print("_");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Call to the recursive method backtrack.
     * 
     * @return path recursive method found.
     */
    public String findSolution()
    {
        return backtrack(0, 0, "");
    }

    /**
     * Recursive method to find path.
     * 
     * @param row row.
     * @param col column.
     * @param pathSF the path so far.
     * @return path if there is one else null.
     */
    private String backtrack(int row, int col, String pathSF)
    {

        if (row == HEIGHT - 1 && col == WIDTH - 1)
        {
            return pathSF;
        }
        
        if (row < 0 || col < 0 || row >= HEIGHT || col >= WIDTH 
            || wall[row][col] || visited[row][col])
        {
            return null;
        }

        visited[row][col] = true;
        String[] posPath = {"up", "down", "left", "right"};
        int[][] moves = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < 4; i++)
        {
            String result = backtrack(row + moves[i][0], 
                col + moves[i][1], pathSF + " " + posPath[i]);
            if (result != null)
            {
                return result;
            }
        }

        visited[row][col] = false;

        return null;
    }
}
