/*
 * Poligono2D.java
 *
 * Created on 26 de diciembre de 2001, 17:53
 */

package JavaRG.Basica;

import JavaRG.Nucleo2D.*;
import JavaRG.Mat;
import JavaRG.Soporte.InterfazCirculador;
import java.awt.*;

/** Un polígono 2D es una cadena cerrada de vértices (objetos de la clase
 * Punto2D), que está definido en un sentido horario u antihorario
 *
 * @author  Pablo Suau
 * @version 1.0
 */
public class Poligono2D extends ObjetoBasico implements InterfazCirculador {
    
    /**
      * Constante indicando el máximo número de puntos del polígono
      */
    private static final int maxPuntos = 1000;
    /** 
      * Vector conteniendo a los vértices que definen al polígono
      */
     private Punto2D vertices[] = new Punto2D[maxPuntos];
  /**
    * Número de puntos de los que se compone el polígono
    */
    private int numPuntos;
  /**
    * Indica si el polígono es completo, es decir, si existe una arista entre el 
    * primer y el últim ovértice
    */
    private boolean poligonoCompleto;
  /**
    * Indica si el polígono es simple. El polígono será simple cuando no exista 
    * intersección entre ninguna de sus aristas
    */
    private boolean poligonoSimple;
    
  /** 
    * Constructor para crear un nuevo polígono vacío (sin ningún vértice 
    * @return un polígono vacío
    * @see Poligono2D#borrar()
    */
    public Poligono2D() {
        this.borrar();
        poligonoSimple = true;
    }
    
  /** Constructor para crear un nuevo polígono al azar con el número de lados 
    * especificado. La primera mitad de los vértices del polígono será dibujada
    * en la parte superior del área de dibujo, y la segunda en el área inferior.
    * Se añaden vértices con un incremento de x constante, resultado de dividir la 
    * anchura entre el número de puntos/2, y con un valor de y al azar, dependiendo
    * de si dicho vértice pertenece a la parte superior o a la inferior.
    * @param lados número de lados del polígono a dibujar
    * @param altura altura del área de dibujo
    * @param anchura anchura del área de dibujo
    * @return un nuevo polígono cerrado y simple con el número de lados indicado
    * @exception GeomException si el número de lados pasados como parámetro es menor
    * de tres, ya que en ese caso es imposible crear un polígono simple cerrado
    * @see Poligono2D#borrar()
    * @see Poligono2D#insertar(Punto2D)
    * @see Punto2D#Punto2D(double, double)
    */
    public Poligono2D(int lados, int altura, int anchura) throws GeomException {
        if (lados<=2)
            throw new GeomException("Poligono (int): El número de lados debe ser mayor de 2");
        else
        {
           do {
              this.borrar();
              poligonoSimple = true;
              // Insertamos todos los vértices , incrementalmente,
              // comprobando cada vez que se introduce alguno que el polígono continúa
              // siendo simple
              int incremento = 2*anchura/lados; 
              double x, y;
                       
              for (int pos=0;pos<lados;pos++)
                do {
                    if (!poligonoSimple) {
                        numPuntos--;
                        poligonoSimple = true;
                    }
                    y = -(altura/3)*java.lang.Math.random();
                    if (pos<lados/2)
                    {
                       x = pos*incremento + 5;
                       y = y - 5;
                    }
                    else
                    {
                       x = (anchura-10) + ((lados/2)-pos)*incremento + 5;
                       y = y - (altura-(altura/3));
                    }
                    this.insertar(new Punto2D(x,y));
                } while (!poligonoSimple);
            
              // Introducimos el último lado, para formar un polígono cerrado
              this.insertar(vertices[numPuntos-1]);
           } while (!poligonoSimple);
        }
    }
   
  /** Constructor de copia del polígono. Genera un nuevo polígono a partir de 
    * uno anterior
    * @param otro polígono a partir del cual se desea obtener el nuevo Poligono2D
    * @return un nuevo Poligono2D con los mismos vértices y atributos que el 
    * polígono pasado como parámetro
    */
    public Poligono2D(Poligono2D otro)
    {
        this.numPuntos = otro.numPuntos;
        this.poligonoCompleto = otro.poligonoCompleto;
        this.poligonoSimple = otro.poligonoSimple;
        for (int i=0; i<numPuntos; i++)
            this.vertices[i] = new Punto2D(otro.vertices[i]);
    }
    
  /** 
    * Elimina todos los vértices del polígono, dejando un polígono vacío
    */
    public void borrar() {
        numPuntos = 0;
        poligonoCompleto = false;
        poligonoSimple = true;
    }
    
  /**
    * Comprueba si el polígono es igual a otro objeto geométrico. Un Poligono2D
    * será igual a otro objeto geométrico si dicho objeto es a su vez un Poligono2D
    * y ambos polígonos tienen los mismos vértices (aunque no tienen por qué estar definidos
    * en el mismo orden, si en el mismo sentido)
    * @param objeto Objeto geométrico con el que se desea comparar el polígono 
    * @return un valor booleano indicando si el polígono es igual al objeto geométrico 
    * pasado como parámetro
    * @see Poligono2D#sentido()
    * @see Punto2D#equals(ObjetoGeometrico)
    */
   public boolean equals (ObjetoGeometrico objeto) {
        if (objeto == this)
            return true;
        if (objeto instanceof Poligono2D)
        {
            Poligono2D poligono = (Poligono2D) objeto;
            if (this.numPuntos == poligono.numPuntos &&
                this.sentido() == poligono.sentido())
            {
                // Buscamos un vértice del polígono pasado como parámetro igual al
                // primero de this, para que nos sirva de referencia
                int pos = 0;
                while (pos<numPuntos && 
                       !(this.vertices[0]).equals(poligono.vertices[pos]))
                            pos++;
                if (pos >= numPuntos)
                    return false;
                // Comparamos punto por punto
                for (int i=1; i<numPuntos; i++)
                {
                    pos++; if (pos>=numPuntos) pos = 0;
                    if (!(this.vertices[i]).equals(poligono.vertices[pos]))
                        return false;
                }
                return true;
            }
                else return false;
        }
        else return false;
    }
  
  /**
    * Comprueba si el polígono es distinto a otro objeto geométrico. Un Poligono2D
    * será  distinto a otro objeto geométrico si dicho objeto no es un Poligono2D
    * o si ambos polígonos tienen distintos vértices, o en distinto sentido
    * @param objeto Objeto geométrico con el que se desea comparar el polígono 
    * @return un valor booleano indicando si el polígono es distinto al objeto geométrico 
    * pasado como parámetro
    * @see Poligono2D#equals(ObjetoGeometrico)
    */
   public boolean not_equals(ObjetoGeometrico objeto) {
    return !this.equals(objeto);
   }
    
  /** 
    * Inserta un nuevo vértice en el polígono. Si las coordenadas de ese vértice 
    * se corresponden con las del último del polígono, el polígono pasará a ser 
    * un polígono cerrado (y el vértice no será introducido). En caso contrario, 
    * el nuevo vértice es introducido al final del array de vértices que definen 
    * al polígono. Nunca se introducira un nuevo vértice en el caso de que el polígono
    * ya fuera un polígono cerrado. Al mismo tiempo que se inserta un vértice, se 
    * determinar si el polígono sigue o no siendo simple (cada vértice es compartido
    * solo por dos lados y no hay ninguna intersección entre aristas)
    * @param p punto a insertar como vértice del polígono
    * @see Punto2D#equals(ObjetoGeometrico)
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#intersectaSegmento(Segmento2D)
    * @see Segmento2D#intersectaPropSegmento(Segmento2D)
    */
    public void insertar (Punto2D p) {
        if (poligonoCompleto) return;
        if (numPuntos>0)
        if (vertices[numPuntos-1].equals(p)) { 
            poligonoCompleto=true;
        // Comprobamos que el último lado del polígono no intersecta con ningún otro
        int i = 2;
        try {
            Segmento2D seg = new Segmento2D(vertices[numPuntos-1] ,vertices[0]);
        
            while (poligonoSimple && i<=numPuntos-2)
            {
                if (seg.intersectaSegmento(new Segmento2D(vertices[i-1], vertices[i])))
                    poligonoSimple = false;
                else
                    i++;
            }
            if (seg.intersectaPropSegmento(new Segmento2D(vertices[0], vertices[1])))
                 poligonoSimple = false;
         } catch(GeomException e) {}
            
        return;
    }
    
    // Comprobación de que cada vértice solo es compartido por dos lados
    int i=0;
    while (poligonoSimple && i<numPuntos)
    {
        if (vertices[i].equals(p))
            poligonoSimple = false;
        else
            i++;
    }
    vertices[numPuntos] = p;
    numPuntos++;
    // Comprobación de que no hay otras intersecciones entre lados aparte
    // de los vértices
    if (numPuntos>1)
    {
           i = 1;
           try {
                Segmento2D seg = new Segmento2D(vertices[numPuntos-2], vertices[numPuntos-1]);
                while (poligonoSimple && i<numPuntos-2)
                {
                   if (seg.intersectaSegmento(new Segmento2D(vertices[i-1], vertices[i])))
                       poligonoSimple = false;
                   else
                       i++;
                }
           } catch (GeomException e) {}
        }
    }
    
  /** Elimina el vértice del polígono cuyo índice se pasa como parámetro. Si se
    * trataba de un polígono completo, dejará de serlo. Si no era un polígono 
    * completo y se elimina un vértice intermedio, se comprobará si el polígono
    * sigue siendo simple. Para ello el segmento que une los dos vértices que eran
    * adyacentes con el vértice recién borrado no deberá intersectar con ningún
    * otro vértice del polígono
    * @param vertice indice del vértice que se desea eliminar
    * @exception GeomException si el índice queda fuera del rango de vértices del 
    * polígono
    * @see Punto2D#Punto2D(Punto2D)
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#intersectaSegmento(Segmento2D)
    */
    public void eliminar(int vertice) throws GeomException {
        if (vertice < 0 || vertice >= numPuntos)
            throw new GeomException("eliminar (int) : El índice indicado no se corresponde con ningún vértice del polígono");
        else
        {
            if (poligonoCompleto)
            {
                if (vertice == numPuntos - 1)
                    numPuntos--;
                else {
                    // Los vértices adyacentes al recien eliminiado serán los nuevos
                    // extremos de la lista de vértices del polígono
                    Punto2D[] nuevosVertices = new Punto2D[maxPuntos];
                    
                    int pos=  vertice + 1;
                    int nuevaPos = 0;
                    while (pos != vertice)
                    {
                        nuevosVertices[nuevaPos] = new Punto2D(vertices[pos]);
                        pos++; if (pos>=numPuntos) pos = 0;
                        nuevaPos++;
		    }
                        numPuntos--;
                        vertices = nuevosVertices;
                }
		poligonoCompleto = false;
            }
            else
            {
                // Si el vértice a eliminar es el último de la lista simplemente
                // disminuimos el número de puntos
                if (vertice == numPuntos - 1)
                    numPuntos--;
                else
                {
                    // En caso contrario desplazamos todos los vértices siguientes
                    // al vértice eliminado a la posición inmediatamente anterior
                    for (int i=vertice; i<numPuntos-1; i++)
                        vertices[i] = new Punto2D(vertices[i+1]);
                    numPuntos--;
		 }
             }
	     // Comprobamos tras eliminar el vértice si el polígono es simple
             if (numPuntos > 1)
             {
                 int i = 1;
                 try {
                     poligonoSimple = true;
                     while (poligonoSimple && i<numPuntos-1)
                     {
			 Segmento2D seg = new Segmento2D(vertices[i-1], vertices[i]);
			 int j = i+1;
			 while (poligonoSimple && j<numPuntos)
			 {
                         	if(seg.intersectaPropSegmento(new Segmento2D(vertices[i-1], vertices[i])))
                                	 poligonoSimple = false;
	                         else
        	                     j++;
			  }
			  i++;
                      }
                 } catch (GeomException e) {}
             }
         }
    }
    
 /** Sustituye un vértice del polígono por otro especificado, comprobando al mismo tiempo
    * que el polígono siga siendo simple en caso de que lo fuera
    * @param vertice índice del vértice que se desea modificar
    * @param nuevo vértice que sustituirá al anterior
    * @exception GeomException si el índice queda fuera del rango de vértices del 
    * polígono
    * @see Punto2D#Punto2D(Punto2D)
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#intersectaSegmento(Segmento2D)
    */
    public void modificar(int vertice, Punto2D nuevo) throws GeomException {
        if (vertice < 0 || vertice >= numPuntos)
            throw new GeomException("modificar (int, Punto2D) : El índice indicado no se corresponde con ningún vértice del polígono");
        else
        {
            // Sustituimos el vértice indicado
            vertices[vertice] = new Punto2D(nuevo);
            // Comprobamos si el polígono contínua siendo simple en caso de que
            // ya lo fuera
            if (numPuntos>1)
            {
                int i = 1;
                boolean uneExtremos1 = false, uneExtremos2 = false;
                
                int verticeAnterior = vertice - 1;
                int verticePosterior = vertice + 1;
                if (verticeAnterior < 0 ) 
                {
                     verticeAnterior = numPuntos - 1;
                     uneExtremos1 = true;
                }
                if (verticePosterior >= numPuntos) 
                {
                    verticePosterior = 0;
                    uneExtremos2 = true;
                }
                try {
                    // Creamos los dos vértices que están conectados al que acabamos de modificar
                    Segmento2D seg1 = new Segmento2D(vertices[verticeAnterior], vertices[vertice]);
                    Segmento2D seg2 = new Segmento2D(vertices[vertice], vertices[verticePosterior]);
                    while (poligonoSimple && i<numPuntos)
                    {
                        Segmento2D segmento = new Segmento2D(vertices[i-1], vertices[i]);
                        if (poligonoCompleto || !uneExtremos1)
                            if (i != vertice &&
                                seg1.intersectaPropSegmento(segmento))
                                    poligonoSimple = false;
                        if (poligonoCompleto || !uneExtremos2)
                            if (i != verticePosterior &&
                                seg2.intersectaPropSegmento(segmento))
                                    poligonoSimple = false;
                        i++;
                    }
                    
                    if (poligonoSimple && poligonoCompleto)
                    {
                        Segmento2D segmento = new Segmento2D(vertices[0], vertices[numPuntos-1]);
                        
                        if ((!uneExtremos1 && seg1.intersectaPropSegmento(segmento)) ||
                            (!uneExtremos2 && seg2.intersectaPropSegmento(segmento)))
                                poligonoSimple = false;
                    }
                } catch (GeomException e) {}
            }
        }
    }
    
    /** Determina si un punto está contenido en el polígono de forma estricta.
      * Para que esto suceda, si el polígono es horario el punto deberá estar a 
      * la derecha de todos sus lados, y a la izquierda en caso contrario, pero
      * nunca contenido por ninguno de sus lados.
      * Si el polígono no es completo el punto no se considerará nunca contenido
      * estrictamente
      * @param punto el punto que se desea comprobar si está contenido estrictamente
      * en el interior del polígono
      * @return un valor booleano indicando si el punto pasado como parámetro 
      * está contenido estrictamente en el polígono
      * @see Poligono2D#sentido()
      * @see Mat#ANTIHORARIO
      * @see Mat#HORARIO
      * @see Mat#IZQUIERDA
      * @see Mat#DERECHA
      * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
      * @see Punto2D#equals(ObjetoGeometrico)
      */
    public boolean contienePuntoEstrictamente(Punto2D punto) {
        boolean contenido = true;
        int i=1;
        
        if (poligonoCompleto)
        {
            int lado;
            if (this.sentido() == Mat.ANTIHORARIO)
                lado = Mat.IZQUIERDA;
            else
                lado = Mat.DERECHA;
            
            try {
                if (punto.equals(vertices[0])) contenido = false;
                while (i<numPuntos && contenido)
                {
                      if (punto.equals(vertices[i])) contenido = false;
                      else contenido = (punto.posicionRelativa(vertices[i-1], vertices[i]) == lado);
                      i++;
                }
                if (contenido)
                    contenido = (punto.posicionRelativa(vertices[numPuntos-1], vertices[0]) == lado);
                return contenido;
            } catch (GeomException e) { return false;} // La excepción de posiciónRelativa nunca
                                         // se va a producir
        }
        else return false;
    }
    
    /** Determina si un punto está contenido en alguno de los lados del polígono
      * (no hace falta que el polígono sea completo para que el punto esté contenido
      * por alguno de los lados)
      * @param punto el punto que se desea conocer si está contenido por alguno 
      * de los lados
      * @return un valor booleano indicando si el punto pasado como parámetro se
      * encuentra contenido por alguno de los lados del polígono
      * @see Punto2D#equals(ObjetoGeometrico)
      * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
      * @see Mat#COLINEAL
    */
    public boolean ladosContienenPunto(Punto2D punto) {
        boolean contiene = false;
        int i = 1;
        
        if (numPuntos > 1)
        {
            try {
                if (punto.equals(vertices[0])) contiene = true;
                while (i<numPuntos && !contiene)
                {
                    if (punto.equals(vertices[i]))
                        contiene = true;
                    else
                        contiene = (punto.posicionRelativa(vertices[i-1], vertices[i]) == Mat.COLINEAL);
                    i++;
                }
                if (!contiene && poligonoCompleto)
                    contiene = (punto.posicionRelativa(vertices[numPuntos-1], vertices[0]) == Mat.COLINEAL);
            } catch (GeomException e) {
                return false; // Esta excepción nunca se va a producir
            }
        }
        
        return contiene;
    }
    
    /** Determina si un punto está contenido por el polígono. Para que esto suceda,
      * el punto deberá estar contenido por alguno de los lados o deberá encontrarse
      * a la izquierda de todos ellos en caso de tratarse de un polígono antihorario
      * o a la derecha si es horario. Si el polígono no es completo se considera que
      * nunca podrá contener un punto
      * @param punto punto que se desea conocer si está contenido por el polígono
      * @return un valor booleano indicando si el punto está contenido por el 
      * polígono o alguno de sus lados
      * @see Poligono2D#contienePuntoEstrictamente(Punto2D)
      * @see Poligono2D#ladosContienenPunto(Punto2D)
      */
    public boolean contienePunto(Punto2D punto) {
        return contienePuntoEstrictamente(punto) || ladosContienenPunto(punto);
    }
    
    /** Obtiene la distancia del polígono a un punto, así como el punto del 
      * polígono más cercano a dicho punto. Si el polígono solo tiene
      * un vértice se calculará la distancia del punto a dicho vértice, y en caso
      * contrario, la distancia será la distancia más corta a alguno de sus lados
      * @param punto punto al que se desea obtener la distancia
      * @param cercano punto del segmento más cercano al punto pasado como primer
      * parámetro. Es un parámetro de SALIDA, y debe ser un punto ya creado
      * @return la distancia del polígono al punto pasado como parámetro
      * @exception GeomException si el punto pasado como segundo parámetro no es un punto
      * ya creado
      * @see Punto2D#distancia(Punto2D)
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
        
        if (numPuntos >= 1)
        {
            if (numPuntos == 1) {
                distancia = punto.distancia(vertices[0]);
                diferencia = vertices[0].vectorDiferencia(cercano);
                cercano.trasladar(diferencia);
            }    
            else
            {
                for (int i=1; i<numPuntos; i++)
                    try {
                        Segmento2D seg = new Segmento2D(vertices[i-1], vertices[i]);
                        double dist = seg.puntoMasCercano(punto, cercanoTemporal);
                        if (dist < distancia) {
                            distancia = dist;
                            diferencia = cercanoTemporal.vectorDiferencia(cercano);
                            cercano.trasladar(diferencia);
                        }
                    } catch (GeomException e) {
                        double dist = vertices[i-1].distancia(punto);
                        if (dist < distancia) {
                            distancia = dist;
                            diferencia = vertices[i-1].vectorDiferencia(cercano);
                            cercano.trasladar(diferencia);
                        }
                    }
                if (poligonoCompleto)
                    try {
                        Segmento2D seg = new Segmento2D(vertices[0], vertices[numPuntos -1]);
                        double dist = seg.puntoMasCercano(punto, cercanoTemporal);
                        if (dist < distancia) {
                            distancia = dist;
                            diferencia = cercanoTemporal.vectorDiferencia(cercano);
                            cercano.trasladar(diferencia);
                        }
                    } catch (GeomException e) {
                        double dist = vertices[0].distancia(punto);
                        if (dist < distancia) {
                            distancia = dist;
                            diferencia = vertices[0].vectorDiferencia(cercano);
                            cercano.trasladar(diferencia);
                        }
                    }
            }
        }
        
        return distancia;
    }
    
   /** Obtiene la distancia del polígono a un punto
     * @param punto punto para el cual se desea obtener la distancia
     * @return la distancia del polígono al punto
     * @see Poligono2D#puntoMasCercano(Punto2D, Punto2D)
     */
    public double distancia(Punto2D punto) {
        try  {
            return puntoMasCercano(punto, new Punto2D());
        } catch (GeomException e) {
            return java.lang.Double.MAX_VALUE; // Nunca se va a producir
        }
    }
    
    
    /** Determina si el polígono es convexo
      * @return un valor booleano indicando si el polígono es convexo
      * @see Poligono2D#esConvexo(int)
      */
    public boolean esConvexo() {
        int i=0;
        boolean convexo = true;
        
        while (i<numPuntos && convexo)
        {
            convexo = esConvexo(i);
            i++;
        }
        
        return convexo;
    }
    
   /** Determina si el polígono es cóncavo
    * @return un valor booleano indicando si el polígono es cóncavo
    * @see Poligono2D#esConvexo()
    */
    public boolean esConcavo() {
        return !esConvexo();
    }
    
   private void dibujo (Graphics g, boolean coordenadas, Color color) {
        int v;
        
        if (numPuntos == 1)
            vertices[0].dibujar(g, coordenadas);
        else
        {
            for (v=0; v<=numPuntos-1; v++) {
                vertices[v].dibujar(g, coordenadas);
                g.setColor(color);
                if (v<(numPuntos-1))
                g.drawLine ((int) vertices[v].x(), -(int) vertices[v].y(),
                            (int) vertices[v+1].x(), -(int) vertices[v+1].y());
                if (v == (numPuntos-1) && poligonoCompleto)
                g.drawLine ((int) vertices[v].x(), -(int) vertices[v].y(),
                            (int) vertices[0].x(), -(int) vertices[0].y());
            }
                   
        }
    }
    
    public void dibujar(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.black);
    }
    
    public void dibujarSeleccionado(Graphics g, boolean coordenadas) {
        dibujo(g, coordenadas, Color.green);
    }
    
    public void dibujarNombre(Graphics g) {
        g.setColor(Color.magenta);
        if (numPuntos > 1)
        {
            try {
                Segmento2D segmento = new Segmento2D(vertices[0].x(), vertices[0].y(), vertices[1].x(), vertices[1].y());
                segmento.cambiarNombre(nombre);
                segmento.dibujarNombre(g);
            } catch (GeomException ge) {return; } // Nunca se va a producir
        }
        else
        {
            vertices[0].cambiarNombre(nombre);
            vertices[0].dibujarNombre(g);
        }
    }
    
    public void dibujarXor (Graphics g) {
        int v;
        
        if (numPuntos == 1)
            vertices[0].dibujarXor(g);
        else
        {
            for (v=0; v<=numPuntos-1; v++) {
                vertices[v].dibujarXor(g);
                g.setColor(Color.white);
                if (v<(numPuntos-1))
                g.drawLine ((int) vertices[v].x(), -(int) vertices[v].y(),
                            (int) vertices[v+1].x(), -(int) vertices[v+1].y());
                if (v == (numPuntos-1) && poligonoCompleto)
                g.drawLine ((int) vertices[v].x(), -(int) vertices[v].y(),
                            (int) vertices[0].x(), -(int) vertices[0].y());
            }
                   
        }
    }
    
 /** 
    * Determina si un polígono es simple. Un polígono será simple si cada vértice es
    * compartido tan solo por dos aristas y si no hay cruce entre ninguna de las 
    * aristas
    * @return un valor booleano indicando si el polígono es simple
    */
    public boolean esSimple()
    {
         return poligonoSimple;
    }
    
  /** 
    * Determina si el polígono es completo. un polígono es completo o cerrado si 
    * existe una arista entre el primer y el último vértice del mismo
    * @return un valor booleano indicando si el polígono es completo
    **/
    public boolean esCompleto()
    {
        return poligonoCompleto;
    }
        
  /** 
    * Obtiene el área del polígono, calculada como la suma de las áreas de los
    * triángulos formados por el origen de coordenadas y cada par de vértices
    * adyacentes del polígono. Devuelve 0 si el polígono no está cerrado o
    * no es simple 
    * @return 0 si el polígono no es simple o cerrado, y su área en caso contrario
    * @see Punto2D#Punto2D()
    * @see Punto2D#x()
    * @see Punto2D#y()
    */
    public double area()
    {
        double parcial = 0;
        Punto2D origen = new Punto2D();
        
        if (poligonoCompleto && poligonoSimple)
        {
            for (int i=0; i<numPuntos-1; i++)
                 parcial += (vertices[i].x()*vertices[i+1].y() -
                        vertices[i].y()*vertices[i+1].x());
            parcial += (vertices[numPuntos-1].x()*vertices[0].y() -
                        vertices[numPuntos-1].y()*vertices[0].x());
        }
        
        return parcial/2;
    }
    
  /** 
    * Determina el sentido en que fueron insertados los vértices en el polígono, 
    * según el signo de su área
    * @return el área del polígono con signo
    * @see Mat#ANTIHORARIO
    * @see Mat#HORARIO
    * @see Poligono2D#area()
    */
    public int sentido()
    {
        if (area()>0)
            return Mat.ANTIHORARIO;
        else
            return Mat.HORARIO;
    }
    
  /**
    * Determina si el vértice en la posición indicada del vector de vértices del
    * polígono es convexo. Para que esto sea así, si el polígono es antihorario, 
    * el punto Pi+1 deberá estar a la izquierda del segmento orientado (Pi-1, Pi).
    * En el caso de sentido horario, debe estar a la derecha
    * @param vertice la posición del vértice a comprobar dentro del array de 
    * vértices del polígono
    * @return un valor booleano indicando si el vértice es convexo
    * @see Poligono2D#sentido()
    * @see Punto2D#posicionRelativa(Punto2D, Punto2D)
    * @see Mat#ANTIHORARIO
    * @see Mat#HORARIO
    * @see Mat#IZQUIERDA
    * @see Mat#DERECHA
    */
    public boolean esConvexo(int vertice)
    {
        int anterior, posterior;
        
        if (vertice == 0) anterior=numPuntos-1; else anterior=vertice-1;
        if (vertice == numPuntos-1) posterior=0; else posterior = vertice+1;
        
        try {
            if (sentido() == Mat.ANTIHORARIO)
               return vertices[posterior].posicionRelativa(vertices[anterior], vertices[vertice]) 
                       != Mat.DERECHA;
            else
               return vertices[posterior].posicionRelativa(vertices[anterior], vertices[vertice]) 
                       != Mat.IZQUIERDA;
        } catch (GeomException e) // Nunca se va a producir
        { return false; }
    }
    
   /**
    * Determina si el vértice en la posición indicada del vector de vértices del
    * polígono es concavo. Para ello simplemente se comprueba que no es convexo
    * @param vertice la posición del vértice a comprobar dentro del array de 
    * vértices del polígono
    * @return un valor booleano indicando si el vértice es cóncavo
    * @see Poligono2D#esConvexo(int)
    */
    public boolean esConcavo(int vertice)
    {
        return !esConvexo(vertice);
    }
    
  /** Determina si el segmento definido entre los dos vértices  indicados es una
    * diagonal. Para ello:
    * <ul>
    * <li>No debe intesectar con ningún lado</li>
    * <li>Debe encontrarse dentro de los conos definidos por los vértices de 
    * origen y destino</li>
    * </ul>
    * @param v1 índice dentro del array de vértices del polígono del origen del 
    * segmento a comprobar
    * @param v2 índice dentro del array de vértices del polígono del destino del
    * segmento a comprobar
    * @return un valor booleano indicando si el segmento es una diagonal
    * @exception GeomException si el origen y el destino son el mismo punto
    * @see Segmento2D#Segmento2D(Punto2D, Punto2D)
    * @see Segmento2D#intersectaPropSegmento(Segmento2D)
    * @see Direccion2D#Direccion2D(Punto2D, Punto2D)
    * @see Poligono2D#sentido()
    * #see Mat#ANTIHORARIO
    * @see Mat#HORARIO
    * @see Direccion2D#sentidoHorario(Direccion2D, Direccion2D)
    * @see Direccion2D#sentidoAntiHorario(Direccion2D, Direccion2D)
    */
    public boolean esDiagonal(int v1, int v2) throws GeomException
    {
        Segmento2D seg = new Segmento2D(vertices[v1], vertices[v2]);
        boolean intersecta = false;
        int vertice = 1;
        
        // Comprobamos si intersecta con algun lado
        while (!intersecta  && vertice<numPuntos)
        {
            if (seg.intersectaPropSegmento(new Segmento2D(vertices[vertice-1], vertices[vertice])))
                intersecta = true;
            else
                vertice++;
        }
        // Si no intersecta con ningún lado, comprobamos que se enceuntre 
        // en el interior de los conos definidos por los dos extremos. 
        if (!intersecta)
        {
            Punto2D anterior1, posterior1, anterior2, posterior2;
            
            if (v1 == 0)
                anterior1 = vertices[numPuntos-1];
            else
                anterior1 = vertices[v1-1];
            if (v1 == numPuntos-1)
                posterior1 = vertices[0];
            else
                posterior1 = vertices[v1+1];
            if (v2 == 0)
                anterior2 = vertices[numPuntos-1];
            else
                anterior2 = vertices[v2-1];
            if (v2 == numPuntos-1)
                posterior2 = vertices[0];
            else
                posterior2 = vertices[v2+1];
            
            Direccion2D dant1 = new Direccion2D(vertices[v1], anterior1);
            Direccion2D dpost1 = new Direccion2D(vertices[v1], posterior1);
            Direccion2D dant2 = new Direccion2D(vertices[v2], anterior2);
            Direccion2D dpost2 = new Direccion2D(vertices[v2], posterior2);
            Direccion2D dir1 = new Direccion2D(seg);
            Direccion2D dir2 = new Direccion2D(seg.opuesto());
            
            if (sentido() == Mat.HORARIO)
                return (dir1.sentidoAntiHorario(dant1, dpost1)
                && dir2.sentidoAntiHorario(dant2, dpost2));
            else
                return (dir1.sentidoHorario(dant1, dpost1)
                && dir2.sentidoHorario(dant2, dpost2));
        }
        return false;
    }
 
    /** Determina cual es el vertice cuya coordenada x está situada más a la
      * izquierda
      * @return el vértice del polígono cuya coordenada x está situada más
      * a la izquierda
      * @exception GeomException si el polígono no contiene ningun vértice
      * @see Punto2D#x()
      * 
      */
    public Punto2D menorX() throws GeomException{
        if (numPuntos == 0)
            throw new GeomException("menorX () : El polígono no tienen ningún vértice");
        else
        {        
            Punto2D menor = vertices[0];
        
            for (int i=1; i<numPuntos; i++)
                if (vertices[i].x() < menor.x())
                    menor = vertices[i];
            
            return menor;
        }
    }

    /** Determina cual es el vertice cuya coordenada x está situada más a la
      * derecha
      * @return el vértice del polígono cuya coordenada x está situada más
      * a la derecha
      * @exception GeomException si el polígono no contiene ningun vértice
      * @see Punto2D#x()
      */
    public Punto2D mayorX() throws GeomException{
        if (numPuntos == 0)
            throw new GeomException("mayorX () : El polígono no tienen ningún vértice");
        else
        {        
            Punto2D mayor = vertices[0];
        
            for (int i=1; i<numPuntos; i++)
                if (vertices[i].x() > mayor.x())
                    mayor = vertices[i];
            
            return mayor;
        }
    }

    /** Determina cual es el vertice cuya coordenada y está situada más abajo
      * @return el vértice del polígono cuya coordenada y está situada más
      * abajo
      * @exception GeomException si el polígono no contiene ningun vértice
      * @see Punto2D#y()
      * 
      */
    public Punto2D menorY() throws GeomException{
        if (numPuntos == 0)
            throw new GeomException("menorY () : El polígono no tienen ningún vértice");
        else
        {        
            Punto2D menor = vertices[0];
        
            for (int i=1; i<numPuntos; i++)
                if (vertices[i].y() < menor.y())
                    menor = vertices[i];
            
            return menor;
        }
    }

    /** Determina cual es el vertice cuya coordenada y está situada más arriba
      * @return el vértice del polígono cuya coordenada y está situada más
      * arriba
      * @exception GeomException si el polígono no contiene ningun vértice
      * @see Punto2D#y()
      */
    public Punto2D mayorY() throws GeomException{
        if (numPuntos == 0)
            throw new GeomException("mayorY () : El polígono no tienen ningún vértice");
        else
        {        
            Punto2D mayor = vertices[0];
        
            for (int i=1; i<numPuntos; i++)
                if (vertices[i].y() > mayor.y())
                    mayor = vertices[i];
            
            return mayor;
        }
    }

    /** Obtiene el vértice cuyo índice se pasa como parámetro
      * @param indice índice del vértice que se desea obtener
      * @return el vértice indicado
      * @exception geomException si el índice pasado como parámetro se encuentra
      * fuera del rango de vértices del polígono
      */
    public Punto2D vertice(int indice) throws GeomException {
        if (indice < 0 || indice >= numPuntos)
            throw new GeomException("vertice (int) : El índice especificado no se corresponde con ningún vértice del polígono");
        
        return vertices[indice];
    }
    
 /** Traslada el polígono en el plano a partir de un determinado incremento en
   * cada coordenada cartesiana. La traslación se aplicará a todos los vértices.
   * @param incX incremento o desplazamiento en el eje x
   * @param incY incremento o desplazamiento en el eje y
   * @return el polígono trasladado
   * @see Punto2D#trasladar(double, double)
   */
    public Poligono2D trasladar (double incX, double incY) {
        for (int i=0; i<numPuntos; i++)
        vertices[i].trasladar(incX, incY);
        return this;
    }

  /** Traslada el polígono en el plano aplicando el mismo incremento en cada
    * eje. La traslación se aplicará a cada vértice.
    * @param incr incremento o desplazamiento que será aplicado tanto al eje x 
    * como al eje y
    * @return el polígono trasladado
    * @see Punto2D#trasladar(double)
    */
    public Poligono2D trasladar (double incr) {
        for (int i=0; i<numPuntos; i++)
        vertices[i].trasladar(incr);
        return this;
    }

  /** Traslada el polígono en la dirección y a la distancia determinados por un 
    * vector. Se aplicará la traslación a cada vértice
    * @param vector el vector que define la traslación del polígono
    * @return el polígono trasladado
    * @see Punto2D#trasladar(Vector2D)
    */
    public Poligono2D trasladar (Vector2D vector)
    {
        for (int i=0; i<numPuntos; i++)
        vertices[i].trasladar(vector);
        return this;
    }

  /** Traslación del polígono a partir de las coordenadas polares (se trasladan
    * los vértices del triángulo una determinada distancia en un determinado ángulo)
    * @param angulo dirección de traslación del polígono
    * @param dist distancia a la que se moverá el polígono de su posición original
    * siguiendo el ángulo indicado
    * @return el polígono trasladado
    * @see Punto2D#trasladarPolar(double, double)
    */
    public Poligono2D trasladarPolar (double angulo, double dist) {
        for (int i=0; i<3; i++)
        vertices[i].trasladar(angulo, dist);
        return this;
    }

   /** Obtiene el centro de gravedad del polígono (su punto central)
     * @return el centro de gravedad del polígono
     * @exception GeomException si el polígono no tiene ningún vértice
     * @see Punto2D#Punto2D(double, double)
     * @see Punto2D#x()
     * @see Punto2D#y()
     * @see Poligono2D#menorX()
     * @see Poligono2D#menorY()
     * @see Poligono2D#mayorX()
     * @see Poligono2D#mayorY()
    */
    public Punto2D centroGravedad() throws GeomException {
        double x = (menorX().x() + mayorX().x())/2.0;
        double y = (menorY().y() + mayorY().y())/2.0;
        
        return new Punto2D(x, y);
    }
    
  /** Escalado de un polígono. Se aplicará el escalado a cada vértice
    * @param escala factor de escalado
    * @return el polígono escalado
    * @exception GeomException si el factor de escalado es cero
    * @see Punto2D#escalado(double)
    */
    public Poligono2D escalado(double escala) throws GeomException{
        for (int i=0; i<numPuntos; i++)
        vertices[i].escalado(escala);
        return this;
    }

   /** Escalado de un polígono con distinto factor de escalado para cada coordenada. 
    * Se aplicará el escalado a cada vértice.
    * @param sx factor de escalado en el eje x
    * @param sy factor de escalado en el eje y
    * @return el polígono escalado
    * @exception GeomException si alguno de los factores de escalado es cero
    * @see Punto2D#escalado(double, double)
    */
    public Poligono2D escalado(double sx, double sy) throws GeomException {
        for (int i=0; i<numPuntos; i++)
        vertices[i].escalado(sx, sy);
        return this;
    }

  /** Rota el polígono respecto del origen de coordenadas
    * @param radio angulo de giro en radianes. Si es positivo el giro será
    * en sentido antihorario y en caso contrario lo será en sentido horario
    * @return el polígono girado
    * @see Punto2D#gira(double)
    */
    public Poligono2D gira(double radio) {
        for (int i=0; i<posiciones(); i++)
        vertices[i].gira(radio);
        return this;
    }

  /** Rota el polígono respecto a un punto. Se aplicará la 
    * rotación respecto a un punto a todos los vértices del triángulo.
    * @param origen punto respecto el cual se hará el giro
    * @radio angulo de giro en radianes. Si es positivo el giro será en sntido
    * antihorario y en caso contrario lo será en sentido horario
    * @return el polígono girado
    * @see Punto2D#gira(Punto2D, double)
    */
    public Poligono2D gira(Punto2D origen, double radio) {
        for (int i=0; i<posiciones(); i++)
        vertices[i].gira(origen, radio);
        return this;
    }
 
    /** Determina cual es la posición siguiente a una posición dada, dentro de la
      * lista de vértices. Como es una lista circular, el siguiente del último
      * vértice será el primero
      * @param posicion posición de la que se desea conocer la siguiente
      * @return la posición siguiente a la indicada como parámetro
      */
    public int posicionSiguiente(int posicion) {
        int nuevaPosicion = posicion+1;
        if (nuevaPosicion >= numPuntos)
            nuevaPosicion = 0;
        
        return nuevaPosicion;
    }
    /** Determina cual es la posición anterior a una posición dada, dentro de la
      * lista de vértices. Como es una lista circular, el anterior al primer
      * vértice será el último
      * @param posicion posición de la que se desea conocer la anterior
      * @return la posición anterior a la indicada como parámetro
      */
    public int posicionAnterior(int posicion) {
        int nuevaPosicion = posicion - 1;
        if (nuevaPosicion < 0)
            nuevaPosicion = numPuntos - 1;
        
        return nuevaPosicion;
    }
    
    /** Determina cual es la posición posterior a una posición dada, dentro de la
      * lista de vértices. Como es una lista circular, el al último vértice
      * será el primero
      * @param posicion posición de la que se desea conocer la posterior
      * @return la posición posterior a la indicada como parámetro
      */
    public int posicionPosterior(int posicion) {
        int nuevaPosicion = posicion + 1;
        if (nuevaPosicion >= numPuntos)
            nuevaPosicion = 0;
        
        return nuevaPosicion;
    }
    
    /** Obtiene el número de elementos dentro de la lista de vértices
     * @return el número de vértices del polígono
     */
    public int posiciones() {
        return numPuntos;
    }

    /** Realiza la conversión del objeto de tipo Poligono2D a una cadena
     * @return una cadena especificando el tipo de objeto (polígono) y las
     * coordenadas cartesianas de sus vértices
     */
    public String toString() {
	    String cadena = nombre + " - Poligono2D: (";
	    for (int i=0; i<numPuntos; i++)
		    cadena = cadena + "\n\t" + vertices[i].toString();
	    cadena = cadena + "\n)";

	    return cadena;
    }
}
