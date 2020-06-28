/*
 * Punto2D.java
 *
 * Created on 17 de octubre de 2000, 9:23
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;
import java.awt.*;
import java.text.*; // Permite definir el formato de salida de las coordenadas
                    // del punto cuando éste va a ser dibujado, mediante DecimalFormat
import JavaRG.Nucleo2D.Vector2D;
/**
 * Los objetos de la clase <code>Punto2D</code> representan puntos en el plano.
 * Por lo tanto, es un objeto geométrico básico que será utilizado en la
 * implementación de otros más complejos. Aunque se podrá tratar con sus coordenadas cartesianas,
 *  internamente estará implementado a partir de sus coordenadas homogéneas
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico
 */
public class Punto2D extends ObjetoGeometrico {
    
  /**
    * Coordenada homogénea x
    */
    double hx;
  /**
    * Coordenada homogénea y
    */
    double hy;
  /**
    * Coordenada homogénea z
    */
    double hz; 

  /**
    * Constructor por defecto de Punto2D. Construye un punto en el origen
    * de coordenadas.
    * @return un punto en el origen de coordenadas
    */
    public Punto2D() {
        hx = 0.0;
        hy = 0.0;
        hz = 1;
    }

  /**
    * Constructor del punto a partir de sus coordenadas cartesianas. Dichas 
    * coordenadas cartesianas deberán ser transformadas en coordenadas homogéneas
    * @param coordX coordenada cartesiana x del nuevo punto
    * @param coordY coordenada cartesiana y del nuevo punto
    * @return un punto en las coordenadas especificadas
    *
    */
    public Punto2D (double coordX, double coordY) {
        hx = coordX;
        hy = coordY;
        hz = 1;
    }
    
  /** Constructor del punto a partir de sus coordenadas homogéneas.
    * @param homX coordenada homogénea x del nuevo punto
    * @param homY coordenada homogénea y del nuevo punto
    * @param homZ coordenada homogénea z del nuevo punto
    * @return un punto en las coordenadas especificadas
    * @exception GeomException si la coordenada homogénea z vale 0
    */
    public Punto2D (double homX, double homY, double homZ) 
                   throws GeomException {
        if (homZ == 0)
            throw new GeomException("Punto 2D (double, double, double): La coordenada homogénea z no puede valer 0");            
        else
        {
            hx = homX;
            hy = homY;
            hz = homZ;
        }
    }
    
  /**
    * Contructor de copia de Punto2D. Construye un nuevo punto a partir de otro
    * ya existente
    * @param original punto a partir del cual se desea construir el nuevo punto
    * @return un nuevo punto con las mismas coordenadas que el punto original
    */
    public Punto2D (Punto2D original) {
        this.hx = original.hx();
        this.hy = original.hy();
        this.hz = original.hz();
    }
    
  /** Calcula la distancia al cuadrado del punto a otro pasado como parámetro
    * @param p punto al que se desea calcular la distancia
    * @return la distancia al cuadrado entre los dos puntos
    */
    public double distancia (Punto2D p) {
        return (Math.sqrt(Mat.cuadrado((hx/hz)-p.x())+Mat.cuadrado((hy/hz)-p.y())));
    }
    
  /** Obtiene el vector de diferencia con otro punto, es decir, 
    * un vector cuyo origen es el punto y su destino será el punto pasado como 
    * parámetro
    * @param otro punto destino del vector diferencia
    * @return vector de diferencia entre los dos puntos
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    */
    public Vector2D vectorDiferencia(Punto2D otro)
    {
        return new Vector2D(otro, this);
    }
    
  /** Obtiene el vector definido entre el origen de coordenadas y el punto
    * @return vector desde el origen
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    */
    public Vector2D vectorDesdeOrigen()
    {
        return new Vector2D(this, new Punto2D());
    }
    
  /** Obtiene el vector definido entre el punto y el origen de coordenadas
    * @return vector hacia el origen
    * @see Vector2D#Vector2D(Punto2D, Punto2D)
    */
    public Vector2D vectorAOrigen()
    {
        return new Vector2D(new Punto2D(), this);
    }
    
  /** Comparación con otro objeto geométrico. Un punto será igual a otro 
    * objeto geométrico si dicho objeto es también un punto y la distancia
    * entre ambos es menor de epsilon
    * @param obj objeto geométrico con el que se desea comparar el punto
    * @return un valor booleano indicando si el punto es igual al objeto pasado
    * como parámetro
    * @see Mat#EPSILON
    * @see Punto2D#distancia(Punto2D)
    */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Punto2D) {
            Punto2D punto = (Punto2D) obj;
            if (this.distancia(punto) > Mat.EPSILON)
            return false;
            else return true;
        }
        else return false;
    }

  /** Comparación con otro objeto geométrico. Un punto será distinto a otro 
    * objeto geométrico si dicho objeto no es un punto o la distancia
    * entre ambos es mayor de epsilon
    * @param obj objeto geométrico con el que se desea comparar el punto
    * @return un valor booleano indicando si el punto es distinto al objeto pasado
    * como parámetro
    * @see Punto2D#equals(ObjetoGeometrico obj)
    */
    public boolean not_equals (ObjetoGeometrico obj) {
        return !this.equals(obj);
    }
    
    
  /** Comparación con otro punto según la coordenada y. 
    * @param otro punto con el que comparar
    * @return según el resultado se devolverá:
    * <ul>
    * <li> 1 si la coordenada y del punto es mayor que la del punto pasado como parámetro </li>
    * <li> 0 si ambas cordenadas y son iguales
    * <li> -1 si la coordenada y del punto es menor que la del punto pasado como parámetro </li>
    * </ul>
    * @see Mat#EPSILON
  **/
    public int comparaY(Punto2D otro)
    {
       if (Mat.absoluto(this.y()-otro.y()) < Mat.EPSILON)
          return 0; 
       if (this.y() > otro.y())
           return 1;
       else
           return -1;
    }
    
    /** Comparación con otro punto según la coordenada x. 
    * @param otro punto con el que comparar
    * @return según el resultado se devolverá:
    * <ul>
    * <li> 1 si la coordenada x del punto es mayor que la del punto pasado como parámetro </li>
    * <li> 0 si ambas cordenadas x son iguales
    * <li> -1 si la coordenada x del punto es menor que la del punto pasado como parámetro </li>
    * </ul>
    * @see Mat#EPSILON
  **/
    public int comparaX(Punto2D otro)
    {
       if (Mat.absoluto(this.x()-otro.x()) < Mat.EPSILON)
          return 0; 
       if (this.x() > otro.x())
           return 1;
       else
           return -1;
    }
    
  /** Posición del punto respecto al segmento orientado formado por otros dos. 
    * La posición se obtiene a partir del sentido del área del triángulo cuyos
    * vertices son los tres puntos
    * @param p1 primer punto respecto al cual se desea obtener la posición
    * @param p2 segundo punto respecto al cual se desea obtener la posición
    * @return una constante indicando la posición (Mat.IZQUIERDA, Mat.DERECHA,
    * , Mat.COLIENAL)
    * @exception GeomException si los dos puntos respecto a los que se desea obtener
    * la posición relativa son iguales
    * @see Triangulo2D#sentido()
    * @see Mat#IZQUIERDA
    * @see Mat#DERECHA
    * @see Mat#COLINEAL
    */
    public int posicionRelativa(Punto2D p1, Punto2D p2) throws GeomException {
        int sentido = Mat.COLINEAL;
        
        if (p1.equals(p2))
            throw new GeomException("posicionRelativa (Punto2D, Punto2D): Los puntos con los que comparar son iguales");
        else 
            sentido = new Triangulo2D(p1, p2, this).sentido();
        
        return sentido;
    }
    
  /** Traslada el punto en el plano a partir de un determinado incremento
    * en cada coordenada cartesiana
    * @param incX incremento o desplazamiento en el eje x
    * @param incY incremento o desplazamiento en el eje y
    * @return el punto trasladado
    */
    public Punto2D trasladar (double incX, double incY) {
        hx += incX*hz;
        hy += incY*hz;
        return this;
    }
    
  /** Traslada el punto en el plano aplicando el mismo incremento en cada
    * eje
    * @param incr incremento o desplazamiento que será aplicado tanto al eje x 
    * como al eje y
    * @return el punto trasladado
    */
    public Punto2D trasladar (double incr) {
        hx += incr * hz;
        hy += incr * hz;
        return this;
    }
    
  /** Traslada el punto en la dirección y a la distancia determinados por un 
    * vector
    * @param vector el vector que define la traslación del punto
    * @return el punto trasladado
    * @see Vector2D#anguloEjeX()
    * @see Vector2D#magnitud()
    */
    public Punto2D trasladar (Vector2D vector)
    {
        if (Mat.absoluto(vector.despX()) > Mat.EPSILON)
            hx += (Math.cos(vector.anguloEjeX()) * vector.magnitud())*hz;
        if (Mat.absoluto(vector.despY()) > Mat.EPSILON)
            hy += (Math.cos(vector.anguloEjeY()) * vector.magnitud())*hz;
        return this;
    }
    
  /** Traslación del punto a partir de las coordenadas polares (se traslada el
    * punto una determinada distancia en un determinado ángulo)
    * @param angulo dirección de traslación del punto
    * @param dist distancia a la que se moverá el punto de su posición original
    * siguiendo el ángulo indicado
    * @return el punto trasladado
    */
    public Punto2D trasladarPolar (double angulo, double dist) {
        hx += (Math.cos(angulo) * dist)*hz;
        hy += (Math.sin(angulo) * dist)*hz;
        return this;
    }
    
  /** Determina el centro de gravedad del punto (el propio punto)
    * @return el propio punto
    */
    Punto2D centroGravedad() {
        return this;
    }
    
  /** Escalado de un punto. El escalado para el punto en realidad supondrá una
    * traslación
    * @param escala factor de escalado
    * @return el punto escalado
    * @exception GeomException si el factor de escalado es cero
    */
    public Punto2D escalado(double escala) throws GeomException{
        if (escala <= 0)
            throw new GeomException("escalado (double) : El factor de escalado debe ser mayor de cero");
        else {
            hx *= escala;
            hy *= escala;
        }
        return this;
    }
    
  /** Escalado de un punto con distinto factor de escalado para cada coordenada. 
    * El escalado para un punto en realidad supondrá una traslación
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return el punto escalado
    * @exception GeomException si alguno de los factores de escalado es cero
    */
    public Punto2D escalado(double sx, double sy) throws GeomException {
        if (sx <= 0 || sy <= 0)
            throw new GeomException("escalado (double, double): Los factores de escalado deben sermayores de cero");
        else {
            hx *= sx;
            hy *= sy;
        }
        return this;
    }
    
  /** Rota el punto respecto del origen de coordenadas
    * @param radio angulo de giro en radianes. Si es positivo el giro será
    * en sentido antihorario y en caso contrario lo será en sentido horario
    * @return el punto girado
    */
    public Punto2D gira(double radio) {
        double aux = hx;
        hx = hx*Math.cos(radio) - hy*Math.sin(radio);
        hy = aux*Math.sin(radio) + hy*Math.cos(radio);
        return this;
    }
    
  /** Rota el punto respecto a otro. Para ello 
    * trasladamos el punto que deseamos girar como si hubieramos trasladado
    * el otro al origen de coordenadas, realizamos la rotación, y volvemos a 
    * trasladar el punto, como si trasladaramos el punto en el origen de coordenadas
    * a su posición inicial.
    * @param origen punto respecto el cual se hará el giro
    * @radio angulo de giro en radianes. Si es positivo el giro será en sentido
    * antihorario y en caso contrario lo será en sentido horario
    * @return el punto girado
    * @see Punto2D#trasladar(double, double)
    * @see Punto2D#gira(double)
    */
    public Punto2D gira(Punto2D origen, double radio) {
        // Trasladamos el punto a partir del cual se realizará la rotación
        // al origen
        this.trasladar(-origen.x(), -origen.y());
        // Aplicamos la rotación respecto al origen
        this.gira(radio);
        // Volvemos a trasladar el punto
        this.trasladar(origen.x(), origen.y());
        return this;
    }
    
  /** Aplica una matriz de transformación al punto. La matriz debe ser cuadrada 
    * y de orden dos (para aplicarla a las coordenadas cartesianas) o tres (en
    * cuyo caso se aplica a las coordenadas homogéneas). Aplicar la matriz de 
    * transformación significa realizar una multiplicación de dicha matriz por 
    * el vector formado por las coordenadas del punto
    * @param matriz matriz de transformación
    * @return el punto transformado
    * @exception GeomException si la matriz de transformación no es cuadrada, o 
    * es cuadrada pero su orden es distinto de dos o tres
    * @see Mat#matrizCuadrada(double[][])
    */
    public Punto2D transforma(double matriz[][]) throws GeomException {
        int orden = Mat.matrizCuadrada(matriz);
        
        if (orden != 2 && orden != 3)
            throw new GeomException("trasnforma (double[][]): La matriz debe ser cuadrada y de orden 2 o 3");
        else
            if (orden == 2) {
                // Aplicamos la matriz a las coordenadas cartesianas
                double aux = hx;
                hx = (matriz[0][0] * hx + matriz[0][1] * hy)*hz;
                hy = (matriz[1][0] * aux + matriz[1][1] * hy)*hz;
            }
            else {
                // Aplicamos la matriz a las coordenadas homgéneas
                double auxX = hx, auxY = hy;
                hx = matriz[0][0] * hx + matriz[0][1] * hy + matriz[0][2] * hz;
                hy = matriz[1][0] * auxX + matriz[1][1] * hy + matriz[1][2] * hz;
                hz = matriz[2][0] * auxX + matriz[2][1] * auxY + matriz[2][2] * hz;
            }
        return this;
    }
    
  /** Realiza la conversión del objeto de tipo Punto2D a una cadena
    * @return una cadena especificando el tipo de objeto (punto) y sus coordenadas
    * cartesianas
    */
    public String toString () {
        return nombre + " - Punto2D: (x="+hx/hz+",y="+hy/hz+")";
    }
    
    private void dibujo (Graphics g, boolean coordenadas, Color color) {
        DecimalFormat formato = new DecimalFormat("###,##0.0");
        
        g.setColor(color);
        g.fillOval((int)(hx/hz)-2,-(int)(hy/hz)-2,4,4);
        if (coordenadas)
        {
            g.setColor(Color.blue);
            g.drawString("("+ formato.format(hx/hz) +"-"+ formato.format(-(hy/hz))+")",(int)(hx/hz)+7,-(int)(hy/hz)+7);
        }
    }
    
    public void dibujar(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.red);
    }
    
    public void dibujarResultado(Graphics g) {
        g.setColor(Color.blue);
        g.fillOval((int)(hx/hz)-2,-(int)(hy/hz)-2,4,4);
    }
    
    public void dibujarSeleccionado(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.green);
    }
   
    public void dibujarNombre (Graphics g) {
        g.setColor(Color.magenta);
        g.drawString(nombre, (int)(hx/hz)-7, -(int)(hy/hz)-7);
    }
    
    public void dibujarXor (Graphics g) {
        DecimalFormat formato = new DecimalFormat("###,##0.0");
        
        g.setColor(Color.white);
        g.fillOval((int)(hx/hz)-2,-(int)(hy/hz)-2,4,4);
        g.setColor(Color.white);
        g.drawString("("+ formato.format(hx/hz) +"-"+ formato.format(-(hy/hz))+")",(int)(hx/hz)+7,-(int)(hy/hz)+7);
    }
    
    public void dibujarCentrado (Graphics g, int anchura, int altura) {
        DecimalFormat formato = new DecimalFormat("###,##0.0");
        
        g.setColor(Color.red);
        g.fillOval((int)(hx/hz)*10-2+anchura,(int)(-(hy/hz)*10+altura)-2,4,4);
        g.setColor(Color.blue);
        g.drawString("("+ formato.format(hx/hz) +"-"+ formato.format(hy/hz)+")",(int)(hx/hz)*10+anchura+7,(int)(-(hy/hz)*10+altura)+7);
    }
    
  /** Obtención de la coordenada cartesiana x 
    * @return coordenada cartesiana en el eje x
    */
    public double x() {
        return hx/hz;
    }
    
  /** Obtención de la coordenada cartesiana y 
    * @return coordenada cartesiana en el eje y
    */
    public double y() {
        return hy/hz;
    }
    
  /** Obtención de la coordenada homogénea x 
    * @return coordenada homogénea en el eje x
    */
    public double hx() {
        return hx;
    }
    
  /** Obtención de la coordenada homogénea y 
    * @return coordenada homogénea en el eje y
    */
    public double hy() {
        return hy;
    }
    
  /** Obtención de la coordenada homogénea z 
    * @return coordenada homogénea en el eje z
    */
    public double hz() {
        return hz;
    }
    
  /** Obtiene la coordenada homogénea cuyo indice numérico es el que se pasa como
    * parámetro. Dicho indice i debe cumplir 0<=i<=2, correspondiéndose el valor
    * 0 con la coordenada x, el 1 con la coordenada y y el 2 con la coordenada
    * z
    * @param i indice numérico de la coordenada que se desea obtener
    * @return la coordenada homogénea solicitada
    * @exception GeomException si el índice es menor de 0 o mayor de 2
    * @see Punto2D#hx()
    * @see Punto2D#hy()
    * @see Punto2D#hz()
    */
    public double homogenea(int i) throws GeomException {
        double retorno = 0;
        
        if (i<0 || i>2)
           throw new GeomException("homogenea (int): El índice proporcionado debe encontrarse entre 0 y 2");
        else 
            switch (i) {
                case 0: retorno =  hx(); break; 
                case 1: retorno =  hy(); break; 
                case 2: retorno =  hz(); 
            }
            
        return retorno;
    }
    
  /** Obtiene la coordenada cartesiana cuyo índice numérico es el que se pasa como
    * parámetro. Dicho índice debe ser 0 ó 1, correspondiéndose el valor 0 con la
    * coordenada x y el valor 1 con la coordenada y
    * @param i índice numérico de la coordenada que se desea obtener
    * @return la coordenada cartesiana solicitada
    * @exception GeomException si el índice es distinto de 0 y de 1
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public double cartesiana(int i) throws GeomException {
        double retorno = 0;
        
        if (i != 0 && i != 1) 
            throw new GeomException("cartesiana (int): El índice proporcionado debe ser 0 ó 1");
        else
            switch (i) {
                case 0: retorno =  x(); 
                case 1: retorno =  y(); 
            }
            
        return retorno;
    }
    
  /** Obtiene la caja contenedora que contiene al punto
    * @return una caja contenedora conteniendo al punto
    * @see Caja2D#Caja2D(Punto2D)
    */
    public Caja2D cajaContenedora() {
        return new Caja2D(this);
    }
}