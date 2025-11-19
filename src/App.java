import java.util.List;

import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) throws Exception {
        int maxima_profundidad=40;

        int [][] root={ {1,0,3},
                        {4,2,5},
                        {6,7,8}};
        Nodo piezaNodo=new Nodo(root);
        Puzzle solucioncilla=new Puzzle();
       // List<Nodo> solucion=solucioncilla.busquedaAnchura(piezaNodo);
       // List<Nodo> solucion=solucioncilla.busquedaProfundidad(piezaNodo);
       // List<Nodo> solucion=profundidad_acotada(maxima_profundidad, piezaNodo, solucioncilla);
        List<Nodo> solucion = solucioncilla.busquedaAsterisco(piezaNodo);

        // Iniciar la Interfaz Gráfica en el Event Dispatch Thread de Swing
        SwingUtilities.invokeLater(() -> {
            new PuzzleGUI(solucion);
        });

        

    }//main



    private static List<Nodo> profundidad_acotada(int maxima_profundidad, Nodo piezaNodo, Puzzle solucioncilla) {
        // Bucle que incrementa el límite de profundidad en cada iteración
        for (int limite = 10; limite <= maxima_profundidad; limite++) {
            System.out.println("Intentando con límite de profundidad: " + limite);
                      
            List<Nodo> solucion = solucioncilla.busquedaProfundidadAcotada(piezaNodo, limite);
                        
            if (solucion != null) {
                System.out.println("Solución encontrada en profundidad: " + limite);
                return solucion;
            }//if
        }//for
        return null; // No se encontró solución dentro del límite dado
    }
}//App
