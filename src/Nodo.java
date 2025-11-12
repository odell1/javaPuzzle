
/// Clase nodo
/// 

import java.util.ArrayList;
import java.util.List;

public class Nodo {
    public int [][] nodo=new int[3][3];
    public Nodo padre; //Referencia al nodo padre
    public List<Nodo> hijos=new ArrayList<>(); //Lista de hijos sucesores. 
    

    // Constructores
    
    public Nodo(int[][] nodo) {
        this.nodo = nodo;
        this.padre=null;
    }

    
    public Nodo(int[][] nodo, Nodo padre) {
        this.nodo = nodo;
        this.padre = padre;
    }


    ///////////
    /// Métodos intersantes a par que curiosos
    /// ////////

    /// Método para comprobar si hemos llegado a la meta o no
    public boolean esMeta(){
        int indice=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 if(this.nodo[i][j]!=indice) return false;
                 indice++;
            }//2º for
        }//1 for
    return true;
    }//esMeta

    /////Método para imprimir
    public void Imprime(){
        String str="";
           for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                 str+=this.nodo[i][j];
                 str+=" ";
                 
            }//2º for
            str+="\n";
        }//1 for
        System.out.println(str);
    }//Imprime

    ///////////////
    //Expandir nodos. Nos va a dejar en la lista que hemos creado los posibles hijos (lista hijos)
    ///////////////
    public void expandirNodo(){
       //Buscamos el hueco
       //Esto estaría bien hacer un método aparte.
       int fila=0;int columna=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.nodo[i][j]==0) {
                    fila=i;columna=j;//Nos quedamos con la posición del cero
                    break;
                }//if
            }//2º for

        }//1 for

        MueveAbajo(fila,columna,nodo);
        MueveArriba(fila,columna, nodo);
        MueveDerecha(fila,columna, nodo);
        MueveIzquierda(fila,columna,nodo);


    }//expandirNodo


    private void MueveAbajo(int fila, int columna, int[][] aux) {
        //Primero comprobamos que se puede mover el cero abajo
        if(fila<2){ //Comprobamos que lo podemos hacer
            int [][] destino=new int[3][3];
            copiaArray(aux,destino);
            int temporal=destino[fila+1][columna];
            destino[fila][columna]=temporal;
            //Tengo el movimiento en destino
            //Creamos el hijo y lo metemos en la lista
            Nodo hijo=new Nodo(destino,this);
            hijos.add(hijo);
        }//if

      
    }//MueveAbajo

    //Método para copiar array
    private void copiaArray(int[][] aux, int[][] destino) {
      for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                destino[i][j]=aux[i][j];
                }//if
            }//2º for

        }//1 for
    }//copiarArray


    //Getter & Setters
    public int[][] getNodo() {
        return nodo;
    }
    public void setNodo(int[][] nodo) {
        this.nodo = nodo;
    }
    public Nodo getPadre() {
        return padre;
    }
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }
    public List<Nodo> getHijos() {
        return hijos;
    }
    public void setHijos(List<Nodo> hijos) {
        this.hijos = hijos;
    }

    


    

}//Nodo
