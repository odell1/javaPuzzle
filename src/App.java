public class App {
    public static void main(String[] args) throws Exception {
        int [][] root={{1,2,3},
                        {4,0,5},
                        {6,7,8}};
        Nodo piezaNodo=new Nodo(root);
        Puzzle solucioncilla=new Puzzle();
        solucioncilla.busquedaAnchura(piezaNodo);
        
       

    }//main
}//App
