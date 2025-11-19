import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PuzzleGUI extends JFrame {

    private final List<Nodo> solutionNodes;
    private int stepIndex = 0;
    private JLabel puzzleLabel;
    private JLabel stepCounterLabel;

public PuzzleGUI(List<Nodo> solutionNodes) { 
        super("Solución del 8-Puzzle)");
        this.solutionNodes = solutionNodes; // Asignación correcta
        
        if (solutionNodes == null || solutionNodes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontró solución.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        initializeUI();
        showCurrentNode();
    }
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Usamos BorderLayout

        // --- Etiqueta del Puzzle (Centro) ---
        puzzleLabel = new JLabel();
        puzzleLabel.setFont(new Font("Monospaced", Font.BOLD, 48)); // Fuente grande para la matriz
        puzzleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(puzzleLabel, BorderLayout.CENTER);

        // --- Panel de Controles (Abajo) ---
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton prevButton = new JButton("◀ Anterior");
        prevButton.addActionListener(e -> navigate(-1));
        
        stepCounterLabel = new JLabel();
        stepCounterLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        
        JButton nextButton = new JButton("Siguiente ▶");
        nextButton.addActionListener(e -> navigate(1));

        controlPanel.add(prevButton);
        controlPanel.add(stepCounterLabel);
        controlPanel.add(nextButton);
        
        add(controlPanel, BorderLayout.SOUTH);

        // Configuraciones finales de la ventana
        setSize(400, 450);
        setLocationRelativeTo(null); // Centrar la ventana
        setVisible(true);
    }

    private void navigate(int direction) {
        int newIndex = stepIndex + direction;
        
        if (newIndex >= 0 && newIndex < solutionNodes.size()) {
            stepIndex = newIndex;
            showCurrentNode();
        } else if (newIndex < 0) {
            JOptionPane.showMessageDialog(this, "Ya estás en el primer paso (Raíz).");
        } else {
            JOptionPane.showMessageDialog(this, "Fin de la solución (Meta).");
        }
    }

    private void showCurrentNode() {
        if (solutionNodes.isEmpty()) return;

        Nodo currentNode = solutionNodes.get(stepIndex);
        
        // 1. Formatear la matriz del puzzle
        String htmlMatrix = formatNodeToHTML(currentNode.nodo);
        puzzleLabel.setText(htmlMatrix);
        
        // 2. Actualizar el contador de pasos
        stepCounterLabel.setText("Paso " + (stepIndex + 1) + " de " + solutionNodes.size());
    }

    /**
     * Convierte la matriz 2D del Nodo en una cadena HTML para mostrarla.
     * @param matrix La matriz del puzzle.
     * @return HTML formateado.
     */
    private String formatNodeToHTML(int[][] matrix) {
        StringBuilder sb = new StringBuilder("<html><div style='text-align: center;'>");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String val = (matrix[i][j] == 0) ? "<span style='color: white; background-color: black;'>&nbsp;0&nbsp;</span>" : String.valueOf(matrix[i][j]);
                sb.append(val).append("&nbsp;&nbsp;");
            }
            sb.append("<br>");
        }
        sb.append("</div></html>");
        return sb.toString();
    }
}