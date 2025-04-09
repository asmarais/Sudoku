import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SudokuServer {
    public static void main(String[] args) {
        try {
            SudokuImpl game = new SudokuImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("SudokuGame", game);
            System.out.println("Sudoku Server is running...");
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
