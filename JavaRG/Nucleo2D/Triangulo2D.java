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
 * Los objetos de la clase <code>Triangulo2D</code> representa un tri�ngulo 
 * situado en el plano. El tri�ngulo se definir� a partir de los puntos en sus
 * v�rtices, y estar� orientado. Es posible definir tri�ngulos degenerados (con
 * sus tres v�rtices colineales) ya que en otro caso no ser�a posible detectar 
 * la colinealidad en otras figuras geom�tricas.
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico Punto2D
 */
public class Triangulo2D extends ObjetoGeometrico {

  /**  
    * Conjunto de v�rtices del tri�ngulo
    */
    Punto2D p[];

  /**
    * Constructor por defecto del tri�ngulo. Define un tri�ngulo cuyos
    * v�rtices son los puntos (0,0), (1,0) y (0,1) en ese orden 
    * @return un tri�ngulo cuyos v�rtices son los puntos (0,0) (1,0) y (0,1)
    * @see Punto2D#Punto2D(double, double)
    */
    public Triangulo2D() {
        p = new Punto2D[3];
        p[0] = new Punto2D (0,0);
        p[1] = new Punto2D (1,0);
        p[2] = new Punto2D (0,1);
    }

  /** Constructor del tri�ngulo a partir de tres puntos. Dichos puntos 
    * constituir�n los v�rtices del tri�ngulo
    * @param punto1 primer v�rtice
    * @param punto2 segundo v�rtice
    * @param punto3 tercer v�rtice
    * @return un tr�angulo cuyos v�rtices son los puntos pasados como par�metro
    * @see Punto2D#Punto2D(Punto2D)
    */
    public Triangulo2D(Punto2D punto1, Punto2D punto2, Punto2D punto3) {
        p = new Punto2D[3];
        p[0] = new Punto2D(punto1);
        p[1] = new Punto2D(punto2);
        p[2] = new Punto2D(punto3);
    }

  /** Constructor del tri�ngulo a partir de las coordenadas cartesianas de
    * tres puntos. Dichos puntos ser�n los v�rtices del nuevo tri�ngulo
    * @param x1 coordenada cartesiana x del primer v�rtice
    * @param y1 coordenada cartesiana y del primer v�rtice
    * @param x2 coordenada cartesiana x del segundo v�rtice
    * @param y2 coordenada cartesiana y del segundo v�rtice
    * @param x3 coordenada cartesiana x del tercer v�rtice
    * @param y3 coordenada cartesiana y del tercer v�rtice
    * @return un tri�ngulo cuyos v�rtices tendr�n las coordenadas cartesianas 
    * pasadas como par�metro
    * @see Punto2D#Punto2D(double, double)
    */
    public Triangulo2D(double x1, double y1, double x2, double y2, double x3, double y3) {
        p = new Punto2D[3];
        p[0] = new Punto2D(x1, y1);
        p[1] = new Punto2D(x2, y2);
        p[2] = new Punto2D(x3, y3);
    }

  /** Contructor del tri�ngulo a partir de las coordenada homog�neas de tres 
    * puntos.
    * @param hx1 coordenada homog�nea x del primer v�rtice
    * @param hy1 coordenada homog�nea y del primer v�rtice
    * @param hz1 coordenada homog�nea z del primer v�rtice
    * @param hx2 coordenada homog�nea x del segundo v�rtice
    * @param hy2 coordenada homog�nea y del segundo v�rtice
    * @param hz2 coordenada homog�nea z del segundo v�rtice
    * @param hx3 coordenada homog�nea x del tercer v�rtice
    * @param hy3 coordenada homog�nea y del tercer v�rtice
    * @param hz3 coordenada homog�nea z del tercer v�rtice
    * @return un tri�ngulo cuyos v�rtices tienen las coordenadas homog�neas 
    * pasadas como par�metro
    * @exception GeomException si alguna de las coordenadas homog�neas vale 0
    * @see Punto2D#Punto2D(double, double, double)
    */
    public Triangulo2D(double hx1, double hy1, double hz1, double hx2, double hy2, double hz2, double hx3, double hy3, double hz3)
    throws GeomException {
        p = new Punto2D[3];
        p[0] = new Punto2D(hx1, hy1, hz1);
        p[1] = new Punto2D(hx2, hy2, hz2);
        p[2] = new Punto2D(hx3, hy3, hz3);
    }
    
  /** Constructor de copia de Triangulo2D. Construye un nuevo tri�ngulo a partir
    * de otro ya existente.
    * @param original tri�ngulo a partir del cual construir el nuevo
    * @return un tri�ngulo con los mismos v�rtices y en el mismo orden que el
    * tri�ngulo original
    * @see Punto2D#Punto2D(Punto2D)
    */
    public Triangulo2D(Triangulo2D original) 
    {
        this.p = new Punto2D[3];
        for (int i=0; i<3; i++)
            this.p[i] = new Punto2D(original.p[i]);
    }
    
  /** 
    * Comparaci�n con otro objeto geom�trico. Un tri�ngulo es igual a otro objeto
    * geom�trico si dicho objeto es tambi�n un tri�ngulo y adem�s existe una 
    * permutaci�n ciclica de los v�rtices de uno igual a los v�rtices del otro
    * @param obj objeto geom�trico con el que se desea comparar el tri�ngulo
    * @return un valor booleano indicando si el tri�ngulo es igual al objeto
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
    * Comparaci�n con otro objeto geom�trico. Un tri�ngulo es distinto a otro objeto
    * geom�trico si dicho objeto no es un tri�ngulo o si no existe una 
    * permutaci�n ciclica de los v�rtices de uno igual a los v�rtices del otro
    * @param obj objeto geom�trico con el que se desea comparar el tri�ngulo
    * @return un valor booleano indicando si el tri�ngulo es distinto al objeto
    * @Triangulo2D#equals(ObjetoGeometrico)
    */
    
    public boolean not_equals (ObjetoGeometrico obj) {
        return !this.equals(obj);
    }
    
  /** Obtiene el tri�ngulo opuesto. Un tri�ngulo es opuesto a otro si sus vertices
    * est�n definidos en sentido contrario.
    * @return el tri�ngulo opuesto.
    */
    public Triangulo2D opuesto()
    {
        Punto2D aux = p[1];
        p[1] = p[2];
        p[2] = aux;

        return this;
    }

  /** Obtiene el �rea del tri�ngulo con signo. Dicho �rea se calcular� a 
    * partir del m�dulo del producto vectorial de los vectores definidos entre
    * el primer v�rtice y el otro par de v�rtices.
    * @return el �rea con signo del tri�ngulo
    * @exception GeomException si el tri�ngulo tiene al menos dos v�rtices iguales
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    * @see Vector2D#moduloProductoVectorial(Vector2D)
    */
    public double area() throws GeomException {
        Vector2D v1 = new Vector2D(p[0], p[1]);
        Vector2D v2 = new Vector2D(p[0], p[2]);

        double area = v1.moduloProductoVectorial(v2);
        
        if (p[0].equals(p[1]) || p[0].equals(p[2]) || p[1].equals(p[2]))
            throw new GeomException("area(): el tri�ngulo tiene al menos dos v�rtices iguales");
        else
            return area;
    }

  /** Obtiene el �rea del tri�ngulo sin signo. Para ello se calcular� el valor
    * absoluto del �rea con signo.
    * @return el �rea del tri�ngulo sin signo
    * @exception GeomException si la �gnitud de alguno de los vectores definidos
    * entre el primer v�rtice y los otros dos es cero
    * @see Triangulo2D#area() 
    * Mat#absoluto(double)
    */
    public double areaSinSigno() throws GeomException {
        return Mat.absoluto(area());
    }

  /** Obtiene la distancia de un punto al tri�ngulo, as� como el punto del 
    * tri�ngulo m�s cercano a dicho punto. Dicha distancia ser� la
    * distancia m�s corta entre el punto y alguno de los lados del tri�ngulo
    * @param punto punto al que se desea obtener la distancia
    * @param cercano punto del segmento m�s cercano al punto pasado como primer
    * par�metro. Es un par�metro de SALIDA, y debe ser un punto ya creado
    * @return la distancia del tri�ngulo al punto pasado como par�metro
    * @exception GeomException si el punto pasado como segundo par�metro no es un punto
    * ya creado
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#puntoMasCercano(Punto2D, Punto2D)
    * @see Punto2D#vectorDiferencia(Punto2D)
    */
    public double puntoMasCercano(Punto2D punto, Punto2D cercano) throws GeomException {
        double distancia = java.lang.Double.POSITIVE_INFINITY;
        
        if (cercano == null)
            throw new GeomException("puntoMasCercano (Punto2D, Punto2D): el segundo par�metro debe estar inicializado");

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
    
   /** Obtiene la distancia del tri�ngulo a un punto
     * @param punto punto para el cual se desea obtener la distancia
     * @return la distancia del tri�ngulo al punto
     * @see Triangulo2D#puntoMasCercano(Punto2D, Punto2D)
     */
    public double distancia(Punto2D punto) {
        try  {
            return puntoMasCercano(punto, new Punto2D());
        } catch (GeomException e) {
            return java.lang.Double.MAX_VALUE; // Nunca se va a producir
        }
    }
    
  /** Determina si el tri�ngulo es degenerado: Para ello, sus v�rtices deben 
    * ser colineales. Es necesario que se permita la definici�n de tri�ngulos
    * degenerados, ya que en otro caso no se podr�a detectar la colinealidad
    * en los m�todos del resto de objetos geom�tricos.
    * @return un valor booleano indicando si el tri�ngulo es degenerado
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

  /** Obtiene el sentido de giro de los v�rtices del tri�ngulo. Para ello se 
    * comprueba el signo del �rea del mismo. Si es cero, ser�n colineales, si 
    * es mayor que cero el sentido ser� antihorario y ser� horario en otro caso
    * @return el sentido de giro de los v�rtices del tri�ngulo
    * @exception GeomException si la magnitud de alguno de los vectores definidos
    * entre el primer v�rtice y los dos restantes es cero
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

  /** Determina si un punto se encuentra estrictamente contenido en el tri�ngulo.
    * Para que un punto est� contenido en el tri�ngulo debe encontrarse a la
    * izquierda de todos sus lados si este es antihorario (o a la derecha si es
    * horario) y para que sea de forma estricta el punto no debe estar contenido
    * por ninguno de los lados
    * @param punto el punto que se desea comprobar si est� contenido
    * @return un valor booleano indicando si el punto se encuentra estrictamente
    * contenido por el tri�ngulo
    * @exception GeomException si la magnitud de alguno de los vectores definidos
    * entre el v�rtice inicial y el resto de v�rtices es cero
    * @see Triangulo2D#sentido() 
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    */
    public boolean contienePuntoEstrictamente(Punto2D punto) throws GeomException
    {
        /** Primero comprobamos que e l punto no sea ninguno de los v�rtices */
        if (punto.equals(p[0]) || punto.equals(p[1]) || punto.equals(p[2]))
           return false;
        else
        {
           int sentidoTriangulo = sentido(); /** Para no tener que realizar el
                                               * c�lculo varias veces
                                               */
           if (punto.posicionRelativa(p[0], p[1]) == sentidoTriangulo &&
               punto.posicionRelativa(p[1], p[2]) == sentidoTriangulo &&
               punto.posicionRelativa(p[2], p[0]) == sentidoTriangulo)
                  return true;
           else
                  return false;
       }
    }

   /** Determina si un punto se encuentra contenido en el tri�ngulo.
    * Para que un punto est� contenido en el tri�ngulo debe encontrarse a la
    * izquierda de todos sus lados si este es antihorario (o a la derecha si es
    * horario).
    * @param punto el punto que se desea comprobar si est� contenido
    * @return un valor booleano indicando si el punto se encuentra contenido 
    * por el tri�ngulo
    * @exception GeomException si la magnitud de alguno de los vectores definidos
    * entre el v�ertice inicial y el resto de v�rtices es cero
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

  /** Determina si el punto pasado como par�metro se encuentra contenido por
    * alguno de los lados del tri�ngulo
    * @param punto el punto que se desea comprobar si est� contenido por alguno
    * de los lados
    * @return un valor booleano indicando si el punto se encuentra contenido
    * por alguno de los lados
    * @exception GeomException si existen al menos dos puntos del tri�ngulo iguales
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
    
 /** Traslada el tri�ngulo en el plano a partir de un determinado incremento en
   * cada coordenada cartesiana. La traslaci�n se aplicar� a todos los v�rtices.
   * @param incX incremento o desplazamiento en el eje x
   * @param incY incremento o desplazamiento en el eje y
   * @return el tri�ngulo trasladado
   * @see Punto2D#trasladar(double, double)
   */
    public Triangulo2D trasladar (double incX, double incY) {
        for (int i=0; i<3; i++)
        p[i].trasladar(incX, incY);
        return this;
    }

  /** Traslada el tri�ngulo en el plano aplicando el mismo incremento en cada
    * eje. La traslaci�n se aplicar� a cada v�rtice.
    * @param incr incremento o desplazamiento que ser� aplicado tanto al eje x 
    * como al eje y
    * @return el tri�ngulo trasladado
    * @see Punto2D#trasladar(double)
    */
    public Triangulo2D trasladar (double incr) {
        for (int i=0; i<3; i++)
        p[i].trasladar(incr);
        return this;
    }

  /** Traslada el tri�ngulo en la direcci�n y a la distancia determinados por un 
    * vector. Se aplicar� la traslaci�n a cada v�rtice
    * @param vector el vector que define la traslaci�n del tri�ngulo
    * @return el tri�ngulo trasladado
    * @see Punto2D#trasladar(Vector2D)
    */
    public Triangulo2D trasladar (Vector2D vector)
    {
        for (int i=0; i<3; i++)
        p[i].trasladar(vector);
        return this;
    }

  /** Traslaci�n del tri�ngulo a partir de las coordenadas polares (se trasladan
    * los v�rtices del tri�ngulo una determinada distancia en un determinado �ngulo)
    * @param angulo direcci�n de traslaci�n del tri�ngulo
    * @param dist distancia a la que se mover� el tri�ngulo de su posici�n original
    * siguiendo el �ngulo indicado
    * @return el tri�ngulo trasladado
    * @see Punto2D#trasladarPolar(double, double)
    */
    public Triangulo2D trasladarPolar (double angulo, double dist) {
        for (int i=0; i<3; i++)
        p[i].trasladar(angulo, dist);
        return this;
    }

   /** Obtiene el centro de gravedad del tri�ngulo (su punto central)
     * @return el centro de gravedad del tri�ngulo
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
    
   /** Escalado de un tri�ngulo. Se aplicar� el escalado a cada v�rtice
    * @param escala factor de escalado
    * @return el tri�ngulo escalado
    * @exception GeomException si el factor de escalado es cero
    * @see Punto2D#escalado(double)
    */
    public Triangulo2D escalado(double escala) throws GeomException{
        for (int i=0; i<3; i++)
        p[i].escalado(escala);
        return this;
    }

   /** Escalado de un tri�ngulo con distinto factor de escalado para cada coordenada. 
    * Se aplicar� el escalado a cada v�rtice.
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return el tri�ngulo escalado
    * @exception GeomException si alguno de los factores de escalado es cero
    * @see Punto2D#escalado(double, double)
    */
    public Triangulo2D escalado(double sx, double sy) throws GeomException {
        for (int i=0; i<3; i++)
        p[i].escalado(sx, sy);
        return this;
    }

  /** Rota el tri�ngulo respecto del origen de coordenadas
    * @param radio angulo de giro en radianes. Si es positivo el giro ser�
    * en sentido antihorario y en caso contrario lo ser� en sentido horario
    * @return el tri�ngulo girado
    * @see Punto2D#gira(double)
    */
    public Triangulo2D gira(double radio) {
        for (int i=0; i<3; i++)
        p[i].gira(radio);
        return this;
    }

  /** Rota el tri�ngulo respecto a un punto. Se aplicar� la 
    * rotaci�n respecto a un punto a todos los v�rtices del tri�ngulo.
    * @param origen punto respecto el cual se har� el giro
    * @radio angulo de giro en radianes. Si es positivo el giro ser� en sntido
    * antihorario y en caso contrario lo ser� en sentido horario
    * @return el tri�ngulo girado
    * @see Punto2D#gira(Punto2D, double)
    */
    public Triangulo2D gira(Punto2D origen, double radio) {
        for (int i=0; i<3; i++)
        p[i].gira(origen, radio);
        return this;
    }

  /** Aplica una matriz de transformaci�n al tri�ngulo. La matriz debe ser cuadrada 
    * y de orden dos (para aplicarla a las coordenadas cartesianas) o tres (en
    * cuyo caso se aplica a las coordenadas homog�neas). Se aplicar� la matriz
    * de transformaci�n a cada uno de los v�rtices del tri�ngulo
    * @param matriz de transformaci�n
    * @return el tri�ngulo transformado
    * @exception GeomException si la matriz de transformaci�n no es cuadrada, o 
    * es cuadrada pero su orden es distinto de dos o tres
    * @see Punto2D#transforma(double[][])
    */
    public Triangulo2D transforma(double matriz[][]) throws GeomException {
        for (int i=0; i<3; i++)
        p[i].transforma(matriz);
        return this;
    }

    /** Determina cual es el vertice cuya coordenada x est� situada m�s a la
      * izquierda
      * @return el v�rtice del tri�ngulo cuya coordenada x est� situada m�s
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

    /** Determina cual es el vertice cuya coordenada x est� situada m�s a la
      * derecha
      * @return el v�rtice del tri�ngulo cuya coordenada x est� situada m�s
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

    /** Determina cual es el vertice cuya coordenada y est� situada m�s abajo
      * @return el v�rtice del tri�ngulo cuya coordenada y est� situada m�s
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

    /** Determina cual es el vertice cuya coordenada y est� situada m�s arriba
      * @return el v�rtice del tri�ngulo cuya coordenada y est� situada m�s
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
    * Obtiene el primer v�rtice del tri�ngulo.
    * @return el primer v�rtice del tri�ngulo
    */
    public Punto2D p1() {
        return p[0];
    }
  /**
    * Obtiene el segundo v�rtice del tri�ngulo.
    * @return el segundo v�rtice del tri�ngulo.
    */
    public Punto2D p2() {
        return p[1];
    }
  /**
    * Obtiene el tercer v�rtice del tri�ngulo
    * @return el tercer v�rtice del tri�ngulo
    */
    public Punto2D p3() {
        return p[2];
    }

  /** Obtiene el v�rtice cuyo �ndice num�rico es el que se pasa como
    * par�mtro. Dicho �ndice i debe cumplir que 0<=i<=2, correspondi�ndose el 
    * 0 al primer v�rtice, el 1 al segundo y el 2 al tercero.
    * @param i �ndice num�rtico del v�rtice que se desea obtener
    * @return el v�rtice solicitado
    * @exception GeomException si el �ndice es menor de 0 o mayor de 2
    */  
    public Punto2D vertice(int i) throws GeomException {
        if (i<0 || i>2)
        throw new GeomException("vertice (int): el �ndice especificado es incorrecto");
        else
        return p[i];
    }

   /** Realiza la conversi�n del objeto de tipo Triangulo2D a una cadena
    * @return una cadena especificando el tipo de objeto (tri�ngulo) y las
    * coordenadas cartesianas de sus v�rtices
    * cartesianas
    */
    public String toString() {
        return nombre + " - Triangulo2D: (" + p[0] + " " + p[1] + " " + p[2] + ")";
    }
    
  /** Obtiene la caja contenedora que contiene al tri�ngulo
    * @return una caja contenedora conteniendo al tri�ngulo
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
