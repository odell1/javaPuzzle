import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    

    public List<Nodo> busquedaAnchura(Nodo Root){
        List<Nodo> abiertos=new ArrayList<>();//Nodos por visitar
        List<Nodo> cerrados=new ArrayList<>();//Nodos visitados
        List<Nodo> solucion=new ArrayList<>();//Solución encontrada
        abiertos.add(Root);

        while (abiertos.size()>0) {
            Nodo actual=abiertos.get(0);//cojo el primero
            abiertos.remove(0);
            cerrados.add(actual);
            if(actual.esMeta()){
                //Hemos encontrado la solución
                System.out.println("Fenómeno!!!!!");
                solucion=trazaSolucion(actual);
                return solucion;
            }
            //Expandimos los nodos
            actual.expandirNodo();
            for (int i = 0; i < actual.hijos.size(); i++) {
                Nodo hijoActual=actual.hijos.get(i);
                if(!contiene(abiertos,hijoActual) && !contiene(cerrados,hijoActual)){
                    //lo metemos en abiertos
                    abiertos.add(hijoActual);
                }
            }//for

        }//while

        return null;//Quiere decir que no hemos encontrado solución.
    }//busquedaAnchura


    private List<Nodo> trazaSolucion(Nodo actual) {
        List<Nodo> solucion=new ArrayList<>();
        Nodo aux=actual;
        while(aux!=null){
            solucion.add(0,aux);//Lo meto al principio
            aux=aux.padre;
        }
        return solucion;
    }//trazaSolucion


    /////Coimprueba si está ese nodo en la lista que se le pase
    private boolean contiene(List<Nodo> aux, Nodo hijoActual) {
        for(Nodo nodo:aux){
            if(nodo.esMismoNodo(hijoActual)) return true;
        }
        return false;
    }//Contiene

}//Puzzle
