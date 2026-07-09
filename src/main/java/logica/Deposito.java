package logica;
import java.util.ArrayList;

/**
 * Clase generica que representa un deposito para almacenar y gestionar elementos.
 * @param <T> Tipo de elemento que se va a almacenar.
 */
public class Deposito<T>{
    private ArrayList<T> elementos;

    /**
     * Constructor que inicializa el deposito con una lista vacia.
     */
    public Deposito(){
        elementos=new ArrayList<T>();
    }

    /**
     * Añade un nuevo elemento al final del deposito.
     * @param objeto Elemento de tipo T que se quiere almacenar.
     */
    public void add(T objeto){
        elementos.add(objeto);
    }

    /**
     * Remueve y devuelve el primer elemento almacenado.
     * @return Primer elemento de tipo T del deposito.
     */
    public T get(){
        if(elementos.size()>0){
            return elementos.remove(0);
        }
        else{
            return null;
        }
    }

    /**
     * Obtiene un elemento del deposito en una posicion especifica sin removerlo.
     * @param indice Indice del elemento que se quiere consultar.
     * @return Elemento de tipo T en el indice indicado o null si el indice está fuera de rango o el deposito está vacio.
     */
    public T getElemento(int indice){
        if(elementos.size()>0 && indice>=0 && indice<elementos.size()){
            return elementos.get(indice);
        }
        else{
            return null;
        }
    }

    /**
     * Obtiene la cantidad de elementos almacenados en el deposito.
     * @return Numero total de elementos en el deposito.
     */
    public int getSize(){
        return elementos.size();
    }
}
