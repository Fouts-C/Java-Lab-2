package maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class MazeGame {

    //Constant variables
    public static final int HEIGHT = 19;
    public static final int WIDTH = 39;
    private static final int COL = 1;
    private static final int ROW = 0;

    //Private fields
    private Scanner playerInput;
    private boolean[][] blocked;
    private boolean[][] visited;
    private int[] player;
    private int[] goal;
    private int[] start;


    //Methods
    //Constructors 1 arg and 2 arg
    public MazeGame(String mazeFile) throws FileNotFoundException{
        this(mazeFile, new Scanner(System.in));
    }

    public MazeGame(String mazeFile, Scanner playerInput) throws FileNotFoundException {
        this.playerInput = playerInput;
        loadMaze(mazeFile);
    }

    //Other methods
    public void playGame(){

        do {
            prompt();
            
        }while(!makeMove(playerInput.nextLine()));

        if(playerAtGoal()){
            System.out.println("You Won!");
        }else{
            System.out.println("Goodbye!");
        }
    }
    

    public void printMaze() {
        System.out.println("*---------------------------------------*");
    
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print("|");
    
            for (int j = 0; j < WIDTH; j++) {
                if (player[ROW] == i && player[COL] == j) {
                    System.out.print("@");
                } else if (start[ROW] == i && start[COL] == j) {
                    System.out.print("S");
                } else if (goal[ROW] == i && goal[COL] == j) {
                    System.out.print("G");
                } else if (visited[i][j]) {
                    System.out.print(".");
                } else if (blocked[i][j]) {
                    System.out.print("X");
                } else {
                    System.out.print(" ");
                }
            }
    
            System.out.println("|");
        }
    
        System.out.println("*---------------------------------------*");
    }
    


    public int getPlayerRow(){
        return this.player[ROW];
    }

    public int getPlayerCol(){
        return this.player[COL];
    }


    public int getGoalRow(){
        return this.goal[ROW];
    }
    public int getGoalCol(){
        return this.goal[COL];
    }


    public int getStartRow(){
        return this.start[ROW];
    }

    public int getStartCol(){
        return this.start[COL];
    }

    public boolean[][] getBlocked() {
        return copyTwoDimBoolArray(blocked);
    }

    public boolean[][] getVisited(){
        return copyTwoDimBoolArray(visited);
    }

    public Scanner getPlayerInput(){
        return this.playerInput;
    }

    public void setPlayerRow(int row){
        if(row >= 0 && row < HEIGHT){
            this.player[ROW] = row;
        }
    }

    public void setPlayerCol(int col){
        if(col >= 0 && col < WIDTH){
            this.player[COL] = col;
        }
    }

    public void setGoalRow(int row){
        if(row >= 0 && row < HEIGHT){
            this.goal[ROW] = row;
        }
    }

    public void setGoalCol(int col){
        if(col >= 0 && col < WIDTH){
            this.goal[COL] = col;
        }
    }

    public void setStartRow(int row){
        if(row >= 0 && row < HEIGHT){
            this.start[ROW] = row;
        }
    }

    public void setStartCol(int col){
        if(col >= 0 && col < WIDTH){
            this.start[COL] = col;
        }
    }

    public void setBlocked(boolean[][] blocked){
        this.blocked = copyTwoDimBoolArray(blocked);
    }

    public void setVisited(boolean[][] visited){
        this.visited = copyTwoDimBoolArray(visited);
    }

    public void setPlayerInput(Scanner playerInput){
        this.playerInput = playerInput;
    }

    private boolean[][] copyTwoDimBoolArray(boolean[][] arrayToCopy){
        
        boolean[][] copiedArr = new boolean[arrayToCopy.length][arrayToCopy[0].length];

        for(int i = 0; i < arrayToCopy.length; i++){

            for(int j = 0; j < arrayToCopy[i].length; j++){
                copiedArr[i][j] = arrayToCopy[i][j];
            }
        }
        return copiedArr;
    }

    private void prompt(){

        printMaze();
        System.out.print("Enter your move (up, down, left, right, or q to quit): ");

    }

    private boolean playerAtGoal(){
        return (getPlayerRow() == getGoalRow() && getPlayerCol() == getGoalCol());
    }

    private boolean valid(int row, int col){
        return row >= 0 && row < HEIGHT && col >= 0 && col < WIDTH && !blocked[row][col];
    }

    private void visit(int row, int col){
        
        visited[row][col] = true;
    }

    private void loadMaze(String mazeFile) throws FileNotFoundException{

        blocked = new boolean[HEIGHT][WIDTH];
        visited = new boolean[HEIGHT][WIDTH];
        player = new int[2];
        goal = new int[2];
        start = new int[2];


        Scanner fileIn = new Scanner(new File(mazeFile));

        //fileIn.next

            for (int i = 0; i < HEIGHT; i++){

                for(int j = 0; j < WIDTH; j++){
                    String readNum = fileIn.next();
                    
                    if (readNum.equals("1")){
                        blocked[i][j] = true;
                    }else if(readNum.equals("0")){
                        blocked[i][j] = false;
                    }else if(readNum.equals("S")){
                        setStartRow(i);
                        setStartCol(j);
                        setPlayerRow(i);
                        setPlayerCol(j);
                    }else if(readNum.equals("G")){
                        setGoalRow(i);
                        setGoalCol(j);
                    }
                }
        }
        fileIn.close();

    }

    private boolean makeMove(String move){
        
        int r = player[ROW];
        int c = player[COL];

        move = move.toLowerCase();
        char mo = move.charAt(0);


        switch (mo){
            
            case 'q':
                return true;
            
            case 'd':
                r++;
                break;

            case 'u':
                r--;
                break;
          
            case 'l':
                c--;
                break;
            
            case 'r':
                c++;
                break;
            
            default:
                return false;
        }

        if(valid(r,c)){
            player[ROW] = r;
            player[COL] = c;
            visit(r,c);
            return playerAtGoal();
        }else{
            return false;
        }
    }
}
