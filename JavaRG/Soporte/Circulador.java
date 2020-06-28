/*
 * Circulador.java
 *
 * Created on 26 de diciembre de 2001, 16:43
 */

package JavaRG.Soporte;

import JavaRG.Nucleo2D.*;
import JavaRG.Basica.ObjetoBasico;

/** Un objeto de la clase circulador contendrá un objeto geométrico no sencillo
 * que implemente la interfaz InterfazCirculador (polígono, mapa planar...)
 * y representará un índice dentro de ese objeto (un vértice, una cara...). Un
 * circulador podrá ser de tres tipos:<br>
 * <ul>
 * <li>UNIDIRECCIONAL: El índice solo puede avanzar hacia la posición siguiente</li>
 * <li>BIDIRECCIONAL: El índice puede avanzar hacia la posición siguiente o
 * hacia la anterior</li>
 * <li>ALEATORIO: El índice puede avanzar a cualquier posición</li>
 * </ul>
 * Hay que aclarar que cuando se varía el índice nos encontramos ante un circulador
 * distinto
 * 
 * @author  Pablo Suau
 * @version 1.0
 */
public class Circulador extends Object {
    /** Tipo del circulador
      */
    int tipo;
    
   /** Objeto Geométrico asociado al circulador
     */
    ObjetoBasico objeto;

   /** Posición dentro del objeto geométrico
     */
    int posicion;    

    /** Crea un nuevo circulador a partir de un objeto geométrico, un tipo, 
      * y una determinada posición.
      * @param nuevoObjeto objeto geométrico al que se asocia el circulador
      * @param nuevoTipo tipo de circulador
      * @param nuevaPosicion posicion del circulador dentro del objeto geométrico
      * @return un nuevo circulador asociado al objeto geométrico pasado como
      * primer parámetro, del tipo indicado por el segundo parámetro, en la 
      * posición especificada por el tercer parámetro
      * @exception GeomException si el tipo de circulador no se corresponde con
      * ningún tipo válido o si la posición pasada como parámetro queda fuera
      * del rango de posiciones del objeto. No hace falta comprobar si el objeto
      * implementa la interfaz InterfazCirculador, porque todos los ObjetosBasicos
      * y sus subclases lo hacen
      * @see ObjetoBasico#posiciones()
      * @see InterfazCirculador#cUNIDIRECCIONAL
      * @see InterfazCirculador#cBIDIRECCIONAL
      * @see InterfazCirculador#cALEATORIO
      */ 
    public Circulador(ObjetoBasico nuevoObjeto, int nuevoTipo, int nuevaPosicion) throws GeomException {
        if (tipo != InterfazCirculador.cUNIDIRECCIONAL &&
            tipo != InterfazCirculador.cBIDIRECCIONAL &&
            tipo != InterfazCirculador.cALEATORIO)
            throw new GeomException("Circulador (ObjetoBasico, int, int) : tipo de circulador incorrecto");
        if (posicion >= nuevoObjeto.posiciones())
            throw new GeomException("Circulador (ObjetoBasico, int, int) : La posición indicada excede el rango de posiciones del objeto");
        
        tipo = nuevoTipo;
        posicion = nuevaPosicion;
        objeto = nuevoObjeto;
    }
    
    /** Constructor de copia del circulador
      * @param original circulador a partir del cual contruir el nuevo
      * @return un nuevo circulador cuyos atributos serán los mismos que los
      * del circulador pasado como parámetro
      */
     public Circulador (Circulador original) {
        this.tipo = original.tipo;
        this.posicion = original.posicion;
        this.objeto = original.objeto;
    }

    /** Determina si el circulador es igual a otro pasado como parámetro. Para que
      * dos circuladores sean iguales, deben estar asociados al mismo objeto
      * geométrico, deben ser del mismo tipo y además encontrarse situados en la
      * misma posición
      * @param otro circulador que se desea conocer si es igual 
      * @return un valor booleano indicando si el circulador pasado como parámetro 
      * es igual
      * 
      */
    public boolean equals(Circulador otro) {
        return ((this.objeto).equals(otro.objeto) &&
                 this.tipo == otro.tipo &&
                 this.posicion == otro.posicion);
    }
    
    /** Determina si el circulador es distinto a otro pasado como parámetro. Para que
      * dos circuladores sean distintos, no deben estar asociados al mismo objeto
      * geométrico, no deben ser del mismo tipo o no deben encontrarse situados
      * en la misma posición
      * @param otro circulador que se desea conocer si es distinto
      * @return un valor booleano indciando si el circuador pasado como parámetro
      * es distinto
      * @see Circulador#equals(Circulador)
      */
    public boolean not_equals(Circulador otro) {
        return !this.equals(otro);
    }
    
    /** Determina si es alcanzable el circulador pasado como parámetro a partir
      * del circulador actual. Como en un objeto geométrico las dos posiciones
      * de los extremos están conectadas, si el objeto geométrico asociado a
      * ambos circuladores es el mismo uno siempre será alcanzable desde el 
      * otro
      * @param otro Circulador que se desea conocer si es alcanzable
      * @return un valor booleano indicando si el circulador es alcanzable desde
      * el circulador actual
      * @see ObjetoGeometrico#equals(ObjetoGeometrico)
      */
    public boolean alcanzable(Circulador otro)
    {
        return this.equals(otro.objetoAsociado());
    }
    
    /** Determina el número de circuladores entre un par de circuladores dados,
      * sin contar el segundo
      * @param circulador otro Extremo derecho del rango (el extremo izquierdo
      * será el circulador para el cual se llama al rango)
      * @return el número de circuladores entre el circulador para el cual se
      * llama al método y el pasado como parámetro, sin contar este último
      * @exception GeomException si el circulador pasado como parámetro no es
      * alcanzable
      * @see Circulador#alcanzable(Circulador)
      * @see InterfazCirculador#cUNIDIRECCIONAL
      * @see InterfazCirculador#cBIDIRECCIONAL
      * @see InterfazCirculador#cALEATORIO
      */
    public int rango(Circulador otro) throws GeomException {
        if (!alcanzable(otro))
            throw new GeomException("rango(Circulador) : El circulador pasado como parámetro no es alcanzable");
        
        int r=0;
            
        // Determinamos el rango según el tipo de THIS, ignorando el 
        // tipo del circulador pasado como parámetro
        switch (this.tipo) {
            case InterfazCirculador.cUNIDIRECCIONAL:
                int pos = this.posicion;
                while (pos != otro.posicion)
                {
                    r++;
                    pos = objeto.posicionSiguiente(pos);
                }
                return r;
             case InterfazCirculador.cBIDIRECCIONAL:
                // Devolvemos la menor distancia en alguna de las dos 
                // direcciones
                int pos1 = this.posicion;
                int pos2 = this.posicion;
                while (pos1 != otro.posicion && pos2 != otro.posicion)
                {
                    r++;
                    pos1 = objeto.posicionSiguiente(pos1);
                    pos2 = objeto.posicionAnterior(pos2);
                }
                
                return r;
            case InterfazCirculador.cALEATORIO:
                // Si el circulador es aleatorio cualquier posición
                // del objeto geométrico puede ser obtenida de forma
                // inmediata
                if (this.posicion == otro.posicion)
                    return 0;
                else
                    return 1;
            default:
                    return -1;
            }
    }
    
    /** Devuelve el número de circuladores alcanzables desde la posición actual
      * @return el número de circuladores alcanzables desde la posición actual
      */
    public int tamanyo() {
        return objeto.posiciones() - 1;
    }
    
    /** Determina el circulador siguiente. Puede ser usado por cualquier tipo
      * de circulador
      * @return un circulador asociado al mismo objeto geométrico cuya posición
      * es la inmediatamente posterior
      * @see Circulador#Circulador(ObjetoBasico, int, int)
      */
    public Circulador avanzar() {
        try {
            return new Circulador(objeto, tipo, objeto.posicionSiguiente(posicion));
        } catch (GeomException e) { // nunca se va a producir
            return null;
        }
    }
    
    /** Determina el circulador anterior. Puede ser usado solo por circuladores
      * de tipo bidireccional o aleatorio
      * @return un circulador asociado al mismo objeto geométrico cuya posición
      * es la inmediatamente anterior
      * @exception GeomException si el circulador es de tipo unidireccional
      * @see InterfazCirculador#cUNIDIRECCIONAL
      * @see Circulador#Circulador(ObjetoBasico, int, int)
      */
    public Circulador retroceder() throws GeomException {
        if (tipo == InterfazCirculador.cUNIDIRECCIONAL)
            throw new GeomException("anterior () : no puede ser usado con circuladores unidireccionales");
        
        return new Circulador(objeto, tipo, objeto.posicionAnterior(posicion));
    }
    
   /** Determina el circulador situado en la posición posterior n-ésima. Puede ser
     * usado solo por circuladores de tipo aleatorio
     * @param n número de posiciones a avanzar
     * @return un nuevo circulador asociado al mismo objeto geométrico cuya posición
     * es la n-ésima posterior
     * @exception GeomException si el circulador es de tipo unidireccional o bidireccional
     * @see InterfazCirculador#cUNIDIRECCIONAL
     * @see InterfazCirculador#cBIDIRECCIONAL
     * @see Circulador#Circulador(ObjetoBasico, int, int)
     */
    public Circulador avanzarAleatorio(int n) throws GeomException {
        if (tipo == InterfazCirculador.cUNIDIRECCIONAL || 
            tipo == InterfazCirculador.cBIDIRECCIONAL)
                throw new GeomException("avanzarAleatorio (int) : no puede ser usado con circuladores unidireccionales o bidireccionales");
        
        int nuevaPos = posicion;
        for (int i = 0; i<n; i++)
            nuevaPos = objeto.posicionSiguiente(nuevaPos);
        
        return new Circulador(objeto, tipo, nuevaPos);
    }
    
    
    /** Devuelve el objeto geométrico al que está asociado el circulador
      * @return el objeto geométrico asociado al circulador
      */
    public ObjetoBasico objetoAsociado() {
        return objeto;
    }
    
    /** Devuelve el tipo de circulador
      * @return una constante entera identificando el tipo de identificador
      * @see InterfazCirculador#cUNIDIRECCIONAL
      * @see InterfazCirculador#cBIDIRECCIONAL
      * @see InterfazCirculador#cALEATORIO
      */
    public int tipo() {
        return tipo;
    }

    /** Devuelve la posición del circulador dentro de la secuencia circular definida
     * por el objeto asociado.
     * @return la posición actual del circulador
     */
    public int posicion() {
	    return posicion;
    }
    
    /** Determina si se trata de un circulador de tipo unidireccional
      * @return un valor booleano según el circulador sea o no de tipo unidireccional
      * @see InterfazCirculador#cUNIDIRECCIONAL
      */
    public boolean esUnidireccional() {
        return tipo == InterfazCirculador.cUNIDIRECCIONAL;
    }
    
    /** Determina si se trata de un circulador de tipo bidireccional
      * @return un valor booleano según el circulador sea o no de tipo bidireccional
      * @see InterfazCirculador#cBIDIRECCIONAL
      */
    public boolean esBidireccional() {
        return tipo == InterfazCirculador.cBIDIRECCIONAL;
    }
    
    /** Determina si se trata de un circulador de tipo aleatorio
      * @return un valor booleano según el circulador sea o no de tipo aleatorio
      * @see InterfazCirculador#cALEATORIO
      */
    public boolean esAleatorio() {
         return tipo == InterfazCirculador.cALEATORIO;
    }
}
