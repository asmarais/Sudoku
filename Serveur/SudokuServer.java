import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;

public class SudokuServer {
    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null)
                System.setSecurityManager(new RMISecurityManager());

            FabSudokuImpl fabrique = new FabSudokuImpl();
            Registry registry = LocateRegistry.createRegistry(2000);
            registry.rebind("FabSudoku", fabrique);

            System.out.println("Sudoku Server with Fabrique is running...");
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
