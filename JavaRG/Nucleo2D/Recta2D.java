/*
 * Recta2D.java
 *
 * Created on 19 de noviembre de 2000, 14:55
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;
import java.awt.*;

/** Un objeto de la clase <code>Recta2D</code> representa una recta en el espacio
 * 2D. Está definida por su ecuación paramétrica (a partir de un punto base
 * y un vector director)
 *
 * @author  Pablo Suau 
 * @version 1.1
 */
public class Recta2D extends ObjetoGeometrico {

    /** Punto base de la ecuación paramétrica
     */
    Punto2D comienzo;
    /** Vector director de la ecuación paramétrica
     */
    Vector2D director;

    /** Constructor por defecto de la recta. Construye la recta horizontal
     * que pasa por el origen de coordenadas
     * @return una nueva recta horizontal que pasa por el origen de coordenadas
     * @see Punto2D#Punto2D()
     * @see Vector2D#Vector2D(Punto2D, Punto2D)
     */
    public Recta2D() {
        comienzo = new Punto2D();
        director = new Vector2D(comienzo, new Punto2D(0, 1));
    }

    /** Constructor de la recta a partir de dos puntos. El punto base de la ecuación
     * paramétrica será el primer punto, y el vector director estará definido entre
     * ese primer punto y el segundo.
     * @param p1 el primer punto
     * @param p2 el segundo punto
     * @return una nueva recta definida a partir de los dos puntos pasados como
     * parámetro
     * @exception GeomException si el vector director definido entre los dos puntos
     * es de magnitud cero
     * @see Punto2D#Punto2D(Punto2D)
     * @see Vector2D#Vector2D(Punto2D, Punto2D)
     * @see Punto2D#distancia(Punto2D)
     * @see Mat#EPSILON
     * @see Vector2D#magnitud()
     */
    public Recta2D(Punto2D p1, Punto2D p2) throws GeomException {
        if (p1.distancia(p2) < Mat.EPSILON)
        throw new GeomException("Recta2D (Punto2D, Punto2D): igual punto de inicio y final");
        else
        {
            comienzo = new Punto2D(p1);
            director = new Vector2D(p1, p2);
        }
    }

    /** Constructor de la recta a partir de las coordenadas cartesianas de dos puntos
     *. El punto base de la ecuación
     * paramétrica será el primer punto, y el vector director estará definido entre
     * ese primer punto y el segundo.
     * @param x1 coordenada cartesiana x del primer punto
     * @param y1 coordenada cartesiana y del primer punto
     * @param x2 coordenada cartesiana x del segundo punto
     * @param y2 coordenada cartesiana y del segundo punto
     * @return una nueva recta definida a partir de los dos puntos cuyas
     * coordenadas cartesianas han sido pasadas como parámetro
     * @exception GeomException si el vector director definido entre los dos puntos
     * es de magnitud cero
     * @see Punto2D#Punto2D(double, double)
     * @see Vector2D#Vector2D(Punto2D, Punto2D)
     * @see Punto2D#distancia(Punto2D)
     * @see Mat#EPSILON
     * @see Vector2D#magnitud()
     */
    public Recta2D (double x1, double y1, double x2, double y2) throws GeomException {
        Punto2D p1 = new Punto2D(x1, y1);
        Punto2D p2 = new Punto2D(x2, y2);
        if (p1.distancia(p2) < Mat.EPSILON)
        throw new GeomException("Recta2D (double, double, double, double): igual punto de inicio y final");
        else
        {
            comienzo = new Punto2D(p1);
            director = new Vector2D(p1, p2);
        }
    }

    /** Constructor de la recta a partir de las coordenadas homogéneas de dos puntos
     *. El punto base de la ecuación
     * paramétrica será el primer punto, y el vector director estará definido entre
     * ese primer punto y el segundo.
     * @param hx1 coordenada homogénea x del primer punto
     * @param hy1 coordenada homogénea y del primer punto
     * @param hz1 coordenada homogénea z del primer punto
     * @param hx2 coordenada homogénea x del segundo punto
     * @param hy2 coordenada homogénea y del segundo punto
     * @param hz2 coordenada homogénea z del segundo punto
     * @return una nueva recta definida a partir de los dos puntos cuyas
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
    public Recta2D (double hx1, double hy1, double hz1, double hx2, double hy2, double hz2) throws GeomException {
        if (hz1 == 0 || hz2 == 0)
        throw new GeomException("Recta2D (double, double, double, double, double, double): alguna de las coordenadas homogeneas en el eje x vale 0");
        Punto2D p1 = new Punto2D(hx1, hy1, hz1);
        Punto2D p2 = new Punto2D(hx2, hy2, hz2);
        if (p1.distancia(p2) < Mat.EPSILON)
        throw new GeomException("Recta2D (double, double, double, double, double, double): igual punto de inicio y final");
        else
        {
            comienzo = new Punto2D(p1);
            director = new Vector2D(p1, p2);
        }
    }

    /** Constructor de la recta a partir de un punto y un vector. La ecuación
     * paramétrica será construida a partir de dichos punto y vector.
     * @param punto punto base de la ecuación paramétrica
     * @param vector vector director de la ecuación paramétrica
     * @return una nueva recta cuya ecuación paramétrica se construye a partir
     * del punto y el vector pasados como parámetro
     * @exception GeomException si el vector pasado como parámetro tiene
     * magnitud cero
     * @see Vector2D#magnitud()
     * @see Punto2D#Punto2D(Punto2D)
     * @see Vector2D#Vector2D(Vector2D)
     */
    public Recta2D (Punto2D punto, Vector2D vector) throws GeomException {
        if (vector.magnitud() == 0)
        throw new GeomException("Recta2D (Punto2D, Vector2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(punto);
            director = new Vector2D(vector);
        }
    }

    /** Constructor de la recta a partir de las coordenadas cartesianas de un
     * punto y un vector. La ecuación
     * paramétrica será construida a partir de dichos punto y vector.
     * @param x coordenada cartesiana x del punto base de la ecuación paramétrica
     * @param y coordenada cartesiana y del punto base de la ecuación paramétrica
     * @param vector vector director de la ecuación paramétrica
     * @return una nueva recta cuya ecuación paramétrica se construye a partir
     * de las coordendas cartesianas del punto y el vector pasados como parámetro
     * @exception GeomException si el vector pasado como parámetro tiene
     * magnitud cero
     * @see Vector2D#magnitud()
     * @see Punto2D#Punto2D(double, double)
     * @see Vector2D#Vector2D(Vector2D)
     */
    public Recta2D (double x, double y, Vector2D vector) throws GeomException {
        if (vector.magnitud() == 0)
        throw new GeomException("Recta2D (double, double, Vector2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(x, y);
            director = new Vector2D(vector);
        }
    }

    /** Constructor de la recta a partir de las coordenadas homogéneas de un
     * punto y un vector. La ecuación
     * paramétrica será construida a partir de dichos punto y vector.
     * @param hx coordenada homogénea x del punto base de la ecuación paramétrica
     * @param hy coordenada homogénea y del punto base de la ecuación paramétrica
     * @param hz coordenada homogénea z del punto base de la ecuación paramétrica
     * @param vector vector director de la ecuación paramétrica
     * @return una nueva recta cuya ecuación paramétrica se construye a partir
     * de las coordendas homogéneas del punto y el vector pasados como parámetro
     * @exception GeomException si el vector pasado como parámetro tiene
     * magnitud cero o si la coordenada homogénea z pasada como parámetro vale
     * cero
     * @see Vector2D#magnitud()
     * @see Punto2D#Punto2D(double, double, double)
     * @see Vector2D#Vector2D(Vector2D)
     */
    public Recta2D (double hx, double hy, double hz, Vector2D vector) throws GeomException {
        if (vector.magnitud() == 0)
        throw new GeomException("Recta2D (double, double, double, Vector2D) : vector director de magnitud cero");
        else {
            comienzo = new Punto2D(hx, hy, hz);
            director = new Vector2D(vector);
        }
    }

    /** Constructor de la recta a partir de un punto y una dirección. La ecuación
     * paramétrica será construida a partir de dichos punto y el vector unitario
     * correspondiente a la dirección
     * @param punto punto base de la ecuación paramétrica
     * @param direccion la dirección a partir de la cual obtener el vector
     * director
     * @return una nueva recta cuya ecuacuón paramétrica se construye a partir
     * del punto y la dirección pasados como parámetro
     * @exception GeomException si el vector obtenido a partir de la dirección
     * pasada como parámetro tiene  magnitud cero
     * @see Vector2D#magnitud()
     * @see Punto2D#Punto2D(Punto2D)
     * @see Vector2D#Vector2D(Vector2D)
     * @see Direccion2D#vector()
     */
    public Recta2D (Punto2D punto, Direccion2D direccion) throws GeomException {
        if (direccion.vector().magnitud() == 0)
        throw new GeomException("Recta2D (Punto2D, Direccion2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(punto);
            director = new Vector2D(direccion.vector());
        }
    }

    /** Constructor de la recta a partir de las coordenadas cartesianas de un
     * punto y una dirección. La ecuación  paramétrica será construida a partir
     * de dichos punto y el vector untiario correspondiente a la dirección.
     * @param x coordenada cartesiana x del punto base de la ecuación paramétrica
     * @param y coordenada cartesiana y del punto base de la ecuación paramétrica
     * @param direccion la dirección a partir de la cual obtener el vector
     * director
     * @return una nueva recta cuya ecuacuón paramétrica se construye a partir
     * de las coordenadas cartesianas del punto y la dirección pasados como
     * parámetro
     * @exception GeomException si el vector obtenido a partir de la dirección
     * pasada como parámetro tiene  magnitud cero
     * @see Vector2D#magnitud()
     * @see Punto2D#Punto2D(double, double)
     * @see Vector2D#Vector2D(Vector2D)
     * @see Direccion2D#vector()
     */
    public Recta2D (double x, double y, Direccion2D direccion) throws GeomException {
        if (direccion.vector().magnitud() == 0)
        throw new GeomException("Recta2D (double, double, Direccion2D): vector director de magnitud cero");
        else
        {
            comienzo = new Punto2D(x, y);
            director = new Vector2D(direccion.vector());
        }
    }

    /** Constructor de la recta a partir de las coordenadas homogéneas de un
     * punto y una dirección. La ecuación  paramétrica será construida a partir
     * de dichos punto y el vector unitario correspondiente a la dirección.
     * @param hx coordenada homogénea x del punto base de la ecuación paramétrica
     * @param hy coordenada homogénea y del punto base de la ecuación paramétrica
     * @param hz coordenada homogénea z del punto base de la ecuación paramétrica
     * @param direccion la dirección a partir de la cual obtener el vector
     * director
     * @return una nueva recta cuya ecuacuón paramétrica se construye a partir
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
    public Recta2D (double hx, double hy, double hz, Direccion2D direccion) throws GeomException {
        if (direccion.vector().magnitud() == 0)
        throw new GeomException("Recta2D (double, double, double, Direccion2D) : vector director de magnitud cero");
        else {
            comienzo = new Punto2D(hx, hy, hz);
            director = new Vector2D(direccion.vector());
        }
    }

    /** Constructor de la recta a partir un segmento. El punto base de la ecuación
     * paramétrica será el punto de inicio del segmento, y el vector director
     * estará definido entre los puntos inicial y final del segmento.
     * @param segmento segmento a partir del cual obtener la recta
     * @return una nueva recta definida a partir de los dos extremos del segmento
     * @see Segmento2D#comienzo()
     * @see Segmento2D#fin()
     * @see Punto2D#Punto2D(Punto2D)
     * @see Vector2D#Vector2D(Punto2D, Punto2D)
     */
    public Recta2D (Segmento2D segmento) {
        comienzo = new Punto2D(segmento.comienzo());
        director = new Vector2D(segmento.comienzo(), segmento.fin());
    }

    /** Construcción de la recta a partir de un rayo. El punto base de la ecuación
     * paramétrica será el punto de comienzo del rayo, y el vector director será
     * también el vector director del rayo.
     * @param rayo rayo a partir del cual obtener la recta
     * @return una nueva recta a partir del punto de comienzo y el vector director
     * del rayo
     * @see Rayo2D#comienzo()
     * @see Rayo2D#director()
     * @see Punto2D#Punto2D(Punto2D)
     * @see Vector2D#Vector2D(Vector2D)
     */
    public Recta2D (Rayo2D rayo) {
        comienzo = new Punto2D(rayo.comienzo());
        director = new Vector2D(rayo.director());
    }

    /** Constructor de la recta a partir de las componentes de la ecuación
     * cartesiana de la recta ax + by + c = 0. Obtenemos dos puntos de la ecuación
     * cartesiana a partir de las cuales construimos la ecuación paramétrica
     * @param a primer coeficiente
     * @param b segunda coeficiente
     * @param c tercer coeficiente
     * @return una nueva recta a partir de su ecuación cartesiana
     * @exception GeomException si la recta tiene pendiente infinita
     * @see Punto2D#Punto2D(double, double)
     * @see Vector2D#Vector2D(double, double, double, double)
     */
    public Recta2D (double a, double b, double c) throws GeomException {
        double x0=0, x1=1, y0, y1;

        if (b == 0)
        throw new GeomException("Recta2D (double, double, double): la recta creada tiene pendiente infinita");
        else {
            y0 = (a/b) * x0 + (c/b);
            y1 = (a/b) * x1 + (c/b);
            comienzo = new Punto2D(x0, y0);
            director = new Vector2D(x0, y0, x1, y1);
        }
    }

    /** Constructor de copia de la recta. Obtiene una nueva recta a partir
     * de una ya existente.
     * @param original recta a partir de la cual construir la nueva recta
     * @return una nueva recta con su punto de comienzo y vector director iguales
     * a los de la recta pasada como parámetro
     * @see Recta2D#punto(double)
     * @see Recta2D#director()
     * @see Punto2D#Punto2D(Punto2D)
     * @see Vector2D#Vector2D(Vector2D)
     */
    public Recta2D (Recta2D original) {
        this.comienzo = new Punto2D(original.punto(0));
        this.director = new Vector2D(original.director());
    }

    /** Comparación de la recta con otro objeto geométrico. Una recta es igual a
     * otro objeto geométrico si dicho objeto geométrico es también una recta
     * y si se cumple lo siguiente:
     * <ul><li> El punto base de una recta está contenido en la otra</li>
     * <li> La dirección de ambos vectores directores es la misma <li></ul>
     * @param obj objeto geométrico con el que se desea comparar la recta
     * @return un valor booleano indicando si el objeto geométrico pasado como
     * parámetro es igual a la recta
     * @see Recta2D#posicionPunto(Punto2D)
     * @see Vector2D#anguloEntreVectores(Vector2D)
     * @see Recta2D#director()
     * @see Mat#EPSILON
     */
    public boolean equals (ObjetoGeometrico obj)
    {
        try {
            if (obj==this)
            return true;
            if (obj instanceof Recta2D) {
                Recta2D recta = (Recta2D) obj;
                if (this.posicionPunto(recta.comienzo) != Mat.CONTENIDO   ||
                this.director().anguloEntreVectores(recta.director()) !=0) //> Mat.EPSILON)
                return false;
                else return true;
            }
            else return false;
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    /** Comparación de la recta con otro objeto geométrico. Una recta es distinta a
     * otro objeto geométrico si dicho objeto geométrico no es una recta
     * o si no se cumple lo siguiente:
     * <ul><li> El punto base de una recta está contenido en la otra</li>
     * <li> La dirección de ambos vectores directores es la misma <li></ul>
     * @param obj objeto geométrico con el que se desea comparar la recta
     * @return un valor booleano indicando si el objeto geométrico pasado como
     * parámetro es distinto a la recta
     * @see Recta2D#equals(ObjetoGeometrico)
     */
    public boolean not_equals (ObjetoGeometrico obj)
    {
        return !this.equals(obj);
    }

    /** Obtiene el resultado de la intersección de la recta con otra recta. Para ello
     * se resuelve el sistema de dos ecuaciones con dos incógnitas a partir de
     * las ecuaciones paramétricas de ambas rectas. Si dicho sistema no tiene solución,
     * se devolverá null, si tiene solución única un punto y si tiene infinitas soluciones
     * es que las rectas son coincidentes y por tanto se devolverá una recta
     * @param otro recta con la que se desea obtener la intersección
     * @return null si las rectas no intersectan, un objeto de tipo Punto2D
     * si intersectan en un punto, o un objeto de tipo Recta2D (la propia recta)
     * si ambas rectas son coincidentes
     * @see Vector2D#despX()
     * @see Vector2D#despY()
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Recta2D#punto(double)
     * @see Recta2D#director()
     * @see Mat#absoluto(double)
     * @see Mat#EPSILON
     */
    public ObjetoGeometrico interseccionRecta (Recta2D otro)
    {
        double coefT, indep, mult;
        
        // Resolvemos un sistema de dos ecuaciones con dos incógnitas a partir
        // de las ecuaciones paramétricas de las rectas
        if (Mat.absoluto(this.director().despX()) > Mat.EPSILON) {
            mult = (this.director().despY() / this.director().despX());
            coefT = -otro.director().despY() + otro.director().despX()*mult;
            indep = (otro.punto(0).y() - this.punto(0).y()) - mult*(otro.punto(0).x() - this.punto(0).x());
        } else if (Mat.absoluto(this.director().despY()) > Mat.EPSILON) {
            mult = (this.director().despX() / this.director().despY());
            coefT = -otro.director().despX() + otro.director().despY()*mult;
            indep = (otro.punto(0).x() - this.punto(0).x()) - mult*(otro.punto(0).y() - this.punto(0).y());
        } else {
            // Caso degenerado. La recta está formada por un único punto
            try {
                if (otro.contienePunto(this.punto(0)))
                    return this.punto(0);
                else
                    return null;    
            } catch (GeomException e) {
                // Las dos rectas son degeneradas
                if (this.punto(0).equals(otro.punto(0))) return this.punto(0); else return null;
            }
        }
            
        if (coefT == 0)
            if (indep == 0)
                //El sistema tiene infinitas soluciones
                return otro;
            else
                // El sistema no tiene solución
                return null;
         else
            // El sistema tiene solución única
            return otro.punto(indep/coefT);
    }

    /** Obtiene el resultado de la intersección de la recta con un rayo. Para ello
     * se resuelve el sistema de dos ecuaciones con dos incógnitas a partir de
     * las ecuaciones paramétricas del rayo y la recta. Si dicho sistema no tiene solución,
     * se devolverá null, si tiene solución única un punto (si ese punto está contenido
     * en el rayo, en otro caso devolverá null) y si tiene infinitas soluciones
     * se devolverá el mismo rayo
     * @param otro rayo con el que se desea obtener la intersección
     * @return null si la recta y el rayo no intersectan, un objeto de tipo Punto2D
     * si intersectan en un punto, o un objeto de tipo Rayo2D (el propio rayo)
     * si las soluciones del sistema son infinitas
     * @see Recta2D#Recta2D(Rayo2D)
     * @see Recta2D#interseccionRecta(Recta2D)
     * @see Rayo2D#contienePunto(Punto2D)
     */

    public ObjetoGeometrico interseccionRayo(Rayo2D otro)
    {
        ObjetoGeometrico result = this.interseccionRecta(new Recta2D(otro));

        if (result instanceof Recta2D)
        return otro;
        else if (result instanceof Punto2D)
        if (otro.contienePunto((Punto2D) result))
        return result;
        else
        return null;
        else
        return result;
    }

    /** Obtiene el resultado de la intersección de la recta con un segmento. Para ello
     * se resuelve el sistema de dos ecuaciones con dos incógnitas a partir de
     * las ecuaciones paramétricas de la recta y la recta obtenida a partir del
     * segmento. Si dicho sistema no tiene solución,
     * se devolverá null, si tiene solución única un punto (si ese punto está contenido
     * en el segmento, en otro caso devolverá null) y si tiene infinitas soluciones
     * se devolverá el mismo segmento
     * @param otro segmento con el que se desea obtener la intersección
     * @return null si la recta y el segmento no intersectan, un objeto de tipo Punto2D
     * si intersectan en un punto, o un objeto de tipo Rayo2D (el propio segmento)
     * si las soluciones del sistema son infinitas
     * @see Recta2D#Recta2D(Segmento2D)
     * @see Recta2D#interseccionRecta(Recta2D)
     * @see Segmento2D#contienePunto(Punto2D)
     */
    public ObjetoGeometrico interseccionSegmento(Segmento2D otro)
    {
        ObjetoGeometrico result = this.interseccionRecta(new Recta2D(otro));

        if (result instanceof Recta2D)
        return otro;
        else if (result instanceof Punto2D)
        if (otro.contienePunto((Punto2D) result))
        return result;
        else
        return null;
        else
        return result;
    }

    /** Obtiene la distancia de la recta a un punto, así como el punto de la recta
     * más cercano a dicho punto. Esta distancia será la que
     * haya entre el punto y el punto de corte entre la recta y su recta perpendicular
     * que pase por dicho punto
     * @param punto punto al cual se desea obtener la distancia
     * @param cercano punto del segmento más cercano al punto pasado como primer
     * parámetro. Es un parámetro de SALIDA, y debe ser un punto ya creado
     * @return la distancia de la recta al punto
     * @exception GeomException si el punto pasado como segundo parámetro no es un punto
     * ya creado
     * @see Recta2D#perpendicular(int)
     * @see Recta2D#interseccionRecta(Recta2D)
     * @see Punto2D#distancia(Punto2D)
     * @see Punto2D#vectorDiferencia(Punto2D)
     */
    public double puntoMasCercano (Punto2D punto, Punto2D cercano) throws GeomException {
        Vector2D diferencia = new Vector2D();

        if (cercano == null)
            throw new GeomException("puntoMasCercano (Punto2D, Punto2D): el segundo parámetro debe estar inicializado");
        
        try {
            Recta2D rectaPerpendicular = new Recta2D(this);
            rectaPerpendicular = rectaPerpendicular.perpendicular(Mat.HORARIO);
            Recta2D recta = new Recta2D(punto, rectaPerpendicular.director());
            ObjetoGeometrico corte = recta.interseccionRecta(this);
            // La distancia será igual a la distancia del punto al punto
            // de corte de la recta con su perpendicular
            Punto2D puntoCorte = (Punto2D) corte;
            diferencia = puntoCorte.vectorDiferencia(cercano);
            cercano.trasladar(diferencia);
            return punto.distancia(puntoCorte);
        } catch (GeomException e) {
            return -1; // No se va a producir nunca
        }
    }
    
   /** Obtiene la distancia de la recta a un punto
     * @param punto punto para el cual se desea obtener la distancia
     * @return la distancia de la recta al punto
     * @see Recta2D#puntoMasCercano(Punto2D, Punto2D)
     */
    public double distancia(Punto2D punto) {
        try  {
            return puntoMasCercano(punto, new Punto2D());
        } catch (GeomException e) {
            return java.lang.Double.MAX_VALUE; // Nunca se va a producir
        }
    }


    /** Obtiene la coordenada cartesiana x de un punto de la recta a partir de su
     * coordenada y.
     * @param y coordenada cartesiana y
     * @return coordenada cartesiana x del punto de la recta correspondiente a
     * la coordenada y pasada como parámetro
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Vector2D#despX()
     * @see Vector2D#despY()
     */
    public double xEnY(double y) {
        double t = (y - comienzo.y()) / director.despY();
        return comienzo.x() + t*director.despX();
    }

    /** Obtiene la coordenada cartesiana y de un punto de la recta a partir de su
     * coordenada x.
     * @param x coordenada cartesiana x
     * @return coordenada cartesiana y del punto de la recta correspondiente a
     * la coordenada x pasada como parámetro
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Vector2D#despX()
     * @see Vector2D#despY()
     */
    public double yEnX (double x) {
        double t = (x - comienzo.x()) / director.despX();
        return comienzo.y() + t*director.despY();
    }

    /** Determina si el punto pasado como parámetro está contenido en la recta.
     * @param punto punto que deseamos comprobar si está contenido en la recta
     * @return un valor booleano indicando si el punto está contenido en la recta
     * @exception GeomException si la recta se trata de una recta degenerada
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Recta2D#punto(double)
     * @see Mat#COLINEAL
     * @see Recta2D#degenerada()
     */
    public boolean contienePunto(Punto2D punto) throws GeomException {
        if (degenerada())
        throw new GeomException("contienePunto (Punto2D): la recta es una recta degenerada");
        else
        return (punto.posicionRelativa(comienzo, punto(1)) == Mat.COLINEAL);
    }

    /** Determina si el punto pasado como parámetro se encuentra a la izquierda de
     * la recta
     * @param punto punto que se desea conocer si se encuentra a la izquierda de la
     * recta
     * @return un valor booleano indicando si el punto se encuentra a la izquierda de
     * la recta
     * @exception GeomException si la recta se trata de una recta degenerada
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Recta2D#punto(double)
     * @see Mat#IZQUIERDA
     * @see Recta2D#degenerada()
     */
    public boolean puntoAIzquierda(Punto2D punto) throws GeomException {
        if (degenerada())
        throw new GeomException("puntoAIzquierda (Punto2D): la recta es una recta degenerada");
        else
        return (punto.posicionRelativa(comienzo, punto(1)) == Mat.IZQUIERDA);
    }

    /** Determina si el punto pasado como parámetro se encuentra a la derecha de
     * la recta
     * @param punto punto que se desea conocer si se encuentra a la derecha de la
     * recta
     * @return un valor booleano indicando si el punto se encuentra a la derecha de
     * la recta
     * @exception GeomException si la recta se trata de una recta degenerada
     * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
     * @see Recta2D#punto(double)
     * @see Mat#DERECHA
     * @see Recta2D#degenerada()
     */
    public boolean puntoADerecha(Punto2D punto) throws GeomException {
        if (degenerada())
        throw new GeomException("puntoADerecha (Punto2D): la recta es una recta degenerada");
        else
        return (punto.posicionRelativa(comienzo, punto(1)) == Mat.DERECHA);
    }

    /** Obtiene el punto de la recta correspondiente a un determinado parámetro.
     * @param t valor por el que sustituir el parámetro en la ecuación paramétrica
     * de la recta
     * @return el punto de la recta obtenido tras sustituir el valor pasado como
     * parámetro en el parámetro de la ecuación paramétrica de la recta
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Vector2D#despX()
     * @see Vector2D#despY()
     */
    public Punto2D punto(double t) {
        double x, y;

        x = comienzo.x() + t*director.despX();
        y = comienzo.y() + t*director.despY();

        return new Punto2D(x, y);
    }

    /** Obtiene la posición de un punto respecto a la recta. Si la dirección entre
     * el punto base de la ecuación paramétrica de la recta y el punto, y la dirección
     * correspondiente al vector director de la recta son iguales (en el mismo sentido
     * o en sentido contrario) el punto estará contenido, en otro caso calculamos la
     * orientación a partir del segmento formado por el punto base de la recta y
     * el punto para el que t=1
     * @param punto punto del que se desea conocer la posición respecto de la recta
     * @return el valor Mat.CONTENIDO, Mat.IZQUIERDA o Mat.DERECHA dependiendo
     * de la posición del punto respecto de la recta
     * @see Vector2D#Vector2D(Punto2D, Punto2D)
     * @see Vector2D#direccion()
     * @see Direccion2D#equals(ObjetoGeometrico)
     * @see Mat#CONTENIDO
     * @see Mat#IZQUIERDA
     * @see Mat#DERECHA
     * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
     * @see Segmento2D#puntoAIzquierda(Punto2D)
     */
    public int posicionPunto(Punto2D punto) {
        Vector2D v1 = new Vector2D(comienzo, punto);

        try {
            if (v1.direccion().equals(director.direccion())
            || v1.direccion().equals(director.direccion().opuesto()))
            return Mat.CONTENIDO;
            else
            {
                Segmento2D seg = new Segmento2D(this.punto(0), this.punto(1));
                if (seg.puntoAIzquierda(punto))
                return Mat.IZQUIERDA;
                else
                return Mat.DERECHA;
            }
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    /** Obtiene la proyección ortogonal de un punto sobre la recta. La proyección
     * ortogonal se obtiene como la intersección de la recta con su recta
     * perpendicular que pasa por dicho punto
     * @param punto punto del que se desea obtener la proyección ortogonal
     * @return la proyección ortogonal del punto sobre la recta
     * @see Recta2D#posicionPunto(Punto2D)
     * @see Vector2D#perpendicular(int)
     * @see Recta2D#interseccionRecta(Recta2D)
     */
    public Punto2D proyeccionOrtogonal(Punto2D punto)  {
        try {
            if (posicionPunto(punto) == Mat.CONTENIDO)
            return punto;
            else
            {
                Vector2D vector = new Vector2D(this.director()).perpendicular(Mat.ANTIHORARIO);
                Recta2D rectaPerp = new Recta2D(punto, vector);
                return (Punto2D) rectaPerp.interseccionRecta(this);
            }
        } catch (GeomException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /** Comprueba si la recta es horizontal. Para que esto suceda el desplazamiento
     * en el eje y del vector director debe ser cero.
     * @return un valor booleano indicando si la recta es horizontal
     * @see Mat#absoluto(double)
     * @see Vector2D#despY()
     * @see Mat#EPSILON
     */
    public boolean esHorizontal() {
        if (Mat.absoluto(director.despY()) < Mat.EPSILON) return true;
        else return false;
    }

    /** Comprueba si la recta es vertical. Para que esto suceda el desplazamiento
     * en el eje x del vector director debe ser cero.
     * @return un valor booleano indicando si la recta es vertical
     * @see Mat#absoluto(double)
     * @see Vector2D#despX()
     * @see Mat#EPSILON
     */
    public boolean esVertical() {
        if (Mat.absoluto(director.despX()) < Mat.EPSILON) return true;
        else return false;
    }

    /** Obtiene la recta opuesta. Dos rectas son opuestas si sus vectores directores
     * son a su vez opuestos y pasan por los mismos puntos.
     * @return la recta opuesta
     * @see Vector2D#opuesto()
     */
    public Recta2D opuesta() {
        director.opuesto();

        return this;
    }

    /** Obtención de la recta perpendicular. Dos rectas son perpendiculares si sus vectores
     * directores lo son. El giro de la recta par obtener la perpendicular puede ser
     * en sentido horario o en antihorario
     * @param orientacion sentido de giro para obtener la recta perpendicular. Debe
     * valer Mat.HORARIO o Mat.ANTIHORARIO
     * @return la recta perpendicular
     * @exception GeomException si el valor de orientación pasado como parámetro es
     * incorrecto
     * @see Mat#HORARIO
     * @see Mat#ANTIHORARIO
     * @see Vector2D#perpendicular(int)
     */
    public Recta2D perpendicular(int orientacion) throws GeomException {
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
        throw new GeomException("perpendicular (int): la orientación es incorrecta");
        else
        {
            director.perpendicular(orientacion);
            return this;
        }
    }

    /** Obtención de la recta perpendicular que pase por un determinado punto.
     * Dos rectas son perpendiculares si sus vectores directores lo son. El giro
     * de la recta par obtener la perpendicular puede ser en sentido horario o en
     * antihorario. Para que la recta pase pro el punto especificado, dicho punto
     * será el punto de comienzo en su ecuación paramétrica
     * @param orientacion sentido de giro para obtener la recta perpendicular. Debe
     * valer Mat.HORARIO o Mat.ANTIHORARIO
     * @param punto punto por el que se desea que pase la recta
     * @return la recta perpendicular que pasa por el punto especificado
     * @exception GeomException si el valor de orientación pasado como parámetro es
     * incorrecto
     * @see Mat#HORARIO
     * @see Mat#ANTIHORARIO
     * @see Vector2D#perpendicular(int)
     * @see Punto2D#Punto2D(Punto2D)
     */
    public Recta2D perpendicularPunto(int orientacion, Punto2D punto) throws GeomException {
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
        throw new GeomException("perpendicular (int): la orientación es incorrecta");
        else
        {
            director.perpendicular(orientacion);
            comienzo = new Punto2D(punto);
            return this;
        }
    }

    /** Obtiene el valor por el que se debe sustituir el parámetro de la ecuación
     * paramétrica de la recta para que el resultado sea un determinado punto.
     * El punto se obtiene a partir de sus coordenadas cartesianas.
     * @param x coordenada cartesiana x del punto
     * @param y coordenada cartesiana y del punto
     * @return el valor del parámetro que al aplicarse a la ecuación parámetrica
     * hace que se obtenga el punto cuyas coordendas cartesianas se han pasado
     * como parámetro
     * @exception GeomException si el punto no pertenece a la recta
     * @see Punto2D#Punto2D(double, double)
     * @see Recta2D#parametroPunto(Punto2D)
     */
    public double parametroPunto(double x, double y) throws GeomException {
        return parametroPunto(new Punto2D(x,y));
    }

    /** Obtiene el valor por el que se debe sustituir el parámetro de la ecuación
     * paramétrica de la recta para que el resultado sea un determinado punto
     * @param punto el punto de la recta
     * @return el valor del parámetro que al aplicarse a la ecuación paramétrica
     * hace que se obtenga el punto pasado como parámetro.
     * @exception GeomException si el punto no pertenece a la recta
     * @see Punto2D#x()
     * @see Vector2D#despX()
     * @see Mat#CONTENIDO
     */
    public double parametroPunto(Punto2D punto) throws GeomException {
        if (posicionPunto(punto) != Mat.CONTENIDO)
        throw new GeomException("parametroPunto (Punto2D): El punto no pertence a la recta");
        else
        return (punto.x() - comienzo.x()) / director.despX();
    }
    /** Traslada la recta en el plano a partir de un determinado incremento en
     * cada coordenada cartesiana. La traslación se aplicará al punto base de la
     * ecuación paramétrica
     * @param incX incremento o desplazamiento en el eje x
     * @param incY incremento o desplazamiento en el eje y
     * @return la recta trasladada
     * @see Punto2D#trasladar(double, double)
     */
    public Recta2D trasladar (double incX, double incY) {
        comienzo.trasladar(incX, incY);
        return this;
    }

    /** Traslada la recta en el plano aplicando el mismo incremento en cada
     * eje. La traslación se aplicará al punto base de la ecuación paramétrica.
     * @param incr incremento o desplazamiento que será aplicado tanto al eje x
     * como al eje y
     * @return la recta trasladada
     * @see Punto2D#trasladar(double)
     */
    public Recta2D trasladar (double incr) {
        comienzo.trasladar(incr);
        return this;
    }

    /** Traslada la recta en la dirección y a la distancia determinados por un
     * vector. Se aplicará la traslación al punto base de la ecuación paramétrica
     * @param vector el vector que define la traslación de la recta
     * @return la recta trasladada
     * @see Punto2D#trasladar(Vector2D)
     */
    public Recta2D trasladar (Vector2D vector)
    {
        comienzo.trasladar(vector);
        return this;
    }

    /** Traslación de la recta a partir de las coordenadas polares (se traslada
     * el punto base de la ecuación paramétrica de la recta una determinada
     * distancia en un determinado ángulo)
     * @param angulo dirección de traslación de la recta
     * @param dist distancia a la que se moverá la recta de su posición original
     * siguiendo el ángulo indicado
     * @return la recta trasladada
     * @see Punto2D#trasladarPolar(double, double)
     */
    public Recta2D trasladarPolar (double angulo, double dist) {
        comienzo.trasladarPolar(angulo, dist);
        return this;
    }

    /** Escalado de una recta. Se aplicará el escalado al punto base de la ecuación
     * paramétrica de la recta y al vector director.
     * @param escala factor de escalado
     * @return la recta escalada
     * @exception GeomException si el factor de escalado es cero
     * @see Punto2D#escalado(double)
     * @see Vector2D#escalado(double)
     */
    public Recta2D escalado(double escala) throws GeomException{
        comienzo.escalado(escala);
        director.escalado(escala);
        return this;
    }

    /** Escalado de una recta con distinto factor de escalado para cada coordenada.
     * Se aplicará el escalado al punto base de la ecuación paramétrica y al
     * vector director.
     * @param sx factor de escalado en el eje x
     * @param sy factor de escalado en el eje y
     * @return la recta escalada
     * @exception GeomException si alguno de los factores de escalado es cero
     * @see Punto2D#escalado(double, double)
     * @see Vector2D#escalado(double, double)
     */
    public Recta2D escalado(double sx, double sy) throws GeomException {
        comienzo.escalado(sx, sy);
        director.escalado(sx, sy);
        return this;
    }

    /** Rota la recta respecto del origen de coordenadas
     * @param radio angulo de giro en radianes. Si es positivo el giro será
     * en sentido antihorario y en caso contrario lo será en sentido horario
     * @return la recta girada según el ángulo pasado como parámetro
     * @see Punto2D#gira(double)
     */
    public Recta2D gira(double radio) {
        comienzo.gira(radio);
        director.gira(radio);
        return this;
    }

    /** Rota la recta respecto un determinado punto
     * @param origen punto respecto al cual rotar la recta
     * @param radio angulo de giro en radianes. Si es positivo el giro será
     * en sentido antihorario y en caso contrario lo será en sentido horario
     * @return la recta girada según el ángulo pasado como parámetro
     * @see Punto2D#gira(Punto2D, double)
     */
    public Recta2D gira(Punto2D origen, double radio) {
        comienzo.gira(origen, radio);
        director.gira(radio);
        return this;
    }
    
    /** Obtiene el vector director de la recta.
     * @return el vector director de la recta
     */
    public Vector2D director()
    {
        return director;
    }

    /** Obtiene la dirección asociada al vector director de la recta.
     * @return dirección obtenida a partir del vector director de la recta
     * @see Vector2D#direccion()
     */
    public Direccion2D direccion()
    {
        return director.direccion();
    }

    /** Obtiene el primer componente cartesiano (a) de la recta. Si a es distinto
     * de cero se devolverá una de las posibles infinitas soluciones, tomando b
     * como uno
     * @return el primer componente cartesiano de la recta
     * @see Punto2D#Punto2D(Punto2D)
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Mat#EPSILON
     * @see Mat#absoluto(double)
     */
    public double a() {
        Punto2D p1 = new Punto2D(punto(0));
        Punto2D p2 = new Punto2D(punto(1));

        if (Mat.absoluto(p2.x() - p1.x()) < Mat.EPSILON)
        // La pendiente es infinita
        return Double.MAX_VALUE;
        else
        if (Mat.absoluto(p2.y() - p1.y()) < Mat.EPSILON)
        return 0;
        else
        return -(p2.y()-p1.y())/(p2.x()-p1.x());
    }

    /** Obtiene el segundo componente cartesiano (b) de la recta. Si b es distinto
     * de cero se devolverá una de las posibles infinitas soluciones, según el valor
     * tomado para a
     * @return el segundo componente cartesiano de la recta
     * @see Punto2D#Punto2D(Punto2D)
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Recta2D#a()
     * @see Mat#EPSILON
     * @see Mat#absoluto(double)
     */
    public double b() {
        double a = a();
        Punto2D p1 = new Punto2D(punto(0));
        Punto2D p2 = new Punto2D(punto(1));
        
        if (a == Double.MAX_VALUE || Mat.absoluto(p2.y() - p1.y()) < Mat.EPSILON)
        return Double.MAX_VALUE;
        else
            return -(a*(p2.x()-p1.x()))/(p2.y()-p1.y());
    }

    /** Obtiene el tercer componente cartesiano (c) de la recta. Si c es distinto
     * de cero se devolverá una de las posibles infinitas soluciones, según los
     * valores tomados de a y b
     * @return el tercer componente cartesiano de la recta
     * @see Punto2D#Punto2D(Punto2D)
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Recta2D#a()
     * @see Recta2D#b()
     */
    public double c() {
        double a = a();
        double b = b();
        Punto2D p = new Punto2D(punto(0));

        if (a == Double.MAX_VALUE || b == Double.MAX_VALUE)
        return Double.MAX_VALUE;
        else
        return -(a*p.x() + b*p.y());
    }

    /** Obtiene el componente Px0 (coordenada x del punto origen) de la ecuación
     * paramétrica de la recta
     * @return la coordenada x del punto origen de la recta
     * @see Punto2D#x()
     */
    public double comienzoX() {
        return comienzo.x();
    }

    /** Obtiene el componente Py0 (coordenada y del punto origen) de la ecuación
     * paramétrica de la recta
     * @return la coordenada y del punto origen de la recta
     * @see Punto2D#y()
     */
    public double comienzoY() {
        return comienzo.y();
    }

    /** Obtiene el componente Dx (desplazamiento en el eje x del vector director)
     * de la ecuación paramétrica de la recta
     * @return el desplazamiento en el eje x del vector director de la recta
     * @see Vector2D#despX()
     */
    public double directorX() {
        return director.despX();
    }

    /** Obtiene el componente Dy (desplazamiento en el eje y del vector director)
     * de la ecuación paramétrica de la recta
     * @return el desplazamiento en el eje y del vector director de la recta
     * @see Vector2D#despY()
     */
    public double directorY() {
        return director.despY();
    }

    /** Determina si la recta es degenerada. Para ello, el vector director tiene
     * que ser nulo (o lo que es lo mismo, los coeficientes a y b de la ecuación
     * cartesiana de la recta ax + by + c = 0 son iguales a cero)
     * @return un valor booleano indicando si la recta es degenerada
     * @see Vector2D#vectorNulo()
     */
    public boolean degenerada() {
        return director.vectorNulo();
    }

    /** Realiza la conversión del objeto de tipo Recta2D a una cadena
     * @return una cadena especificando el tipo de objeto (recta) y sus
     * componentes (punto base y vector director de la ecuación paramétrica)
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Vector2D#despX()
     * @see Vector2D#despY()
     */
    public String toString() {
        return nombre + " - Recta2D :( (" + comienzo.x() + "," + comienzo.y() + ") + t*(" + director.despX() + "," + director.despY() + ") )";
    }

    private void dibujo(Graphics g, int anchura, int altura , Color color) {
        try {
            ObjetoGeometrico p[] = new ObjetoGeometrico[4];
            // Determinamos con cual de los cuatro bordes del canvas intersecciona
            // el rayo
            p[0] = interseccionSegmento(new Segmento2D(0, 0, 0, -(altura+1)));
            p[1] = interseccionSegmento(new Segmento2D(0, 0, anchura+1, 0));
            p[2] = interseccionSegmento(new Segmento2D(anchura+1, -(altura+1), anchura+1, 0));
            p[3] = interseccionSegmento(new Segmento2D(anchura+1, -(altura+1), 0, -(altura+1)));
            int pos1 = -1, pos2 = -1;
            for (int i=0; i<4; i++)
            {
                if (p[i] instanceof Segmento2D)
                {
                    pos1 = i; pos2 = i;
                }
                else
                if (pos1 == -1 && p[i] != null)
                pos1 = i;
                else
                if (pos2 == -1 && p[i] != null)
                pos2 = i;

            }

            // Determinamos el punto de destino de la línea a dibujar para representar
            // el rayo (el comienzo será el propio comienzo del rayo)
            Punto2D punto;
            if (pos1 != -1 && pos2 != -1)
            {
                g.setColor(color);
                if (p[pos1] instanceof Segmento2D)
                {
                    Segmento2D seg = (Segmento2D) p[pos1];
                    g.drawLine((int)seg.comienzo().x(), -(int)seg.comienzo().y(), (int)seg.fin().x(), -(int)seg.fin().y());
                }
                else
                {
                    Punto2D p1 = (Punto2D) p[pos1];
                    Punto2D p2 = (Punto2D) p[pos2];
                    g.drawLine((int)p1.x(), -(int)p1.y(), (int)p2.x(), -(int)p2.y());
                }
            }
        } catch (GeomException e) {
            System.out.println(e);
        }
    }
    
    public void dibujar(Graphics g, int anchura, int altura) {
        dibujo(g, anchura, altura, Color.black);
    }
    
    public void dibujarSeleccionado(Graphics g, int anchura, int altura) {
        dibujo(g, anchura, altura, Color.green);
    }
    
    public void dibujarResultado(Graphics g, int anchura, int altura) {
        dibujo(g, anchura, altura, Color.red);
    }

    public void dibujarNombre(Graphics g)
    {
        g.setColor(Color.magenta);
        g.drawString(nombre, (int)comienzo.x() - 12, -(int)comienzo.y());
    }
}
