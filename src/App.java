import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        int [][] root={ {1,0,3},
                        {4,2,5},
                        {6,7,8}};
        Nodo piezaNodo=new Nodo(root);
        Puzzle solucioncilla=new Puzzle();
       // List<Nodo> solucion=solucioncilla.busquedaAnchura(piezaNodo);
       List<Nodo> solucion=solucioncilla.busquedaProfundidad(piezaNodo);
        
        for (Nodo nodo : solucion) {
            nodo.Imprime();
            System.out.println("------");
        }//for
       

    }//main
}//App
