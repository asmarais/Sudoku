import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class SudokuImpl extends UnicastRemoteObject implements SudokuInterface {
    String[] puzzle = {
    "--3-9----", "-6---2--5", "8--4--7--", 
    "--7-5--9-", "9----1---", "-3-9--2--", 
    "5--6--8--", "--2-1--7", "----4--3-"
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
}
