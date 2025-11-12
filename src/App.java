public class App {
    public static void main(String[] args) throws Exception {
        int [][] root={{1,2,3},
                        {0,8,4},
                        {6,7,5}};
        Nodo piezaNodo=new Nodo(root);
        piezaNodo.expandirNodo();
        System.out.println("imprime nodos hijos");
        for (Nodo hijo : piezaNodo.hijos) {
            hijo.Imprime();
            System.out.println("\n\n");
        }

    }//main
}//App
