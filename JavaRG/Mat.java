/*
 * Mat.java
 *
 * Created on 17 de octubre de 2000, 9:38
 */

package JavaRG;

/**
 *
 * @author  Pablo Suau
 * @version 1.1
 */
public class Mat extends Object {
    
  /** Valor de epsilon para la aritm�tica en punto flotante. Se ha escogido un 
    * epsilon mayor del que se dio en el enunciado (el original era demasiado 
    * bajo).
    */
    public static final double EPSILON = 0.01;

  /** Definici�n de orientaci�n: orientaci�n horaria
    */
    public static final int HORARIO = -1;
  /** Definici�n de orientaci�n: orientaci�n antihoraria
   */
    public static final int ANTIHORARIO = 1;
  /** Definici�n de orientaci�n: orientaci�n colineal
   */
    public static final int COLINEAL = 0;

  /** Definici�n de posici�n: objeto contenido en otro
    */
    public static final int CONTENIDO = 0;
  /** Definici�n de posici�n: objeto a la izquierda de otro
    */
    public static final int IZQUIERDA = 1;
  /** Definici�n de posici�n: objeto a la derecha de otro
   */
    public static final int DERECHA = -1;
  /** Definici�n de posici�n: parte positiva del espacio
    */
    public static final int POSITIVA = +1;
  /** Definici�n de posici�n: parte negativa del espacio
    */
    public static final int NEGATIVA = -1;

 /** C�lculo de un valor al cuadrado.
   * @param d valor que se desea elevar al cuadrado
   * @result resultado de elevar el valor pasado como par�metro al 
   * cuadrado
   */
    public static double cuadrado(double d) {
        return(d*d);
    }

  /** Convierte un angulo en grados en su equivalente en radianes
    * @param grados �ngulo en grados
    * @return el �ngulo equivalente en radianes
    */
    public static double grad2Rad(double grados) {
        return (grados * Math.PI / 180.0);
    }

  /** Convierte un �ngulo en radianes en su equivalente en grados
    * @param radianes �ngulo en radianes
    * @return el �ngulo equivalente en grados
    */
    public static double rad2Grad(double radianes) {
        return (180.0 * radianes / Math.PI);
    }

  /** Comprueba si una matriz es cuadrada. De ser as� devolver�
     * el orden de la matriz y en caso contrario devolver� 0
     * @param matriz matriz de la que se desea obtener el orden
     * @return 0 si la matriz no es cuadrada y su orden en otro
     * caso
     */
    public static int matrizCuadrada(double matriz[][]) {
        int orden = matriz.length;
        for (int i=0; i<matriz.length && orden != 0; i++)
        if (matriz[i].length != orden)
        orden = 0;

        return orden;
    }

  /** C�lculo del valor absoluto.
    * @param valor valor del que se desea obtener el valor absoluto
    * @return el valor absoluto de la cantidad pasada como par�metro
    */
    public static double absoluto(double valor) {
        if (valor<0)
        return -valor;
        else
        return valor;
    }
    
  /** Funci�n l�gica XOR. Devuelve cierto si el valor booleano de ambos
    * par�metros es distinto
    * @param a primer valor booleano
    * @param b segundo valor booleano
    * @return cierto si el valor booleano de los par�metros es distinto, falso
    * en otro caso*/
    public static boolean xor(boolean a, boolean b)
    {
        if (a != b) return true;
        else return false;
    }
    
  /** Cambio de coordenadas java a cartesianas. Devuelve la coordenada y
    * en un sistema de referencia cartesiano
    * @param javaY coordenada Y en sistema Java
    * @return la coordenada Y en sistema cartesiano*/
    public static int cartesianY(int javaY) {
        return (-javaY);
    }
    
  /** Cambio de coordenadas cartesianas a Java. Devuelve la coordenada y
    * en un sistema de referencia Java
    * @param cartesianY coordenada Y cartesiana
    * @return la coordenada Y en sistema Java*/
    public static int javaY(int cartesianY) {
        return (-cartesianY);
    }
    
}