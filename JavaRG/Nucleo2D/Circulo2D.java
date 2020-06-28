/*
 * Circulo2D.java
 *
 * Created on 12 de diciembre de 2001, 21:03
 */

package JavaRG.Nucleo2D;

import JavaRG.*;
import JavaRG.Nucleo2D.*;
import java.awt.*;

/** Un objeto de la clase <code>Circulo2D</code> representa un c�rculo orientado
 * (su circunferencia asociada tiene un sentido horario o antihorario). Divide el 
 * espacio en una zona positva y otra negativa, quedando la positiva a la
 * izquierda de la circunferencia
 *
 * @author  Pablo Suau
 * @version 1.1
 * @see ObjetoGeometrico
 */
public class Circulo2D extends ObjetoGeometrico {

    /**
     * Centro del c�rculo
     */
    Punto2D centro;
    
    /** 
     * Radio de la circunferencia
     */
    double radio;
    
    /**
      * Sentido de la circunferencia asociada al c�rculo
      */
    int sentido;

    /**
     * Constructor por defecto del c�rculo, con centro en el origen de coordendas,
     * radio 0 y orientaci�n anithoraria
     * @return un nuevo c�rculo con centro en el origen de coordenadas, radio nulo y
     * sentido antihorario
     * @see Punto2D#Punto2D()
     * @see Mat#ANTIHORARIO
     */
    public Circulo2D()
    {
        centro = new Punto2D();
        radio = 0;
        sentido = Mat.ANTIHORARIO;
    }

  /** Constructor a partir de un punto (centro), un radio y un sentido. 
    * @param punto centro del c�rculo
    * @param r radio del c�rculo
    * @param o orientaci�n de la circunferencia
    * @return un nuevo c�rculo cuyo centro, radio y orientaci�n se corresponder�n     * con los pasados como par�metro
    * @exception GeomException si el radio es negativoo el sentido pasado como
    * par�metro no tiene un valor correcto
    * @see Mat#HORARIO
    * @see Mat#ANTIHORARIO
    * @see Punto2D#Punto2D(Punto2D)
    */
    public Circulo2D (Punto2D punto, double r, int o) throws GeomException {
        if (r < 0)
            throw new GeomException("Circulo2D (Punto2D, double, int): el radio debe ser mayor o igual que cero");
        else if (o != Mat.HORARIO || o != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (Punto2D, double, int): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
                centro = new Punto2D(punto);
                radio = r;
                sentido = o;
        }
    }

  /** Constructor a partir de las coordenadas cartesianas de un punto (centro), 
    * un radio y un sentido. 
    * @param px coordenada cartesiana x del centro
    * @param py coordenada cartesiana y del centro
    * @param o orientaci�n de la circunferencia
    * @param r radio del c�rculo
    * @return un nuevo c�rculo cuyo centro, radio y orientaci�n se corresponder�n     * con los pasados como par�metro
    * @exception GeomException si el radio es negativoo el sentido pasado como
    * par�metro no tiene un valor correcto
    * @see Mat#HORARIO
    * @see Mat#ANTIHORARIO
    * @see Punto2D#Punto2D(double, double)
    */
    public Circulo2D (double px, double py, int o, double r) throws GeomException {
        if (r < 0)
            throw new GeomException("Circulo2D (double, double, int, double): el radio debe ser mayor o igual que cero");
        else if (o != Mat.HORARIO || o != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (double, double, int, double): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
                centro = new Punto2D(px, py);
                radio = r;
                sentido = o;
        }
    }
    
  /** Constructor a partir de las coordenadas homog�neas de un punto (centro), 
    * un radio y un sentido. 
    * @param hx primera coordenada homog�nea del centro
    * @param hy segunda coordenada homog�nea del centro
    * @param hz tercera coordenada homog�nea del centro
    * @param r radio del c�rculo
    * @param o orientaci�n de la circunferencia
    * @return un nuevo c�rculo cuyo centro, radio y orientaci�n se corresponder�n     * con los pasados como par�metro
    * @exception GeomException si el radio es negativoo el sentido pasado como
    * par�metro no tiene un valor correcto
    * @see Mat#HORARIO
    * @see Mat#ANTIHORARIO
    * @see Punto2D#Punto2D(double, double, double)
    */
    public Circulo2D (double hx, double hy, double hz, double r, int o) throws GeomException {
        if (r < 0)
            throw new GeomException("Circulo2D (double, double, double, double, int): el radio debe ser mayor o igual que cero");
        else if (o != Mat.HORARIO || o != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (double, double, double, double, int): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
                centro = new Punto2D(hx, hy, hz);
                radio = r;
                sentido = o;
        }
    }
    
   /** Constructor del c�rculo orientado a partir de dos puntos que definen el radio
     * (siendo el primero el centro) y una orientaci�n.
     * @param punto1 centro del c�rculo
     * @param punto2 punto usado para calcular el radio
     * @param orientacion orientacion de la circunferencia
     * @return un nuevo c�rculo orientado cuyo centro es el primer punto, el 
     * radio es la distancia entre los dos puntos, y la orientaci�n es la pasada
     * como par�metro
     * @exception GeomException si la orientaci�n no es Mat.HORARIO ni Mat.ANITTIHORARIO
     * @see Mat#HORARIO
     * @see Mat#ANTIHORARIO
     * @see Punto2D#Punto2D(Punto2D)
     * @see Punto2D#distancia(Punto2D)
     */
    public Circulo2D (Punto2D punto1, Punto2D punto2, int orientacion) throws GeomException {
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (Punto2D, Punto2D, int): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
            centro = new Punto2D(punto1);
            radio = punto1.distancia(punto2);
            sentido = orientacion;
        }
    }
   
   /** Constructor del c�rculo orientado a partir de un segmento cuyos dos 
     * extremos definen el radio(siendo el primero el centro) y una 
     * orientaci�n.
     * @param segmento segmento a partir del cual obtener el centro y el radio
     * del c�rculo
     * @param orientacion orientacion de la circunferencia
     * @return un nuevo c�rculo orientado cuyo centro es el primer extremo del segmento, el 
     * radio es la distancia entre sus dos extremos, y la orientaci�n es la pasada
     * como par�metro
     * @exception GeomException si la orientaci�n no es Mat.HORARIO ni Mat.ANITTIHORARIO
     * @see Mat#HORARIO
     * @see Mat#ANTIHORARIO
     * @see Punto2D#Punto2D(Punto2D)
     * @see Segmento2D#comienzo()
     * @see Segmento2D#fin()
     * @see Punto2D#distancia(Punto2D)
     */
    public Circulo2D (Segmento2D segmento, int orientacion) throws GeomException {
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (Segmento2D, int): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
            centro = new Punto2D(segmento.comienzo());
            radio = centro.distancia(segmento.fin());
            sentido = orientacion;
        }
    }
    
   /** Constructor del c�rculo orientado a partir de un punto (centro) y una orientaci�n
     * @param punto punto que ser� el centro del c�rculo
     * @param orientacion sentido de la circunferencia
     * @return un nuevo c�rculo orientado con centro igual al primer par�metro, 
     * radio 0 y orientaci�n igual al segundo par�metro
     * @exception GeomException si el sentido pasado como par�metro es distinto
     * de Mat.HORARIO y Mat.ANTIHORARIO
     * @see Mat#HORARIO
     * @see Mat#ANTIHORARIO
     * @see Punto2D#Punto2D(Punto2D)
     */
    public Circulo2D (Punto2D punto, int orientacion) throws GeomException { 
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (Punto2D, int): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
            centro = new Punto2D(punto);
            radio = 0;
            sentido = orientacion;
        }
    }
    
   /** Constructor del c�rculo orientado a partir de las coordenadas cartesianas
     * de un punto (centro) y una orientaci�n
     * @param px coordenada cartesiana x del punto que ser� el centro del c�rculo
     * @param py coordenada cartesiana y del punto que ser� el centro del c�rculo 
     * @param orientacion sentido de la circunferencia
     * @return un nuevo c�rculo orientado con centro igual al punto definido por
     * los dos primeros par�metros, radio 0 y orientaci�n igual al tercer par�metro
     * @exception GeomException si el sentido pasado como par�metro es distinto
     * de Mat.HORARIO y Mat.ANTIHORARIO
     * @see Mat#HORARIO
     * @see Mat#ANTIHORARIO
     * @see Punto2D#Punto2D(double, double)
     */
    public Circulo2D (double px, double py, int orientacion) throws GeomException { 
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (double, double, int): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
            centro = new Punto2D(px, py);
            radio = 0;
            sentido = orientacion;
        }
    }

   /** Constructor del c�rculo orientado a partir de las coordenadas homog�neas
     * de un punto (centro) y una orientaci�n
     * @param hx primera coordenada homog�nea del punto que ser� el centro del c�rculo
     * @param hy segunda coordenada homog�nea del punto que ser� el centro del c�rculo 
     * @param hz tercera coordenada homog�nea del punto que ser� el centro del c�rculo
     * @param orientacion sentido de la circunferencia
     * @return un nuevo c�rculo orientado con centro igual al punto definido por
     * los tres primeros par�metros, radio 0 y orientaci�n igual al cuarto par�metro
     * @exception GeomException si el sentido pasado como par�metro es distinto
     * de Mat.HORARIO y Mat.ANTIHORARIO
     * @see Mat#HORARIO
     * @see Mat#ANTIHORARIO
     * @see Punto2D#Punto2D(double, double, double)
     */
    public Circulo2D (double hx, double hy, double hz, int orientacion) throws GeomException { 
        if (orientacion != Mat.HORARIO && orientacion != Mat.ANTIHORARIO)
            throw new GeomException("Circulo2D (double, double, double, int): el sentido debe ser Mat.HORARIO o Mat.ANTIHORARIO");
        else
        {
            centro = new Punto2D(hx, hy, hz);
            radio = 0;
            sentido = orientacion;
        }
    }
    
   /** Constructor de copia del c�rculo orientado
     * @param circulo circulo a partir del cual se desea construir el nuevo 
     * c�rculo orientado
     * @return un nuevo c�rculo orientado cuyos centro, radio y orientaci�n
     * ser�n iguales a los del c�rculo pasado como par�metro
     * @see Punto2D#Punto2D(Punto2D)
     * @see Circulo2D#centroCirculo()
     * @see Circulo2D#radioCirculo()
     * @see Circulo2D#sentidoCirculo()
     */
    public Circulo2D (Circulo2D circulo) {
        centro = new Punto2D(circulo.centroCirculo());
        radio = circulo.radioCirculo();
        sentido = circulo.sentidoCirculo();
    }
    
   /** Comparaci�n con otro objeto geom�trico. Un circulo orientado es igual a
     * otro objeto geom�trico si �ste es un c�rculo orientado y coinciden en 
     * centro, radio y sentido de la circunferencia
     * @param obj el objeto geom�trico con el que se desea comparar el c�rculo
     * @return un valor booleano indicando si el c�rculo orientado es igual al objeto
     * geom�trico pasado como par�metro
     * @see Mat#EPSILON
     * @see Mat#absoluto(double)
     * @see Punto2D#equals(ObjetoGeometrico)
     */
    public boolean equals (ObjetoGeometrico obj) {
        if (obj==this)
        return true;
        if (obj instanceof Circulo2D) {
            Circulo2D circulo = (Circulo2D) obj;
            return (this.centro.equals(circulo.centro) &&
                    Mat.absoluto(this.radio - circulo.radio) < Mat.EPSILON &&
                    this.sentido == circulo.sentido);
        }
        else return false;
    }
   
    /** Comparaci�n con otro objeto geom�trico. Un circulo orientado es distinto a
     * otro objeto geom�trico si �ste no es un c�rculo orientado o no coinciden en 
     * centro, radio o sentido de la circunferencia
     * @param obj el objeto geom�trico con el que se desea comparar el c�rculo
     * @return un valor booleano indicando si el c�rculo orientado es distinto al objeto
     * geom�trico pasado como par�metro
     * @Circulo2D#equals(ObjetoGeometrico)
     */
    public boolean not_equals (ObjetoGeometrico obj) {
        return !equals(obj);
    }
    
  /** Obtiene el centro del c�rculo orientado
    * @return el centro del c�rculo
    */
    public Punto2D centroCirculo() {
        return centro;
    }
    
  /** Obtiene la coordenada cartesiana x del centro del c�rculo orientado
    * @return la coordenada cartesiana x del centro del c�rculo 
    * @see Punto2D#x()
    */
    public double centroCirculoX() {
        return centro.x();
    }
    
  /** Obtiene la coordenada cartesiana y del centro del c�rculo orientado
    * @return la coordenada cartesiana y del centro del c�rculo
    * @see Punto2D#y()
    */
    public double centroCirculoY() {
        return centro.y();
    }
    
  /** Obtiene la primera coordenada homog�nea del centro del c�rculo orientado
    * @return la primera coordenada homog�nea del centro del c�rculo
    * @see Punto2D#hx()
    */
    public double centroCirculoHx() {
        return centro.hx();
    }
    
  /** Obtiene la segunda coordenada homog�nea del centro del c�rculo orientado
    * @return la segunda coordenada homog�nea del centro del c�rculo
    * @see Punto2D#hy()
    */
    public double centroCirculoHy() {
        return centro.hy();
    }
    
  /** Obtiene la tercera coordenada homog�nea del centro del c�rculo orientado
    * @return la tercera coordenada homog�nea del centro del c�irculo
    * @see Punto2D#hz()
    */
    public double centroCirculoHz() {
        return centro.hz();
    }
    
  /** Obtiene el radio del c�rculo orientado
    * @return el radio del c�rculo
    */
    public double radioCirculo() {
        return radio;
    }
    
  /** Obtiene el sentido de la circunferencia asociada al c�rculo orientado
    * @return el sentido de la circunferencia
    */
    public int sentidoCirculo() {
        return sentido;
    }
    
  /** Determina si un punto se encuentra en la circunferencia del c�rculo 
    * orientado
    * @param punto el punto que se desea conocer si se enceuntra en la circunferencia
    * @return un valor booleano indicando si el punto pasado como par�metro se
    * encuentra contenido en la circunfeerencia del c�rculo
    * @see Mat#absoluto(double)
    * @see Punto2D#distancia(Punto2D)
    * @see Mat#EPSILON
    */
    public boolean circunferenciaContienePunto(Punto2D punto) {
        return (Mat.absoluto(punto.distancia(centro) - radio) < Mat.EPSILON);
    }
    
  /** Determina si un punto se encuentra en el interior del c�rculo orientado
    * @param punto el punto que se desea comprobar si se encuentra en el interior
    * del c�rculo orientado
    * @return un valor booleano indicando si el punto se encuentra en el interior
    * del c�rculo orientado
    * @Punto2D#distancia(Punto2D)
    */
    public boolean contienePunto(Punto2D punto) {
        return (punto.distancia(centro) <= radio);
    }
    
  /** Determina si un punto se encuentra en el interior del c�rculo orientado
    * sin contar la propia circunferencia
    * @param punto el punto que se desea comprobar si se encuentra en el interior
    * del c�rculo orientado pero no en la propia circunferencia
    * @return un valor booleano indicando si el punto se encuentra en el interior
    * del c�rculo pero no en la propia circunferencia
    * @see Circulo2D#circunferenciaContienePunto(Punto2D)
    * @see Circulo2D#contienePunto(Punto2D)
    */
    public boolean contienePuntoEstrictamente(Punto2D punto) {
        return (contienePunto(punto) && !circunferenciaContienePunto(punto));
    }
    
  /** Determina si un punto est� en la parte positiva del espacio definida 
    * por el sentido del c�rculo orientado. La parte positiva es la que queda
    * a la izquierda de la circunferencia
    * @param punto punto que se desea comprobar si se encuentra en la parte positiva
    * del plano definida por el sentido del c�rculo
    * @return un valor booelano indicando si el punto se encuentra en la parte positiva
    * del plano definida por el sentido del c�rculo
    * @see Mat#HORARIO
    * @see Mat#ANTIHORARIO
    * @see Circulo2D#contienePuntoEstrictamente(Punto2D)
    * @see Circulo2D#circunferenciaContienePunto(Punto2D)
    */
    public boolean partePositiva(Punto2D punto) {
        if (contienePuntoEstrictamente(punto))
        {
            if (sentido == Mat.ANTIHORARIO) return true;
            else return false;
        }
        else if (!circunferenciaContienePunto(punto))
        {
            if (sentido == Mat.HORARIO) return true;
            else return false;
        }
        else return false;
    }
  
  /** Determina si un punto est� en la parte negativa del espacio definida 
    * por el sentido del c�rculo orientado. La parte negativa es la que queda
    * a la derecha de la circunferencia
    * @param punto punto que se desea comprobar si se encuentra en la parte negativa
    * del plano definida por el sentido del c�rculo
    * @return un valor booelano indicando si el punto se encuentra en la parte negativa
    * del plano definida por el sentido del c�rculo
    * @see Mat#HORARIO
    * @see Mat#ANTIHORARIO
    * @see Circulo2D#contienePuntoEstrictamente(Punto2D)
    * @see Circulo2D#circunferenciaContienePunto(Punto2D)
    */
    public boolean parteNegativa(Punto2D punto) {
        if (contienePuntoEstrictamente(punto))
        {
            if (sentido == Mat.HORARIO) return true;
            else return false;
        }
        else if (!circunferenciaContienePunto(punto))
        {
            if (sentido == Mat.ANTIHORARIO) return true;
            else return false;
        }
        else return false;
    }  
    
  /** Determina si un punto se encuentra en la parte positiva o negativa seg�n
    * la divisi�n que hace del espacio el c�rculo orientado
    * @param punto punto que se desea determinar en que posici�n del espacio se
    * encuentra
    * @return un valor indicando si el punto se enceuntra en la parte positiva del 
    * espacio, en la negativa, o contenido en la circunferencia
    * @see Mat#IZQUIERDA
    * @see Mat#DERECHA
    * @see Mat#CONTENIDO
    * @see Circulo2D#partePositiva(Punto2D)
    * @see Circulo2D#parteNegativa(Punto2D)
    */
    public int posicionRelativa(Punto2D punto) {
        // Comprobamos primero si se encuentra en la parte negativa porque
        // hay m�s posibilidades de que un punto se encuentre en esa zona
        if (parteNegativa(punto)) return Mat.NEGATIVA;
        else 
        {
            if (partePositiva(punto)) return Mat.POSITIVA;
            else return Mat.CONTENIDO;
        }
    }
    
  /** Obtiene el c�rculo orientado opuesto, que es aquel que coincide en centro
    * y radio pero que tiene sentido opuesto
    * @return un c�rculo orientado en sentido inverso
    * @see Mat#HORARIO
    * @see Mat#ANTIHORARIO
    * @see Circulo2D#Circulo2D(Punto2D, double, int)
    */
    public Circulo2D opuesto() {
        try { // La excepci�n geom�trica del constructor del Circulo2D no 
              // se cumplir� nunca en este caso
        if (sentido == Mat.HORARIO)
            return new Circulo2D(centro, radio, Mat.ANTIHORARIO);
        else
            return new Circulo2D(centro, radio, Mat.HORARIO);
        } catch (GeomException e) {
            return null;
        }
    }
    
  /** Obtiene la caja contenedora que contiene al c�rculo
    * @return una caja contenedora conteniendo al c�rculo
    * @see Caja2D#Caja2D(Circulo2D)
    */
    public Caja2D cajaContenedora() {
        return new Caja2D(this);
    }
    
  /** Comprueba si el c�rculo orientado se trata de un c�rculo degenerado, lo 
    * cual es cierto si el radio es nulo
    * @return un valor booleano indicando si el c�rculo es degenerado
    * @see Mat#EPSILON
    */
    public boolean degenerado()
    {
        return (radio < Mat.EPSILON);
    }
  
  /** Obtiene la distancia del c�rculo a un punto. Esta distancia ser� el valor 
    * absoluto de la diferencia entre la distancia del punto al centro y el radio.
    * @param punto punto al que se desea obtener la distancia
    * @return la distancia del c�rculo al punto pasado como par�metro
    * @see Mat#absoluto(double)
    * @see Punto2D#distancia(Punto2D)
    */
    public double distancia(Punto2D punto) {
        double distCentro = punto.distancia(centro);
        if (distCentro <= radio)
            return distCentro;
        else
            return (distCentro - radio);
    }
    
  /** Traslada el c�rculo en el plano, aplicando el mismo desplazamiento
    * en ambos ejes. Se aplica una traslaci�n a su centro
    * @param desp desplazamiento en ambos ejes del centro del c�rculo
    * @return el c�rculo trasladado
    * @see Punto2D#trasladar(double)
    */
    public Circulo2D trasladar (double desp) {
        centro.trasladar(desp);
        return this;
    }
    
  /** Traslada el c�rculo en el plano, aplicando un desplazamiento distinto 
    * en cada uno de los ejes. Se aplica la traslaci�n a su centro
    * @param despx desplazamiento en el eje x
    * @param despy desplazamiento en el eje y
    * @return el c�rculo trasladado
    * @see Punto2D#trasladar(double, double)
    */
    public Circulo2D trasladar (double despx, double despy) {
        centro.trasladar(despx, despy);
        return this;
    }
    
  /** Traslada el c�rculo en la direcci�n y seg�n la direcci�n marcados por un vector
    * en el plano. Se aplica la traslaci�n a su centro
    * @param vector vector que indica la direcci�n y magnitud del desplaxamiento
    * @return el c�rculo trasladado
    * @see Punto2D#trasladar(Vector2D)
    */
    public Circulo2D trasladar (Vector2D vector) {
        centro.trasladar(vector);
        return this;
    }
    
  /** Escalado del c�rculo. El factor de esacalado se aplica al radio
    * @param incr factor de escalado
    * @return el c�rculo escalado
    */
    public Circulo2D escalado (double incr) {
        radio = radio * incr;
        return this;
    }
    
  /** Realiza la conversi�n del objeto de tipo Vector2D a una cadena
    * @return una cadena especificando el tipo de objeto (vector) y sus
    * componentes (desplazamiento en el eje x y desplazamiento en el eje y)
    */  
    public String toString() {
        String cadenaSentido;
        
        if (sentido == Mat.ANTIHORARIO)
            cadenaSentido = "Antihorario";
        else
            cadenaSentido = "Horario";
        return nombre + " - Circulo2D: ( centro: " + centro + " - radio: " + radio + " - sentido: " + cadenaSentido + " )";
    }
    
    private void dibujo(Graphics g, boolean coordenadas, Color color) {
        centro.dibujar(g, coordenadas);
        g.setColor(color);
        g.drawOval((int)(centro.x()-radio/1.0), -(int)(centro.y()+radio/1.0), (int)radio*2, (int)radio*2);
    }
    
    public void dibujar(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.black);
    }
    
    public void dibujarSeleccionado(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.green);
    }
        
    public void dibujarXor(Graphics g) {
        centro.dibujarXor(g);
        g.setColor(Color.white);
        g.drawOval((int)(centro.x()-radio/1.0), -(int)(centro.y()+radio/1.0), (int)radio*2, (int)radio*2);
    }
    
    public void dibujarNombre(Graphics g) {
        g.setColor(Color.magenta);
        g.drawString(nombre, (int)centro.x() - 15, -(int)centro.y() - 7);
    }
}

