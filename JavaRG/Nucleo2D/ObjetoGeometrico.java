/*
 * ObjetoGeometrico.java
 *
 * Created on 17 de octubre de 2000, 9:21
 */

package JavaRG.Nucleo2D;

import java.awt.*;

/** La clase <code>ObjetoGeométrico</code> es una clase abstracta que 
 * representa cualquiera de los objetos geométricos de la libreria 
 * geométrica
 *
 * @author  Pablo Suau
 * @version 1.1
 */
public abstract class ObjetoGeometrico extends Object {

  /** Nombre asignado al objeto */
    protected String nombre = new String("");
    
  /** Constructor por defecto del objeto geométrico. Es un contructor
    * vacío
    * @return un nuevo objeto geométrico
    */
    public ObjetoGeometrico() {
    }

  /** Determina si dos objetos geométricos son iguales (función abstracta)
    * @param otro Objeto geométrico con el que se desea realizar la comparación
    * @return un valor booleano indicando si ambos objetos geométricos son 
    * iguales
    */
    public abstract boolean equals(ObjetoGeometrico otro);
    
  /** Obtiene la distancia del objeto a un punto
    * @param punto punto al cual se desea obtener la distancia
    * @return la distancia del objeto geométrico al punto pasado como parámetro
    */
    public abstract double distancia (Punto2D punto);

  /**
    * Realiza la conversión de un objeto geométrico a una cadena.
    * @return una cadena indicando el tipo de objeto (ObjetoGeometrico)
    */
    public String toString() {
        return nombre + " - ObjetoGeometrico()";
    }
    
  /**
    * Dibujo del objeto como resultado (función vacía)
    */
    public void dibujarResultado(Graphics g) {
    }
    
  /** Cambia el nombre asignado al objeto geométrico
    * @param nom nombre que se desea asignar al objeto
    */
    public void cambiarNombre(String nom) {
        nombre = nom;
    }
    
  /** Obtiene el nombre asignado al objeto geométrico
    * @return el nombre del objeto geométrico
    */
    public String obtenerNombre() {
        return nombre;
    }
    
  /** Devuelve el número de dimensiones del objeto geométrico. Al ser una librería
    * geométrica 2D, este valor será constante (2)
    */
    public int dimensiones() {
        return 2;
    }
}