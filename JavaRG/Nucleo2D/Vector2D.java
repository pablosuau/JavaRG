/*
 * Vector2D.java
 *
 * Created on 11 de noviembre de 2000, 11:23
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;

/** Un objeto de la clase <code>Vector2D</code> representa un vector en el
 * 2D. Geométricamente, un vector es la diferencia entre un par de puntos, y
 * denota la dirección y la distancia desde el primer punto hasta el segundo.
 * Internamente el vector estará implementado mediante desplazamientos en el
 * eje x y en el eje y
 *
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico
 */
public class Vector2D extends ObjetoGeometrico {

    /**
     * Desplazamientos en el eje x y en el eje y
     */
    double despX, despY;

    /**
     * Constructor por defecto de un vector. Construye un vector nulo, con ambos
     * deplazamientos a cero.
     * @return un vector nulo
     */
    public Vector2D() {
        despX = despY = 0;
    }

    /**
     * Constructor del vector a partir de dos desplazamientos.
     * @param dx desplazamiento en el eje x
     * @param dy desplazamiento en el eje y
     * @return un nuevo vector cuya dirección y magnitud están definidos
     * por los dos desplazamientos pasados como parámetro
     */
    public Vector2D(double dx, double dy) {
        despX = dx;
        despY = dy;
    }

    /** Constructor del vector a partir de dos puntos. El vector representará
     * la diferencia entre esos dos puntos, es decir, la dirección y la distancia
     * de uno a otro.
     * @param p1 el primer punto
     * @param p2 el segundo punto
     * @return un nuevo vector cuyos desplazamientos vemdrán dado por la diferencia
     * entre las coordenadas de ambos puntos
     * @see Punto2D#x()
     * @see Punto2D#y()
     */
    public Vector2D(Punto2D p1, Punto2D p2) {
        despX = p2.x() - p1.x();
        despY = p2.y() - p1.y();
    }

    /** Constructor de copia de Vector2D. Construye un nuevo vector a partir de
     * otro ya existente.
     * @param original vector a partir del cual construir el nuevo vector
     * @return un nuevo vector con los mismos desplazamientos en el eje x y en el
     * eje y que el vector pasado como parámetro
     * @see Vector2D#despX()
     * @see Vector2D#despY()
     */
    public Vector2D(Vector2D original) {
        this.despX = original.despX();
        this.despY = original.despY();
    }

    /** Constructor del vector a partir de las coordenadas cartesianas de dos
     * puntos. El vector representará la diferencia entre esos dos puntos, es
     * decir, la dirección y la distancia de uno a otro.
     * @param x0 coordenada cartesiana x del primer punto
     * @param y0 coordenada cartesiana y del primer punto
     * @param x1 coordenada cartesiana x del segundo punto
     * @param y1 coordenada cartesiana y del segundo punto
     * @return un nuevo vector cuyos desplazamientos vemdrán dado por la diferencia
     * entre las coordenadas de ambos puntos
     */
    public Vector2D(double x0, double y0, double x1, double y1) {
        despX = x1 - x0;
        despY = y1 - y0;
    }

    /** Constructor del vector a partir de las coordenadas homogéneas de dos
     * puntos. El vector representará la diferencia entre esos dos puntos, es
     * decir, la dirección y la distancia de uno a otro.
     * @param hx0 coordenada homogénea x del primer punto
     * @param hy0 coordenada homogénea y del primer punto
     * @param hz0 coordenada homogénea z del primer punto
     * @param hx1 coordenada homogénea x del segundo punto
     * @param hy1 coordenada homogénea y del segundo punto
     * @param hz1 coordenada homogénea z del segundo punto
     * @param hx2 coordenada homogénea x del tercer punto
     * @param hy2 coordenada homogénea y del tercer punto
     * @param hz2 coordenada homogénea z del tercer punto
     * @return un nuevo vector cuyos desplazamientos vemdrán dado por la diferencia
     * entre las coordenadas de ambos puntos
     * @exception GeomException si la coordenada homogénea z de alguno de los
     * puntos es cero
     */
    public Vector2D(double hx0, double hy0, double hz0,
    double hx1, double hy1, double hz1) throws GeomException {
        if (hz0 == 0 || hz1 == 0)
        throw new GeomException("Vector2D (double, double, double, double, double, double): Las coordenadas homogéneas en el eje z no pueden tener valor cero");
        else {
            despX = hx1/hz1 - hx0/hz0;
            despY = hy1/hz1 - hy0/hz0;
        }
    }

    /** Constructor del vector a partir de un segmento. El vector se construirá
     * como la diferencia entre los puntos inicial y final del segmento, es decir,
     * el vector representará el ángulo y la distancia entre dichos puntos
     * @param segmento el segmento a partir del cual construir el vector
     * @return un nuevo vector cuyos desplazamientos vendrán dados por la diferencia
     * en las coordenadas entre los puntos inicial y final del segmento
     * @see Segmento2D#fin() 
     * @see Segmento2D#comienzo()
     * @see Punto2D#x()
     * @see Punto2D#y()
     */
    public Vector2D(Segmento2D segmento) {
        despX = (segmento.fin()).x() - (segmento.comienzo()).x();
        despY = (segmento.fin()).y() - (segmento.comienzo()).y();
    }
    
  /** Constructor del vector a partir de la recta. El vector será igual al vector
    * director de la recta
    * @param recta recta a partir de la cual se desea construir el vector
    * @return un nuevo vector cuyos desplazamientos vendrán dados por los 
    * correspondientes desplazamientos del vector director de la recta
    * @see Recta2D#director()
    * @see Vector2D#despX()
    * @see Vector2D#despY()
    */
    public Vector2D (Recta2D recta) {
        despX = (recta.director()).despX();
        despY = (recta.director()).despY();
    }

   /** Constructor del vector a partir del rayo. El vector será igual al vector
    * director del rayo
    * @param rayo rayo a partir del cual se desea construir el vector
    * @return un nuevo vector cuyos desplazamientos vendrán dados por los
    * correspondientes desplazamientos del vector director del rayo
    * @see Rayo2D#director()
    * @see Vector2D#despX()
    * @see Vector2D#despY()
    */
    public Vector2D(Rayo2D rayo) {
        despX = (rayo.director()).despX();
        despY = (rayo.director()).despY();
    }
    
  /** Constructor a partir de una dirección y una longitud. Primero se tomarán
    * los desplazamientos de la dirección construyendo un vector unitario y
    * después incrementaremos dichos desplazamientos según la longitud
    * @param direccion direccion de la que se tomarán los desplazamientos para
    * construir el vector
    * @param longitud longitud del nuevo vector
    * @return un nuevo vector con la misma dirección que la dirección pasada 
    * como primer parámetro y longitud igual al valor del segundo parámetro
    * @see Vector2D#Vector2D(double, double)
    * @see Direccion2D#despX()
    * @see Direccion2D#despY()
    * @see Vector2D#vectorUnidad()
    */
    public Vector2D(Direccion2D direccion, double longitud) {
        Vector2D vectorNormalizado = new Vector2D(direccion.despX(), direccion.despY());
        
        despX = (vectorNormalizado.vectorUnidad()).despX() * longitud;
        despY = (vectorNormalizado.vectorUnidad()).despY() * longitud;
    }
    
   /** Comparación con otro objeto geométrico. Un vector será igual a otro objeto
     * geométrico si dicho objeto es otro vector y coinciden en los desplazamientos
     * en el eje x y en el eje y.
     * @param obj el objeto geométrico con el que se desea comparar el vector
     * @return un valor booleano indicando si el vector es igual al objeto
     * geométrico pasado como parámetro
     * @see Vector2D#despX() 
     * @see Vector2D#despY()
     */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Vector2D) {
            Vector2D vector = (Vector2D) obj;
            // Dos vectores son iguales si tienen el mismo ángulo y magnitud
            if (this.despX() != vector.despX() || this.despY() != vector.despY())
            return false;
            else return true;
        }
        else return false;
    }

   /** Comparación con otro objeto geométrico. Un vector será distinto a otro objeto
     * geométrico si dicho objeto no es un vector o no coinciden en los desplazamientos
     * en el eje x y en el eje y.
     * @param obj el objeto geométrico con el que se desea comparar el vector
     * @return un valor booleano indicando si el vector es igual al objeto
     * geométrico pasado como parámetro
     * @see Vector2D#equals(ObjetoGeometrico) 
     */
    public boolean not_equals (ObjetoGeometrico obj) {
        return !this.equals(obj);
    }
    
    /**
     * Suma de vectores. Para sumar un vector a otro sumamos a los desplazamientos
     * en el eje x y en el eje y del primero los correspondientes desplazamientos
     * del segundo.
     * @param otro vector que se desea sumar
     * @return el vector resultado de la suma
     * @see Vector2D#despX()
     * @see Vector2D#despY()
     */
    public Vector2D suma(Vector2D otro) {
        this.despX += otro.despX();
        this.despY += otro.despY();

        return this;
    }

    /**
     * Obtención del vector opuesto. Un vector es opuesto a otro si tiene los
     * mismos desplazamientos en el eje x y en el eje y pero en sentido contrario.
     * @return el vector opuesto al original
     */
    public Vector2D opuesto() {
        despY = -despY;
        despX = -despX;

        return this;
    }

    /**
     * Obtención del vector perpendicular en el plano. El vector perpendicular
     * se obtiene como resultado de girar el vector 90 grados en sentido horario
     * o antihorario.
     * @param orientacion sentido de giro, puede ser horario o antihorario
     * @return el vector perpendicular en el plano según la orientación pasada
     * como parámetro
     * @exception GeomException si la orientación no se corresponde con Mat.HORARIO
     * o Mat.ANTIHORARIO
     * @see Mat#HORARIO 
     * @see Mat#ANTIHORARIO
     */
    public Vector2D perpendicular(int orientacion) throws GeomException {
        double aux;
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
        throw new GeomException("perpendicular (int): La orientación dada es incorrecta");
        else {
            aux = despY;
            if (orientacion == Mat.ANTIHORARIO) {
                despY = despX;
                despX = -aux;
            } else {
                despY = -despX;
                despX = aux;
            }
        }

        return this;
    }

    /**
     * Obtemción del vector unidad. Devuelve un vector con la misma dirección que
     * el vector original, pero normalizado, es decir, con norma 1. Para ello,
     * dividimos cada uno de los desplazamientos entre la norma del vector.
     * @return el vector normalizado
     * @see Vector2D#magnitud()
     */
    public Vector2D vectorUnidad() {
        double norma = magnitud();

        despX = despX / norma;
        despY = despY / norma;

        return this;
    }

   /** Determina si el vector es nulo. Para que el vector sea nulo sus dos 
     * desplazamientos deben ser igual a cero
     * @return un valor booleano indicando si el vector es nulo
     * @see Mat#EPSILON
     */
     public boolean vectorNulo() {
        return (despX <= Mat.EPSILON && despY == Mat.EPSILON);
    }
    
   /** Módulo del producto vectorial. Dicho módulo se obtiene como el producto de
     * la norma del primer vector, la norma del segundo, y el seno del ángulo entre
     * ambos. El signo del módulo vendrá dado por el sentido en el que se aplique
     * el producto a los vectores.
     * @param otro vector con el que se desa realizar el producto vectorial
     * @return el módulo del producto vectorial entre el vector y el pasado como
     * parámetro
     * @exception GeomException si la norma de alguno de los vectores es cero
     * @see Vector2D#magnitud()
     * Vector2D#anguloEntreVectores(Vector2D)
     */
    public double moduloProductoVectorial(Vector2D otro) throws GeomException {
        double producto = this.magnitud() * otro.magnitud() * Math.sin(this.anguloEntreVectores(otro));
        return producto;
    }

    /**
     * Resta de vectores. Para restar un vector a otro restamos a los desplazamientos
     * en el eje x y en el eje y del primero los correspondientes desplazamientos
     * del segundo.
     * @param otro vector que se desea restar
     * @return el vector resultado de la resta
     * @see Vector2D#despX() 
     * @see Vector2D#despY()
     */
    public Vector2D resta(Vector2D otro) {
        this.despX -= otro.despX();
        this.despY -= otro.despY();

        return this;
    }

  /** Producto escalar de vectores. El producto escalar de los vectores v1 y v2
    * se obtiene como v1x*v2x + v1y*v2y.
    * @param otro vector con el que se desea realizar el producto escalar
    * @return el producto escalar del vector por el vector pasado como parámetro
    * @see Vector2D#despX() 
    * @see Vector2D#despY()
    */
    public double productoEscalar(Vector2D otro) {
        return this.despX*otro.despX() + this.despY*otro.despY();
    }

  /** Escalado de un vector. Se aplicará el mismo factor de escalado tanto al 
    * desplazamiento en el eje x como al desplazamiento en el eje y. Al contrario
    * que la dirección, el vector sí tiene magnitud, por lo que si tendrá sentido
    * incluir para el mismo la transformación de escalado. Si el factor de escalado
    * es cero el resultado será un vector nulo
    * @param escala factor de escalado
    * @return el vector escalado
    */
    public Vector2D escalado(double escalado) {
        despX = despX * escalado;
        despY = despY * escalado;
        return this;
    }

  /** Escalado de un vector. Se aplicará distinto factor de escalado al
    * desplazamiento en el eje x y al desplazamiento en el eje y. Al contrario
    * que la dirección, el vector sí tiene magnitud, por lo que si tendrá sentido
    * incluir para el mismo la transformación de escalado. Si ambos facotres de 
    * escalado valen cero el resultado será un vector nulo
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return el vector escalado
    */
    public Vector2D escalado(double sx, double sy) {
        despX = despX * sx;
        despY = despY * sy;
        return this;
    }

  /** Gira el vector el número de radianes indicado. 
    * @param radio angulo de giro en radianes. Si es positivo el giro será
    * en sentido antihorario y en caso contrario lo será en sentido horario
    * @return el vector rotado
    * @see Vector2D#magnitud()
    * @see Vector2D#anguloEjeX()
    */
    public Vector2D gira(double angulo) {
        double norma = magnitud();
        double nuevoAngulo = angulo + anguloEjeX();

        despX = norma*Math.cos(nuevoAngulo);
        despY = norma*Math.sin(nuevoAngulo);

        return this;
    }

  /** Realiza la división del vector entre un escalar. Se realiza la división por el escalar
    * tanto del desplazamiento en el eje x y en el eje y entre dicho escalar. 
    * @param valor escalar con el que se desea realizar la división
    * @return el vector una vez realizada la división private el escalar pasado
    * como parámetro
    * @exception GeomException si el escalar vale 0
    */
    public Vector2D division(double valor) throws GeomException {
        if (valor == 0)
        throw new GeomException("division (double): división entre cero");
        else {
            despX = despX / valor;
            despY = despY / valor;
        }
        return this;
    }

 /** Obtiene la norma o magnitud del vector. La norma de un vector es el resultado
   * de la raíz cuadrada de la suma de sus componentes al cuadrado.
   * @return la norma del vector
   * @see Mat#cuadrado(double)
   */
    public double magnitud() {
        if (this.vectorNulo())
            return 0;
        else
            return Math.sqrt(Mat.cuadrado(despX) + Mat.cuadrado(despY));
    }
    
  /** Obtiene el ángulo en radianes respecto al eje x. Será positivo si el
    * desplazamiento en el eje y también lo es, y negativo en otro caso.
    * @return el ángulo en radianes respecto al eje x
    * @see Vector2D#magnitud() 
    */
    public double anguloEjeX() {
        double angulo = Math.acos(despX / this.magnitud());
        if (despY<0) angulo = -angulo;
        return angulo;
    }

  /** Obtiene el ángulo en grados respecto al eje x. Será positivo si el
    * desplazamiento en el eje y también lo es, y negativa en otro caso.
    * @return el ángulo en grados respecto al eje x
    * @see Vector2D#anguloEjeX() 
    * @see Mat#rad2Grad(double)
    */
    public double gradosEjeX() {
        return Mat.rad2Grad(anguloEjeX());
    }

  /** Obtiene el ángulo en radianes respecto al eje y. Será positivo si el
    * desplazamiento en el eje x también lo es, y negativo en otro caso.
    * @return el ángulo en radianes respecto al eje y
    * @see Vector2D#magnitud()
    */
    public double anguloEjeY() {
        double angulo = Math.acos(despY/ this.magnitud());
        if (despX<0) angulo = -angulo;
        return angulo;
    }

  /** Obtiene el ángulo en grados respecto al eje y. Será positivo si el
    * desplazamiento en el eje x también lo es, y negativo en otro caso.
    * @return el ángulo en grados respecto al eje y
    * @see Vector2D#anguloEjeY()
    * @see Mat#rad2Grad(double)
    */
    public double gradosEjeY() {
        return Mat.rad2Grad(anguloEjeY());
    }

 /** Calcula el ángulo con signo entre dos vectores, en radianes. 
   * @param otra vector con respecto al cual se desea obtener el ángulo 
   * @return el ángulo con signo entre el vector y el vector pasado como 
   * parámetro, en radianes
   * @exception GeomException si alguno de los vectores tiene norma cero
   * @see Vector2D#despX()
   * @see Vector2D#despY()
   * @see Vector2D#magnitud() 
   */
    public double anguloEntreVectores(Vector2D otro) throws GeomException  {
        double angulo, cociente;

        if (Mat.absoluto(this.magnitud()) < Mat.EPSILON || Mat.absoluto(otro.magnitud()) < Mat.EPSILON)
        throw new GeomException("anguloEntreVectores (Vector2D): uno de los vectores no tiene norma");
        else {
            cociente = (this.despX()*otro.despX() +  this.despY()*otro.despY())/ (this.magnitud() * otro.magnitud());
            if (cociente > 1.0 && cociente < (1.0 + Mat.EPSILON)) cociente = 1.0;
            angulo = Math.acos(cociente);

            // Calculamos el signo del ángulo
            if (this.despX()*otro.despY() - otro.despX()*this.despY() < 0 )
            angulo = (-1)*angulo;

            return angulo;
        }
    }

  /** Calcula el ángulo con signo entre dos vectores, en grados. 
   * @param otra vector con respecto al cual se desea obtener el ángulo 
   * @return el ángulo con signo entre la dirección y la dirección pasada como 
   * parámetro, en grados
   * @see Vector2D#anguloEntreVectores(Vector2D) 
   * @see Mat#rad2Grad(double)
   */
    public double gradosEntreVectores(Vector2D otro) throws GeomException {
        return Mat.rad2Grad(this.anguloEntreVectores(otro));
    }

  /** Obtiene el desplazamiento en el eje x
    * @return desplazamiento en el eje x
    */
    public double despX() {
        return despX;
    }

  /** Obtiene el desplazamiento en el eje y
    * @return desplazamiento en el eje y
    */
    public double despY() {
        return despY;
    }

  /** Función que obtiene el desplazamiento cuyo índice numérico se pasa como
    * parámetro. Dicho índice debe valer 0 si deseamos obtener el desplazamiento
    * en el eje x y 1 si lo que deseamos es el desplazamiento en el eje y
    * @param i índice numérico del desplazamiento que se desea obtener
    * @return el desplazamiento solicitado
    * @exception GeomException si el índice es distinto de 0 y de 1
    */
    public double desp(int i) throws GeomException {
        double devuelto = 0;

        if (i != 0 && i != 1)
        throw new GeomException("desp (int): El índice proporcionado debe ser 0 o 1");
        else
        switch (i) {
            case 0:
            devuelto = despX;
            case 1:
            devuelto =  despY;
        }
        return devuelto;
    }
    
    public Direccion2D direccion() {
        return new Direccion2D(this);
    }

    
  /** Implementación de la función abstracta <i>distancia</i> de <code>ObjetoGeometrico</code>.
   * Simplemente devuelve -1
   */
    public double distancia(Punto2D punto) {
        return -1;
    }  
    
  /** Realiza la conversión del objeto de tipo Vector2D a una cadena
    * @return una cadena especificando el tipo de objeto (vector) y sus
    * componentes (desplazamiento en el eje x y desplazamiento en el eje y)
    */  
    public String toString() {
        return nombre + " - Vector2D: (" + despX + "i + " + despY + "j)";
    }
}
