/*
 * Rayo2D.java
 *
 * Created on 19 de noviembre de 2000, 14:55
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;
import java.awt.*;

/** Un objeto de la clase <code>Rayo2D</code> representa un rayo en el espacio
 * 2D. Está definido por su ecuación paramétrica (a partir de un punto base
 * y un vector director). El parámetro de la ecuación paramétrica solo podrá tomar
 * valor mayor o igual que cero
 *
 * @author  Pablo Suau
 * @version 1.1
 */
public class Rayo2D extends ObjetoGeometrico {

  /** Punto base de la ecuación paramétrica
    */
    Punto2D comienzo;
  /** Vector director de la ecuación paramétrica
    */
    Vector2D director;
    
  /** Constructor por defecto de la rayo. Construye un rayo vertical
    * a partir del origen de coordenadas
    * @return un nuevo rayo vertical cuyo inicio es el origen de coordenadas
    * @see Punto2D#Punto2D()
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    */
    public Rayo2D() {
        comienzo = new Punto2D();
        director = new Vector2D(comienzo, new Punto2D(0, 1));
    }
    
  /** Constructor del rayo a partir de dos puntos. El punto inicial del rayo 
    * será el primer punto, y el vector director estará definido entre
    * ese primer punto y el segundo.
    * @param p1 el primer punto
    * @param p2 el segundo punto
    * @return un nuevo rayo definido a partir de los dos puntos pasados como 
    * parámetro
    * @exception GeomException si el vector director definido entre los dos puntos
    * es de magnitud cero
    * @see Punto2D#Punto2D(Punto2D)
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    * @see Punto2D#distancia(Punto2D)
    * @see Mat#EPSILON
    * @see Vector2D#magnitud()
    */
    public Rayo2D(Punto2D p1, Punto2D p2) throws GeomException {
        if (p1.distancia(p2) < Mat.EPSILON)
            throw new GeomException("Rayo2D (Punto2D, Punto2D): igual punto de inicio y final");
        else
        {
            comienzo = new Punto2D(p1);
            director = new Vector2D(p1, p2);
        }
    }
    
  /** Constructor del rayo a partir de las coordenadas cartesianas de dos puntos
    *. El punto inicial del rayo
    * será el primer punto, y el vector director estará definido entre
    * ese primer punto y el segundo.
    * @param x1 coordenada cartesiana x del primer punto
    * @param y1 coordenada cartesiana y del primer punto
    * @param x2 coordenada cartesiana x del segundo punto
    * @param y2 coordenada cartesiana y del segundo punto
    * @return un nuevo rayo definido a partir de los dos puntos cuyas 
    * coordenadas cartesianas han sido pasadas como parámetro
    * @exception GeomException si el vector director definido entre los dos puntos
    * es de magnitud cero
    * @see Punto2D#Punto2D(double, double)
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    * @see Punto2D#distancia(Punto2D)
    * @see Mat#EPSILON
    * @see Vector2D#magnitud()
    */
    public Rayo2D (double x1, double y1, double x2, double y2) throws GeomException {
        Punto2D p1 = new Punto2D(x1, y1);
        Punto2D p2 = new Punto2D(x2, y2);
        if (p1.distancia(p2) < Mat.EPSILON)
            throw new GeomException("Rayo2D (double, double, double, double): igual punto de inicio y final");
        else
        {
            comienzo = new Punto2D(p1);
            director = new Vector2D(p1, p2);
        }
    }
    
  /** Constructor del rayo a partir de las coordenadas homogéneas de dos puntos
    *. El punto inicial del rayo será el primer punto, y el vector director estará definido entre
    * ese primer punto y el segundo.
    * @param hx1 coordenada homogénea x del primer punto
    * @param hy1 coordenada homogénea y del primer punto
    * @param hz1 coordenada homogénea z del primer punto
    * @param hx2 coordenada homogénea x del segundo punto
    * @param hy2 coordenada homogénea y del segundo punto
    * @param hz2 coordenada homogénea z del segundo punto
    * @return un nuevo rayo definido a partir de los dos puntos cuyas 
    * coordenadas homogéneas han sido pasadas como parámetro
    * @exception GeomException si el vector director definido entre los dos puntos
    * es de magnitud cero o si la coordenada homogénea z de alguno de los dos 
    * puntos es cero
    * @see Punto2D#Punto2D(double, double, double)
    * @see Punto2D#Punto2D(Punto2D)
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    * @see Punto2D#distancia(Punto2D)
    * @see Mat#EPSILON
    * @see Vector2D#magnitud()
    */
    public Rayo2D (double hx1, double hy1, double hz1, double hx2, double hy2, double hz2) throws GeomException {
        if (hz1 == 0 || hz2 == 0)
            throw new GeomException("Rayo2D (double, double, double, double, double, double): alguna de las coordenadas homogeneas en el eje x vale 0");
        Punto2D p1 = new Punto2D(hx1, hy1, hz1);
        Punto2D p2 = new Punto2D(hx2, hy2, hz2);
        if (p1.distancia(p2) < Mat.EPSILON)
            throw new GeomException("Rayo2D (double, double, double, double, double, double): igual punto de inicio y final");
        else
        {
            comienzo = new Punto2D(p1);
            director = new Vector2D(p1, p2);
        }
    }
    
  /** Constructor del rayo a partir de un punto y un vector. La ecuación 
    * paramétrica será construida a partir de dichos punto y vector.
    * @param punto punto inicial del rayo
    * @param vector vector director de la ecuación paramétrica
    * @return un nuevo rayo cuya ecuación paramétrica se construye a partir
    * del punto y el vector pasados como parámetro
    * @exception GeomException si el vector pasado como parámetro tiene 
    * magnitud cero
    * @see Vector2D#magnitud()
    * @see Punto2D#Punto2D(Punto2D)
    * @see Vector2D#Vector2D(Vector2D)
    */
    public Rayo2D (Punto2D punto, Vector2D vector) throws GeomException {
        if (vector.magnitud() == 0)
            throw new GeomException("Rayo2D (Punto2D, Vector2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(punto);
            director = new Vector2D(vector);
        }
    }
    
  /** Constructor del rayo a partir de las coordenadas cartesianas de un 
    * punto y un vector. La ecuación 
    * paramétrica será construida a partir de dichos punto y vector.
    * @param x coordenada cartesiana x del punto inicial del rayo
    * @param y coordenada cartesiana y del punto inicial del rayo
    * @param vector vector director de la ecuación paramétrica
    * @return un nuevo rayo cuya ecuación paramétrica se construye a partir
    * de las coordendas cartesianas del punto y el vector pasados como parámetro
    * @exception GeomException si el vector pasado como parámetro tiene 
    * magnitud cero
    * @see Vector2D#magnitud()
    * @see Punto2D#Punto2D(double, double)
    * @see Vector2D#Vector2D(Vector2D)
    */
    public Rayo2D (double x, double y, Vector2D vector) throws GeomException {
        if (vector.magnitud() == 0)
            throw new GeomException("Rayo2D (double, double, Vector2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(x, y);
            director = new Vector2D(vector);
        }
    }
    
  /** Constructor del rayo a partir de las coordenadas homogéneas de un 
    * punto y un vector. La ecuación 
    * paramétrica será construida a partir de dichos punto y vector.
    * @param hx coordenada homogénea x del punto inicial del rayo
    * @param hy coordenada homogénea y del punto inicial del rayo
    * @param hz coordenada homogénea z del punto inical del rayo
    * @param vector vector director de la ecuación paramétrica
    * @return un nuevo rayo cuya ecuación paramétrica se construye a partir
    * de las coordendas homogéneas del punto y el vector pasados como parámetro
    * @exception GeomException si el vector pasado como parámetro tiene 
    * magnitud cero o si la coordenada homogénea z pasada como parámetro vale
    * cero
    * @see Vector2D#magnitud()
    * @see Punto2D#Punto2D(double, double, double)
    * @see Vector2D#Vector2D(Vector2D)
    */
    public Rayo2D (double hx, double hy, double hz, Vector2D vector) throws GeomException {
        if (vector.magnitud() == 0)
            throw new GeomException("Rayo2D (double, double, double, Vector2D) : vector director de magnitud cero");
        else {
            comienzo = new Punto2D(hx, hy, hz);
            director = new Vector2D(vector);
        }
    }
    
  /** Constructor del rayo a partir de un punto y una dirección. La ecuación 
    * paramétrica será construida a partir de dichos punto y el vector unitario
    * correspondiente a la dirección
    * @param punto punto inicial del rayo
    * @param direccion la dirección a partir de la cual obtener el vector
    * director
    * @return un nuevo cuya ecuacuón paramétrica se construye a partir
    * del punto y la dirección pasados como parámetro
    * @exception GeomException si el vector obtenido a partir de la dirección
    * pasada como parámetro tiene  magnitud cero
    * @see Vector2D#magnitud()
    * @see Punto2D#Punto2D(Punto2D)
    * @see Vector2D#Vector2D(Vector2D)
    * @see Direccion2D#vector()
    */
    public Rayo2D (Punto2D punto, Direccion2D direccion) throws GeomException {
        if (direccion.vector().magnitud() == 0)
            throw new GeomException("Rayo2D (Punto2D, Direccion2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(punto);
            director = new Vector2D(direccion.vector());
        }
    }
    
  /** Constructor del rayo a partir de las coordenadas cartesianas de un
    * punto y una dirección. La ecuación  paramétrica será construida a partir
    * de dichos punto y el vector untiario correspondiente a la dirección.
    * @param x coordenada cartesiana x del punto inicial del rayo
    * @param y coordenada cartesiana y del punto inicial del rayo
    * @param direccion la dirección a partir de la cual obtener el vector
    * director
    * @return un nuevo rayo cuya ecuacuón paramétrica se construye a partir
    * de las coordenadas cartesianas del punto y la dirección pasados como
    * parámetro
    * @exception GeomException si el vector obtenido a partir de la dirección
    * pasada como parámetro tiene  magnitud cero
    * @see Vector2D#magnitud()
    * @see Punto2D#Punto2D(double, double)
    * @see Vector2D#Vector2D(Vector2D)
    * @see Direccion2D#vector()
    */
    public Rayo2D (double x, double y, Direccion2D direccion) throws GeomException {
        if (direccion.vector().magnitud() == 0)
            throw new GeomException("Rayo2D (double, double, Direccion2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(x, y);
            director = new Vector2D(direccion.vector());
        }
    }
    
  /** Constructor del rayo a partir de las coordenadas homogéneas de un
    * punto y una dirección. La ecuación  paramétrica será construida a partir
    * de dichos punto y el vector unitario correspondiente a la dirección.
    * @param hx coordenada homogénea x del punto inicial del rayo
    * @param hy coordenada homogénea y del punto inicial del rayo
    * @param hz coordenada homogénea z del punto inicial del rayo
    * @param direccion la dirección a partir de la cual obtener el vector
    * director
    * @return un nuevo rayo cuya ecuacuón paramétrica se construye a partir
    * de las coordenadas homogéneas del punto y la dirección pasados como
    * parámetro
    * @exception GeomException si el vector obtenido a partir de la dirección
    * pasada como parámetro tiene  magnitud cero o si la coordenada homogénea 
    * z del punto vale cero
    * @see Vector2D#magnitud()
    * @see Punto2D#Punto2D(double, double)
    * @see Vector2D#Vector2D(Vector2D)
    * @see Direccion2D#vector()
    */
    public Rayo2D (double hx, double hy, double hz, Direccion2D direccion) throws GeomException {
        if (direccion.vector().magnitud() == 0)
            throw new GeomException("Rayo2D (double, double, double, Direccion2D) : vector director de magnitud cero");
        else {
            comienzo = new Punto2D(hx, hy, hz);
            director = new Vector2D(direccion.vector());
        }
    }
   
  /** Constructor del rayo a partir un segmento. El punto inicial del rayo
    * será el punto de inicio del segmento, y el vector director 
    * estará definido entre los puntos inicial y final del segmento.
    * @param segmento segmento a partir del cual obtener la recta
    * @return una nueva recta definida a partir de los dos extremos del segmento
    * @see Segmento2D#comienzo()
    * @see Segmento2D#fin()
    * @see Punto2D#Punto2D(Punto2D)
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    */
    public Rayo2D (Segmento2D segmento) {
        comienzo = new Punto2D(segmento.comienzo());
        director = new Vector2D(segmento.comienzo(), segmento.fin());
    }
    
 /** Construcción del rayo a partir de una recta y un punto perteneciente a 
   * dicha recta. El punto inicial del rayo será
   * el pasado como parámetro, y el vector director será el mismo que el de la recta
   * @param punto punto perteneciente a la recta
   * @param recta recta a partir de la cual se desea obtener el rayo
   * @return un nuevo rayo a partir del punto  y del vector director
   * de la recta
   * @exception GeomException si el punto no pertenece a la recta
   * @see Recta2D#posicionPunto(Punto2D)
   * @see Recta2D#director()
   * @see Punto2D#Punto2D(Punto2D)
   * @see Vector2D#Vector2D(Vector2D)
   * @see Mat#CONTENIDO
   */
    public Rayo2D (Punto2D punto, Recta2D recta) throws GeomException {
        if (recta.posicionPunto(punto) != Mat.CONTENIDO)
            throw new GeomException("Rayo2D (Punto2D, Recta2D): el punto no pertenece a la recta");
        else
        {
            comienzo = new Punto2D(punto);
            director = new Vector2D(recta.director());
        }
    }
    
  /** Construcción del rayo a partir de una recta y las coordendas cartesianas
   * de un punto perteneciente a 
   * dicha recta. El punto inicial del rayo será
   * el pasado como parámetro, y el vector director será el mismo que el de la recta
   * @param x coordenada cartesiana x del punto perteneciente a la recta
   * @param y coordenada cartesiana y del punto perteneciente a la recta
   * @param recta recta a partir de la cual se desea obtener el rayo
   * @return un nuevo rayo a partir del punto del punto y del vector director
   * de la recta
   * @exception GeomException si el punto no pertenece a la recta
   * @see Recta2D#posicionPunto(Punto2D)
   * @see Recta2D#director()
   * @see Punto2D#Punto2D(Punto2D)
   * @see Punto2D#Punto2D(double, double)
   * @see Vector2D#Vector2D(Vector2D)
   * @see Mat#CONTENIDO
   */
    public Rayo2D (double x, double y, Recta2D recta) throws GeomException {
        Punto2D punto = new Punto2D(x, y);
        
        if (recta.posicionPunto(punto) != Mat.CONTENIDO)
            throw new GeomException("Rayo2D (double, double, Recta2D): el punto no pertenece a la recta");
        else
        comienzo = new Punto2D(punto);
        director = new Vector2D(recta.director());
    }
    
    /** Construcción del rayo a partir de una recta y las coordendas homogéneas
   * de un punto perteneciente a 
   * dicha recta. El punto inicial del rayo será
   * el pasado como parámetro, y el vector director será el mismo que el de la recta
   * @param hx coordenada homogénea x del punto perteneciente a la recta
   * @param hy coordenada homogénea y del punto perteneciente a la recta
   * @param hz coordenada homogénea z del punto perteneciente a la recta
   * @param recta recta a partir de la cual se desea obtener el rayo
   * @return un nuevo rayo a partir del punto del punto y del vector director
   * de la recta
   * @exception GeomException si el punto no pertenece a la recta o si la coordenada
   * homogénea pasada como parámetro vale cero
   * @see Recta2D#posicionPunto(Punto2D)
   * @see Recta2D#director()
   * @see Punto2D#Punto2D(Punto2D)
   * @see Punto2D#Punto2D(double, double, double)
   * @see Vector2D#Vector2D(Vector2D)
   * @see Mat#CONTENIDO
   */
    public Rayo2D (double hx, double hy, double hz, Recta2D recta) throws GeomException {
        Punto2D punto = new Punto2D(hx, hy, hz);
        
        if (recta.posicionPunto(punto) != Mat.CONTENIDO)
            throw new GeomException("Rayo2D (double, double, double, Recta2D): el punto no pertenece a la recta");
        else
        comienzo = new Punto2D(punto);
        director = new Vector2D(recta.director());
    }
    
    /** Construcción del rayo a partir de una recta y un valor para sustituir en 
   * la ecuación paramétrica de la recta para obtener un punto. El punto inicial del rayo será
   * el obtenido tras sustituir el parámetro en la ecuación de la recta por el
   * valor especificado, y el vector director será el mismo que el de la recta
   * @param t valor por el que sustituir el parámetro de la ecuación paramétrica de
   * la recta
   * @param recta recta a partir de la cual se desea obtener el rayo
   * @return un nuevo rayo a partir del punto obtenido tras sustituir 
   * el parámetro de la ecuación paramétrica de la recta por el valor especificado
   * y del vector director de la recta
   * @see Recta2D#punto(double)
   * @see Recta2D#director()
   * @see Punto2D#Punto2D(Punto2D)
   * @see Vector2D#Vector2D(Vector2D)
   * @see Mat#CONTENIDO
   */
    public Rayo2D (double t, Recta2D recta) throws GeomException {
        comienzo = new Punto2D(recta.punto(t));
        director = new Vector2D(recta.director());
    }
    
  /** Constructor de copia del rayo. Obtiene un nuevo rayo a partir
    * de uno ya existente.
    * @param original rayo a partir del cual construir la nueva recta
    * @return una nueva recta con su punto de comienzo y vector director iguales 
    * a los del rayo pasado como parámetro
    * @see Rayo2D#comienzo()
    * @see Rayo2D#director()
    * @see Punto2D#Punto2D(Punto2D)
    * @see Vector2D#Vector2D(Vector2D)
    */
    public Rayo2D (Rayo2D original) {
        this.comienzo = new Punto2D(original.comienzo());
        this.director = new Vector2D(original.director());
    }
        
  /** Comparación del rayo con otro objeto geométrico. Un rayo es igual a 
    * otro objeto geométrico si dicho objeto geométrico es también un rayo
    * y si tiene el mismo punto de comienzo y la misma dirección
    * @param obj objeto geométrico con el que se desea comparar la recta
    * @return un valor booleano indicando si el objeto geométrico pasado como
    * parámetro es igual al rayo
    * @see Rayo2D#comienzo()
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Rayo2D#director()
    * @see Vector2D#direccion()
    * @see Direccion2D#equals(ObjetoGeometrico)
    */
    public boolean equals (ObjetoGeometrico obj) 
    {
        if (obj==this)
        return true;
        if (obj instanceof Rayo2D) {
            Rayo2D rayo = (Rayo2D) obj;
            if (this.comienzo().equals(rayo.comienzo()) &&
                this.director().direccion().equals(rayo.director().direccion()))
                return true;
                else return false;
        }
        else return false;
    }

  /** Comparación del rayo con otro objeto geométrico. Un rayo es distinto a 
    * otro objeto geométrico si dicho objeto geométrico no es un rayo
    * o si no tiene el mismo punto de comienzo y dirección
    * @param obj objeto geométrico con el que se desea comparar la recta
    * @return un valor booleano indicando si el objeto geométrico pasado como
    * parámetro es distinto al rayo
    * @see Rayo2D#equals(ObjetoGeometrico)
    */
    public boolean not_equals (ObjetoGeometrico obj) 
    {
        return !this.equals(obj);
    }
   
  /** Obtiene el resultado de la intersección del rayo con una recta. Para ello
    * se resuelve el sistema de dos ecuaciones con dos incógnitas a partir de
    * las ecuaciones paramétricas de ambos objetos geométricos. Si dicho sistema no tiene solución,
    * se devolverá null, si tiene solución única un punto (a menos que la solución 
    * quede fuera del rayo, por lo que se devolverá también null) y si tiene 
    * infinitas soluciones es que existen infinitos puntos de interección, por lo
    * que se devolverá el rayo
    * @param otro recta con la que se desea obtener la intersección
    * @return null si la recta y el rayo no intersectan, un objeto de tipo Punto2D
    * si intersectan en un punto, o un objeto de tipo Rayo2D (el propio rayo)
    * si hay infinitos puntos de intersección
    * @see Vector2D#despX()
    * @see Vector2D#despY()
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Recta2D#punto(double)
    * @see Rayo2D#punto(double)
    * @see Rayo2D#contienePunto
    * @see Recta2D#director()
    * @see Mat#absoluto(double)
    * @see Mat#EPSILON
    */
    public ObjetoGeometrico interseccionRecta (Recta2D otro)
    {
         double coefT, indep, mult;
       
        try {
        if (Mat.absoluto(this.director().despX()) > Mat.EPSILON) {
            mult = (this.director().despY()/this.director().despX());
            coefT = -otro.director().despY() + otro.director().despX()*mult;
            indep = (otro.punto(0).y() - this.punto(0).y()) -
                    mult*(otro.punto(0).x() - this.punto(0).x());       
        } else {
            mult = (this.director().despX() / this.director().despY());
            coefT = -otro.director().despX() + otro.director().despY()*mult;
            indep = (otro.punto(0).x() - this.punto(0).x()) - mult*(otro.punto(0).y() - this.punto(0).y());
        }
        if (coefT == 0)
           if (indep == 0)
              // El sistema tiene infinitas soluciones
              return this;
           else
              // El sistema no tiene solución
              return null;
        else
        {
            double t = indep/coefT;
            if (this.contienePunto(otro.punto(t)))
                // El sistema tiene solución única
                return otro.punto(t);
            else
                // Aunque el sistema tiene solución única, ésta queda fuera
                // del rayo
                return null;
        }
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
  /** Obtiene el resultado de la intersección del rayo con otro rayo. Solucionamos el sistema
    * de ecuaciones paramétricas como si el segundo rayo fuera una recta. Si la solución
    * es un rayo, podemos tener diversas situaciones
    *<ul>
    *<li> si el punto de comienzo de un rayo se encuentra contenido en el otro y
    * ambos tienen la misma dirección, el resultado será un rayo</li>
    *<li> si el punto de comienzo de un rayo se encuentra contenido en el otro, pero
    * las direcciones son distintas, el resultado será un segmento</li>
    *<li> si el punto de comienzo de ninguno de los rayos está contenido en el otro,
    * no habrá ninguna intersección</li>
    <li>Si ambos rayos coinciden en el punto inicial y tiene direcciones opuestas, el 
    * resultado será un punto</li>
    *</ul>
    * @param otro rayo con el que se desea obtener la intersección
    * @return el ObjetoGeométrico resultante de la intersección (null, un punto, 
    * un segmento o un rayp)
    * @see Recta2D#Recta2D(Rayo2D)
    * @see Rayo2D#interseccionRecta(Recta2D)
    * @see Rayo2D#contienePunto(Punto2D)
    * @see Rayo2D#comienzo()
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Rayo2D#director()
    * @see Vector2D#direccion()
    * @see Direccion2D#equals(ObjetoGeometrico)
    */
    public ObjetoGeometrico interseccionRayo(Rayo2D otro) 
    {
        ObjetoGeometrico result = this.interseccionRecta(new Recta2D(otro));
        
        try {
        if (result instanceof Rayo2D)
            if (this.comienzo().equals(otro.comienzo()))
                if (this.director().direccion().equals(otro.director().direccion()))
                    return this;
                else
                    return this.comienzo();
            else if (otro.contienePunto(this.comienzo()))
                if (this.director().direccion().equals(otro.director().direccion()))
                    return this;
                else
                    return new Segmento2D(this.comienzo(),  otro.comienzo()); 
            else if (this.contienePunto(otro.comienzo()))
                if (this.director().direccion().equals(otro.director().direccion()))
                    return otro;
                else
                    return new Segmento2D(otro.comienzo(), this.comienzo());
            else
                return null;
        else if (result instanceof Punto2D)
            if (otro.contienePunto((Punto2D) result))
                return result;
            else
                return null;
        else
            return result;
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
  /** Obtiene el resultado de la intersección del rayo con un segmento. Resolvemos 
    * el sistema de ecuaciones paramétricas a partir del rayo y de la recta obtenida
    * a partir del segmento. Si el resultado es un rayo, podemos tener varias situaciones:
    *<ul>
    *<li> Si ambos extremos del segmento están contenidos en el rayo, el resultado
    * de la intersección será el propio segmento </li>
    *<li> Si solo un extremo del segmento se encuentra en el rayo, el resultado será
    * otro rayo, cuys extremos serán el del segmento que está contenido en el rayo
    * y el punto de comienzo del propio rayo </li>
    *<li> si solo un punto del segmento está contenido en el rayo (un extremo del
    * segmento que es igual al comiezno del rayo) el resultado será un punto</li>
    *<li>Si ningún extremo del segmento está contenido en el rayo no habra ninguna 
    * intersección</li>
    * </ul>
    * @param otro segmento con el que se desea obtener la intersección
    * @return null si la recta y el segmento no intersectan, un objeto de tipo Punto2D
    * si intersectan en un punto, o un objeto de tipo Rayo2D (el propio segmento)
    * si las soluciones del sistema son infinitas
    * @see Recta2D#Recta2D(Segmento2D)
    * @see Rayo2D#interseccionRecta(Recta2D)
    * @see Segmento2D#comienzo()
    * @see Segmento2D#fin()
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Rayo2D#contienePunto(Punto2D)
    */
    public ObjetoGeometrico interseccionSegmento(Segmento2D otro) 
    {
        ObjetoGeometrico result = this.interseccionRecta(new Recta2D(otro));
        
        try {
        if (result instanceof Rayo2D)
            if (this.contienePunto(otro.comienzo()) && this.contienePunto(otro.fin()))
                return otro;
            else if (this.contienePunto(otro.comienzo()))
                if (this.comienzo.equals(otro.comienzo()))
                    return otro.comienzo();
                else
                    return new Segmento2D(otro.comienzo(), this.comienzo);
            else if (this.contienePunto(otro.fin()))
                if (this.comienzo.equals(otro.fin()))
                    return otro.fin();
                else
                    return new Segmento2D(this.comienzo, otro.fin());
            else
                 return null;
        else if (result instanceof Punto2D)
            if (otro.contienePunto((Punto2D) result))
                return result;
            else
                return null;
        else
            return result;
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
    
  /** Obtiene el punto del rayo correspondiente a un determinado parámetro.
    * @param t valor por el que sustituir el parámetro en la ecuación paramétrica
    * de la recta
    * @return el punto del rayo obtenido tras sustituir el valor pasado como 
    * parámetro en el parámetro de la ecuación paramétrica del rayo
    * @exception GeomException si el valor pasado como parámetro es menor que cero
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Vector2D#despX()
    * @see Vector2D#despY() 
    */
    public Punto2D punto(double t) throws GeomException{
        double x, y;
        
        if (t < 0)
            throw new GeomException("punto (double): el parámetro no puede ser menor que cero para el rayo");
        else
        {
            x = comienzo.x() + t*director.despX();
            y = comienzo.y() + t*director.despY();
        
            return new Punto2D(x, y);
        }
    }

  /** Comprueba si un punto está contenido en el rayo. El punto estará contenido
    * si es igual al comienzo del rayo o si la dirección formada desde el comienzo
    * del rayo al punto es igual a la formada desde el origen del rayo a cualquier
    * otro punto del mismo
    * cualquiera del rayo
    * @param punto punto que se desea comprobar si está contenido en el rayo
    * @return un valor booleano indicando si el punto está contenido en el rayo
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Rayo2D#punto(double)
    * @see Direccion2D#Direccion2D(Punto2D, Punto2D)
    * @see Direccion2D#equals(ObjetoGeometrico)
    */
    public boolean contienePunto(Punto2D punto) {
           try {
           if (punto.equals(comienzo) || punto.equals(this.punto(1))) 
                return true;
            else {
                Direccion2D d1 = new Direccion2D(comienzo, punto);
                Direccion2D d2 = new Direccion2D(comienzo, this.punto(1));
                if (d1.equals(d2))
                    return true;
                else
                    return false;
            }
           } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
           }
    }       
    
   /** Comprueba si un punto está contenido en el rayo. El punto estará contenido
    * si es igual al comienzo del rayo o si es colineal respecto a dos puntos 
    * cualquiera del rayo
    * @param x coordenada cartesiana x del punto que se desea comprobar si está 
    * contenido en el rayo
    * @param y coordenada cartesiana y del punto que se desea comprobar si está 
    * contenido en el rayo
    * @return un valor booleano indicando si el punto está contenido en el rayo
    * @see Rayo2D#contienePunto(Punto2D)
    * @see Punto2D#Punto2D(double, double)
    */
    public boolean contienePunto(double x, double y) {
           return contienePunto(new Punto2D(x, y));
    }
    
  /** Comprueba si un punto está contenido en el rayo. El punto estará contenido
    * si es igual al comienzo del rayo o si es colineal respecto a dos puntos 
    * cualquiera del rayo
    * @param hx coordenada homogénea x del punto que se desea comprobar si está 
    * contenido en el rayo
    * @param hy coordenada homogénea y del punto que se desea comprobar si está 
    * contenido en el rayo
    * @param hz coordenada homogénea z del punto que se desea comprobar si está
    * contenido en el rayo
    * @return un valor booleano indicando si el punto está contenido en el rayo
    * @exception GeomException si la coordenada homogénea z pasada como parámetro
    * vale cero
    * @see Rayo2D#contienePunto(Punto2D)
    * @see Punto2D#Punto2D(double, double, double)
    */
    public boolean contienePunto(double x, double y, double z) throws GeomException{
           return contienePunto(new Punto2D(x, y));
    }
  
  /** Comprueba si el rayo es colineal con el punto pasado como parámetro o si lo
    * contiene
    * @param punto punto que se desea saber si está contenido en el rayo o es
    * colineal con éste
    * @return un valor booleano indicando si el punto pasado como parámetro está
    * contenido en el rayo o es colineal con éste
    * @exception GeomException si el rayo es un rayo degenerado
    * @see Rayo2D#degenerado()
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Rayo2D#punto(double)
    * @see Mat#COLINEAL
    */
    public boolean colinealOContiene(Punto2D punto) throws GeomException {
        if (this.degenerado())
            throw new GeomException ("colinearOContiene (Punto2D): el rayo es degenerado");
        else
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.COLINEAL);
    }

  /** Comprueba si el rayo es colineal con el punto cuyas coordenadas cartesianas
    * se pasan como parámetro o si lo contiene
    * @param px coordenada cartesiana x del punto que se desea conocer si está contenido
    * en el rayo o es colineal con éste
    * @param py coordenada cartesiana y del punto que se desea conocer si está contenido
    * en el rayo o es colineal con éste
    * @return un valor booleano indicando si el punto cuyas coordenadas cartesianas
    * se han pasado como parámetro está contenido en el rayo o es colineal con éste
    * @exception GeomException si el rayo es un rayo degenerado
    * @see Rayo2D#degenerado()
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Rayo2D#punto(double)
    * @see Mat#COLINEAL
    */
    public boolean colinealOContiene(double px, double py) throws GeomException {
        if (this.degenerado())
            throw new GeomException ("colinearOContiene (double, double): el rayo es degenerado");
        else
        {
            Punto2D punto = new Punto2D(px, py);
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.COLINEAL);
        }
    }
  /** Comprueba si el rayo es colineal con el punto cuyas coordenadas homogéneas
    * se pasan como parámetro o si lo contiene
    * @param hx primera coordenada homogénea del punto que se desea conocer si está contenido
    * en el rayo o es colineal con éste
    * @param hy segunda coordenada homogénea del punto que se desea conocer si está contenido
    * en el rayo o es colineal con éste
    * @param hz tercera coordenada homogénea del punto que se desea conocer si está contenido
    * en el rayo o es colineal con éste
    * @return un valor booleano indicando si el punto cuyas coordenadas homogéneas
    * se han pasado como parámetro está contenido en el rayo o es colineal con éste
    * @exception GeomException si el rayo es un rayo degenerado
    * @see Rayo2D#degenerado()
    * @see Punto2D#Punto2D(double, double, double)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Rayo2D#punto(double)
    * @see Mat#COLINEAL
    */
    public boolean colinealOContiene(double hx, double hy, double hz) throws GeomException {
        if (this.degenerado())
            throw new GeomException ("colinearOContiene (double, double, double): el rayo es degenerado");
        else
        {
            Punto2D punto = new Punto2D(hx, hy, hz);
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.COLINEAL);
        }
    }

  /** Comprueba si un punto está situado a la derecha del rayo. Esto sucederá cuando
    * el punto esté a la derecha del segmento cuyo punto de inicio sea el comienzo del
    * rayo y su punto final sea cualquier otro perteneciente al rayo
    * @param punto punto que se desea conocer si se encuentra a la derecha del
    * rayo
    * @return un valor booleano indicando si se dan las condiciones para que 
    * el punto se encuentre a la derecha del rayo
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Mat#DERECHA
    */
    public boolean puntoADerecha(Punto2D punto) {
        try {
        if (punto.equals(comienzo) || punto.equals(punto(1)))
            return false;
        else 
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.DERECHA);
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
           }
    }

  /** Comprueba si un punto está situado a la derecha del rayo. Esto sucederá cuando
    * el punto esté a la derecha del segmento cuyo punto de inicio sea el comienzo del
    * rayo y su punto final sea cualquier otro perteneciente al rayo
    * @param x coordedana cartesiana x del punto 
    * @param y coordenada cartesiana y del punto
    * @return un valor booleano indicando si se dan las condiciones para que 
    * el punto se encuentre a la derecha del rayo
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Mat#DERECHA
    */
    public boolean puntoADerecha(double x, double y) {
        Punto2D punto = new Punto2D(x,y);
        try {
        if (punto.equals(comienzo) || punto.equals(punto(1)))
            return false;
        else 
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.DERECHA);
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
   /** Comprueba si un punto está situado a la derecha del rayo. Esto sucederá cuando
    * el punto esté a la derecha del segmento cuyo punto de inicio sea el comienzo del
    * rayo y su punto final sea cualquier otro perteneciente al rayo
    * @param hx coordedana homogénea x del punto 
    * @param hy coordenada homogénea y del punto
    * @param hz coordenada homogénea z del punto
    * @return un valor booleano indicando si se dan las condiciones para que 
    * el punto se encuentre a la derecha del rayo
    * @exception GeomException si la coordenada homogénea z pasada como parámetro
    * vale cero
    * @see Punto2D#Punto2D(double, double, double)
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Mat#DERECHA
    */
    public boolean puntoADerecha(double hx, double hy, double hz) throws GeomException {
        Punto2D punto = new Punto2D(hx, hy, hz);
        
        if (punto.equals(comienzo) || punto.equals(punto(1)))
            return false;
        else 
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.DERECHA);
    }
    
  /** Comprueba si un punto está situado a la izquierda del rayo. Esto sucederá cuando
    * el punto esté a la izquierda del segmento cuyo punto de inicio sea el comienzo del
    * rayo y su punto final sea cualquier otro perteneciente al rayo
    * @param punto punto que se desea conocer si se encuentra a la izquierda del
    * rayo
    * @return un valor booleano indicando si se dan las condiciones para que 
    * el punto se encuentre a la izquierda del rayo
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Mat#IZQUIERDA
    */
    public boolean puntoAIzquierda(Punto2D punto) {
        try {
        if (punto.equals(comienzo) || punto.equals(punto(1)))
            return false;
        else 
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.IZQUIERDA);
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
           }
    }
    
   /** Comprueba si un punto está situado a la izquierda del rayo. Esto sucederá cuando
    * el punto esté a la izquierda del segmento cuyo punto de inicio sea el comienzo del
    * rayo y su punto final sea cualquier otro perteneciente al rayo
    * @param x coordedana cartesiana x del punto 
    * @param y coordenada cartesiana y del punto
    * @return un valor booleano indicando si se dan las condiciones para que 
    * el punto se encuentre a la izquierda del rayo
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Mat#IZQUIERDA
    */
    public boolean puntoAIzquierda(double x, double y) {
        Punto2D punto = new Punto2D(x,y);
        try {
        if (punto.equals(comienzo) || punto.equals(punto(1)))
            return false;
        else 
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.IZQUIERDA);
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
           }
    }
    
   /** Comprueba si un punto está situado a la izquierda del rayo. Esto sucederá cuando
    * el punto esté a la izquierda del segmento cuyo punto de inicio sea el comienzo del
    * rayo y su punto final sea cualquier otro perteneciente al rayo
    * @param hx coordedana homogénea x del punto 
    * @param hy coordenada homogénea y del punto
    * @param hz coordenada homogénea z del punto
    * @return un valor booleano indicando si se dan las condiciones para que 
    * el punto se encuentre a la izquierda del rayo
    * @exception GeomException si la coordenada homogénea z pasada como parámetro
    * vale cero
    * @see Punto2D#Punto2D(double, double, double)
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Mat#DERECHA
    */
    public boolean puntoAIzquierda(double hx, double hy, double hz) throws GeomException {
        Punto2D punto = new Punto2D(hx, hy, hz);
        
        if (punto.equals(comienzo) || punto.equals(punto(1)))
            return false;
        else 
            return (punto.posicionRelativa(comienzo, punto(1)) == Mat.IZQUIERDA);
    }
    
  /** Obtiene la distancia del rayo a un punto, así como el punto más cercano del
    * rayo a dicho punto. Para obtenerla se determina
    * si la recta perpendicular al rayo que pasa por el punto corta al rayo.
    * En caso afirmativo la distancia será la del punto al punto de corte de la recta
    * con el rayo, y en caso negativo será la distancia del punto al punto de 
    * comienzo del rayo
    * @param punto punto al cual se desea obtener la distancia
    * @param cercano punto del segmento más cercano al punto pasado como primer
    * parámetro. Es un parámetro de SALIDA, y debe ser un punto ya creado
    * @return la distancia del rayo al punto
    * @exception GeomException si el punto pasado como segundo parámetro no es
    * un punto inicializado
    * @see Recta2D#Recta2D(Rayo2D)
    * @see Recta2D#perpendicular(int)
    * @see Recta2D#interseccionRayo(Rayo2D)
    * @see Punto2D#distancia(Punto2D)
    * @see Punto2D#vectorDiferencia(Punto2D)
    */
    public double puntoMasCercano (Punto2D punto, Punto2D cercano) throws GeomException {
        double distancia;
        Vector2D diferencia = new Vector2D();
        
        if (cercano == null) 
            throw new GeomException("puntoMasCercano (Punto2D, Punto2D): el segundo parámetro debe estar inicializado");
        try {
            Recta2D rectaPerpendicular = new Recta2D(this);
            rectaPerpendicular = rectaPerpendicular.perpendicular(Mat.HORARIO);
            Recta2D recta = new Recta2D(punto, rectaPerpendicular.director());
            ObjetoGeometrico corte = recta.interseccionRayo(this);
            if (corte instanceof Punto2D)
            {
                // La distancia será igual a la distancia del punto al punto
                // de corte de la recta con el rayo
                Punto2D puntoCorte = (Punto2D) corte;
                distancia = punto.distancia(puntoCorte);
                diferencia = puntoCorte.vectorDiferencia(cercano);
            }
            else
            {
                // La distancia será igual a la distancia del punto al 
                // comienzo del rayo
                distancia = punto.distancia(comienzo);
                diferencia = comienzo.vectorDiferencia(cercano);
            }
        } catch (GeomException e) {
            return -1; // No se va a producir nunca
        }
        
        cercano.trasladar(diferencia);
        
        return distancia;
    }  
    
  /** Obtiene la distancia del rayo a un punto
    * @param punto punto para el cual se desea obtener la distancia
    * @return la distancia del rayo al punto
    * @see Rayo2D#puntoMasCercano(Punto2D, Punto2D)
    */
    public double distancia(Punto2D punto) {
        try  {
            return puntoMasCercano(punto, new Punto2D());
        } catch (GeomException e) {
            return java.lang.Double.MAX_VALUE; // Nunca se va a producir
        }
    }
    
  /** Comprueba si el rayo es horizontal. Para que esto suceda el desplazamiento
    * en el eje y del vector director debe ser cero.
    * @return un valor booleano indicando si el es horizontal
    * @see Mat#absoluto(double)
    * @see Vector2D#despY()
    * @see Mat#EPSILON
    */
    public boolean esHorizontal() {
        if (Mat.absoluto(director.despY()) < Mat.EPSILON) return true;
        else return false;
    }
    
  /** Comprueba si el rayo es vertical. Para que esto suceda el desplazamiento 
    * en el eje x del vector director debe ser cero.
    * @return un valor booleano indicando si el rayo es vertical
    * @see Mat#absoluto(double)
    * @see Vector2D#despX() 
    * @see Mat#EPSILON
    */
    public boolean esVertical() {
        if (Mat.absoluto(director.despX()) < Mat.EPSILON) return true;
        else return false;
    }
    
  /** Determina si el rayo es un rayo degenerado. Esto sucederá si el vector 
    * director es u vector nulo
    * @return un valor booleano indicando si el rayo es un rayo degenerado
    * @see Vector2D#vectorNulo()
    */
    public boolean degenerado() {
        return director.vectorNulo();
    }
    
  /** Obtiene el rayo opuesta. Dos rayos son opuestos si sus vectores directores
    * son a su vez opuestos y tienen el mismo punto de inicio.
    * @return el rayo opuesto
    * @see Vector2D#opuesto()
    */
    public Rayo2D opuesto() {
        director.opuesto();
        
        return this;
    }
    
    
  /** Obtiene el valor por el que se debe sustituir el parámetro de la ecuación 
    * paramétrica del rayo para que el resultado sea un determinado punto.
    * El punto se obtiene a partir de sus coordenadas cartesianas.
    * @param x coordenada cartesiana x del punto
    * @param y coordenada cartesiana y del punto
    * @return el valor del parámetro que al aplicarse a la ecuación parámetrica
    * hace que se obtenga el punto cuyas coordendas cartesianas se han pasado
    * como parámetro
    * @exception GeomException si el punto no pertenece al rayo
    * @see Punto2D#Punto2D(double, double)
    * @see Rayo2D#parametroPunto(Punto2D) 
    */
    public double parametroPunto(double x, double y) throws GeomException {
        return parametroPunto(new Punto2D(x,y));
    }
    
  /** Obtiene el valor por el que se debe sustituir el parámetro de la ecuación
    * paramétrica del rayo para que el resultado sea un determinado punto
    * @param punto el punto del rayo
    * @return el valor del parámetro que al aplicarse a la ecuación paramétrica
    * hace que se obtenga el punto pasado como parámetro.
    * @exception GeomException si el punto no pertenece al rayo
    * @see Punto2D#x()
    * @see Vector2D#despX()
    * @see Rayo2D#contienePunto(Punto2D)
    * @see Mat#CONTENIDO
    */
    public double parametroPunto(Punto2D punto) throws GeomException {
        if (!contienePunto(punto))
            throw new GeomException("parametroPunto (Punto2D): El punto no pertence al rayo");
        else
           return (punto.x() - comienzo.x()) / director.despX();
    }
    
  /** Traslada el rayo en el plano a partir de un determinado incremento en
   * cada coordenada cartesiana. La traslación se aplicará al punto inicial
   * del rayo
   * @param incX incremento o desplazamiento en el eje x
   * @param incY incremento o desplazamiento en el eje y
   * @return el rayo trasladado
   * @see Punto2D#trasladar(double, double)
   */
    public Rayo2D trasladar (double incX, double incY) {
        comienzo.trasladar(incX, incY);
        return this;
    }
    
  /** Traslada el rayo en el plano aplicando el mismo incremento en cada
    * eje. La traslación se aplicará al punto inicial del rayo.
    * @param incr incremento o desplazamiento que será aplicado tanto al eje x 
    * como al eje y
    * @return el rayo trasladado
    * @see Punto2D#trasladar(double)
    */
    public Rayo2D trasladar (double incr) {
        comienzo.trasladar(incr);
        return this;
    }
    
  /** Traslada el rayo en la dirección y a la distancia determinados por un 
    * vector. Se aplicará la traslación al punto inicial del rayo
    * @param vector el vector que define la traslación del rayo
    * @return el rayo trasladado
    * @see Punto2D#trasladar(Vector2D)
    */
    public Rayo2D trasladar (Vector2D vector)
    {
        comienzo.trasladar(vector);
        return this;
    }
    
  /** Traslación del rayo a partir de las coordenadas polares (se traslada
    * el punto inicial del rayo una determinada
    * distancia en un determinado ángulo)
    * @param angulo dirección de traslación del rayo
    * @param dist distancia a la que se moverá el rayo de su posición original
    * siguiendo el ángulo indicado
    * @return el rayo trasladado
    * @see Punto2D#trasladarPolar(double, double)
    */
    public Rayo2D trasladarPolar (double angulo, double dist) {
        comienzo.trasladarPolar(angulo, dist);
        return this;
    }
    
  /** Escalado de un rayo. Se aplicará el escalado al punto inicial del rayo y
    * al vector director. 
    * @param escala factor de escalado
    * @return el rayo
    * @exception GeomException si el factor de escalado es cero
    * @see Punto2D#escalado(double)
    * @see Vector2D#escalado(double)
    */
    public Rayo2D escalado(double escala) throws GeomException{
        comienzo.escalado(escala);
        director.escalado(escala);
        return this;
    }
    
  /** Escalado de un rayo con distinto factor de escalado para cada coordenada. 
    * Se aplicará el escalado al punto inicial del rayo y al 
    * vector director.
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return el rayo escalado
    * @exception GeomException si alguno de los factores de escalado es cero
    * @see Punto2D#escalado(double, double)
    * @see Vector2D#escalado(double, double)
    */
    public Rayo2D escalado(double sx, double sy) throws GeomException {
        comienzo.escalado(sx, sy);
        director.escalado(sx, sy);
        return this;
    }
    
  /** Rota el rayo respecto del origen de coordenadas
    * @param radio angulo de giro en radianes. Si es positivo el giro será
    * en sentido antihorario y en caso contrario lo será en sentido horario
    * @return el rayo girado según el ángulo pasado como parámetro
    * @see Punto2D#gira(double)
    */
    public Rayo2D gira(double radio) {
        comienzo.gira(radio);
        director.gira(radio);
        return this;
    }
    
   /** Rota el rayo respecto a un determinado punto
    * @param origen punto respecto al cual realizar la rotación
    * @param radio angulo de giro en radianes. Si es positivo el giro será
    * en sentido antihorario y en caso contrario lo será en sentido horario
    * @return el rayo girado según el ángulo pasado como parámetro
    * @see Punto2D#gira(Punto2D, double)
    */
    public Rayo2D gira(Punto2D origen, double radio) {
        comienzo.gira(origen, radio);
        director.gira(radio);
        return this;
    }   
    
  /** Obtiene el vector director del rayo
    * @return el vector director del rayo
    */
    public Vector2D director()
    {
        return director;
    }
    
  /** Obtiene el punto inicial del rayo
    * @return el punto inicial del rayo
    */
    public Punto2D comienzo()
    {
        return comienzo;
    }
    
  /** Obtiene la dirección asociada al vector director del rayo.
    * @return dirección obtenida a partir del vector director del rayo
    * @see Vector2D#direccion()
    */
    public Direccion2D direccion()
    {
        return director.direccion();
    }
    
  /** Obtiene la recta que contiene al rayo
    * @return una recta cuyo punto base de la ecuación paramétrica será el punto
    * inicial del rayo y cuyo vector director será el mismo que el del rayo
    * @see Recta2D#Recta2D(Rayo2D)
    */
    public Recta2D recta()
    {
        return new Recta2D(this);
    }
    
  /** Realiza la conversión del objeto de tipo Rayo2D a una cadena
    * @return una cadena especificando el tipo de objeto (rayo) y sus
    * componentes (punto de inicio y vector director de la ecuación paramétrica)
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Vector2D#despX()
    * @see Vector2D#despY()
    */   
    public String toString() {
        return nombre + " - Rayo2D :( (" + comienzo.x() + "," + comienzo.y() + ") + t*(" + director.despX() + "," + director.despY() + ") )";
    }
    
    private void dibujo(Graphics g, int anchura, int altura, boolean coordenadas, Color color) {
        comienzo.dibujar(g, coordenadas);
        try {
            ObjetoGeometrico p[] = new ObjetoGeometrico[4];
            // Determinamos con cual de los cuatro bordes del canvas intersecciona
            // el rayo
            p[0] = interseccionSegmento(new Segmento2D(0, 0, 0, -(altura+1)));
            p[1] = interseccionSegmento(new Segmento2D(0, 0, anchura+1, 0));
            p[2] = interseccionSegmento(new Segmento2D(anchura+1, -(altura+1), anchura+1, 0));
            p[3] = interseccionSegmento(new Segmento2D(anchura+1, -(altura+1), 0, -(altura+1)));
            int pos = -1;
            for (int i=0; i<4; i++)
            {
                if (pos == -1 && p[i] != null)
                    pos = i;
                else if (p[i] instanceof Segmento2D)
                    pos = i;
            }
            
            // Determinamos el punto de destino de la línea a dibujar para representar
            // el rayo (el comienzo será el propio comienzo del rayo)
            Punto2D punto;
            if (pos != -1)
            {
            if (p[pos] instanceof Segmento2D)
            {
                double xTemp, yTemp;
                if (comienzo.x() >= punto(1).x())
                    xTemp = 0;
                else
                    xTemp = anchura+1;
                if (-comienzo.y() >= -punto(1).y())
                    yTemp = 0;
                else
                    yTemp = -(altura+1);
                punto = new Punto2D(xTemp, yTemp);
            }
            else
            {
                Punto2D puntoTemporal = (Punto2D)p[pos];
                punto = new Punto2D(puntoTemporal);
            }
            g.setColor(color);
            g.drawLine((int)comienzo.x(), -(int)comienzo.y(), (int)punto.x(), -(int)punto.y());
            }    
        } catch (GeomException e) {
            System.out.println(e);
        }
    }
    
    public void dibujar(Graphics g, int anchura, int altura, boolean coordenadas) {
        dibujo(g, anchura, altura, coordenadas, Color.black);
    }
    
    public void dibujarResultado(Graphics g, int anchura, int altura) {
        dibujo(g, anchura, altura, false, Color.red);
    }
    
    public void dibujarSeleccionado(Graphics g, int anchura, int altura, boolean coordenadas) {
        dibujo(g, anchura, altura, coordenadas, Color.green);
    }
    
    public void dibujarNombre(Graphics g)
    {
        g.setColor(Color.magenta);
        try {
            if (punto(1).y() > comienzo.y())
                g.drawString(nombre, (int)comienzo.x() - 10, -(int)comienzo.y() + 12);
            else
                g.drawString(nombre, (int)comienzo.x() - 10, -(int)comienzo.y() - 7);
        } catch (GeomException e) {return; } // Nunca se va a producir
    }
}