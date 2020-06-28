/*
 * ObjetoBasico.java
 *
 * Created on 29 de diciembre de 2001, 17:58
 */

package JavaRG.Basica;

import JavaRG.Soporte.InterfazCirculador;
import JavaRG.Nucleo2D.ObjetoGeometrico;
/**
 *
 * @author  Pablo Suau
 * @version 1.0
 */
public abstract class ObjetoBasico extends ObjetoGeometrico implements InterfazCirculador {

    /** Constructor vacío */
    public ObjetoBasico() {
        super();
    }

    /** Rl objeto geométrico debe indicar cuál es la posición siguiente a una dada
     */
    public abstract int posicionSiguiente(int posicion);
    /** El objeto geométrico debe indicar cuál es la posición anterior a una dada
     */
    public abstract int posicionAnterior(int posicion);
    /** El objeto geométrico debe indicar el número de posiciones en las que
     * se puede situar un circulador
     */
    public abstract int posiciones();
}
