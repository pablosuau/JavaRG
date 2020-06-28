/*
 * InterfazCirculador.java
 *
 * Created on 26 de diciembre de 2001, 16:36
 */

package JavaRG.Soporte;

/** Interfaz a implementar por todos aquellos objetos geométricos que 
 * vayan a ser usados con la clase circulador
 *
 * @author  Pablo Suau
 * @version 1.0
 */
public interface InterfazCirculador {
    
    /** Tipo para el circulador unidireccional (que es aquel que solo puede
      * avanzar hacia una posición anterior o una posición posterior)
      */
    final int cUNIDIRECCIONAL = 0;
    
    /** Tipo para el circulador bidireccional (puede avanzar una posición 
      * en ambas direcciones)
      */
    final int cBIDIRECCIONAL = 1;
    
    /** Tipo para el circulador aleatorio (Puede avanzar a cualquier posición)
      */
    final int cALEATORIO = 2;
    
    /** Rl objeto geométrico debe indicar cuál es la posición siguiente a una dada
      */
    int posicionSiguiente(int posicion);
    
    /** El objeto geométrico debe indicar cuál es la posición anterior a una dada
      */
    int posicionAnterior(int posicion);
    
    /** El objeto geométrico debe indicar el número de posiciones en las que 
      * se puede situar un circulador
      */
    int posiciones();
}
