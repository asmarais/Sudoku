import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class SudokuImpl extends UnicastRemoteObject implements SudokuInterface {
    String[] puzzle = {
         "-8------5", "2-------9", "-----7-1-",
        "-5------4", "--3----9-", "--6-----7",
        "9-------2", "67-83----", "8----5---"
    
};
       

    private final String[] solution = {
        "387491625", "241568379", "569327418",
        "758619234", "123784596", "496253187",
        "934176852", "675832941", "812945763"
    };

    public SudokuImpl() throws RemoteException {
        super();
    }

    public String[] getPuzzle() throws RemoteException {
        return puzzle;
    }
    public String[] getSolution() throws RemoteException {
        return solution;
    }

    public boolean validateMove(int row, int col, String value) throws RemoteException {
        return String.valueOf(solution[row].charAt(col)).equals(value);
    }
    @Override
    public void callMeBack(int time, String param, ICallback callback) throws RemoteException {
        // Create and start a new Servant thread that will execute the callback
        Servant servant = new Servant(time, param, callback);
        servant.start();
    }
}
