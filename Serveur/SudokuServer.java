import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;


public class SudokuServer {
    public static void main(String[] args) {
        //Sécurité
        try {
            if(System.getSecurityManager() == null)
		   	System.setSecurityManager(new RMISecurityManager());
            SudokuImpl game = new SudokuImpl();
            Registry registry = LocateRegistry.createRegistry(2000);
            registry.rebind("SudokuGame", game);
            System.out.println("Sudoku Server is running...");
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
