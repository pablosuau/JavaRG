/*
 * Segmento2D.java
 *
 * Created on 17 de octubre de 2000, 9:31
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;
import java.awt.*;

/**
 * Un objeto de la clase <code>Segmento2D</code> representa un segmento
 * orientado en el plano. Esta definido entre un punto de inicio y otro final,
 * ambos contenidos dentro del segemento.
 *
 * @author  Pablo Suau 
 * @version 1.1
 */
public class Segmento2D extends ObjetoGeometrico {

    /**
     * punto inicial del segmento orientado
     * @see Punto2D
     */
    Punto2D comienzo;
    /**
     * punto final del segmento orientado
     * @see Punto2D
     */
    Punto2D fin;

    /** Constructor por defecto del segmento. Construye un segmento entre el
     * origen de coordendas y el punto (0,1)
     * @return un nuevo segmento orientado cuyo punto inicial es (0,0) y punto
     * final (0,1)
     * @see Punto2D#Punto2D()
     * @see Punto2D#Punto2D(double, double)
     */
    public Segmento2D ()
    {
        comienzo = new Punto2D();
        fin = new Punto2D(0,1);
    }

    /** Constructor del segmento orientado a partir de las coordendas cartesianas
     * de dos dos puntos. El segmento quedará definido entre dichos puntos
     * @param comienzoX coordenada cartesiana x del punto inicial
     * @param comienzoY coordenada cartesiana y del punto inicial
     * @param finX coordenada cartesiana x del punto final
     * @param finY coordenada cartesiana y del punto final
     * @return un nuevo segmento orientado cuyos puntos inicial y final son
     * aquellos cuyas coordenadas cartesianas han sido pasadas como parámetro
     * @exception GeomException si los puntos inicial y final son iguales
     * @see Punto2D#Punto2D(double, double)
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public Segmento2D (double comienzoX, double comienzoY, double finX, double finY)
    throws GeomException {
        Punto2D p1, p2;

        p1 = new Punto2D (comienzoX, comienzoY);
        p2 = new Punto2D (finX, finY);
        if (p1.equals(p2))
        throw new GeomException("Segmento2D (double, double, double, double): igual punto de inicio y final");
        else {
            comienzo = p1;
            fin = p2;
        }
    }

    /** Constructor del segmento orientado a partir de dos puntos. El segmento
     * quedará definido entre dichos puntos.
     * @param p1 punto inicial del segmento
     * @param p2 punto final del segmento
     * @return un nuevo segmento orientado cuyos puntos inicial y final serám
     * los puntos pasados como parámetro
     * @exception GeomException si los dos puntos pasados como parámetro son iguales
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public Segmento2D (Punto2D p1, Punto2D p2) throws GeomException {
        if (p1.equals(p2))
        throw new GeomException("Segmento2D (Point2D, Point2D): igual punto de inicio y de final");
        else {
            comienzo = p1;
            fin = p2;
        }
    }

    /** Constructor del segmento orientado a partir de las coordendas homogéneas
     * de dos dos puntos. El segmento quedará definido entre dichos puntos
     * @param cominezoHx coordenada homogénea x del punto inicial
     * @param comienzoHy coordenada homogénea y del punto inicial
     * @param comienzoHz coordenada homogénea z del punto inicial
     * @param finHx coordenada homogénea x del punto final
     * @param finHy coordenada homogénea y del punto final
     * @param finHz coordenada homogénea z del punto final
     * @return un nuevo segmento orientado cuyos puntos inicial y final son
     * aquellos cuyas coordenadas homogéneas han sido pasadas como parámetro
     * @exception GeomException si los puntos inicial y final son iguales o si
     * la coordenada homogénea z de alguno de ellos vale cero
     * @see Punto2D#Punto2D(double, double, double)
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public Segmento2D (double comienzoHx, double comienzoHy, double comienzoHz, double finHx, double finHy, double finHz)
    throws GeomException {
        Punto2D p1, p2;

        p1 = new Punto2D (comienzoHx, comienzoHy, comienzoHz);
        p2 = new Punto2D (finHx, finHy, finHz);
        if (p1.equals(p2))
        throw new GeomException("Segmento2D (double, double, double, double, double, double): igual punto de inicio y final");
        else {
            comienzo = p1;
            fin = p2;
        }
    }

    /** Constructor del segmento orientado a partir de un punto y un vector. El
     * segundo punto se obtendrá como aquel resultante del desplazamiento del
     * primer punto usando el vector
     * @param punto punto inicial del segmente
     * @param vector vector usado para obtener el punto final del segmente a partir
     * de la traslación del punto inicial
     * @return un nuevo segmento orientado cuyo punto inicial es el pasado como
     * parámetro y punto final se obtiene mediante la traslación de éste a partir
     * del vector pasado como segundo parámetro
     * @exception GeomException si el vector pasado como parámetro es nulo
     * @see Punto2D#trasladar(Vector2D)
     * @see Punto2D#Punto2D(Punto2D)
     * @see Vector2D#vectorNulo()
     */
    public Segmento2D (Punto2D punto, Vector2D vector) throws GeomException {
        if (vector.vectorNulo())
        throw new GeomException("Segmento2D (Punto2D, Vector2D): el vector es degenerado");
        else
        {
            comienzo = new Punto2D(punto);
            fin = new Punto2D(punto.trasladar(vector));
        }
    }

    /** Constructor del segmento orientado a partir de las coordenadas cartesianas
     * de un punto y un vector. El segundo punto se obtendrá como aquel resultante
     * del desplazamiento del primer punto usando el vector
     * @param px coordenada cartesiana x del punto inicial del segmento
     * @param py coordenada cartesiana y del punto inicial del segmento
     * @param vector vector usado para obtener el punto final del segmente a partir
     * de la traslación del punto inicial
     * @return un nuevo segmento orientado cuyo punto inicial es aquel cuyas coordenadas
     * cartesianas se pasan como primer y segundo parámetros y punto final se
     * obtiene mediante la traslación del punto inicial a partir del vector pasado
     * como tercer parámetro
     * @exception GeomException si el vector pasado como parámetro es nulo
     * @see Punto2D#trasladar(Vector2D)
     * @see Punto2D#Punto2D(double, double)
     * @see Vector2D#vectorNulo()
     */
    public Segmento2D (double px, double py, Vector2D vector) throws GeomException {
        if (vector.vectorNulo())
        throw new GeomException("Segmento2D (double, double, Vector2D): el vector es degenerado");
        else
        {
            comienzo = new Punto2D(px, py);
            fin = new Punto2D(comienzo.trasladar(vector));
        }
    }

    /** Constructor del segmento orientado a partir de las coordenadas homogéneas
     * de un punto y un vector. El segundo punto se obtendrá como aquel resultante
     * del desplazamiento del primer punto usando el vector
     * @param hx primera coordenada homogénea del punto inicial del segmento
     * @param hy segunda coordenada homogénea del punto inicial del segmento
     * @param hz tercera coordenada homogénea del punto inicial del segmento
     * @param vector vector usado para obtener el punto final del segmente a partir
     * de la traslación del punto inicial
     * @return un nuevo segmento orientado cuyo punto inicial es aquel cuyas coordenadas
     * homogéneas se pasan como primer, segundo y tercer parámetros y punto final se
     * obtiene mediante la traslación del punto inicial a partir del vector pasado
     * como cuarto parámetro
     * @exception GeomException si el vector pasado como parámetro es nulo
     * @see Punto2D#trasladar(Vector2D)
     * @see Punto2D#Punto2D(double, double, double)
     * @see Vector2D#vectorNulo()
     */
    public Segmento2D (double hx, double hy, double hz, Vector2D vector) throws GeomException {
        if (vector.vectorNulo())
        throw new GeomException("Segmento2D (double, double, double, Vector2D): el vector es degenerado");
        else
        {
            comienzo = new Punto2D(hx, hy, hz);
            fin = new Punto2D(comienzo.trasladar(vector));
        }
    }

    /** Constructor de copia del segmento. Construye un nuevo segmento orientado
     * a partir de uno ya existente
     * @param original segmento a partir del cual construir el nuevo segmento
     * @return un nuevo segmento con los mismos puntos inicial y final que el
     * segmento pasado como parámetro
     * @see Punto2D#Punto2D(Punto2D)
     * @see Segmento2D#comienzo()
     * @see Segmento2D#fin()
     */
    public Segmento2D (Segmento2D original) {
        this.comienzo = new Punto2D(original.comienzo());
        this.fin = new Punto2D(original.fin());
    }

    /** Comparación con otro objeto geométrico. Un segmento orientado es igual a
     * otro objeto geométrico si dicho objeto es también un segmento orientado y
     * si ambos segmentos tienen el mismo punto de inicio y final
     * @param obj objeto geométrico con el que se desea comparar el segmento
     * @return un valor booleano indicando si el segmento es igual al objeto
     * geométrico pasado como parámetro
     * @see Segmento2D#comienzo()
     * @see Segmento2D#fin()
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Segmento2D) {
            Segmento2D segmento = (Segmento2D) obj;
            if (this.fin().equals(segmento.fin()) && (this.comienzo().equals(segmento.comienzo()) ))
            return true;
            else return false;
        }
        else return false;
    }

    /** Comparación con otro objeto geométrico. Un segmento orientado es distinto a
     * otro objeto geométrico si dicho objeto no es un segmento orientado o
     * si ambos segmentos no tienen el mismo punto de inicio y final
     * @param obj objeto geométrico con el que se desea comparar el segmento
     * @return un valor booleano indicando si el segmento es distinto al objeto
     * geométrico pasado como parámetro
     * @see Segmento2D#equals(ObjetoGeometrico)
     */
    public boolean not_equals (ObjetoGeometrico obj) {
        return !this.equals(obj);
    }

    /** Obtiene la longitud del segmento. Para ello se calcula la distancia entre
     * el punto de inicio y final.
     * @return la longitud del segmento
     * @see Punto2D#distancia(Punto2D)
     */
    public double longitud() {
        return comienzo.distancia(fin);
    }

    /** Obtiene la longitud al cuadrado del segmento. En lugar de obtenerla
     * a partir de la función <code>longitud</code> de <code>Segmento2D</code>,
     * se ha calculado como (fin.x-comienzo.x)^2+(fin.y-comienzo.y)^2, para que
     * sea más exacto
     * @return la longitud del segmento elevada al cuadrado
     * @see Mat#cuadrado(double)
     * @see Segmento2D#longitud()
     */
    public double longitudCuadrado() {
        return (Mat.cuadrado(fin.x()-comienzo.x()) + Mat.cuadrado(fin.y() - comienzo.y())) ;
    }

    /** Obtiene el segmento opuesto. Dicho segmento opuesto es el resultado de
     * intercambiar los puntos inicial y final.
     * @return el segmento opuesto al original
     * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
     */
    public Segmento2D opuesto() {
        try {
            return new Segmento2D(fin, comienzo);
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /** Obtiene un vector a partir de los extremos del segmento.
     * @return el vector definido entre los puntos inicial y final del segmento
     * #see Vector2D#Vector2D(Punto2D, Punto2D)
     */
    public Vector2D vector() {
        return new Vector2D(comienzo, fin);
    }

    /** Obtiene una dirección a partir del segmento. Los desplazamientos de la
     * dirección se obtendrán a partir de los puntos inicial y final del
     * segmento
     * @return la dirección definida entre los puntos inicial y final del
     * segmento
     * @see Direccion2D#Direccion2D(Punto2D, Punto2D)
     */
    public Direccion2D direccion()  {
        try {
            return new Direccion2D (comienzo, fin);
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /** Obtiene un nuevo rayo cuyo punto inicial será el punto inicial del segmento
     * orientado y cuyo vector director se obtendrá a partir de sus extremos
     * @return un rayo cuyo punto de inicio es el punto de inicio del segmento
     * y vector director vendrá definido por los extremos por sus extremos
     * @see Segmento2D#vector()
     * @see Rayo2D#Rayo2D(Punto2D, Vector2D)
     */
    public Rayo2D rayo() throws GeomException {
        try {
            return new Rayo2D(comienzo, this.vector());
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /** Obtiene una recta a partir del segmento. El punto base de la recta será
     * el punto inicial del segmento y el vector director estará definido entre los
     * puntos inicial y final del segmento
     * @return la recta que intersecta totalmente con el segmento
     * @see Recta2D#Recta2D(Punto2D, Vector2D)
     * @see Vector2D#Vector2D(Punto2D, Punto2D)
     */
    public Recta2D recta() {
        try {
            return new Recta2D(comienzo, new Vector2D(comienzo, fin));
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /** Realiza la conversión del objeto de tipo Segmento2D a una cadena
     * @return una cadena especificando el tipo de objeto (segmento) y las
     * coordendadas cartesianas de sus puntos inicial y final
     * @see Punto2D#toString()
     */
    public String toString() {
        return nombre + " - Segmento2D: (" + comienzo.toString() + "," + fin.toString() + ")";
    }

    /** Comprueba si el segmento es colineal con el punto pasado como parámetro o si lo
     * contiene
     * @param punto punto que se desea saber si está contenido en el segmento o es
     * colineal con éste
     * @return un valor booleano indicando si el punto pasado como parámetro está
     * contenido en el segmento o es colineal con éste
     * @exception GeomException si el segmento es degenerado
     * @see Segmento2D#degenerado()
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Mat#COLINEAL
     */
    public boolean colinealOContiene(Punto2D punto) throws GeomException {
        if (this.degenerado())
        throw new GeomException ("colinearOContiene (Punto2D): el segmento es degenerado");
        else
        return (punto.posicionRelativa(comienzo, fin) == Mat.COLINEAL);
    }

    /** Comprueba si el segmento es colineal con el punto cuyas coordenadas cartesianas
     * se pasan como parámetro o si lo contiene
     * @param px coordenada cartesiana x del punto que se desea conocer si está contenido
     * en el segmento o es colineal con éste
     * @param py coordenada cartesiana y del punto que se desea conocer si está contenido
     * en el segmento o es colineal con éste
     * @return un valor booleano indicando si el punto cuyas coordenadas cartesianas
     * se han pasado como parámetro está contenido en el segmento o es colineal con éste
     * @exception GeomException si el segmento es degenerado
     * @see Segmento2D#degenerado()
     * @see Punto2D#Punto2D(double, double)
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Mat#COLINEAL
     */
    public boolean colinealOContiene(double px, double py) throws GeomException {
        if (this.degenerado())
        throw new GeomException ("colinearOContiene (double, double): el segmento es degenerado");
        else
        {
            Punto2D punto = new Punto2D(px, py);
            return (punto.posicionRelativa(comienzo, fin) == Mat.COLINEAL);
        }
    }

    /** Comprueba si el segmento es colineal con el punto cuyas coordenadas homogéneas
     * se pasan como parámetro o si lo contiene
     * @param hx primera coordenada homogénea del punto que se desea conocer si está contenido
     * en el segmento o es colineal con éste
     * @param hy segunda coordenada homogénea del punto que se desea conocer si está contenido
     * en el segmento o es colineal con éste
     * @param hz tercera coordenada homogénea del punto que se desea conocer si está contenido
     * en el segmento o es colineal con éste
     * @return un valor booleano indicando si el punto cuyas coordenadas homogéneas
     * se han pasado como parámetro está contenido en el segmento o es colineal con éste
     * @exception GeomException si el segmento es degenerado
     * @see Segmento2D#degenerado()
     * @see Punto2D#Punto2D(double, double, double)
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Mat#COLINEAL
     */
    public boolean colinealOContiene(double hx, double hy, double hz) throws GeomException {
        if (this.degenerado())
        throw new GeomException ("colinearOContiene (double, double, double): el segmento es degenerado");
        else
        {
            Punto2D punto = new Punto2D(hx, hy, hz);
            return (punto.posicionRelativa(comienzo, fin) == Mat.COLINEAL);
        }
    }


    /** Comprueba si un punto está contenido en el segmento. Dicho punto debe
     * ser colineal con los extremos del segmento, y además sus coordenadas deben
     * encontrarse entre las de los dos extremos
     * @param punto el punto que se desea comprobar si está contenido en el
     * segmento
     * @return un valor booleano indicando si el punto pasado como parámetro
     * está contenido en el segmento
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Segmento2D#minimoX()
     * @see Segmento2D#maximoX()
     * @see Segmento2D#minimoY()
     * @see Segmento2D#maximoY()
     */
    public boolean contienePunto(Punto2D punto)
    {
        try {
            if (punto.equals(this.comienzo()) || punto.equals(this.fin()) ||
            (punto.posicionRelativa(comienzo, fin) == Mat.COLINEAL
            && punto.y()>=minimoY().y() && punto.y()<=maximoY().y()
            && punto.x()>=minimoX().x() && punto.x()<=maximoX().x()))
            return true;
            else
            return false;
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /** Comprueba si un punto está contenido en el segmento, a partir de las
     * coordenadas cartesianas del mismo. Dicho punto debe
     * ser colineal con los extremos del segmento, y además sus coordenadas deben
     * encontrarse entre las de los dos extremos
     * @param x coordenada cartesiana x del punto
     * @param y coordenada cartesiana y del punto
     * @return un valor booleano indicando si el punto pasado como parámetro
     * está contenido en el segmento
     * @see Punto2D#Punto2D(double, double)
     * @see Segmento2D#contienePunto(Punto2D)
     */
    public boolean contienePunto(double x, double y)
    {
        return contienePunto(new Punto2D(x, y));
    }

    /** Comprueba si un punto está contenido en el segmento, a partir de las
     * coordenadas homogéneas del mismo. Dicho punto debe
     * ser colineal con los extremos del segmento, y además sus coordenadas deben
     * encontrarse entre las de los dos extremos
     * @param hx coordenada homogénea x del punto
     * @param hy coordenada homogénea y del punto
     * @param hz coordenada homogénea z del punto
     * @return un valor booleano indicando si el punto pasado como parámetro
     * está contenido en el segmento
     * @exception GeomException si la coordenada homogénea z del punto es cero
     * @see Punto2D#Punto2D(double, double, double)
     * @see Segmento2D#contienePunto(Punto2D)
     */
    public boolean contienePunto(double hx, double hy, double hz) throws GeomException
    {
        return contienePunto(new Punto2D(hx, hy, hz));
    }

    /** Comprueba si un punto se encuentra a la izquierda del segmento orientado.
     * La posición relativa de ese punto respecto a los extremeos del segmento
     * debe ser antihoraria.
     * @param punto punto que se desea comprobar si se encuentra a la izquierda
     * del segmento orientado
     * @return un valor booleano indicando si se cumple la condición necesaria
     * para que el punto este situado a la izquierda del segmento orientado
     * @see Punto2D#equals(ObjetoGeometrico)
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Mat#IZQUIERDA
     */
    public boolean puntoAIzquierda(Punto2D punto)
    {
        try {
            if (punto.equals(comienzo) || punto.equals(fin))
            return false;
            else
            return punto.posicionRelativa(comienzo, fin) == Mat.IZQUIERDA;
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /** Comprueba si un punto se encuentra a la izquierda del segmento orientado.
     * La posición relativa de ese punto respecto a los extremeos del segmento
     * debe ser antihoraria.
     * @param x coordenada cartesiana x del punto
     * @param y coordenada cartesiana y del punto
     * @return un valor booleano indicando si se cumple la condición necesaria
     * para que el punto cuyas coordenadas cartesianas se han pasado como parámetro
     * este situado a la izquierda del segmento orientado
     * @see Punto2D#Punto2D(double, double)
     * @see Segmento2D#puntoAIzquierda(Punto2D)
     */
    public boolean puntoAIzquierda(double x, double y)
    {
        return puntoAIzquierda(new Punto2D(x, y));
    }

    /** Comprueba si un punto se encuentra a la izquierda del segmento orientado.
     * La posición relativa de ese punto respecto a los extremeos del segmento
     * debe ser antihoraria.
     * @param hx coordenada homogénea x del punto
     * @param hy coordenada homogénea y del punto
     * @param hz coordenada homogénea z del punto
     * @return un valor booleano indicando si se cumple la condición necesaria
     * para que el punto cuyas coordenadas homogéneas se han pasado como parámetro
     * este situado a la izquierda del segmento orientado
     * @exception GeomException si la coordenada homogénea z del punto es igual a cero
     * @see Punto2D#Punto2D(double, double, double)
     * @see Segmento2D#puntoAIzquierda(Punto2D)
     */
    public boolean puntoAIzquierda(double hx, double hy, double hz) throws GeomException
    {
        return puntoAIzquierda(new Punto2D(hx, hy, hz));
    }

    /** Comprueba si un punto se encuentra a la derecha del segmento orientado.
     * La posición relativa de ese punto respecto a los extremos del segmento
     * debe ser horaria.
     * @param punto punto que se desea comprobar si se encuentra a la derecha
     * del segmento orientado
     * @return un valor booleano indicando si se cumple la condición necesaria
     * para que el punto este situado a la derecha del segmento orientado
     * @see Punto2D#equals(ObjetoGeometrico)
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Mat#DERECHA
     */
    public boolean puntoADerecha(Punto2D punto)
    {
        try {
            if (punto.equals(comienzo) || punto.equals(fin))
            return false;
            else
            return punto.posicionRelativa(comienzo, fin) == Mat.DERECHA;
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /** Comprueba si un punto se encuentra a la derecha del segmento orientado.
     * La posición relativa de ese punto respecto a los extremeos del segmento
     * debe ser horaria.
     * @param x coordenada cartesiana x del punto
     * @param y coordenada cartesiana y del punto
     * @return un valor booleano indicando si se cumple la condición necesaria
     * para que el punto cuyas coordenadas cartesianas se han pasado como parámetro
     * este situado a la derecha del segmento orientado
     * @see Punto2D#Punto2D(double, double)
     * @see Segmento2D#puntoAIzquierda(Punto2D)
     */
    public boolean puntoADerecha(double x, double y)
    {
        return puntoADerecha(new Punto2D(x, y));
    }

    /** Comprueba si un punto se encuentra a la derecha del segmento orientado.
     * La posición relativa de ese punto respecto a los extremeos del segmento
     * debe ser horaria.
     * @param hx coordenada homogénea x del punto
     * @param hy coordenada homogénea y del punto
     * @param hz coordenada homogénea z del punto
     * @return un valor booleano indicando si se cumple la condición necesaria
     * para que el punto cuyas coordenadas homogéneas se han pasado como parámetro
     * este situado a la derecha del segmento orientado
     * @exception GeomException si la coordenada homogénea z del punto es igual a cero
     * @see Punto2D#Punto2D(double, double, double)
     * @see Segmento2D#puntoADerecha(Punto2D)
     */
    public boolean puntoADerecha(double hx, double hy, double hz) throws GeomException
    {
        return puntoADerecha(new Punto2D(hx, hy, hz));
    }

    /** Comprueba la intersección propia con otro segmento. Cada extremo de cada uno
     * de los dos segmentos debe estar a lados distintos respecto al otro. Para que
     * la intersección sea propia, el punto de intersección no debe ser el
     * extremo de ninguno de los segmentos.
     * @param otro segmento con el que se desea conocer si hay intersección propia
     * @return un valor booleano indicando si los dos segmentos intersectan de forma
     * propia
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Segmento2D#comienzo()
     * @see Segmento2D#fin()
     * @see Punto2D#equals(ObjetoGeometrico)
     * @see Mat#COLINEAL
     * @see Mat#ANTIHORARIO
     * @see Mat#HORARIO
     * @see Mat#xor(boolean, boolean)
     */
    public boolean intersectaPropSegmento(Segmento2D otro)
    {
        try {
            if (this.comienzo().equals(otro.comienzo()) || this.comienzo.equals(otro.fin())
            || this.fin().equals(otro.fin()) || this.fin().equals(otro.comienzo()))
            return false;
            else
            // Primero comprobamos si la intersección se produce en más de 
            // un punto
            if (comienzo.posicionRelativa(otro.comienzo(), otro.fin()) == Mat.COLINEAL &&
                fin.posicionRelativa(otro.comienzo(), otro.fin()) == Mat.COLINEAL &&
                (this.contienePunto(otro.comienzo()) || this.contienePunto(otro.fin())))
                    return true;
            else
            // En caso contrario comprobamos las condiciones para que la 
            // intersección sea en un único punto
            return (Mat.xor(comienzo.posicionRelativa(otro.comienzo(),otro.fin())==Mat.ANTIHORARIO &&
            fin.posicionRelativa(otro.comienzo(),otro.fin())==Mat.HORARIO,
            comienzo.posicionRelativa(otro.comienzo(),otro.fin())==Mat.HORARIO &&
            fin.posicionRelativa(otro.comienzo(),otro.fin())==Mat.ANTIHORARIO) &&
            Mat.xor(otro.comienzo().posicionRelativa(comienzo,fin)==Mat.ANTIHORARIO &&
            otro.fin().posicionRelativa(comienzo,fin)==Mat.HORARIO,
            otro.comienzo().posicionRelativa(comienzo,fin)==Mat.HORARIO &&
            otro.fin().posicionRelativa(comienzo,fin)==Mat.ANTIHORARIO));
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /** Comprueba si hay intersección con otro segmento. Dicha intersección puede
     * ser propia (debida a que algún extremo de uno de los segmentos está
     * contenido en el otro)
     * @param otro segmento con el que se desea conocer si hay intersección
     * @return un valor booleano indicando si los dos segmentos intersectan
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Punto2D#equals(ObjetoGeometrico)
     * @see Segmento2D#comienzo()
     * @see Segmento2D#fin()
     */
    public boolean intersectaSegmento(Segmento2D otro)
    {
        if (this.intersectaPropSegmento(otro))
        return true;
        else
        if (this.comienzo().equals(otro.comienzo()) || this.comienzo.equals(otro.fin())
        || this.fin().equals(otro.fin()) || this.fin().equals(otro.comienzo()) )
        return true;
        else
        return false;
    }

    /** Obtiene el objeto geométrico resultante de la intersección del segmento con
     * otro segmento. Este ObjetoGeométrico puede ser null, si no existe intersección,
     * un punto si las direcciones de ambos segemntos son distintas (o si son iguales
     * pero coinciden en tan solo uno de los extremos) o un segmento
     * @param otro segmento con el que se desea obtener la intersección
     * @return el objeto geométrico resultante de la intersección de los dos segmentos
     * @see Segmento2D#intersectaSegmento(Segmento2D)
     * @see Direccion2D#equals(ObjetoGeometrico)
     * @see Segmento2D#minimo()
     * @see Segmento2D#maximo()
     * @see Recta2D#interseccionRecta(Recta2D)
     */
    public ObjetoGeometrico interseccionSegmento(Segmento2D otro)
    {
        try {
            if (! this.intersectaSegmento(otro))
            return null;
            else
            if (!this.direccion().equals(otro.direccion())
            && !this.direccion().equals(otro.direccion().opuesto()))
            // El resultado de la intersección es un punto
            return otro.recta().interseccionSegmento(this);
            else
            // El resultado de la intersección es otro segmento (a menos que
            // el punto inicial y final sean el mismo, en cuyo caso el
            // resultado será un punto)
            if (this.maximo().x()>otro.maximo().x() ||
            (Mat.absoluto(this.maximo().x() - otro.maximo().x()) < Mat.EPSILON
            && this.maximo().y()>otro.maximo().y()))
            if (this.minimo().equals(otro.maximo()))
            return this.minimo();
            else
            return new Segmento2D(this.minimo(), otro.maximo());
            else
            if (otro.minimo().equals(this.maximo()))
            return otro.minimo();
            else
            return new Segmento2D(otro.minimo(), this.maximo());
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /** Obtiene la distancia del segmento a un punto, así como el punto del segmento
     * más cercano a dicho punto. Para obtenerla se determina
     * si la recta perpendicular al segmento que pasa por el punto corta al segmento.
     * En caso afirmativo la distancia será la del punto al punto de corte de la recta
     * con el segmento, y en caso negativo será la distancia del punto al extremo
     * del segmento más cercano
     * @param punto punto al cual se desea obtener la distancia
     * @param cercano punto del segmento más cercano al punto pasado como primer
     * parámetro. Es un parámetro de SALIDA, y debe ser un punto ya creado
     * @return la distancia del segmento al punto
     * @exception GeomException si el punto pasado como segundo parámetro no es un punto
     * ya creado
     * @see Recta2D#Recta2D(Punto2D, Punto2D)
     * @see Recta2D#perpendicular(int)
     * @see Recta2D#interseccionSegmento(Segmento2D)
     * @see Punto2D#distancia(Punto2D)
     * @see Punto2D#vectorDiferencia(Punto2D)
     */
    public double puntoMasCercano (Punto2D punto, Punto2D cercano) throws GeomException {
        double distancia;
        Vector2D diferencia = new Vector2D();

        if (cercano == null)
            throw new GeomException("puntoMasCercano (Punto2D, Punto2D): el segundo parámetro debe estar inicializado");

        try {
            Recta2D rectaPerpendicular = new Recta2D(comienzo, fin);
            rectaPerpendicular = rectaPerpendicular.perpendicular(Mat.HORARIO);
            Recta2D recta = new Recta2D(punto, rectaPerpendicular.director());
            ObjetoGeometrico corte = recta.interseccionSegmento(this);
            if (corte instanceof Punto2D)
            {
                // La distancia será igual a la distancia del punto al punto
                // de corte de la recta con el segmento
                Punto2D puntoCorte = (Punto2D) corte;
                distancia = punto.distancia(puntoCorte);
                diferencia = puntoCorte.vectorDiferencia(cercano);
            }
            else
            {
                // La distancia será igual a la distancia más corta del punto
                // a alguno de los extremos
                double dist1 = punto.distancia(comienzo);
                double dist2 = punto.distancia(fin);
                if (dist1 < dist2) {
                    distancia = dist1;
                    diferencia = comienzo.vectorDiferencia(cercano);
                } else {
                    distancia = dist2;
                    diferencia = fin.vectorDiferencia(cercano);
                }
            }
        } catch (GeomException e) {
            return -1; // No se va a producir nunca
        }

        cercano.trasladar(diferencia);

        return distancia;
    }

   /** Obtiene la distancia del segmento a un punto
     * @param punto punto para el cual se desea obtener la distancia
     * @return la distancia del segmento al punto
     * @see Segmento2D#puntoMasCercano(Punto2D, Punto2D)
     */
    public double distancia(Punto2D punto) {
        try  {
            return puntoMasCercano(punto, new Punto2D());
        } catch (GeomException e) {
            return java.lang.Double.MAX_VALUE; // Nunca se va a producir
        }
    }

    /** Comprueba si se trata de un segmento orientado degenerado. Para que esto
     * sea así, los dos extremos deben ser iguales
     * @return un valor booleano indicando si el segmento es degenerado
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public boolean degenerado() {
        return comienzo.equals(fin);
    }

    /** Comprueba si el segmento orientado es horizontal. Un segmento es horizontal
     * si la coordenada cartesiana y de ambos extremos es igual
     * @return un valor booleano indicando si el segmento es horizontal
     * @see Mat#absoluto(double)
     * @see Mat#EPSILON
     * @see Punto2D#y()
     */
    public boolean horizontal() {
        if (Mat.absoluto(comienzo.y() - fin.y()) < Mat.EPSILON)
        return true;
        else
        return false;
    }

    /** Comprueba si el segmento orientado es verticañ. Un segmento es vertical
     * si la coordenada cartesiana x de ambos extremos es iguañ
     * @return un valor booleano indicando si el segmento es horizontal
     * @see Mat#absoluto(double)
     * @see Mat#EPSILON
     * @see Punto2D#x()
     */
    public boolean vertical() {
        if (Mat.absoluto(comienzo.x() - fin.x()) < Mat.EPSILON)
        return true;
        else
        return false;
    }

    /** Traslada el segmento en el plano aplicando el mismo incremento en cada
     * eje. La traslación se aplicará a cada extremo.
     * @param incr incremento o desplazamiento que será aplicado tanto al eje x
     * como al eje y
     * @return el segmento trasladado
     * @see Punto2D#trasladar(double)
     */
    public Segmento2D trasladar(double desp) {
        comienzo.trasladar(desp);
        fin.trasladar(desp);

        return this;
    }

    /** Traslada el segmento en el plano a partir de un determinado incremento en
     * cada coordenada cartesiana. La traslación se aplicará a todos los extremos.
     * @param incX incremento o desplazamiento en el eje x
     * @param incY incremento o desplazamiento en el eje y
     * @return el segmento trasladado
     * @see Punto2D#trasladar(double, double)
     */
    public Segmento2D trasladar(double despX, double despY) {
        comienzo.trasladar(despX, despY);
        fin.trasladar(despX, despY);

        return this;
    }

    /** Traslada el segmento en la dirección y a la distancia determinados por un
     * vector. Se aplicará la traslación a cada extremo
     * @param vector el vector que define la traslación del segmento
     * @return el segmento trasladado
     * @see Punto2D#trasladar(Vector2D)
     */
    public Segmento2D trasladar (Vector2D vector)
    {
        comienzo.trasladar(vector);
        fin.trasladar(vector);
        return this;
    }

    /** Traslación del segmento a partir de las coordenadas polares (se trasladan
     * los extremos del segmento una determinada distancia en un determinado ángulo)
     * @param angulo dirección de traslación del segmento
     * @param dist distancia a la que se moverá el segmento de su posición original
     * siguiendo el ángulo indicado
     * @return el segmento trasladado
     * @see Punto2D#trasladarPolar(double, double)
     */
    public Segmento2D trasladarPolar (double angulo, double dist) {
        comienzo.trasladar(angulo, dist);
        fin.trasladar(angulo, dist);
        return this;
    }

    /** Rota el segmento respecto del origen de coordenadas
     * @param radio angulo de giro en radianes. Si es positivo el giro será
     * en sentido antihorario y en caso contrario lo será en sentido horario
     * @return el segmento girado
     * @see Punto2D#gira(double)
     */
    public Segmento2D gira(double angulo) {
        comienzo.gira(angulo);
        fin.gira(angulo);

        return this;
    }

    /** Rota el segmento respecto a un punto. Se aplicará la
     * rotación respecto a un punto a los extremos del segmento.
     * @param origen punto respecto el cual se hará el giro
     * @radio angulo de giro en radianes. Si es positivo el giro será en sentido
     * antihorario y en caso contrario lo será en sentido horario
     * @return el segmento girado
     * @see Punto2D#gira(Punto2D, double)
     */
    public Segmento2D gira(Punto2D origen, double angulo) {
        comienzo.gira(origen, angulo);
        fin.gira(origen, angulo);

        return this;
    }

    /** Obtiene el punto de gravedad del segmento (es decir, su punto central)
      * @return el centro de gravedad del segmento
      * @see Punto2D#Punto2D(double, double)
      * @see Punto2D#x()
      * @see Punto2D#y()
      */
    public Punto2D centroGravedad() {
        double x = (comienzo.x() + fin.x())/2.0;
        double y = (comienzo.y() + fin.y())/2.0;
        return new Punto2D(x,y);
    }
    
    /** Escalado de un segmento. Se aplicará el escalado a cada extremo
     * @param escala factor de escalado
     * @return el segmento escalado
     * @exception GeomException si el factor de escalado es cero
     * @see Punto2D#escalado(double)
     */
    public Segmento2D escalado(double escala) throws GeomException {
        comienzo.escalado(escala);
        fin.escalado(escala);
        return this;
    }

    /** Escalado de un segmento con distinto factor de escalado para cada coordenada.
     * Se aplicará el escalado a cada extremo.
     * @param sx factor de escalado en el eje x
     * @param sy factor de escalado en el eje y
     * @return el segmento escalado
     * @exception GeomException si alguno de los factores de escalado es cero
     * @see Punto2D#escalado(double, double)
     */
    public Segmento2D escalado(double sx, double sy) throws GeomException {
        comienzo.escalado(sx, sy);
        fin.escalado(sx, sy);
        return this;
    }

    /** Aplica una matriz de transformación al segmento. La matriz debe ser cuadrada
     * y de orden dos (para aplicarla a las coordenadas cartesianas) o tres (en
     * cuyo caso se aplica a las coordenadas homogéneas). Se aplicará la matriz
     * de transformación a cada uno de los extremos del segmento
     * @param matriz de transformación
     * @return el segmento transformado
     * @exception GeomException si la matriz de transformación no es cuadrada, o
     * es cuadrada pero su orden es distinto de dos o tres
     * @see Punto2D#transforma(double[][])
     */
    public Segmento2D transforma(double matriz[][]) throws GeomException {
        comienzo.transforma(matriz);
        fin.transforma(matriz);

        return this;
    }

    /** Obtiene el punto de inicio del segmento orientado.
     * @return el punto de inicio del segmento
     */
    public Punto2D comienzo() {
        return comienzo;
    }

    /** Obtiene el punto final del segmento orientado
     * @return el punto final del segmento
     */
    public Punto2D fin() {
        return fin;
    }

    /** Obtiene el punto del segmento orientado cuya coordenda cartesiana y es menor.
     * Ese punto será uno de los dos extremos de dicho segmento.
     * @return el extremo del segmento con menor valor de coordenada cartesiana y
     * @see Punto2D#y()
     */
    public Punto2D minimoY() {
        if (comienzo.y() < fin.y())
        return comienzo;
        else
        return fin;
    }

    /** Obtiene el punto del segmento orientado cuya coordenda cartesiana x es menor.
     * Ese punto será uno de los dos extremos de dicho segmento.
     * @return el extremo del segmento con menor valor de coordenada cartesiana x
     * @see Punto2D#x()
     */
    public Punto2D minimoX() {
        if (comienzo.x() < fin.x())
        return comienzo;
        else
        return fin;
    }

    /** Obtiene el punto del segmento orientado cuya coordenda cartesiana y es mayor.
     * Ese punto será uno de los dos extremos de dicho segmento.
     * @return el extremo del segmento con mayor valor de coordenada cartesiana y
     * @see Punto2D#y()
     */
    public Punto2D maximoY() {
        if (comienzo.y() > fin.y())
        return comienzo;
        else
        return fin;
    }

    /** Obtiene el punto del segmento orientado cuya coordenda cartesiana x es mayor.
     * Ese punto será uno de los dos extremos de dicho segmento.
     * @return el extremo del segmento con mayor valor de coordenada cartesiana x
     * @see Punto2D#x()
     */
    public Punto2D maximoX() {
        if (comienzo.x() > fin.x())
        return comienzo;
        else
        return fin;
    }

    /** Obtiene el punto con las menores coordenadas en orden lexicográfico. Primer
     * se compara la coordenada x, y si es la misma para ambos puntos se compara
     * la coordenada y
     * @return el punto con las menores coordenadas en orden lexicográfico
     * @see Mat#absoluto(double)
     * @see Mat#EPSILON
     * @see Punto2D#x()
     * @see Punto2D#y()
     */
    public Punto2D minimo()
    {
        if (Mat.absoluto(comienzo.x()-fin.x()) < Mat.EPSILON)
        if (comienzo.y() < fin.y())
        return comienzo;
        else
        return fin;
        else if (comienzo.x() < fin.x())
        return comienzo;
        else
        return fin;
    }

    /** Obtiene el punto con las mayores coordenadas en orden lexicográfico. Primer
     * se compara la coordenada x, y si es la misma para ambos puntos se compara
     * la coordenada y
     * @return el punto con las mayores coordenadas en orden lexicográfico
     * @see Mat#absoluto(double)
     * @see Mat#EPSILON
     * @see Punto2D#x()
     * @see Punto2D#y()
     */
    public Punto2D maximo()
    {
        if (Mat.absoluto(comienzo.x()-fin.x()) < Mat.EPSILON)
        if (comienzo.y() > fin.y())
        return comienzo;
        else
        return fin;
        else if (comienzo.x() > fin.x())
        return comienzo;
        else
        return fin;
    }


    /** Obtiene el extremo del segmento cuyo índice numérico se pasa como parámetro.
     * dicho índice debe valer 0 o 1, correspondiéndose el valor 0 al punto inicial
     * y 1 al punto final.
     * @param i índice numértico del extremo del segmento que se desea obtener
     * @return el punto seleccionado
     * @exception GeomException si el índice tien un valor distinto de cero y
     * de uno
     */
    public Punto2D vertice(int i) throws GeomException {
        if (i != 0 && i != 1)
        throw new GeomException("vertice (int): el índice especificado es incorrecto");
        else
        if (i==0) return comienzo;
        else return fin;
    }

    /** Obtiene el extremo del segmento cuyo índice numérico se pasa como parámetro.
     * dicho índice debe valer 0 o 1, correspondiéndose el valor 0 al punto inicial
     * y 1 al punto final.
     * @param i índice numértico del extremo del segmento que se desea obtener
     * @return el punto seleccionado
     * @exception GeomException si el índice tien un valor distinto de cero y
     * de uno
     * @see Segmento2D#vertice(int)
     */
    public Punto2D punto(int i) throws GeomException {
        return vertice(i);
    }

    /** Obtiene la caja contenedora que contiene al segmento
     * @return una caja contenedora conteniendo al segmento
     * @see Caja2D#Caja2D(Segmento2D)
     */
    public Caja2D cajaContenedora() {
        return new Caja2D(this);
    }

    private void dibujo(Graphics g, boolean coordenadas, Color color) {
        comienzo.dibujar(g, coordenadas);
        fin.dibujar(g, coordenadas);
        g.setColor(color);
        g.drawLine((int)comienzo.x(), -(int)comienzo.y(), (int)fin.x(), -(int)fin.y());
    }

    public void dibujar(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.black);
    }

    public void dibujarResultado(Graphics g) {
        g.setColor(Color.red);
        g.drawLine((int)comienzo.x(), -(int)comienzo.y(), (int)fin.x(), -(int)fin.y());
    }

    public void dibujarSeleccionado(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.green);
    }

    public void dibujarNombre(Graphics g) {
        g.setColor(Color.magenta);
        // Obtenemos el punto central del segmento
        try {
            Recta2D recta = new Recta2D(comienzo, fin);
            Punto2D central = new Punto2D(recta.punto(0.5));
            g.drawString(nombre, (int)central.x() - 15, -(int)central.y());
        } catch (GeomException e) {
            return; // Nunca se va a producir
        }
    }
}
