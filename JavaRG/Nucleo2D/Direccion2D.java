/*
 * Direccion2D.java
 *
 * Created on 14 de noviembre de 2000, 23:10
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;

/** Un objeto de la clase <code>Direccion2D</code> es un vector en dos dimensiones
 * del cual obviamos su longitud. Pueden ser vistos como vectores unitarios, 
 * aunque internamente no existe normalizaci�n. Pueden tambi�n ser vistos como �ngulos.
 * Su implementaci�n se basa en desplazamientos, de la misma forma en que se
 * implementa un vector
 *
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico Vector2D
 */
public class Direccion2D extends ObjetoGeometrico {

  /** 
    * desplazamiento en el eje x 
    */
    double dx;
  /**
    * desplazamiento en el eje y
    */
    double dy;

  /**
    * Constructor por defecto de una direcci�n. La direcci�n por defecto
    * formar� cero grados respecto al eje x
    * @return una direcci�n de cero grados respecto al eje x
    */
    public Direccion2D() {
        dx = 1; dy = 0;
    }

 /** Constructor de la direcci�n a partir de un punto. La direcci�n vendr� definida
   * por el �ngulo del vector entre el origen de coordenadas y el punto pasado
   * como par�metro
   * @param p punto a partir del cual construir la direcci�n
   * @return una nueva direcci�n cuyos desplazamientos en el eje x y en el eje y
   * vendr�n determinados por las coordenadas del punto pasado como par�metro
   * @exception GeomException si el punto pasado como par�metro es el origen
   * de coordenadas
   * @see Punto2D#x() 
   * @see Punto2D#y()
   */
    public Direccion2D(Punto2D p) throws GeomException {
        if (p.x() == 0 && p.y() == 0)
        throw new GeomException("Direccion2D (Punto2D): C�lculo de una direccion a partir de dos puntos con las mismas coordenadas");
        else {
            dx = p.x();
            dy = p.y();
        }
    }

  /** Constructor de la direcci�n a partir de las coordenadas cartesianas de un 
   * punto. La direcci�n vendr� definida
   * por el �ngulo del vector entre el origen de coordenadas y el punto pasado
   * como par�metro
   * @param x coordenada cartesiana x del punto a partir del cual construir la direcci�n
   * @param y coordenada cartesiana y del punto a partir del cual construir la direcci�n
   * @return una nueva direcci�n cuyos desplazamientos en el eje x y en el eje y
   * vendr�n determinados por las coordenadas del punto pasado como par�metro
   * @exception GeomException si el punto pasado como par�metro es el origen
   * de coordenadas
   */
   public Direccion2D (double x, double y) throws GeomException {
        if (x == 0 && y == 0)
        throw new GeomException("Direccion2D (double. double): C�lculo de una direcci�n a partir de dos puntos con las mismas coordenadas");
        else {
            dx = x;
            dy = y;
        }
    }

  
  /** Constructor de la direcci�n a partir de las coordenadas homog�neas de un 
   * punto. La direcci�n vendr� definida
   * por el �ngulo del vector entre el origen de coordenadas y el punto pasado
   * como par�metro
   * @param hx coordenada homog�nea x del punto a partir del cual construir la direcci�n
   * @param hy coordenada homog�nea y del punto a partir del cual construir la direcci�n
   * @param hz coordenada homog�nea z del punto a partir del cual construir la direcci�n
   * @return una nueva direcci�n cuyos desplazamientos en el eje x y en el eje y
   * vendr�n determinados por las coordenadas del punto pasado como par�metro
   * @exception GeomException si el punto pasado como par�metro es el origen
   * de coordenadas
   */
    public Direccion2D(double hx, double hy, double hz) throws GeomException {
        if (hz == 0)
        throw new GeomException("Direccion2D (double, double, double): La coordenada homogenea en el eje z vale 0");
        else if (hx/hz == 0 && hy/hz == 0)
        throw new GeomException("Direccion2D (double, double, double): c�lculo de una direcci�n a partir de dos puntos con las mismas coordenadas");
        else {
            dx = hx/hz;
            dy = hy/hz;
        }
    }

 /** Constructor de la direcci�n a partir de dos puntos. La direcci�n vendr� 
   * definida por el �ngulo del vector definido por los dos puntos
   * @param p1 primer punto a partir del cual construir la direcci�n
   * @param p2 segundo punto a partir del cual construir la direcci�n
   * @return una nueva direcci�n cuyos desplazamientos en el eje x e y se obtienen
   * a partir de la diferencia entre las coordenadas de los dos puntos
   * @exception GeomException si los dos puntos tienen las mismas coordenadas
   * @see Punto2D#x()
   * @see Punto2D#y()
   */
    public Direccion2D(Punto2D p1, Punto2D p2) throws GeomException {
        if (p1.equals(p2))
        throw new GeomException ("Direccion2D (Punto2D, Punto2D): Calculo de una direcci�n a partir de dos puntos con las mismas coordenadas");
        else {
            dx = p2.x() - p1.x();
            dy = p2.y() - p1.y();
        }
    }

  /** Constructor de la direcci�n a partir de las coordenadas cartesianas de
   * dos puntos. La direcci�n vendr� 
   * definida por el �ngulo del vector definido por los dos puntos
   * @param x1 coordenada cartesiana x del primer punto
   * @param y1 coordenada cartesiana y del primer punto
   * @param x2 coordenada cartesiana x del segundo punto
   * @param y2 coordenada cartesiana y del segundo punto
   * @return una nueva direcci�n cuyos desplazamientos en el eje x e y se obtienen
   * a partir de la diferencia entre las coordenadas de los dos puntos
   * @exception GeomException si los dos puntos tienen las mismas coordenadas
   * @see Punto2D#Punto2D(double, double)
   */
    public Direccion2D(double x1, double y1, double x2, double y2) throws GeomException {
        if (new Punto2D(x1, y1).equals(new Punto2D(x2,y2)))
        throw new GeomException("Direccion2D (double, double, double, double): c�lculo de una direcci�n a partir de dos puntos con las mismas coordenadas");
        else {
            dx = x2 - x1;
            dy = y2 - y1;
        }
    }

  /** Constructor de la direcci�n a partir de las coordenadas homog�neas de
   * dos puntos. La direcci�n vendr� 
   * definida por el �ngulo del vector definido por los dos puntos
   * @param hx1 coordenada homog�nea x del primer punto
   * @param hy1 coordenada homog�nea y del primer punto
   * @param hz1 coordenada homog�nea z del primer punto
   * @param hx2 coordenada homog�nea x del segundo punto
   * @param hy2 coordenada homog�nea y del segundo punto
   * @param hz2 coordenada homog�nea z del segundo punto
   * @param hx3 coordenada homog�nea x del tercer punto
   * @param hy3 coordenada homog�nea y del tercer punto
   * @param hz3 coordenada homog�nea z del tercer punto 
   * @return una nueva direcci�n cuyos desplazamientos en el eje x e y se obtienen
   * a partir de la diferencia entre las coordenadas de los dos puntos
   * @exception GeomException si los dos puntos tienen las mismas coordenadas o si
   * la coordenada homog�nea de alguno de los puntos es cero
   * @see Punto2D#Punto2D(double, double, double)
   */
    public Direccion2D(double hx1, double hy1, double hz1, double hx2, double hy2, double hz2) throws GeomException {
        if (hz1 == 0 || hz2 == 0)
        throw new GeomException("Direccion2D (double, double, double, double, double, double): la coordenada homogenea z de alguno de los puntos era cero");
        else if (new Punto2D(hx1, hy1, hz1).equals(new Punto2D(hx2, hy2, hz2)))
        throw new GeomException("Direccion2D (double, double, double, double, double, double): c�lculo de una direcci�n a partir de dos puntos con las mismas coordenadas");
        else {
            dx = hx2/hz2 - hx1/hz1;
            dy = hy2/hz2 - hy1/hz1;
        }
    }

  /** Constructor de la direcci�n a partir de un vector. La direcci�n tendr� 
    * el �ngulo formado por el vector con el eje x
    * @param vector el vector a partir del cual construir la direcci�n
    * @return una nueva direcci�n con el mismo �ngulo que el vector pasado
    * como par�metro
    * @see Vector2D#despX()
    * @see Vector2D#despY()
    */
    public Direccion2D (Vector2D vector) {
        dx = vector.despX();
        dy = vector.despY();
    }

  /** Constructor de la direcci�n a partir de una recta. La direcci�n tendr� 
    * el �ngulo formado por el vector director de la recta con el eje x
    * @param recta la recta a partir de la cual construimos la direcci�n
    * @return una nueva direcci�n con el mismo �ngulo que el vector director
    * de la recta pasada como par�metro
    * @see Recta2D#director()
    * @see Vector2D#despX()
    * @see Vector2D#despY()
    */
    public Direccion2D (Recta2D recta) {
        dx = (recta.director()).despX();
        dy = (recta.director()).despY();
    }
    
  /** Constructor de la direcci�n a partir de un rayo. La direcci�n tendr�
    * el �ngulo formado por el vector director del rayo con el eje x
    * @param rayo el rayo a partir del cual contruimos la direcci�n
    * @return una nueva direcci�n con el mismo �ngulo que el vector director
    * del rayo pasado como par�metro
    * @see Rayo2D#director()
    * @see Vector2D#despX()
    * @see Vector2D#despY()
    */
    public Direccion2D (Rayo2D rayo) {
        dx = (rayo.director()).despX();
        dy = (rayo.director()).despY();
    }
    
  /** Constructor de la direcci�n a partir de un segmento. La direcci�n se construir� a partir
    * del vector definido entre los puntos inicial y final del segmento
    * @param segmento el semento a partir del cual construir la direcci�n
    * @return una nueva direcci�n cuyos desplazamietos se obtienen por la diferencia
    * de coordenadas entre el punto final e inicial del segmento
    * @see Segmento2D#fin()
    * @see Segmento2D#comienzo()
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public Direccion2D (Segmento2D segmento) {
        dx = (segmento.fin()).x() - (segmento.comienzo()).x();
        dy = (segmento.fin()).y() - (segmento.comienzo()).y();
    }

  /** Constructor de copia de <code>Direccion2D</code>. Construye una nueva direcci�n a 
    * partir de otra ya existente.
    * @param original direcci�n a partir de la cual construir la nueva direcci�n
    * @return una nueva direcci�n con los mismos desplazamientos que la direcci�n
    * pasada como par�metro
    * @see Direccion2D#despX()
    * @see Direccion2D#despY()
    */
    public Direccion2D (Direccion2D original) {
        this.dx = original.despX();
        this.dy = original.despY();
    }

  /**
    * Comparaci�n con otro objeto geom�trico. Una direcci�n es igual a un objeto
    * geom�trico si dicho objeto es otra direcci�n y las dos direcciones tienen
    * el mismo �ngulo respecto al eje x (o lo que es lo mismo, igual �ngulo
    * respecto al eje y)
    * @param obj objeto geom�trico con el que se desea comparar la direcci�n
    * @return un valor booleano indicando si la direcci�n es igual al objeto geom�trico
    * pasado como par�metro
    * @see Direccion2D#anguloEjeX()
    * @see Mat#EPSILON
    * @see Mat#absoluto(double)
    */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Direccion2D) {
            Direccion2D dir = (Direccion2D) obj;
            if (Mat.absoluto(this.anguloEjeX() - dir.anguloEjeX()) > Mat.EPSILON)
            return false;
            else return true;
        }
        else return false;
    }

  /**
    * Comparaci�n con otro objeto geom�trico. Una direcci�n es distinta a un objeto
    * geom�trico si dicho objeto no es otra direcci�n o las dos direcciones no tienen
    * el mismo �ngulo respecto al eje x (o lo que es lo mismo, diferente �ngulo
    * respecto al eje y)
    * @param obj objeto geom�trico con el que se desea comparar la direcci�n
    * @return un valor booleano indicando si la direcci�n es distinto al objeto geom�trico
    * pasado como par�metro
    * @see Direccion2D#equals(ObjetoGeometrico)
    */  
    public boolean not_equals (ObjetoGeometrico obj) {
        return !equals(obj);
    }
       
  /** Determina si la direcci�n es mayor a otra pasada como par�metro. El orden
    * se establece a partir del eje x en sentido antihorario
    * @param dir direcci�n con la que se desea comparar
    * @return un valor booleano indicando si la direcci�n es mayor a la pasada
    * como par�metro
    * @see Direccion2D#anguloEjeX()
    */
    public boolean mayorQue (Direccion2D dir) {
        double anguloThis, anguloDir;

        anguloThis = this.anguloEjeX();
        anguloDir = dir.anguloEjeX();
        if (anguloThis < 0) anguloThis = anguloThis + 2*Math.PI;
        if (anguloDir < 0) anguloDir = anguloDir + 2*Math.PI;
        if (anguloThis > anguloDir)
        return true;
        else
        return false;
    }
    
  /** Determina si la direcci�n es mayor o igual a otra pasada como par�metro. El orden
    * se establece a partir del eje x en sentido antihorario
    * @param dir direcci�n con la que se desea comparar
    * @return un valor booleano indicando si la direcci�n es mayor o igual a la pasada
    * como par�metro
    * @see Direccion2D#equals(ObjetoGeometrico)
    * @see Direccion2D#mayorQue(Direccion2D)
    */ 
    public boolean mayorIgualQue(Direccion2D dir) {
        return (this.equals(dir) || this.mayorQue(dir));
    }
    
  /** Determina si la direcci�n es menor a otra pasada como par�metro. El orden
    * se establece a partir del eje x en sentido antihorario
    * @param dir direcci�n con la que se desea comparar
    * @return un valor booleano indicando si la direcci�n es menor a la pasada
    * como par�metro
    * @see Direccion2D#anguloEjeX()
    */
    public boolean menorQue (Direccion2D dir) {
        double anguloThis, anguloDir;

        anguloThis = this.anguloEjeX();
        anguloDir = dir.anguloEjeX();
        if (anguloThis < 0) anguloThis = anguloThis + 2*Math.PI;
        if (anguloDir < 0) anguloDir = anguloDir + 2*Math.PI;
        if (anguloThis < anguloDir)
        return true;
        else
        return false;
    }

  /** Determina si la direcci�n es menor o igual a otra pasada como par�metro. El orden
    * se establece a partir del eje x en sentido antihorario
    * @param dir direcci�n con la que se desea comparar
    * @return un valor booleano indicando si la direcci�n es menor o igual a la pasada
    * como par�metro
    * @see Direccion2D#equals(ObjetoGeometrico)
    * @see Direccion2D#menorQue(Direccion2D)
    */ 
    public boolean menorIgualQue(Direccion2D dir) {
        return (this.equals(dir) || this.menorQue(dir));
    }
    
    
  /** 
    * Determina si la direcci�n se encuentra entre otras dos, en sentido horario
    * y de forma estricta. Una direcci�n estar� entre otras dos de forma estricta
    * si su �ngulo no es igual al de ninguna de las otras dos
    * @param dir1 primera direcci�n
    * @param dir2 segunda direcci�n
    * @return un valor booleano indicando si la direcci�n se encuentra entre las
    * otras dos pasadas como par�metro en sentido horario y de forma estricta
    * @see Direccion2D#menorQue(Direccion2D)
    * @see Direccion2D#mayorQue(Direccion2D)
    */
    public boolean sentidoHorarioEstricto(Direccion2D dir1, Direccion2D dir2) {
        if (dir1.mayorQue(dir2))
            return (this.menorQue(dir1) && this.mayorQue(dir2));
        else
            return (this.menorQue(dir1) || this.mayorQue(dir2));
    }

  /** 
    * Determina si la direcci�n se encuentra entre otras dos, en sentido antihorario
    * y de forma estricta. Una direcci�n estar� entre otras dos de forma estricta
    * si su �ngulo no es igual al de ninguna de las otras dos
    * @param dir1 primera direcci�n
    * @param dir2 segunda direcci�n
    * @return un valor booleano indicando si la direcci�n se encuentra entre las
    * otras dos pasadas como par�metro en sentido antihorario y de forma estricta
    * @see Direccion2D#mayorQue(Direccion2D)
    * @see Direccion2D#menorQue(Direccion2D)
    */
    public boolean sentidoAntiHorarioEstricto(Direccion2D dir1, Direccion2D dir2) {
        if (dir2.mayorQue(dir1))
            return (this.mayorQue(dir1) && this.menorQue(dir2));
        else
            return (this.mayorQue(dir1) || this.menorQue(dir2));
    }

  /** 
    * Determina si la direcci�n se encuentra entre otras dos, en sentido horario.
    * @param dir1 primera direcci�n
    * @param dir2 segunda direcci�n
    * @return un valor booleano indicando si la direcci�n se encuentra entre las
    * otras dos pasadas como par�metro en sentido horario
    * @see Direccion2D#menorQue(Direccion2D)
    * @see Direccion2D#mayorQue(Direccion2D)
    * @see Direccion2D#equals(ObjetoGeometrico)
    */
    public boolean sentidoHorario(Direccion2D dir1, Direccion2D dir2) throws GeomException {
        if (this.equals(dir1) || this.equals(dir2))
            return true;
        else
            if (dir1.mayorQue(dir2))
                return (this.menorQue(dir1) && this.mayorQue(dir2));
            else
                return (this.menorQue(dir1) || this.mayorQue(dir2));
    }

  /** 
    * Determina si la direcci�n se encuentra entre otras dos, en sentido antihorario.
    * @param dir1 primera direcci�n
    * @param dir2 segunda direcci�n
    * @return un valor booleano indicando si la direcci�n se encuentra entre las
    * otras dos pasadas como par�metro en sentido antihorario
    * @see Direccion2D#mayorQue(Direccion2D)
    * @see Direccion2D#menorQue(Direccion2D)
    * @see Direccion2D#equals(ObjetoGeometrico)
    */
    public boolean sentidoAntiHorario(Direccion2D dir1, Direccion2D dir2) throws GeomException {
        if (this.equals(dir1) || this.equals(dir2))
            return true;
        else
            if (dir2.mayorQue(dir1))
                return (this.mayorQue(dir1) && this.menorQue(dir2));
            else
                return (this.mayorQue(dir1) || this.menorQue(dir2));
    }
    
  /** Calcula el �ngulo con signo entre dos direcciones. 
   * @param otra direcci�n con respecto a la cual se desea obtener el �ngulo 
   * @return el �ngulo con signo entre la direcci�n y la direcci�n pasada como 
   * par�metro, en radianes
   * @see Direccion2D#despX()
   * @see Direccion2D#despY() 
   */
    public double anguloEntreDirecciones(Direccion2D otra) {
        double cociente = (this.despX()*otra.despX() +  this.despY()*otra.despY())/ (this.hipotenusa() * otra.hipotenusa());
        double angulo = Math.acos(cociente);

        // Calculamos el signo del �ngulo
        if (this.despX()*otra.despY() - otra.despX()*this.despY() < 0 )
        angulo = (-1)*angulo;

        return angulo;
    }
    
  /** Obtiene el �ngulo con signo entre dos direcciones en grados. Dicho �ngulo
    * se obtendr� a partir del �ngulo en radianes.
    * @param otra direcci�n con respecto a la cual se desea obtener el �ngulo
    * @return el �ngulo con signo entre la direcci�n y la pasada como par�metro,
    * en grados
    * @see Direccion2D#anguloEntreDirecciones(Direccion2D)
    * @see Mat#rad2Grad(double)
    */
    public double gradosEntreDirecciones(Direccion2D otra) {
        return Mat.rad2Grad(this.anguloEntreDirecciones(otra));
    }

 /** Implementaci�n de la funci�n abstracta <i>distancia</i> de <code>ObjetoGeometrico</code>.
   * Simplemente devuelve -1
   */
    public double distancia(Punto2D punto) {
        return -1;
    }
    
 /** Obtenci�n de la direcci�n opuesta. La direcci�n opuesta es aquella cuyos
   * desplazamientos son los mismos pero en sentido contario
   * @return la direcci�n opuesta
   */
    public Direccion2D opuesto() {
        dy = - dy;
        dx = - dx;

        return this;
    }

  /** Rota la direcci�n el n�mero de radianes indicado. 
    * @param radio angulo de giro en radianes. Si es positivo el giro ser�
    * en sentido antihorario y en caso contrario lo ser� en sentido horario
    * @return la direcci�n rotada
    */
    public Direccion2D gira(double angulo) {

        double nuevoAngulo = angulo + anguloEjeX();

        dx = hipotenusa()*Math.cos(nuevoAngulo);
        dy = hipotenusa()*Math.sin(nuevoAngulo);

        return this;
    }

  /** Obtiene el desplazamiento en el eje x
    * @return desplazamiento en el eje x
    */
    public double despX() {
        return dx;
    }

  /** 
    * Obtiene el desplazamiento en el eje y 
    * @return desplazamiento en el eje y
    */
    public double despY() {
        return dy;
    }

  /** Funci�n que obtiene el desplazamiento cuyo �ndice num�rico se pasa como
    * par�metro. Dicho �ndice debe valer 0 si deseamos obtener el desplazamiento
    * en el eje x y 1 si lo que deseamos es el desplazamiento en el eje y
    * @param i �ndice num�rico del desplazamiento que se desea obtener
    * @return el desplazamiento solicitado
    * @exception GeomException si el �ndice es distinto de 0 y de 1
    */
    public double desp(int i) throws GeomException {
        double devuelto = 0;

        if (i != 0 && i != 1)
        throw new GeomException("desp (int): El �ndice proporcionado debe ser 0 o 1");
        else
        switch (i) {
            case 0:
            devuelto = dx;
            case 1:
            devuelto = dy;
        }
        return devuelto;
    }

  /** Obtiene la pendiente de la direcii�n. Dicha pendiente se obtiene como el 
    * cociente entre el desplazamiento en el eje y y el desplazamiento en el 
    * eje x. 
    * @return la pendiente de la direcci�n
    * @exception GeomException si la pendiente es infinita, es decir, si el 
    * desplazamiento en x es cero
    * @see Mat#absoluto(double)
    * @see Mat#EPSILON
    */
    public double pendiente() throws GeomException {
        if (Mat.absoluto(dx) < Mat.EPSILON)
        throw new GeomException("pendiente (): La pendiente es infinita");
        else
        return dy/dx;
    }

  /** Obtiene el �ngulo en radianes respecto al eje x. Ser� positivo si el
    * desplazamiento en el eje y tambi�n lo es, y negativo en otro caso.
    * @return el �ngulo en radianes respecto al eje x
    * @see Mat#cuadrado(double)
    */
    public double anguloEjeX() {
        double hipotenusa = Math.sqrt(Mat.cuadrado(dx) + Mat.cuadrado(dy));
        double angulo = Math.acos(dx / hipotenusa);
        if (dy<0) angulo = -angulo;
        return angulo;
    }

  /** Obtiene el �ngulo en grados respecto al eje x. Ser� positivo si el
    * desplazamiento en el eje y tambi�n lo es, y negativa en otro caso.
    * @return el �ngulo en grados respecto al eje x
    * @see Direccion2D#anguloEjeX()
    * @see Mat#rad2Grad(double)
    */
    public double gradosEjeX() {
        return Mat.rad2Grad(anguloEjeX());
    }

  /** Obtiene el �ngulo en radianes respecto al eje y. Ser� positivo si el
    * desplazamiento en el eje x tambi�n lo es, y negativo en otro caso.
    * @return el �ngulo en radianes respecto al eje y
    * @see Mat#cuadrado(double)
    */
    public double anguloEjeY() {
        double hipotenusa = Math.sqrt(Mat.cuadrado(dx) + Mat.cuadrado(dy));
        double angulo = Math.acos(dy/ hipotenusa);
        if (dx<0) angulo = -angulo;
        return angulo;
    }
    
  /** Obtiene el �ngulo en grados respecto al eje y. Ser� positivo si el
    * desplazamiento en el eje x tambi�n lo es, y negativo en otro caso.
    * @return el �ngulo en grados respecto al eje y
    * @see Direccion2D#anguloEjeY()
    * @see Mat#rad2Grad(double)
    */
    public double gradosEjeY() {
        return Mat.rad2Grad(anguloEjeY());
    }

  /** Realiza la conversi�n del objeto de tipo Direccion2D a una cadena
    * @return una cadena especificando el tipo de objeto (direcci�n) y los
    * grados formados con el eje x
    * @see Direccion2D#gradosEjeX()
    */  
    public String toString() {
        return nombre + " - Direccion2D: (�ngulo con X: " +  gradosEjeX() + " grados)";
    }

  /** M�todo privado para obtener la "magnitud" de una direcci�n. Te�ricamente 
    * una direcci�n no tiene magnitud, pero internamente s�, debido a que se 
    * define por un par de desplazamientos, y dicha magnitud ser� necesaria
    * para realizar algunos c�lculos
    * @return la magnitud del vector con los mismos desplazamientos en el eje x
    * y en el eje y que la direcci�n
    * @see Mat#cuadrado(double)
    */
    private double hipotenusa()
    {
        return Math.sqrt(Mat.cuadrado(dx) + Mat.cuadrado(dy));
    }
    
    public Vector2D vector()
    {
        return new Vector2D(dx,dy);
    }
}
