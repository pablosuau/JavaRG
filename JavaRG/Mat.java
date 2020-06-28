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
    
  /** Valor de epsilon para la aritmética en punto flotante. Se ha escogido un 
    * epsilon mayor del que se dio en el enunciado (el original era demasiado 
    * bajo).
    */
    public static final double EPSILON = 0.01;

  /** Definición de orientación: orientación horaria
    */
    public static final int HORARIO = -1;
  /** Definición de orientación: orientación antihoraria
   */
    public static final int ANTIHORARIO = 1;
  /** Definición de orientación: orientación colineal
   */
    public static final int COLINEAL = 0;

  /** Definición de posición: objeto contenido en otro
    */
    public static final int CONTENIDO = 0;
  /** Definición de posición: objeto a la izquierda de otro
    */
    public static final int IZQUIERDA = 1;
  /** Definición de posición: objeto a la derecha de otro
   */
    public static final int DERECHA = -1;
  /** Definición de posición: parte positiva del espacio
    */
    public static final int POSITIVA = +1;
  /** Definición de posición: parte negativa del espacio
    */
    public static final int NEGATIVA = -1;

 /** Cálculo de un valor al cuadrado.
   * @param d valor que se desea elevar al cuadrado
   * @result resultado de elevar el valor pasado como parámetro al 
   * cuadrado
   */
    public static double cuadrado(double d) {
        return(d*d);
    }

  /** Convierte un angulo en grados en su equivalente en radianes
    * @param grados ángulo en grados
    * @return el ángulo equivalente en radianes
    */
    public static double grad2Rad(double grados) {
        return (grados * Math.PI / 180.0);
    }

  /** Convierte un ángulo en radianes en su equivalente en grados
    * @param radianes ángulo en radianes
    * @return el ángulo equivalente en grados
    */
    public static double rad2Grad(double radianes) {
        return (180.0 * radianes / Math.PI);
    }

  /** Comprueba si una matriz es cuadrada. De ser así devolverá
     * el orden de la matriz y en caso contrario devolverá 0
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

  /** Cálculo del valor absoluto.
    * @param valor valor del que se desea obtener el valor absoluto
    * @return el valor absoluto de la cantidad pasada como parámetro
    */
    public static double absoluto(double valor) {
        if (valor<0)
        return -valor;
        else
        return valor;
    }
    
  /** Función lógica XOR. Devuelve cierto si el valor booleano de ambos
    * parámetros es distinto
    * @param a primer valor booleano
    * @param b segundo valor booleano
    * @return cierto si el valor booleano de los parámetros es distinto, falso
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