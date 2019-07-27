package manoharprabhu.sudokusolver;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
    private int[][] grid = new int[9][9];

    public boolean readLine(int lineNumber, String data) {
        if(data == null || data.length() != 9) {
            return false;
        }

        if(lineNumber < 0 || lineNumber >= 9) {
            return false;
        }

        int i = 0;
        for(Character c : data.toCharArray()) {
            
            if(c < '0' || c > '9') { 
                return false;
            }

            this.grid[lineNumber][i++] = c - '0';
        }
        return true;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void printGrid() {
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 9; k++) {
                System.out.print(grid[i][k] + " ");
            }
            System.out.println();
        }
    }

    public boolean solveGrid() {
        
        if(this.isBoardSolved()) {
            return true;
        }
        
        int selectedRow = -1;
        int selectedColumn = -1;
        boolean found = false;
        for(int row = 0; row < 9; row++) {
            if(found) { 
                break;
            }
            for(int col = 0; col < 9; col++) {
                if(grid[row][col] == 0) {
                    selectedRow = row;
                    selectedColumn = col;
                    found = true;
                    break;
                }
            }
        }

        if(!found) { 
            return false;
        }

        List<Integer> validEntries = this.getValidEntries(selectedRow, selectedColumn);

        if(validEntries.isEmpty()) { 
            return false;
        }

        for(Integer ent : validEntries) {
            grid[selectedRow][selectedColumn] = ent;
            if(solveGrid()) {
                return true;
            } else {
                grid[selectedRow][selectedColumn] = 0;
            }
        }
        return false;
    }

    private boolean isBoardSolved() {
        for(int i = 0; i < 9; i++) {
            for(int k = 0; k < 9 ; k++) {
                if(grid[i][k] == 0) { 
                    return false;
                }

                int cellEntry = grid[i][k];
                grid[i][k] = 0;
                List<Integer> validEntries = this.getValidEntries(i, k);
                if(validEntries.size() != 1) {
                    grid[i][k] = cellEntry; 
                    return false;
                }
                if(validEntries.get(0) != cellEntry) {
                    grid[i][k] = cellEntry; 
                    return false;
                }
                grid[i][k] = cellEntry;
            }
        }
        return true;
    }

    private List<Integer> getValidEntries(int row, int col) {
        List<Integer> list = new ArrayList<>();
        if(row < 0 || row >= 9 || col < 0 || col >= 9) {
            return list;
        }
        
        if(grid[row][col] != 0) { 
            return list;
        }

        for(int i = 1; i <= 9; i++) {
            if(isNumberPresentInRowColumn(i, row, col)) {
                continue;
            }

            if(isNumberPresentInSection(i, row, col)) {
                continue;
            }

            list.add(i);
        }

        return list;
    }

    private boolean isNumberPresentInSection(int number, int row, int col) {
        int r = ((int)(row / 3)) * 3;
        int c = ((int)(col / 3)) * 3;
        for(int i = r; i < r + 3; i++) {
            for(int k = c; k < c + 3; k++) {
                if(grid[i][k] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isNumberPresentInRowColumn(int number, int row, int col) {
        for(int i = 0; i < 9; i++) {
            if(grid[row][i] == number || grid[i][col] == number) {
                return true;
            }
        }
        return false;
    }
}