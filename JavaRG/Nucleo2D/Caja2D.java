/*
 * Caja2D.java
 *
 * Created on 12 de diciembre de 2001, 9:07
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;

/** Un objeto de la clase <code>Caja2D</code> representa una caja contenedora 
 * en el espacio 2D, que normalmente contendrá un objeto geométrico,
 *
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico
 */
public class Caja2D extends ObjetoGeometrico {

    /**
     * Esquina inferior izquierda de la caja contenedora
     */
    Punto2D inferiorIzquierda;
    
    /** 
     * Esquina superior derecha de la caja contenedora
     */
    Punto2D superiorDerecha;

    /**
     * Constructor por defecto de la caja contenedora, que contendrá un objeto 
     * geométrico nulo, y cuyos extremos estarán todos situados en el origen
     * @return una caja nula
     * @see Punto2D#Punto2D()
     */
    public Caja2D()
    {
        inferiorIzquierda = new Punto2D();
        superiorDerecha = new Punto2D();
    }

  /** Constructor a partir del segmento. Las esquinas se obtendrán a partir de 
    * las coordenadas de los extremos del segmento
    * @param segmento segmento contenido por la caja contenedora
    * @return una nueva caja contenedora conteniendo al segmento
    * @see Punto2D#Punto2D(double, double)
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Segmento2D#minimoX()
    * @see Segmento2D#minimoY()
    * @see Segmento2D#maximoX()
    * @see Segmento2D#maximoY()
    */
    public Caja2D(Segmento2D segmento) {
        inferiorIzquierda = new Punto2D((segmento.minimoX()).x(), (segmento.minimoY()).y());
        superiorDerecha = new Punto2D((segmento.maximoX()).x(),(segmento.maximoY()).y());
    }
    
  /** Constructor de la caja contenedroa a partir del punto. Todos los extremos
    * de dicha caja contenedora serán iguales a dicho punto
    * @param punto punto a partir del cual se desea construir la caja contenedora
    * @return una nueva caja contenedora conteniendo al punto pasado como parámetro
    * @see Punto2D#Punto2D(Punto2D)
    */
    public Caja2D(Punto2D punto) {
        inferiorIzquierda = new Punto2D(punto);
        superiorDerecha = new Punto2D(punto);
    }
    
  /** Constructor de la caja contenedora a partir del triángulo. El punto inferior
    * izquierdo tendrá como coordenada x la menor coordenada x de todos los vértices
    * del triángulo y como coordenada y la menor coordenada y de todos los vértices
    * del triángulo. El punto superior derecho se obtiene de la misma forma pero con
    * las mayores coordenadas x e y
    * @param triangulo triángulo a partir del cual construir la caja contenedora
    * @return una nueva caja contenedora conteniendo el triángulo pasado como parámetro
    * @see Triangulo2D#vertice(int)
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Punto2D#Punto2D(double, double)
    */
    public Caja2D (Triangulo2D triangulo) {
        try{
            double minX = triangulo.vertice(0).x();
            double maxX = minX;
            double minY = triangulo.vertice(0).y();
            double maxY = minY;
            for (int i=1; i<2; i++)
            {
                 Punto2D punto = triangulo.vertice(i);
                 if (punto.x() < minX) minX = punto.x();
                 if (punto.x() > maxX) maxX = punto.x();
                 if (punto.y() < minY) minY = punto.y();
                 if (punto.y() > maxY) maxY = punto.y();
            }
        
            inferiorIzquierda = new Punto2D(minX, minY);
            superiorDerecha = new Punto2D(maxX, maxY);
        }
        // La excepción geométrica nunca se producirá, porque siempre indicamos
        // un valor correcto para el valor Triangulo2D.vertice(int)
        catch (GeomException e) { inferiorIzquierda = null; superiorDerecha = null; }
    }
    
  /** Constructor de la caja contenedora a partir de un círculo orientado.
    * @param circulo circulo a partir del cual construir la caja
    * @return una nueva caja contenedora conteniendo al círculo orientado
    * @see Punto2D#Punto2D(double, double)
    * @see Circulo2D#centroCirculo()    
    * @see Circulo2D#radioCirculo()
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public Caja2D(Circulo2D circulo) {
        double cx = (circulo.centroCirculo()).x();
        double cy = (circulo.centroCirculo()).y();
        double r = circulo.radioCirculo();
        inferiorIzquierda = new Punto2D(cx-r, cy-r);
        superiorDerecha = new Punto2D(cx+r, cy+r);
    }
    
  /** Constructor de la caja contenedora a partir de un rectángulo
    * @param rectangulo rectángulo a partir del cual se desea construir la caja
    * contenedora
    * @return una nueva caja contenedora conteniendo al rectángulo
    * @see Rectangulo2D#minimoX()
    * @see Rectangulo2D#maximoX()
    * @see Punto2D#Punto2D(Punto2D)
    */
    public Caja2D (Rectangulo2D rectangulo) {
        inferiorIzquierda = new Punto2D(rectangulo.minimoX());
        superiorDerecha = new Punto2D(rectangulo.maximoX());
    }
    
  /** Constructor de copia de la caja contenedora
    * @param original caja contenedora a partir de la cual se desea obtener la 
    * nueva
    * @return una nueva caja contenedora con todos sus atributos iguales a los
    * de la caja original
    * 
    * @see Punto2D#Punto2D(double, double)
    * @see Caja2D#xmin()
    * @see Caja2D#xmax()
    * @see Caja2D#ymin()
    * @see Caja2D#ymax()
    */
    public Caja2D(Caja2D original) {
        this.inferiorIzquierda = new Punto2D(original.xmin(), original.ymin());
        this.superiorDerecha = new Punto2D(original.xmax(), original.ymax());
    }
    
   /** Comparación con otro objeto geométrico. Una caja contenedora será igual a un objeto
     * geométrico si dicho objeto geométrico es una caja y ambas cajas tienen
     * los mismos extremos
     * @param obj el objeto geométrico con el que se desea comparar la caja
     * @return un valor booleano indicando si la caja contenedora es igual al objeto
     * geométrico pasado como parámetro
     * @see Caja2D#minimoX()
     * @see Caja2D#maximoX()
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Caja2D) {
            Caja2D caja = (Caja2D) obj;
            if ((this.minimoX()).equals(caja.minimoX()) &&
                (this.maximoX()).equals(caja.maximoX()))
                return true;
            else
                return false;
        }
        else return false;
    }
   
   /** Comparación con otro objeto geométrico. Una caja contenedora será distinta a un objeto
     * geométrico si dicho objeto geométrico no es una caja o si ambas cajas tienen
     * distintos extremos
     * @param obj el objeto geométrico con el que se desea comparar la caja
     * @return un valor booleano indicando si la caja contenedora es distinta al objeto
     * geométrico pasado como parámetro
     * @see Caja2D#equals(ObjetoGeometrico)
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
    
  /** Traslada los extremos de la caja aplicando el mismo incremento en cada eje
    * @param d desplazamiento o incremento que será aplicado tanto al eje x como 
    * al eje y
    * @return la caja trasladada
    * @see Punto2D#trasladar(double)
    */
    public Caja2D trasladar(double d) {
        inferiorIzquierda.trasladar(d);
        superiorDerecha.trasladar(d);
        return this;
    }
    
  /** Traslada los extremos de la caja aplicando un incremento distinto a cada eje
    * @param dx desplazamiento o incremento que será aplicado al eje x
    * @param dy desplazamiento o incremento que será aplicado al eje y
    * @return la caja trasladada
    * @see Punto2D#trasladar(double, double)
    */
    public Caja2D trasladar(double dx, double dy) {
        inferiorIzquierda.trasladar(dx,dy);
        superiorDerecha.trasladar(dx,dy);
        return this;
    }
    
  /** Traslada los extremos de la caja en la dirección y distancia indicados por un
    * vector
    * @param vector vector que indicará la dirección y la magnitud del desplazamiento
    * de los extremos
    * @return la caja trasladada
    * @see Punto2D#trasladar(Vector2D)
    */
    public Caja2D trasladar(Vector2D vector) {
        inferiorIzquierda.trasladar(vector);
        superiorDerecha.trasladar(vector);
        return this;
    }
    
  /** Escalado de la caja contenedora, aplicando un factor de escalado distinto 
    * a cada eje
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return la caja escalada
    * @see Punto2D#x()
    * @see Punto2D#y()
    * @see Punto2D#trasladar(double, double)
    */
    public Caja2D escalado(double sx, double sy) {
        double distX = superiorDerecha.x() - inferiorIzquierda.x();
        double distY = superiorDerecha.y() - inferiorIzquierda.y();
        double desplazX = ((distX * sx) - distX)/2;
        double desplazY = ((distY * sy) - distY)/2;
        inferiorIzquierda.trasladar(-desplazX, -desplazY);
        superiorDerecha.trasladar(desplazX, desplazY);
        
        return this;
    }
    
  /** Escalado de la caja contenedora, aplicando el mismo factor de escalado a
    * ambos ejes
    * @param s factor de escalado que se aplicará a ambos ejes
    * @return la caja escalada
    * @see Caja2D#escalado(double)
    */
    public Caja2D escalado (double s) {
        return this.escalado(s, s);
    }
    
  /** Obtiene la distancia de la caja a un punto
    * @param punto punto al que se desea obtener la distancia
    * @return la distancia de la caja al punto pasado como parámetro
    */
    public double distancia(Punto2D punto) {
        Rectangulo2D rect = new Rectangulo2D(inferiorIzquierda, superiorDerecha);
        return rect.distancia(punto);
    }
    
  /** Obtiene el extremo de la caja de mayor coordenada y
    * @return el punto superior derecho de la caja contenedora
    */
    public Punto2D maximoY() {
        return superiorDerecha;
    }
    
  /** Realiza la conversión del objeto de tipo Vector2D a una cadena
    * @return una cadena especificando el tipo de objeto (vector) y sus
    * componentes (desplazamiento en el eje x y desplazamiento en el eje y)
    */  
    public String toString() {
        return nombre + " - Caja2D: ( " + inferiorIzquierda + " - " + superiorDerecha + " )";
    }
}

