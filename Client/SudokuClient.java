import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;

public class SudokuClient {
    private SudokuInterface stub;
    private String[] puzzle;
    private String[] solution;

    private JFrame frame = new JFrame("Sudoku Client");
    private JPanel boardPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();
    private JLabel statusLabel = new JLabel("Errors: 0", SwingConstants.CENTER);

    private JButton numSelected = null;
    private int errors = 0;

    class Tile extends JButton {
        int r, c;

        Tile(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public SudokuClient() {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2000);
            FabSudokuInterface fabrique = (FabSudokuInterface) registry.lookup("FabSudoku");
            stub = fabrique.createSudokuGame();  // Récupération dynamique du jeu via la fabrique

            puzzle = stub.getPuzzle();
            solution = stub.getSolution();
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(statusLabel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9, 9));
        setupTiles();
        frame.add(boardPanel, BorderLayout.CENTER);

        buttonsPanel.setLayout(new GridLayout(1, 9));
        setupButtons();
        frame.add(buttonsPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void setupTiles() {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = new Tile(r, c);
                char ch = puzzle[r].charAt(c);
                if (ch != '-') {
                    tile.setText(String.valueOf(ch));
                    tile.setFont(new Font("Arial", Font.BOLD, 20));
                    tile.setBackground(Color.LIGHT_GRAY);
                    tile.setEnabled(false);
                } else {
                    tile.setFont(new Font("Arial", Font.PLAIN, 20));
                    tile.setBackground(Color.WHITE);
                    tile.addActionListener(e -> {
                        if (numSelected != null && tile.getText().equals("")) {
                            try {
                                boolean correct = stub.validateMove(tile.r, tile.c, numSelected.getText());
                                if (correct) {
                                    tile.setText(numSelected.getText());
                                } else {
                                    errors++;
                                    statusLabel.setText("Errors: " + errors);
                                }
                                checkIfCompleted();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }

                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                boardPanel.add(tile);
            }
        }
    }

    private void setupButtons() {
        for (int i = 1; i <= 9; i++) {
            JButton btn = new JButton(String.valueOf(i));
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.setFocusable(false);
            btn.setBackground(Color.WHITE);

            btn.addActionListener(e -> {
                if (numSelected != null) numSelected.setBackground(Color.WHITE);
                numSelected = btn;
                btn.setBackground(Color.LIGHT_GRAY);
            });

            buttonsPanel.add(btn);
        }
    }

    private void checkIfCompleted() {
        boolean completed = true;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Tile tile = (Tile) boardPanel.getComponent(r * 9 + c);
                if (tile.getText().equals("") || !tile.getText().equals(String.valueOf(solution[r].charAt(c)))) {
                    completed = false;
                    break;
                }
            }
            if (!completed) break;
        }

        if (completed) {
            JOptionPane.showMessageDialog(frame, "Félicitations ! Vous avez terminé la grille !", "Victoire", JOptionPane.INFORMATION_MESSAGE);
            int option = JOptionPane.showConfirmDialog(frame, "Voulez-vous rejouer ?", "Rejouer", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                frame.dispose();
                new SudokuClient();
            } else {
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) {
        System.setSecurityManager(new RMISecurityManager());
        new SudokuClient();
    }
}
