/*
 * Triangulo2D.java
 *
 * Created on 25 de noviembre de 2000, 1:02
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;
import java.awt.*;
 
/**
 * Los objetos de la clase <code>Triangulo2D</code> representa un triángulo 
 * situado en el plano. El triángulo se definirá a partir de los puntos en sus
 * vértices, y estará orientado. Es posible definir triángulos degenerados (con
 * sus tres vértices colineales) ya que en otro caso no sería posible detectar 
 * la colinealidad en otras figuras geométricas.
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico Punto2D
 */
public class Triangulo2D extends ObjetoGeometrico {

  /**  
    * Conjunto de vértices del triángulo
    */
    Punto2D p[];

  /**
    * Constructor por defecto del triángulo. Define un triángulo cuyos
    * vértices son los puntos (0,0), (1,0) y (0,1) en ese orden 
    * @return un triángulo cuyos vértices son los puntos (0,0) (1,0) y (0,1)
    * @see Punto2D#Punto2D(double, double)
    */
    public Triangulo2D() {
        p = new Punto2D[3];
        p[0] = new Punto2D (0,0);
        p[1] = new Punto2D (1,0);
        p[2] = new Punto2D (0,1);
    }

  /** Constructor del triángulo a partir de tres puntos. Dichos puntos 
    * constituirán los vértices del triángulo
    * @param punto1 primer vértice
    * @param punto2 segundo vértice
    * @param punto3 tercer vértice
    * @return un tríangulo cuyos vértices son los puntos pasados como parámetro
    * @see Punto2D#Punto2D(Punto2D)
    */
    public Triangulo2D(Punto2D punto1, Punto2D punto2, Punto2D punto3) {
        p = new Punto2D[3];
        p[0] = new Punto2D(punto1);
        p[1] = new Punto2D(punto2);
        p[2] = new Punto2D(punto3);
    }

  /** Constructor del triángulo a partir de las coordenadas cartesianas de
    * tres puntos. Dichos puntos serán los vértices del nuevo triángulo
    * @param x1 coordenada cartesiana x del primer vértice
    * @param y1 coordenada cartesiana y del primer vértice
    * @param x2 coordenada cartesiana x del segundo vértice
    * @param y2 coordenada cartesiana y del segundo vértice
    * @param x3 coordenada cartesiana x del tercer vértice
    * @param y3 coordenada cartesiana y del tercer vértice
    * @return un triángulo cuyos vértices tendrán las coordenadas cartesianas 
    * pasadas como parámetro
    * @see Punto2D#Punto2D(double, double)
    */
    public Triangulo2D(double x1, double y1, double x2, double y2, double x3, double y3) {
        p = new Punto2D[3];
        p[0] = new Punto2D(x1, y1);
        p[1] = new Punto2D(x2, y2);
        p[2] = new Punto2D(x3, y3);
    }

  /** Contructor del triángulo a partir de las coordenada homogéneas de tres 
    * puntos.
    * @param hx1 coordenada homogénea x del primer vértice
    * @param hy1 coordenada homogénea y del primer vértice
    * @param hz1 coordenada homogénea z del primer vértice
    * @param hx2 coordenada homogénea x del segundo vértice
    * @param hy2 coordenada homogénea y del segundo vértice
    * @param hz2 coordenada homogénea z del segundo vértice
    * @param hx3 coordenada homogénea x del tercer vértice
    * @param hy3 coordenada homogénea y del tercer vértice
    * @param hz3 coordenada homogénea z del tercer vértice
    * @return un triángulo cuyos vértices tienen las coordenadas homogéneas 
    * pasadas como parámetro
    * @exception GeomException si alguna de las coordenadas homogéneas vale 0
    * @see Punto2D#Punto2D(double, double, double)
    */
    public Triangulo2D(double hx1, double hy1, double hz1, double hx2, double hy2, double hz2, double hx3, double hy3, double hz3)
    throws GeomException {
        p = new Punto2D[3];
        p[0] = new Punto2D(hx1, hy1, hz1);
        p[1] = new Punto2D(hx2, hy2, hz2);
        p[2] = new Punto2D(hx3, hy3, hz3);
    }
    
  /** Constructor de copia de Triangulo2D. Construye un nuevo triángulo a partir
    * de otro ya existente.
    * @param original triángulo a partir del cual construir el nuevo
    * @return un triángulo con los mismos vértices y en el mismo orden que el
    * triángulo original
    * @see Punto2D#Punto2D(Punto2D)
    */
    public Triangulo2D(Triangulo2D original) 
    {
        this.p = new Punto2D[3];
        for (int i=0; i<3; i++)
            this.p[i] = new Punto2D(original.p[i]);
    }
    
  /** 
    * Comparación con otro objeto geométrico. Un triángulo es igual a otro objeto
    * geométrico si dicho objeto es también un triángulo y además existe una 
    * permutación ciclica de los vértices de uno igual a los vértices del otro
    * @param obj objeto geométrico con el que se desea comparar el triángulo
    * @return un valor booleano indicando si el triángulo es igual al objeto
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Triangulo2D#p1()
    * @see Triangulo2D#p2()
    * @see Triangulo2D#p3()
    */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Triangulo2D) {
            Triangulo2D triangulo = (Triangulo2D) obj;
            
            if ((this.p1().equals(triangulo.p1()) && this.p2().equals(triangulo.p2()) && this.p3().equals(triangulo.p3()))
                || (this.p1().equals(triangulo.p2()) && this.p2().equals(triangulo.p3()) && this.p3().equals(triangulo.p1()))
                || (this.p1().equals(triangulo.p3()) && this.p2().equals(triangulo.p1()) && this.p3().equals(triangulo.p2())))
            return true;
            else return false;
        }
        else return false;
    }

  /** 
    * Comparación con otro objeto geométrico. Un triángulo es distinto a otro objeto
    * geométrico si dicho objeto no es un triángulo o si no existe una 
    * permutación ciclica de los vértices de uno igual a los vértices del otro
    * @param obj objeto geométrico con el que se desea comparar el triángulo
    * @return un valor booleano indicando si el triángulo es distinto al objeto
    * @Triangulo2D#equals(ObjetoGeometrico)
    */
    
    public boolean not_equals (ObjetoGeometrico obj) {
        return !this.equals(obj);
    }
    
  /** Obtiene el triángulo opuesto. Un triángulo es opuesto a otro si sus vertices
    * están definidos en sentido contrario.
    * @return el triángulo opuesto.
    */
    public Triangulo2D opuesto()
    {
        Punto2D aux = p[1];
        p[1] = p[2];
        p[2] = aux;

        return this;
    }

  /** Obtiene el área del triángulo con signo. Dicho área se calculará a 
    * partir del módulo del producto vectorial de los vectores definidos entre
    * el primer vértice y el otro par de vértices.
    * @return el área con signo del triángulo
    * @exception GeomException si el triángulo tiene al menos dos vértices iguales
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    * @see Vector2D#moduloProductoVectorial(Vector2D)
    */
    public double area() throws GeomException {
        Vector2D v1 = new Vector2D(p[0], p[1]);
        Vector2D v2 = new Vector2D(p[0], p[2]);

        double area = v1.moduloProductoVectorial(v2);
        
        if (p[0].equals(p[1]) || p[0].equals(p[2]) || p[1].equals(p[2]))
            throw new GeomException("area(): el triángulo tiene al menos dos vértices iguales");
        else
            return area;
    }

  /** Obtiene el área del triángulo sin signo. Para ello se calculará el valor
    * absoluto del área con signo.
    * @return el área del triángulo sin signo
    * @exception GeomException si la ágnitud de alguno de los vectores definidos
    * entre el primer vértice y los otros dos es cero
    * @see Triangulo2D#area() 
    * Mat#absoluto(double)
    */
    public double areaSinSigno() throws GeomException {
        return Mat.absoluto(area());
    }

  /** Obtiene la distancia de un punto al triángulo, así como el punto del 
    * triángulo más cercano a dicho punto. Dicha distancia será la
    * distancia más corta entre el punto y alguno de los lados del triángulo
    * @param punto punto al que se desea obtener la distancia
    * @param cercano punto del segmento más cercano al punto pasado como primer
    * parámetro. Es un parámetro de SALIDA, y debe ser un punto ya creado
    * @return la distancia del triángulo al punto pasado como parámetro
    * @exception GeomException si el punto pasado como segundo parámetro no es un punto
    * ya creado
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#puntoMasCercano(Punto2D, Punto2D)
    * @see Punto2D#vectorDiferencia(Punto2D)
    */
    public double puntoMasCercano(Punto2D punto, Punto2D cercano) throws GeomException {
        double distancia = java.lang.Double.POSITIVE_INFINITY;
        
        if (cercano == null)
            throw new GeomException("puntoMasCercano (Punto2D, Punto2D): el segundo parámetro debe estar inicializado");

        Punto2D cercanoTemporal = new Punto2D();
        Vector2D diferencia = null;
        
        try {
            Segmento2D segmento1 = new Segmento2D(p[0], p[1]);
            distancia = segmento1.puntoMasCercano(punto, cercano);
        } catch (GeomException e) {
            distancia = p[0].distancia(punto);
            diferencia = p[0].vectorDiferencia(cercano);
            cercano.trasladar(diferencia);
        }
        
        try {
            Segmento2D segmento2 = new Segmento2D(p[1], p[2]);
            double dist2 = segmento2.puntoMasCercano(punto, cercanoTemporal);
            if (dist2 < distancia) {
                distancia = dist2;
                diferencia = cercanoTemporal.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        } catch (GeomException e) {
            double dist2 = p[1].distancia(punto);
            if (dist2 < distancia) {
                distancia = dist2;
                diferencia = p[1].vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        }   
        
        try {
            Segmento2D segmento3 = new Segmento2D(p[0], p[2]);
            double dist3 = segmento3.puntoMasCercano(punto, cercanoTemporal);
            if (dist3 < distancia) {
                distancia = dist3; 
                diferencia = cercanoTemporal.vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        } catch (GeomException e) {
            double dist3 = p[0].distancia(punto);
            if (dist3 < distancia) {
                distancia = dist3;
                diferencia = p[0].vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }
        }
        
        return distancia;
    }
    
   /** Obtiene la distancia del triángulo a un punto
     * @param punto punto para el cual se desea obtener la distancia
     * @return la distancia del triángulo al punto
     * @see Triangulo2D#puntoMasCercano(Punto2D, Punto2D)
     */
    public double distancia(Punto2D punto) {
        try  {
            return puntoMasCercano(punto, new Punto2D());
        } catch (GeomException e) {
            return java.lang.Double.MAX_VALUE; // Nunca se va a producir
        }
    }
    
  /** Determina si el triángulo es degenerado: Para ello, sus vértices deben 
    * ser colineales. Es necesario que se permita la definición de triángulos
    * degenerados, ya que en otro caso no se podría detectar la colinealidad
    * en los métodos del resto de objetos geométricos.
    * @return un valor booleano indicando si el triángulo es degenerado
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Triangulo2D#sentido()
    */
    public boolean degenerado() 
    {
        try {
            if (p[0].equals(p[1]) || p[0].equals(p[2]) || p[1].equals(p[2])
                || this.sentido() == Mat.COLINEAL) 
                return true;
            else
                return false;
        } catch (GeomException e) {
            return true;
        }
    }

  /** Obtiene el sentido de giro de los vértices del triángulo. Para ello se 
    * comprueba el signo del área del mismo. Si es cero, serán colineales, si 
    * es mayor que cero el sentido será antihorario y será horario en otro caso
    * @return el sentido de giro de los vértices del triángulo
    * @exception GeomException si la magnitud de alguno de los vectores definidos
    * entre el primer vértice y los dos restantes es cero
    * @see Mat#COLINEAL
    * @see Mat#ANTIHORARIO 
    * @see Mat#HORARIO 
    * @see Mat#EPSILON
    * @see Mat#absoluto(double)
    * @see Triangulo2D#area()
    */
    public int sentido() throws GeomException {
        double areaConSigno = area();
        if (Mat.absoluto(areaConSigno) < Mat.EPSILON) return Mat.COLINEAL;
        else if (areaConSigno > 0) return Mat.ANTIHORARIO;
        else return Mat.HORARIO;
    }

  /** Determina si un punto se encuentra estrictamente contenido en el triángulo.
    * Para que un punto esté contenido en el triángulo debe encontrarse a la
    * izquierda de todos sus lados si este es antihorario (o a la derecha si es
    * horario) y para que sea de forma estricta el punto no debe estar contenido
    * por ninguno de los lados
    * @param punto el punto que se desea comprobar si está contenido
    * @return un valor booleano indicando si el punto se encuentra estrictamente
    * contenido por el triángulo
    * @exception GeomException si la magnitud de alguno de los vectores definidos
    * entre el vértice inicial y el resto de vértices es cero
    * @see Triangulo2D#sentido() 
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    */
    public boolean contienePuntoEstrictamente(Punto2D punto) throws GeomException
    {
        /** Primero comprobamos que e l punto no sea ninguno de los vértices */
        if (punto.equals(p[0]) || punto.equals(p[1]) || punto.equals(p[2]))
           return false;
        else
        {
           int sentidoTriangulo = sentido(); /** Para no tener que realizar el
                                               * cálculo varias veces
                                               */
           if (punto.posicionRelativa(p[0], p[1]) == sentidoTriangulo &&
               punto.posicionRelativa(p[1], p[2]) == sentidoTriangulo &&
               punto.posicionRelativa(p[2], p[0]) == sentidoTriangulo)
                  return true;
           else
                  return false;
       }
    }

   /** Determina si un punto se encuentra contenido en el triángulo.
    * Para que un punto esté contenido en el triángulo debe encontrarse a la
    * izquierda de todos sus lados si este es antihorario (o a la derecha si es
    * horario).
    * @param punto el punto que se desea comprobar si está contenido
    * @return un valor booleano indicando si el punto se encuentra contenido 
    * por el triángulo
    * @exception GeomException si la magnitud de alguno de los vectores definidos
    * entre el vñertice inicial y el resto de vértices es cero
    * @see Triangulo2D#sentido() 
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Triangulo2D#contienePuntoEstrictamente(Punto2D)
    */
    public boolean contienePunto(Punto2D punto) throws GeomException
    {
        Segmento2D lado1 = new Segmento2D(p[0], p[1]);
        Segmento2D lado2 = new Segmento2D(p[1], p[2]);
        Segmento2D lado3 = new Segmento2D(p[2], p[0]);

        if (lado1.contienePunto(punto) || lado2.contienePunto(punto) || lado3.contienePunto(punto))
        return true;
        else return contienePuntoEstrictamente(punto);
    }

  /** Determina si el punto pasado como parámetro se encuentra contenido por
    * alguno de los lados del triángulo
    * @param punto el punto que se desea comprobar si está contenido por alguno
    * de los lados
    * @return un valor booleano indicando si el punto se encuentra contenido
    * por alguno de los lados
    * @exception GeomException si existen al menos dos puntos del triángulo iguales
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#contienePunto(Punto2D)
    */
    public boolean ladosContienenPunto(Punto2D punto) throws GeomException {
        Segmento2D lado1 = new Segmento2D(p[0], p[1]);
        Segmento2D lado2 = new Segmento2D(p[1], p[2]);
        Segmento2D lado3 = new Segmento2D(p[2], p[0]);
        
        return (lado1.contienePunto(punto) || lado2.contienePunto(punto) ||
                lado3.contienePunto(punto));
    }
    
 /** Traslada el triángulo en el plano a partir de un determinado incremento en
   * cada coordenada cartesiana. La traslación se aplicará a todos los vértices.
   * @param incX incremento o desplazamiento en el eje x
   * @param incY incremento o desplazamiento en el eje y
   * @return el triángulo trasladado
   * @see Punto2D#trasladar(double, double)
   */
    public Triangulo2D trasladar (double incX, double incY) {
        for (int i=0; i<3; i++)
        p[i].trasladar(incX, incY);
        return this;
    }

  /** Traslada el triángulo en el plano aplicando el mismo incremento en cada
    * eje. La traslación se aplicará a cada vértice.
    * @param incr incremento o desplazamiento que será aplicado tanto al eje x 
    * como al eje y
    * @return el triángulo trasladado
    * @see Punto2D#trasladar(double)
    */
    public Triangulo2D trasladar (double incr) {
        for (int i=0; i<3; i++)
        p[i].trasladar(incr);
        return this;
    }

  /** Traslada el triángulo en la dirección y a la distancia determinados por un 
    * vector. Se aplicará la traslación a cada vértice
    * @param vector el vector que define la traslación del triángulo
    * @return el triángulo trasladado
    * @see Punto2D#trasladar(Vector2D)
    */
    public Triangulo2D trasladar (Vector2D vector)
    {
        for (int i=0; i<3; i++)
        p[i].trasladar(vector);
        return this;
    }

  /** Traslación del triángulo a partir de las coordenadas polares (se trasladan
    * los vértices del triángulo una determinada distancia en un determinado ángulo)
    * @param angulo dirección de traslación del triángulo
    * @param dist distancia a la que se moverá el triángulo de su posición original
    * siguiendo el ángulo indicado
    * @return el triángulo trasladado
    * @see Punto2D#trasladarPolar(double, double)
    */
    public Triangulo2D trasladarPolar (double angulo, double dist) {
        for (int i=0; i<3; i++)
        p[i].trasladar(angulo, dist);
        return this;
    }

   /** Obtiene el centro de gravedad del triángulo (su punto central)
     * @return el centro de gravedad del triángulo
     * @see Punto2D#Punto2D(double, double)
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Triangulo2D#menorX()
     * @see Triangulo2D#menorY()
     * @see Triangulo2D#mayorX()
     * @see Triangulo2D#mayorY()
    */
    public Punto2D centroGravedad() {
        double x = (menorX().x() + mayorX().x())/2.0;
        double y = (menorY().y() + mayorY().y())/2.0;
        
        return new Punto2D(x, y);
    }
    
   /** Escalado de un triángulo. Se aplicará el escalado a cada vértice
    * @param escala factor de escalado
    * @return el triángulo escalado
    * @exception GeomException si el factor de escalado es cero
    * @see Punto2D#escalado(double)
    */
    public Triangulo2D escalado(double escala) throws GeomException{
        for (int i=0; i<3; i++)
        p[i].escalado(escala);
        return this;
    }

   /** Escalado de un triángulo con distinto factor de escalado para cada coordenada. 
    * Se aplicará el escalado a cada vértice.
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return el triángulo escalado
    * @exception GeomException si alguno de los factores de escalado es cero
    * @see Punto2D#escalado(double, double)
    */
    public Triangulo2D escalado(double sx, double sy) throws GeomException {
        for (int i=0; i<3; i++)
        p[i].escalado(sx, sy);
        return this;
    }

  /** Rota el triángulo respecto del origen de coordenadas
    * @param radio angulo de giro en radianes. Si es positivo el giro será
    * en sentido antihorario y en caso contrario lo será en sentido horario
    * @return el triángulo girado
    * @see Punto2D#gira(double)
    */
    public Triangulo2D gira(double radio) {
        for (int i=0; i<3; i++)
        p[i].gira(radio);
        return this;
    }

  /** Rota el triángulo respecto a un punto. Se aplicará la 
    * rotación respecto a un punto a todos los vértices del triángulo.
    * @param origen punto respecto el cual se hará el giro
    * @radio angulo de giro en radianes. Si es positivo el giro será en sntido
    * antihorario y en caso contrario lo será en sentido horario
    * @return el triángulo girado
    * @see Punto2D#gira(Punto2D, double)
    */
    public Triangulo2D gira(Punto2D origen, double radio) {
        for (int i=0; i<3; i++)
        p[i].gira(origen, radio);
        return this;
    }

  /** Aplica una matriz de transformación al triángulo. La matriz debe ser cuadrada 
    * y de orden dos (para aplicarla a las coordenadas cartesianas) o tres (en
    * cuyo caso se aplica a las coordenadas homogéneas). Se aplicará la matriz
    * de transformación a cada uno de los vértices del triángulo
    * @param matriz de transformación
    * @return el triángulo transformado
    * @exception GeomException si la matriz de transformación no es cuadrada, o 
    * es cuadrada pero su orden es distinto de dos o tres
    * @see Punto2D#transforma(double[][])
    */
    public Triangulo2D transforma(double matriz[][]) throws GeomException {
        for (int i=0; i<3; i++)
        p[i].transforma(matriz);
        return this;
    }

    /** Determina cual es el vertice cuya coordenada x está situada más a la
      * izquierda
      * @return el vértice del triángulo cuya coordenada x está situada más
      * a la izquierda
      * @see Punto2D#x()
      * 
      */
    public Punto2D menorX() {
        Punto2D menor = p[0];
        
        for (int i=1; i<3; i++)
            if (p[i].x() < menor.x())
                menor = p[i];
            
        return menor;
    }

    /** Determina cual es el vertice cuya coordenada x está situada más a la
      * derecha
      * @return el vértice del triángulo cuya coordenada x está situada más
      * a la derecha
      * @see Punto2D#x()
      */
    public Punto2D mayorX() {
        Punto2D mayor = p[0];
        
        for (int i=1; i<3; i++)
            if (p[i].x() > mayor.x())
                mayor = p[i];
            
        return mayor;
    }

    /** Determina cual es el vertice cuya coordenada y está situada más abajo
      * @return el vértice del triángulo cuya coordenada y está situada más
      * abajo
      * @see Punto2D#y()
      * 
      */
    public Punto2D menorY() {
        Punto2D menor = p[0];
        
        for (int i=1; i<3; i++)
            if (p[i].y() < menor.y())
                menor = p[i];
          
        return menor;
    }

    /** Determina cual es el vertice cuya coordenada y está situada más arriba
      * @return el vértice del triángulo cuya coordenada y está situada más
      * arriba
      * @see Punto2D#y()
      */
    public Punto2D mayorY() {
        Punto2D mayor = p[0];
        
        for (int i=1; i<3; i++)
            if (p[i].y() > mayor.y())
                mayor = p[i];
            
        return mayor;
    }
    
  /**
    * Obtiene el primer vértice del triángulo.
    * @return el primer vértice del triángulo
    */
    public Punto2D p1() {
        return p[0];
    }
  /**
    * Obtiene el segundo vértice del triángulo.
    * @return el segundo vértice del triángulo.
    */
    public Punto2D p2() {
        return p[1];
    }
  /**
    * Obtiene el tercer vértice del triángulo
    * @return el tercer vértice del triángulo
    */
    public Punto2D p3() {
        return p[2];
    }

  /** Obtiene el vértice cuyo índice numérico es el que se pasa como
    * parámtro. Dicho índice i debe cumplir que 0<=i<=2, correspondiéndose el 
    * 0 al primer vértice, el 1 al segundo y el 2 al tercero.
    * @param i índice numértico del vértice que se desea obtener
    * @return el vértice solicitado
    * @exception GeomException si el índice es menor de 0 o mayor de 2
    */  
    public Punto2D vertice(int i) throws GeomException {
        if (i<0 || i>2)
        throw new GeomException("vertice (int): el índice especificado es incorrecto");
        else
        return p[i];
    }

   /** Realiza la conversión del objeto de tipo Triangulo2D a una cadena
    * @return una cadena especificando el tipo de objeto (triángulo) y las
    * coordenadas cartesianas de sus vértices
    * cartesianas
    */
    public String toString() {
        return nombre + " - Triangulo2D: (" + p[0] + " " + p[1] + " " + p[2] + ")";
    }
    
  /** Obtiene la caja contenedora que contiene al triángulo
    * @return una caja contenedora conteniendo al triángulo
    * @see Caja2D#Caja2D(Triangulo2D)
    */
    public Caja2D cajaContenedora() {
        return new Caja2D(this);
    }
    
    private void dibujo(Graphics g, boolean coordenadas, Color color) {
        for (int i=0; i<3; i++)
            p[i].dibujar(g, coordenadas);
        g.setColor(color);
        g.drawLine((int)p[0].x(), -(int)p[0].y(), (int)p[1].x(), -(int)p[1].y());
        g.drawLine((int)p[1].x(), -(int)p[1].y(), (int)p[2].x(), -(int)p[2].y());
        g.drawLine((int)p[2].x(), -(int)p[2].y(), (int)p[0].x(), -(int)p[0].y());
    }
    
    public void dibujar(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.black);
    }
    
    public void dibujarResultado(Graphics g) {
        g.setColor(Color.red);
        g.drawLine((int)p[0].x(), -(int)p[0].y(), (int)p[1].x(), -(int)p[1].y());
        g.drawLine((int)p[1].x(), -(int)p[1].y(), (int)p[2].x(), -(int)p[2].y());
        g.drawLine((int)p[2].x(), -(int)p[2].y(), (int)p[0].x(), -(int)p[0].y());
    }
    
    public void dibujarSeleccionado(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.green);
    }
 
    public void dibujarNombre(Graphics g) {
        g.setColor(Color.magenta);
        try {
            Segmento2D segmento = new Segmento2D(p[0], p[1]);
            segmento.cambiarNombre(nombre);
            segmento.dibujarNombre(g);
        } catch (GeomException e) {return; }
    }    
}
