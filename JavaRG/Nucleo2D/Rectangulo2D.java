/*
 * Rectangulo2D.java
 *
 * Created on 12 de diciembre de 2001, 9:07
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;
import java.awt.*;

/** Un objeto de la clase <code>Rectangulo2D</code> representa un rectángulo en 
 * el plano 2D, con sus lados paralelos al eje x y al eje y de coordenadas. Se
 * representa mediante dos vértices, el inferior izquierdo y el superior derecho
 *
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico
 */
public class Rectangulo2D extends ObjetoGeometrico {

    /**
     * Esquina inferior izquierda del rectángulo
     */
    Punto2D inferiorIzquierda;
    
    /** 
     * Esquina superior derecha del rectángulo
     */
    Punto2D superiorDerecha;

    /**
     * Constructor por defecto del rectángulo, cuyos extremos estarán todos 
     * situados en el origen de coordenadas
     * @return un rectángulo nulo
     * @see Punto2D#Punto2D()
     */
    public Rectangulo2D()
    {
        inferiorIzquierda = new Punto2D();
        superiorDerecha = new Punto2D();
    }

  /** Constructor del rectángulo a partir de dos esquinas opuestas
    * @param punto1 primera esquina
    * @param punto2 segunda esquina
    * @return un nuevo rectángulo cuyos extremos inferior izquierdo y superior derecho
    * son obtenidos a partir de los dos puntos pasados como parámetro
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public Rectangulo2D(Punto2D punto1, Punto2D punto2) {
        double inferiorx, inferiory, superiorx, superiory;
        
        if (punto1.x() < punto2.x())
        {
            inferiorx = punto1.x();
            superiorx = punto2.x();
        }
        else
        {   
            inferiorx = punto2.x();
            superiorx = punto1.x();
        }
        
        if (punto1.y() < punto2.y())
        {
            inferiory = punto1.y();
            superiory = punto2.y();
        }
        else
        {
            inferiory = punto2.y();
            superiory = punto1.y();
        }
        
        inferiorIzquierda = new Punto2D(inferiorx, inferiory);
        superiorDerecha = new Punto2D(superiorx, superiory);
    }
    
  /** Constructor del rectángulo a partir de las coordenadas cartesianas de 
    * dos esquinas opuestas
    * @param p1x coordenada cartesiana x de la primera esquina
    * @param p1y coordenada cartesiana y de la primera esquina
    * @param p2x coordenada cartesiana x de la segunda esquina
    * @param p2y coordenada cartesiana y de la segunda esquina
    * @return un nuevo rectángulo cuyas esquinas inferior izquierda y superior
    * derecha son obtenidas a partir de los dos puntos cuyas coordenadas 
    * cartesianas se pasan como parámetro
    * @see Punto2D#Punto2D(double, double)
    */
    public Rectangulo2D(double p1x, double p1y, double p2x, double p2y) {
        if (p1x < p2x)
        {
            if (p1y < p2y)
            {
                inferiorIzquierda = new Punto2D(p1x, p1y);
                superiorDerecha = new Punto2D(p2x, p2y);
            }
            else
            {
                inferiorIzquierda = new Punto2D(p1x, p2y);
                superiorDerecha = new Punto2D(p2x, p1y);
            }
        }
        else
        {
            if (p1y < p2y)
            {
                inferiorIzquierda = new Punto2D(p2x, p1y);
                superiorDerecha = new Punto2D(p1x, p2y);
            }
            else
            {
                inferiorIzquierda = new Punto2D(p2x, p2y);
                superiorDerecha = new Punto2D(p1x, p1y);
            }
        }
    }

  /** Constructor del rectángulo a partir de las coordenadas homogéneas de 
    * dos esquinas opuestas
    * @param p1x primera coordenada homogénea de la primera esquina
    * @param p1y segunda coordenada homogénea de la primera esquina
    * @param p1z tercera coordenada homogénea de la primera esquina
    * @param p2x primera coordenada homogénea de la segunda esquina
    * @param p2y segunda coordenada homogénea de la segunda esquina
    * @param p2z tercera coordenada homogénea de la segunda esquina
    * @return un nuevo rectángulo cuyas esquinas inferior izquierda y superior
    * derecha son obtenidas a partir de los dos puntos cuyas coordenadas 
    * homogéneas se pasan como parámetro
    * @see Punto2D#Punto2D(double, double)
    */
    public Rectangulo2D(double p1x, double p1y, double p1z, double p2x, double p2y, double p2z) {
        double p1hx = p1x/p1z;
        double p1hy = p1y/p1z;
        double p2hx = p2x/p2z;
        double p2hy = p2y/p2z;
        
        if (p1hx < p2hx)
        {
            if (p1hy < p2hy)
            {
                inferiorIzquierda = new Punto2D(p1hx, p1hy);
                superiorDerecha = new Punto2D(p2hx, p2hy);
            }
            else
            {
                inferiorIzquierda = new Punto2D(p1hx, p2hy);
                superiorDerecha = new Punto2D(p2hx, p1hy);
            }
        }
        else
        {
            if (p1hy < p2hy)
            {
                inferiorIzquierda = new Punto2D(p2hx, p1hy);
                superiorDerecha = new Punto2D(p1hx, p2hy);
            }
            else
            {
                inferiorIzquierda = new Punto2D(p2hx, p2hy);
                superiorDerecha = new Punto2D(p1hx, p1hy);
            }
        }
    }
        
    
  /** Constructor del rectángulo
    * @param original rectángulo a partir del cual se desea obtener el nuevo
    * @return un nuevo rectángulo con todos sus atributos iguales a los
    * del rectángulo original
    * 
    * @see Punto2D#Punto2D(double, double)
    * @see Rectangulo2D#xmin()
    * @see Rectangulo2D#xmax()
    * @see Rectangulo2D#ymin()
    * @see Rectangulo2D#ymax()
    */
    public Rectangulo2D(Rectangulo2D original) {
        this.inferiorIzquierda = new Punto2D(original.xmin(), original.ymin());
        this.superiorDerecha = new Punto2D(original.xmax(), original.ymax());
    }
    
   /** Comparación con otro objeto geométrico. Un rectángulo será igual a un objeto
     * geométrico si dicho objeto geométrico es un rectángulo y ambos  tienen
     * los mismos extremos
     * @param obj el objeto geométrico con el que se desea comparar el rectángulo
     * @return un valor booleano indicando si el rectángulo es igual al objeto
     * geométrico pasado como parámetro
     * @see Rectangulo2D#minimoX()
     * @see Rectangulo2D#maximoX()
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Rectangulo2D) {
            Rectangulo2D rect = (Rectangulo2D) obj;
            if ((this.minimoX()).equals(rect.minimoX()) &&
                (this.maximoX()).equals(rect.maximoX()))
                return true;
            else
                return false;
        }
        else return false;
    }
   
   /** Comparación con otro objeto geométrico. Un rectángulo será distinta a un objeto
     * geométrico si dicho objeto geométrico no es un rectángulo o si ambos tienen
     * distintos extremos
     * @param obj el objeto geométrico con el que se desea comparar el rectángulo
     * @return un valor booleano indicando si el rectángulo es distinto al objeto
     * geométrico pasado como parámetro
     * @see Rectangulo2D#equals(ObjetoGeometrico)
     */
    public boolean not_equals (ObjetoGeometrico obj) {
        return !this.equals(obj);
    }
    
  /** Obtiene la coordenada cartesiana x del punto inferior izquierdo
    * @return la coordenada cartesiana x del punto infreior izquierdo
    * @see Punto2D#x()
    */
    public double xmin() {
        return inferiorIzquierda.x();
    }
    
  /** Obtiene la coordenada cartesiana y del punto inferior izquierdo
    * @return la coordeanda cartesiana y del punto inferior izquierdo
    * @see Punto2D#y()
    */
    public double ymin() {
        return inferiorIzquierda.y();
    }
    
  /** Obtiene la coordenada cartesiana x del punto superior derecho
    * @return la coordenada cartesiana x del punto superior derecho
    * @see Punto2D#x()
    */
    public double xmax() {
        return superiorDerecha.x();
    }
    
  /** Obtiene la coordenada cartesiana y del punto superior derecho
    * @return la coordenada cartesiana y del punto superior derecho
    * @see Punto2D#y()
    */
    public double ymax() {
        return superiorDerecha.y();
    }
    
  /** Obtiene la primera coordenada homogénea del punto inferior izquierdo
    * @return la primera coordenada homogénea del punto inferior izquierdo
    * @see Punto2D#hx()
    */
    public double hxmin() {
        return inferiorIzquierda.hx();
    }
    
  /** Obtiene la segunda coordenada homogénea del punto inferior izquierdo
    * @return la segunda coordenada homogénea del punto inferior izquierdo
    * @see Punto2D#hy()
    */
    public double hymin() {
        return inferiorIzquierda.hy();
    }
    
  /** Obtiene la tercera coordenada homogénea del punto inferior izquierdo
    * @return la tercera coordenada homogénea del punto inferior izquierdo
    * @see Punto2D#hz()
    */
    public double hzmin() {
        return inferiorIzquierda.hz();
    }
    
  /** Obtiene la primera coordenada homogénea del punto superior derecho
    * @return la primera coordenada homogénea del punto superior derecho
    * @see Punto2D#hx()
    */
    public double hxmax() {
        return superiorDerecha.hx();
    }
    
  /** Obtiene la segunda coordenada homogénea del punto superior derecho
    * @return la segunda coordenada homogénea del punto superior derecho
    * @see Punto2D#hy()
    */
    public double hymax() {
        return superiorDerecha.hy();
    }
    
  /** Obtiene la tercera coordenada homogénea del punto superior derecho
    * @return la tercera coordenada homogénea del punto superior derecho
    * @see Punto2D#hz()
    */
    public double hzmax() {
        return superiorDerecha.hz();
    }
  
  /** Obtiene el extremo de la caja de menor coordenada x
    * @return el punto inferior izquierdo de la caja contenedora
    */
    public Punto2D minimoX() {
        return inferiorIzquierda;
    }
    
  /** Obtiene el extremo de la caja de menor coordenada y
    * @return el punto inferior izquierdo de la caja contenedora
    */
    public Punto2D minimoY() {  
        return inferiorIzquierda;
    }
    
  /** Obtiene el extremo de la caja de mayor coordenada x
    * @return el punto superior derecho de la caja contenedora
    */
    public Punto2D maximoX() {
        return superiorDerecha;
    }
    
  /** Obtiene la distancia de un punto al rectángulo, que será la mínima distancia
    * del punto a alguno de sus lados, así como el punto del rectángulo más
    * cercano a dicho punto
    * @param punto punto al que se desea obtener la distancia
    * @param cercano punto del segmento más cercano al punto pasado como primer
    * parámetro. Es un parámetro de SALIDA, y debe ser un punto ya creado    
    * @return la distancia del rectángulo al punto pasado como parámetro
    * @exception GeomException si el punto pasado como segundo parámetro no es un punto
    * ya creado
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#puntoMasCercano(Punto2D, Punto2D)
    * @see Punto2D#vectorDiferencia(Punto2D)
    */
    public double puntoMasCercano(Punto2D punto, Punto2D cercano) throws GeomException {
        
        if (cercano == null)
            throw new GeomException("puntoMasCercano (Punto2D, Punto2D): el segundo parámetro debe estar inicializado");
        
        Punto2D cercanoTemporal = new Punto2D();
        
        Punto2D inferiorDerecha = new Punto2D(superiorDerecha.x(), inferiorIzquierda.y());
        Punto2D superiorIzquierda = new Punto2D(inferiorIzquierda.x(), superiorDerecha.y());
        double distancia = java.lang.Double.POSITIVE_INFINITY;
        
        try {
            Segmento2D segmento1 = new Segmento2D(superiorIzquierda, superiorDerecha);
            distancia = segmento1.puntoMasCercano(punto, cercano);    
        } catch (GeomException e) {
            distancia = superiorIzquierda.distancia(punto);
            Vector2D diferencia = superiorIzquierda.vectorDiferencia(cercano);
            cercano.trasladar(diferencia);
        }
        
        try {
            Segmento2D segmento2 = new Segmento2D(superiorDerecha, inferiorDerecha);
            double dist2 = segmento2.puntoMasCercano(punto, cercanoTemporal);
            if (dist2 < distancia) {
                distancia = dist2;
                Vector2D diferencia = cercanoTemporal.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        } catch (GeomException e) {
            double dist2 = superiorDerecha.distancia(punto);
            if (dist2 < distancia) {
                distancia = dist2;
                Vector2D diferencia = superiorDerecha.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        }
        
        try {
            Segmento2D segmento3 = new Segmento2D(inferiorDerecha, inferiorIzquierda);
            double dist3 = segmento3.puntoMasCercano(punto, cercanoTemporal);
            if (dist3 < distancia) {
                distancia = dist3;
                Vector2D diferencia = cercanoTemporal.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        } catch (GeomException e) {
            double dist3 = inferiorDerecha.distancia(punto);
            if (dist3 < distancia) {
                distancia = dist3;
                Vector2D diferencia = inferiorDerecha.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        }
        
        try {
            Segmento2D segmento4 = new Segmento2D(inferiorIzquierda, superiorIzquierda);
            double dist4 = segmento4.puntoMasCercano(punto, cercanoTemporal);
            if (dist4 < distancia) {
                distancia = dist4;
                Vector2D diferencia = cercanoTemporal.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        } catch (GeomException e) {
            double dist4 = inferiorIzquierda.distancia(punto);
            if (dist4 < distancia) {
                distancia = dist4;
                Vector2D diferencia = inferiorIzquierda.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        }
        
        return distancia;
    }
    
   /** Obtiene la distancia del rectángulo a un punto
     * @param punto punto para el cual se desea obtener la distancia
     * @return la distancia del rectángulo al punto
     * @see Rectangulo2D#puntoMasCercano(Punto2D, Punto2D)
     */
    public double distancia(Punto2D punto) {
        try  {
            return puntoMasCercano(punto, new Punto2D());
        } catch (GeomException e) {
            return java.lang.Double.MAX_VALUE; // Nunca se va a producir
        }
    }
    
  /** Traslada los extremos del rectángulo aplicando el mismo incremento en cada eje
    * @param d desplazamiento o incremento que será aplicado tanto al eje x como 
    * al eje y
    * @return el rectángulo trasladado
    * @see Punto2D#trasladar(double)
    */
    public Rectangulo2D trasladar(double d) {
        inferiorIzquierda.trasladar(d);
        superiorDerecha.trasladar(d);
        return this;
    }
    
  /** Traslada los extremos del rectángulo aplicando un incremento distinto a cada eje
    * @param dx desplazamiento o incremento que será aplicado al eje x
    * @param dy desplazamiento o incremento que será aplicado al eje y
    * @return el rectángulo trasladado
    * @see Punto2D#trasladar(double, double)
    */
    public Rectangulo2D trasladar(double dx, double dy) {
        inferiorIzquierda.trasladar(dx,dy);
        superiorDerecha.trasladar(dx,dy);
        return this;
    }
    
  /** Traslada los extremos del rectángulo en la dirección y distancia indicados por un
    * vector
    * @param vector vector que indicará la dirección y la magnitud del desplazamiento
    * de los extremos
    * @return el rectángulo trasladado
    * @see Punto2D#trasladar(Vector2D)
    */
    public Rectangulo2D trasladar(Vector2D vector) {
        inferiorIzquierda.trasladar(vector);
        superiorDerecha.trasladar(vector);
        return this;
    }

  /** Obtiene el centro de gravedad del rectángulo (su punto central)
    * @return el centro de gravedad del rectángulo
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public Punto2D centroGravedad() {
        double x = (inferiorIzquierda.x() + superiorDerecha.x())/2.0;
        double y = (inferiorIzquierda.y() + superiorDerecha.y())/2.0;
        return new Punto2D(x,y);
    }
    
  /** Escalado del rectángulo, aplicando un factor de escalado distinto 
    * a cada eje
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return el rectángulo trasladado
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Punto2D#trasladar(double, double)
    */
    public Rectangulo2D escalado(double sx, double sy) {
        double distX = superiorDerecha.x() - inferiorIzquierda.x();
        double distY = superiorDerecha.y() - inferiorIzquierda.y();
        double desplazX = ((distX * sx) - distX)/2;
        double desplazY = ((distY * sy) - distY)/2;
        inferiorIzquierda.trasladar(-desplazX, -desplazY);
        superiorDerecha.trasladar(desplazX, desplazY);
        
        return this;
    }
    
  /** Escalado del rectángulo, aplicando el mismo factor de escalado a
    * ambos ejes
    * @param s factor de escalado que se aplicará a ambos ejes
    * @return el rectángulo escalado
    * @see Caja2D#escalado(double)
    */
    public Rectangulo2D escalado (double s) {
        return this.escalado(s, s);
    }
    
  /** Obtiene el extremo de la caja de mayor coordenada y
    * @return el punto superior derecho de la caja contenedora
    */
    public Punto2D maximoY() {
        return superiorDerecha;
    }
    
  /** Obtiene el vértice indicado como parámetro, tomándose los vértices del
    * rectángulo en sentido horario desde el superior derecho
    * @param i indice del vértice del rectángulo que se desea obtener
    * @return el vértice del rectángulo cuyo indice se pasó como parámetro
    * @exception GeomException si el índice pasado como parámetro no se encuentra
    * en el rango [0-3]
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public Punto2D vertice (int i) throws GeomException {
        switch (i)
        {
            case 0:
                return superiorDerecha;
            case 1:
                return new Punto2D(inferiorIzquierda.x(), superiorDerecha.y());
            case 2:
                return inferiorIzquierda;
            case 3:
                return new Punto2D(superiorDerecha.x(), inferiorIzquierda.y());
            default:
                throw new GeomException("vertice (int): el valor del índice debe ser un valor entre 0 y 3");
        }
    }
    
  /** Obtiene la coordenada cartesiana del punto inferior izquierdo del rectángulo
    * cuyo índice se pasa como parámetro
    * @param i indice de la coordenada cartesiana que se desea obtener
    * @return la coordenada cartesiana x o y del punto inferior izquierdo
    * según el índice pasado como parámetro sea 0 ó 1 respectivamente
    * @see GeomException si el valor del índice no es ni 0 ni 1
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public double inferiorCartesiana (int i) throws GeomException {
        switch (i) {
            case 0:
                return inferiorIzquierda.x();
            case 1:
                return inferiorIzquierda.y();
            default:
                throw new GeomException("inferiorCartesiana (int): el índice debe valer 0 ó 1");
            }
        }

/** Obtiene la coordenada cartesiana del punto superior derecho del rectángulo
    * cuyo índice se pasa como parámetro
    * @param i indice de la coordenada cartesiana que se desea obtener
    * @return la coordenada cartesiana x o y del punto superiorDerecho
    * según el índice pasado como parámetro sea 0 ó 1 respectivamente
    * @see GeomException si el valor del índice no es ni 0 ni 1
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public double superiorCartesiana (int i) throws GeomException {
        switch (i) {
            case 0:
                return superiorDerecha.x();
            case 1:
                return superiorDerecha.y();
            default:
                throw new GeomException("inferiorCartesiana (int): el índice debe valer 0 ó 1");
            }
        }

/** Obtiene la coordenada homogénea del punto inferior izquierdo del rectángulo
    * cuyo índice se pasa como parámetro
    * @param i indice de la coordenada homogénea que se desea obtener
    * @return la primera, segunda o tercera coordenada homogénea  del punto
    * inferior izquierdo según el índice pasado como parámetro sea 0, 1 ó 2 
    * respectivamente
    * @see GeomException si el valor del índice no es ni 0 ni 1 ni 2
    * @see Punto2D#hx()
    * @see Punto2D#hy()
    * @see Punto2D#hz()
    */
    public double inferiorHomogenea (int i) throws GeomException {
        switch (i) {
            case 0:
                return inferiorIzquierda.hx();
            case 1:
                return inferiorIzquierda.hy();
            case 2:
                return inferiorIzquierda.hz();
            default:
                throw new GeomException("inferiorHomogenea (int): el índice debe valer 0, 1 ó 2");
            }
        }

  /** Obtiene la coordenada homogénea del punto superior derecho del rectángulo
    * cuyo índice se pasa como parámetro
    * @param i indice de la coordenada homogénea que se desea obtener
    * @return la primera, segunda o tercera coordenada homogénea  del punto
    * superior derecho según el índice pasado como parámetro sea 0, 1 ó 2 
    * respectivamente
    * @see GeomException si el valor del índice no es ni 0 ni 1 ni 2
    * @see Punto2D#hx()
    * @see Punto2D#hy()
    * @see Punto2D#hz()
    */
    public double superiorHomogenea (int i) throws GeomException {
        switch (i) {
            case 0:
                return superiorDerecha.hx();
            case 1:
                return superiorDerecha.hy();
            case 2:
                return superiorDerecha.hz();
            default:
                throw new GeomException("superiorHomogenea (int): el índice debe valer 0, 1 ó 2");
        }
    }
        
  /** Calcula el área del rectángulo
    * @return área del rectángulo (base * altura)
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public double area() {
        double base = superiorDerecha.x() - inferiorIzquierda.x();
        double altura = superiorDerecha.y() - inferiorIzquierda.y();
        
        return base*altura;
    }
    
  /** Comprueba si el rectángulo es un rectángulo degenerado. Esto sucederá
    * cuando todos los vértices sean colineales
    * @return un valor booleano indicando si el rectángulo es degenerado
    * @see Mat#absoluto(double)
    * @see Mat#EPSILON
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public boolean degenerado() {
        return (Mat.absoluto(inferiorIzquierda.x() - superiorDerecha.x()) < Mat.EPSILON ||
                Mat.absoluto(inferiorIzquierda.y() - superiorDerecha.y()) < Mat.EPSILON);
    }
    
  /** Comprueba si un punto se encuentra en los bordes del rectángulo
    * @param punto el punto que se desea comprobar si se encuentra contenido por
    * los bordes del rectángulo
    * @return un valor booleano indicando si el punto pasado como parámetro está
    * contenido por los bordes del rectángulo
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#contienePunto(Punto2D)
    * @see Rectangulo2D#vertice(int)
    * @see Punto2D#equals(ObjetoGeometrico)
    */
    public boolean bordeContienePunto(Punto2D punto) {
        int i=0;
        boolean contenido = false;
        
        if (punto.equals(inferiorIzquierda) || punto.equals(superiorDerecha))
            return true;
        else
            try {
                while (i<3 && !contenido)
                {
                    Segmento2D segmento = new Segmento2D(vertice(i), vertice(i+1));
                    contenido = segmento.contienePunto(punto);
                    i++;
                }
                if (!contenido)
                {
                    Segmento2D segmento = new Segmento2D(vertice(3), vertice(0));
                    contenido = segmento.contienePunto(punto);
                }
        
                return contenido;
            } catch (GeomException e) {
                return false; // Esta excepción unnca se va a producir
            }
    }
    
  /** Comprueba si un punto está contenido en el rectángulo
    * @param punto punto que se desea comprobar si está contenido por el 
    * rectángulo
    * @return un valor booleano indicando si el punto está contenido por el
    * rectángulo
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public boolean contienePunto(Punto2D punto) {
        return (punto.x() >= inferiorIzquierda.x() &&
                punto.x() <= superiorDerecha.x() &&
                punto.y() >= inferiorIzquierda.y() &&
                punto.y() <= superiorDerecha.y());
    }
    
  /** Comprueba si un punto está contenido de forma estricta por el rectángulo
    * (el punto está contenido pero no está contenido por ninguno de sus bordes)
    * @param punto punto que se desea comprobar si está contenido estrictamente
    * en el rectángulo
    * @return un valor booleano indicando si el punto está contenido estrictamente
    * en el rectángulo
    * @see Rectangulo2D#bordeContienePunto(Punto2D)
    * @see Rectangulo2D#contienePunto(Punto2D)
    */
    public boolean contienePuntoEstrictamente(Punto2D punto) {
        return (contienePunto(punto) && !bordeContienePunto(punto));
    }
    
  /** Obtiene la caja contenedora que contiene al rectángulo
    * @return una caja contenedora conteniendo al rectángulo
    * @see Caja2D#Caja2D(Rectangulo2D)
    */
    public Caja2D cajaContenedora() {
        return new Caja2D(this);
    }
        
  /** Realiza la conversión del objeto de tipo Vector2D a una cadena
    * @return una cadena especificando el tipo de objeto (vector) y sus
    * componentes (desplazamiento en el eje x y desplazamiento en el eje y)
    */  
    public String toString() {
        return nombre + " - Rectangulo2D: ( " + inferiorIzquierda + " - " + superiorDerecha + " )";
    }
    
    private void dibujo(Graphics g, boolean coordenadas, Color color, boolean seleccionado) {
        Punto2D p1 = new Punto2D(superiorDerecha.x(), inferiorIzquierda.y());
        Punto2D p2 = new Punto2D(inferiorIzquierda.x(), superiorDerecha.y());
        
        g.setColor(color);
        if (!superiorDerecha.equals(inferiorIzquierda)) {
            try {
            if (!seleccionado)
            {
                (new Segmento2D(superiorDerecha, p1)).dibujar(g, coordenadas);
                (new Segmento2D(superiorDerecha, p2)).dibujar(g, coordenadas);
                (new Segmento2D(inferiorIzquierda, p1)).dibujar(g, coordenadas);
                (new Segmento2D(inferiorIzquierda, p2)).dibujar(g, coordenadas);
            }
            else
            {
                (new Segmento2D(superiorDerecha, p1)).dibujarSeleccionado(g, coordenadas);
                (new Segmento2D(superiorDerecha, p2)).dibujarSeleccionado(g, coordenadas);
                (new Segmento2D(inferiorIzquierda, p1)).dibujarSeleccionado(g, coordenadas);
                (new Segmento2D(inferiorIzquierda, p2)).dibujarSeleccionado(g, coordenadas);
            }
            } catch (GeomException ge) {
                superiorDerecha.dibujar(g, coordenadas);
            }
        } else {
            superiorDerecha.dibujar(g, coordenadas);
        }
    }
    
    public void dibujar(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.black, false);
    }
    
    public void dibujarSeleccionado(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.green, true);
    }
    
    public void dibujarResultado(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.red, true);
    }
    
    public void dibujarNombre(Graphics g) {
        g.setColor(Color.magenta);
        int y = (int)(superiorDerecha.y());
        g.drawString(nombre, (int)inferiorIzquierda.x() + 2, -y-3);
    }
    
    public void dibujarXor(Graphics g) {
        Punto2D p1 = new Punto2D(superiorDerecha.x(), inferiorIzquierda.y());
        Punto2D p2 = new Punto2D(inferiorIzquierda.x(), superiorDerecha.y());
        
        superiorDerecha.dibujarXor(g);
        inferiorIzquierda.dibujarXor(g);
        p1.dibujarXor(g);
        p2.dibujarXor(g);
        g.setColor(Color.white);
            
        g.drawLine((int)superiorDerecha.x(), -(int)superiorDerecha.y(),
                   (int)p1.x(), -(int)p1.y());
        g.drawLine((int)superiorDerecha.x(), -(int)superiorDerecha.y(),
                   (int)p2.x(), -(int)p2.y());
        g.drawLine((int)inferiorIzquierda.x(), -(int)inferiorIzquierda.y(), 
                   (int)p1.x(), -(int)p1.y());
        g.drawLine((int)inferiorIzquierda.x(), -(int)inferiorIzquierda.y(), 
                   (int)p2.x(), -(int)p2.y());
    }
}



