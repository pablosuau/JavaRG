/*
 * Circulador.java
 *
 * Created on 26 de diciembre de 2001, 16:43
 */

package JavaRG.Soporte;

import JavaRG.Nucleo2D.*;
import JavaRG.Basica.ObjetoBasico;

/** Un objeto de la clase circulador contendr� un objeto geom�trico no sencillo
 * que implemente la interfaz InterfazCirculador (pol�gono, mapa planar...)
 * y representar� un �ndice dentro de ese objeto (un v�rtice, una cara...). Un
 * circulador podr� ser de tres tipos:<br>
 * <ul>
 * <li>UNIDIRECCIONAL: El �ndice solo puede avanzar hacia la posici�n siguiente</li>
 * <li>BIDIRECCIONAL: El �ndice puede avanzar hacia la posici�n siguiente o
 * hacia la anterior</li>
 * <li>ALEATORIO: El �ndice puede avanzar a cualquier posici�n</li>
 * </ul>
 * Hay que aclarar que cuando se var�a el �ndice nos encontramos ante un circulador
 * distinto
 * 
 * @author  Pablo Suau
 * @version 1.0
 */
public class Circulador extends Object {
    /** Tipo del circulador
      */
    int tipo;
    
   /** Objeto Geom�trico asociado al circulador
     */
    ObjetoBasico objeto;

   /** Posici�n dentro del objeto geom�trico
     */
    int posicion;    

    /** Crea un nuevo circulador a partir de un objeto geom�trico, un tipo, 
      * y una determinada posici�n.
      * @param nuevoObjeto objeto geom�trico al que se asocia el circulador
      * @param nuevoTipo tipo de circulador
      * @param nuevaPosicion posicion del circulador dentro del objeto geom�trico
      * @return un nuevo circulador asociado al objeto geom�trico pasado como
      * primer par�metro, del tipo indicado por el segundo par�metro, en la 
      * posici�n especificada por el tercer par�metro
      * @exception GeomException si el tipo de circulador no se corresponde con
      * ning�n tipo v�lido o si la posici�n pasada como par�metro queda fuera
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
            throw new GeomException("Circulador (ObjetoBasico, int, int) : La posici�n indicada excede el rango de posiciones del objeto");
        
        tipo = nuevoTipo;
        posicion = nuevaPosicion;
        objeto = nuevoObjeto;
    }
    
    /** Constructor de copia del circulador
      * @param original circulador a partir del cual contruir el nuevo
      * @return un nuevo circulador cuyos atributos ser�n los mismos que los
      * del circulador pasado como par�metro
      */
     public Circulador (Circulador original) {
        this.tipo = original.tipo;
        this.posicion = original.posicion;
        this.objeto = original.objeto;
    }

    /** Determina si el circulador es igual a otro pasado como par�metro. Para que
      * dos circuladores sean iguales, deben estar asociados al mismo objeto
      * geom�trico, deben ser del mismo tipo y adem�s encontrarse situados en la
      * misma posici�n
      * @param otro circulador que se desea conocer si es igual 
      * @return un valor booleano indicando si el circulador pasado como par�metro 
      * es igual
      * 
      */
    public boolean equals(Circulador otro) {
        return ((this.objeto).equals(otro.objeto) &&
                 this.tipo == otro.tipo &&
                 this.posicion == otro.posicion);
    }
    
    /** Determina si el circulador es distinto a otro pasado como par�metro. Para que
      * dos circuladores sean distintos, no deben estar asociados al mismo objeto
      * geom�trico, no deben ser del mismo tipo o no deben encontrarse situados
      * en la misma posici�n
      * @param otro circulador que se desea conocer si es distinto
      * @return un valor booleano indciando si el circuador pasado como par�metro
      * es distinto
      * @see Circulador#equals(Circulador)
      */
    public boolean not_equals(Circulador otro) {
        return !this.equals(otro);
    }
    
    /** Determina si es alcanzable el circulador pasado como par�metro a partir
      * del circulador actual. Como en un objeto geom�trico las dos posiciones
      * de los extremos est�n conectadas, si el objeto geom�trico asociado a
      * ambos circuladores es el mismo uno siempre ser� alcanzable desde el 
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
    
    /** Determina el n�mero de circuladores entre un par de circuladores dados,
      * sin contar el segundo
      * @param circulador otro Extremo derecho del rango (el extremo izquierdo
      * ser� el circulador para el cual se llama al rango)
      * @return el n�mero de circuladores entre el circulador para el cual se
      * llama al m�todo y el pasado como par�metro, sin contar este �ltimo
      * @exception GeomException si el circulador pasado como par�metro no es
      * alcanzable
      * @see Circulador#alcanzable(Circulador)
      * @see InterfazCirculador#cUNIDIRECCIONAL
      * @see InterfazCirculador#cBIDIRECCIONAL
      * @see InterfazCirculador#cALEATORIO
      */
    public int rango(Circulador otro) throws GeomException {
        if (!alcanzable(otro))
            throw new GeomException("rango(Circulador) : El circulador pasado como par�metro no es alcanzable");
        
        int r=0;
            
        // Determinamos el rango seg�n el tipo de THIS, ignorando el 
        // tipo del circulador pasado como par�metro
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
                // Si el circulador es aleatorio cualquier posici�n
                // del objeto geom�trico puede ser obtenida de forma
                // inmediata
                if (this.posicion == otro.posicion)
                    return 0;
                else
                    return 1;
            default:
                    return -1;
            }
    }
    
    /** Devuelve el n�mero de circuladores alcanzables desde la posici�n actual
      * @return el n�mero de circuladores alcanzables desde la posici�n actual
      */
    public int tamanyo() {
        return objeto.posiciones() - 1;
    }
    
    /** Determina el circulador siguiente. Puede ser usado por cualquier tipo
      * de circulador
      * @return un circulador asociado al mismo objeto geom�trico cuya posici�n
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
      * @return un circulador asociado al mismo objeto geom�trico cuya posici�n
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
    
   /** Determina el circulador situado en la posici�n posterior n-�sima. Puede ser
     * usado solo por circuladores de tipo aleatorio
     * @param n n�mero de posiciones a avanzar
     * @return un nuevo circulador asociado al mismo objeto geom�trico cuya posici�n
     * es la n-�sima posterior
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
    
    
    /** Devuelve el objeto geom�trico al que est� asociado el circulador
      * @return el objeto geom�trico asociado al circulador
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

    /** Devuelve la posici�n del circulador dentro de la secuencia circular definida
     * por el objeto asociado.
     * @return la posici�n actual del circulador
     */
    public int posicion() {
	    return posicion;
    }
    
    /** Determina si se trata de un circulador de tipo unidireccional
      * @return un valor booleano seg�n el circulador sea o no de tipo unidireccional
      * @see InterfazCirculador#cUNIDIRECCIONAL
      */
    public boolean esUnidireccional() {
        return tipo == InterfazCirculador.cUNIDIRECCIONAL;
    }
    
    /** Determina si se trata de un circulador de tipo bidireccional
      * @return un valor booleano seg�n el circulador sea o no de tipo bidireccional
      * @see InterfazCirculador#cBIDIRECCIONAL
      */
    public boolean esBidireccional() {
        return tipo == InterfazCirculador.cBIDIRECCIONAL;
    }
    
    /** Determina si se trata de un circulador de tipo aleatorio
      * @return un valor booleano seg�n el circulador sea o no de tipo aleatorio
      * @see InterfazCirculador#cALEATORIO
      */
    public boolean esAleatorio() {
         return tipo == InterfazCirculador.cALEATORIO;
    }
}
