/*
 * GeomException.java
 *
 * Created on 17 de octubre de 2000, 9:20
 */

package JavaRG.Nucleo2D;

/** La clase <code>GeomException</code> es la usada para implementar
 * las excepciones geom�tricas.
 *
 * @author  Pablo Suau
 * @version 1.1
 * @see Exception
 */
public class GeomException extends Exception {

    /**
     * Crea una nueva <code>GeomException</code> sin ningun mensaje
     * @return una nueva excepci�n geom�trica
     */
    public GeomException() {
    }


    /**
     * Construye una <code>GeomException</code> con un determinado mensaje.
     * @param msg el mensaje asociado a la excepci�n
     * @return una nueva excepci�n geom�trica
     */
    public GeomException(String msg) {
        super(msg);
    }
}

