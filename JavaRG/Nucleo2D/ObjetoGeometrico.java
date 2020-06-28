/*
 * ObjetoGeometrico.java
 *
 * Created on 17 de octubre de 2000, 9:21
 */

package JavaRG.Nucleo2D;

import java.awt.*;

/** La clase <code>ObjetoGeom�trico</code> es una clase abstracta que 
 * representa cualquiera de los objetos geom�tricos de la libreria 
 * geom�trica
 *
 * @author  Pablo Suau
 * @version 1.1
 */
public abstract class ObjetoGeometrico extends Object {

  /** Nombre asignado al objeto */
    protected String nombre = new String("");
    
  /** Constructor por defecto del objeto geom�trico. Es un contructor
    * vac�o
    * @return un nuevo objeto geom�trico
    */
    public ObjetoGeometrico() {
    }

  /** Determina si dos objetos geom�tricos son iguales (funci�n abstracta)
    * @param otro Objeto geom�trico con el que se desea realizar la comparaci�n
    * @return un valor booleano indicando si ambos objetos geom�tricos son 
    * iguales
    */
    public abstract boolean equals(ObjetoGeometrico otro);
    
  /** Obtiene la distancia del objeto a un punto
    * @param punto punto al cual se desea obtener la distancia
    * @return la distancia del objeto geom�trico al punto pasado como par�metro
    */
    public abstract double distancia (Punto2D punto);

  /**
    * Realiza la conversi�n de un objeto geom�trico a una cadena.
    * @return una cadena indicando el tipo de objeto (ObjetoGeometrico)
    */
    public String toString() {
        return nombre + " - ObjetoGeometrico()";
    }
    
  /**
    * Dibujo del objeto como resultado (funci�n vac�a)
    */
    public void dibujarResultado(Graphics g) {
    }
    
  /** Cambia el nombre asignado al objeto geom�trico
    * @param nom nombre que se desea asignar al objeto
    */
    public void cambiarNombre(String nom) {
        nombre = nom;
    }
    
  /** Obtiene el nombre asignado al objeto geom�trico
    * @return el nombre del objeto geom�trico
    */
    public String obtenerNombre() {
        return nombre;
    }
    
  /** Devuelve el n�mero de dimensiones del objeto geom�trico. Al ser una librer�a
    * geom�trica 2D, este valor ser� constante (2)
    */
    public int dimensiones() {
        return 2;
    }
}