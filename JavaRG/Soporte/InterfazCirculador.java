/*
 * InterfazCirculador.java
 *
 * Created on 26 de diciembre de 2001, 16:36
 */

package JavaRG.Soporte;

/** Interfaz a implementar por todos aquellos objetos geom�tricos que 
 * vayan a ser usados con la clase circulador
 *
 * @author  Pablo Suau
 * @version 1.0
 */
public interface InterfazCirculador {
    
    /** Tipo para el circulador unidireccional (que es aquel que solo puede
      * avanzar hacia una posici�n anterior o una posici�n posterior)
      */
    final int cUNIDIRECCIONAL = 0;
    
    /** Tipo para el circulador bidireccional (puede avanzar una posici�n 
      * en ambas direcciones)
      */
    final int cBIDIRECCIONAL = 1;
    
    /** Tipo para el circulador aleatorio (Puede avanzar a cualquier posici�n)
      */
    final int cALEATORIO = 2;
    
    /** Rl objeto geom�trico debe indicar cu�l es la posici�n siguiente a una dada
      */
    int posicionSiguiente(int posicion);
    
    /** El objeto geom�trico debe indicar cu�l es la posici�n anterior a una dada
      */
    int posicionAnterior(int posicion);
    
    /** El objeto geom�trico debe indicar el n�mero de posiciones en las que 
      * se puede situar un circulador
      */
    int posiciones();
}
