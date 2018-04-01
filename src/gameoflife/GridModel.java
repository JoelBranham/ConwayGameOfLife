package gameoflife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

public class GridModel extends Observable{
    
    private Cell[][] cellGrid;
    private int numUnits;
    
    public GridModel(int rows, int cols, int numUnits){
        cellGrid = new Cell[rows][cols];
        this.numUnits = numUnits; 
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                cellGrid[i][j] = new Cell();
            }
        }
        setUp();
    }
    public GridModel(String filename){
        try{
            Scanner sc  = new Scanner(new File(filename));
            int rows = Integer.parseInt(sc.next());
            int cols = Integer.parseInt(sc.next());
            int units = Integer.parseInt(sc.next());      
            cellGrid = new Cell[rows][cols];
            this.numUnits = numUnits;
            sc.nextLine();
            
            for (int i = 0; i < rows; i++){
                String line = sc.nextLine();
                String[] parts = line.split(" ");
                
                for (int j = 0; j < cols; j++){
                    String cell = parts[j];
                    if (cell.equals("1")){
                        cellGrid[i][j] = new Cell(true);
                    }
                    else{
                        cellGrid[i][j] = new Cell(false);
                    }
                }
            }
        }
        catch(FileNotFoundException fe){
            System.out.println("File Not Found");
        }
        
    }
    
    public GridModel(){
        cellGrid = new Cell[10][10];
        numUnits = 25;
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                cellGrid[i][j] = new Cell();
            }
        }
        setUp();
    }
    
    public void setUp(){
        Random r = new Random();
        for (int i = 0; i < numUnits; i++){
            boolean placed = false;
            while (!placed){
                int row = r.nextInt(getNumRows());
                int col = r.nextInt(getNumCols());
                if (!cellGrid[row][col].isAlive()){
                    cellGrid[row][col].setState(true);
                    placed = true;
                }
            }
        }
        setChanged();
        notifyObservers();
    }
    
    public void tick(){
        boolean[][] futureUnitsState = new boolean[getNumRows()][getNumCols()];
        
        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumCols(); j++){
                futureUnitsState[i][j] = updateCellStatus(i, j);
            }
        }
        
        for (int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumCols(); j++){
                cellGrid[i][j].setState(futureUnitsState[i][j]);
            }
        }
        setChanged();
        notifyObservers();
    }
    
    private boolean updateCellStatus(int row, int col){
        int numAliveNeighbors = getNumAliveNeighbors(row, col);
        
        if (cellGrid[row][col].isAlive()){
            if (numAliveNeighbors <= 1){
                return false;
            }
            if (numAliveNeighbors >= 4){
                return false;
            }
            return true;
        }
        else if(numAliveNeighbors == 3){
            return true;
        } 
        return false;
    }
    
    private int getNumAliveNeighbors(int row, int col){
        int numNeighbors = 0;
        for (int i = -1; i <= 1; i++){
            for (int j = -1; j <= 1; j++){
                if ((i != 0 || j != 0) && isValidIndex(row + i, col + j)){
                    if (cellGrid[row + i][col + j].isAlive()){
                        numNeighbors++;
                    }
                }
            }
        }
        return numNeighbors;
    }
    
    public void toggleCellStatus(int row, int col){
        cellGrid[row][col].setState(!cellGrid[row][col].isAlive());
    }
    
    private boolean isValidIndex(int row, int col){
        return row >= 0 && row < getNumRows() && col >= 0 && col < getNumCols();
    }
    
    public boolean locationAlive(int row, int col){
        return cellGrid[row][col].isAlive();
    }   
    
    public int getNumRows(){
        return cellGrid.length;
    }
    public int getNumCols(){
        return cellGrid[0].length;
    }
}