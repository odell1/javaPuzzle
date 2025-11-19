import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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

// ---------------------------------------------------------------------
    /**
     * Búsqueda en Profundidad (DFS - Depth-First Search).
     * Utiliza una Pila (Deque) para la lista 'abiertos', garantizando un orden LIFO.
     * * @param Root El nodo inicial.
     * @return Lista de Nodos con el camino solución, o null si no se encuentra.
     */
    public List<Nodo> busquedaProfundidad(Nodo Root){
        //Cambiamos List a Deque y usamos ArrayDeque como implementación de Pila
        Deque<Nodo> abiertos = new ArrayDeque<>();// Nodos por visitar (Pila LIFO)
        List<Nodo> cerrados = new ArrayList<>();// Nodos visitados
        List<Nodo> solucion = new ArrayList<>();// Solución encontrada
        int contador=0;//Vueltas del bucle
        
        abiertos.push(Root); // Añadir el nodo raíz (Push en la Pila)

        while (!abiertos.isEmpty()) {
            
            // *** CAMBIO CLAVE para DFS (LIFO) ***
            //Usamos pop() para coger y eliminar el ÚLTIMO elemento añadido
            Nodo actual = abiertos.pop(); 
            // **********************************

            // Si el nodo ya fue visitado (está en cerrados), lo saltamos
            // Esto es más estricto que en BFS simple, pero previene ciclos.
           /* if(contiene(cerrados, actual)) {
                continue;
            }
            */
                       
            if(actual.esMeta()){
                // Hemos encontrado la solución
                System.out.println("Fenómeno!!!!!");
                solucion = trazaSolucion(actual);
                return solucion;
            } 

            cerrados.add(actual);

            // Expandimos los nodos
            actual.expandirNodo();
            
            //Recorremos los hijos e invertimos el orden de inserción si es necesario 
            //para que la DFS sea determinista (ej. Derecha antes que Izquierda)
            //Aquí, simplemente los añadimos: el último hijo expandido será el primero en ser 'pop'
            for (int i = 0; i < actual.hijos.size(); i++) {
                Nodo hijoActual = actual.hijos.get(i);
                
                //Comprobación de que no esté en ninguna de las dos listas
                if(!contiene(abiertos, hijoActual) && !contiene(cerrados, hijoActual)){
                    //lo metemos en abiertos (Push en la Pila)
                    abiertos.push(hijoActual);
                }
            }//for
            System.out.println("Vueltas del bucle DFS: " + (++contador) + ", Tamaño abiertos: " + abiertos.size() + ", Tamaño cerrados: " + cerrados.size());

        }//while

        return null;//Quiere decir que no hemos encontrado solución.
    }//busquedaProfundidad

// ---------------------------------------------------------------------

/**
     * Búsqueda en Profundidad Acotada (DLS).
     * Realiza una DFS limitada a una profundidad máxima 'limite'.
     * @param Root El nodo inicial.
     * @param limite La profundidad máxima (Costo) a explorar.
     * @return Lista de Nodos con el camino solución, o null si no se encuentra.
     */
    public List<Nodo> busquedaProfundidadAcotada(Nodo Root, int limite) {
        
        // Variables
        List<Nodo> abiertos = new ArrayList<>();
        List<Nodo> cerrados = new ArrayList<>(); 
        List<Nodo> caminoSolucion = new ArrayList<>(); 
        

        //Inicializar el costo/profundidad del nodo raíz
        Root.Costo = 0; // El coste del nodo raíz es 0
        abiertos.add(Root); // Añadir el raíz (Push)

        while (abiertos.size() > 0) {
            
            // Cogemos y eliminamos el último elemento
            int ultimoIndice = abiertos.size() - 1;
            Nodo actual = abiertos.get(ultimoIndice);
            
            cerrados.add(actual); // Marcamos como visitado

            // 1. Verificar si es la meta
            if (actual.esMeta()) { 
                
                trazaSolucion(actual); 
                return caminoSolucion;
            }
            abiertos.remove(ultimoIndice);

           
            //Solo expandimos el nodo si su profundidad (Costo) es menor que el límite.
            if (actual.Costo < limite) { 

                //Expandimos el nodo actual
                actual.expandirNodo(); 
                
                for (int i = 0; i < actual.hijos.size(); i++) {
                    Nodo hijoActual = actual.hijos.get(i);
                    
                    //profundidad acumulada
                    hijoActual.Costo = actual.Costo + 1; 

                    //Si no está en abiertos ni en cerrados
                    if (!contiene(abiertos, hijoActual) && !contiene(cerrados, hijoActual)) { 
                        //Metemos como una posible solución (Push en la Pila)
                        abiertos.add(hijoActual); 
                    }//if
                }//for
            }
    
        }//while

        // Si se vacía la lista 'abiertos' sin encontrar la meta dentro del límite.
        System.out.println("No se encontró solución en el límite " + limite);
        return null;

}//busquedaProfundidadAcotada

// ******************************************************
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

    ////////////////////////////////
    private boolean contiene(Deque<Nodo> lista, Nodo hijoActual) {
        for (Nodo nodo : lista) {
            
        
            if (nodo.esMismoNodo(hijoActual.nodo)) { 
                return true;
            }
        }
        return false;
    }

}//Puzzle
