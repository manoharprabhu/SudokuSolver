package manoharprabhu.sudokusolver;

public class App {
    public static void main(String[] args) throws Exception {
        Sudoku sudoku = new Sudoku();
        boolean status = true;
        status &= sudoku.readLine(0, "200080300");
        status &= sudoku.readLine(1, "060070084");
        status &= sudoku.readLine(2, "030500209");
        status &= sudoku.readLine(3, "000105408");
        status &= sudoku.readLine(4, "000000000");
        status &= sudoku.readLine(5, "402706000");
        status &= sudoku.readLine(6, "301007040");
        status &= sudoku.readLine(7, "720040060");
        status &= sudoku.readLine(8, "004010003");

        if(!status) {
            System.out.println("Grid is unsolvable");
        }

        if(sudoku.solveGrid()) {
            sudoku.printGrid();
        } else {
            System.out.println("Grid is unsolvable");
        }
    }
}