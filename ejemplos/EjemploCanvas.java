import JavaRG.Nucleo2D.*;
import JavaRG.Basica.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EjemploCanvas extends JComponent {

	private static int tamX = 400;
	private static int tamY = 400;

	// Posibles acciones a realizar
	public static final int NADA = 0; // Ninguna accion
	public static final int D_PUNTO = 1; // Dibujar puntos
	public static final int S_PUNTO = 2; // Seleccionar puntos
	public static final int D_POLIGONO = 3; // Dibujar polígono
	public static final int S_POLIGONO = 4; // Seleccionar polígono

	// Maxima distancia de seleccion de una figura
	public static final int umbral = 4;
	
	// Accion actual
	private int accion = NADA;
	
	// Numero maximo de objetos geometricos de cada tipo
	private static int max = 100;

	// Objetos geometricos dibujados
	private Punto2D puntos[] = new Punto2D[max];
	private Poligono2D poligonos[] = new Poligono2D[max];

	// Numero de objetos geometricos dibujados
	private int numPuntos = 0;
	private int numPoligonos = 0;

	// Polígono que se está dibujando
	private Poligono2D poligonoTemporal = null;
	
	// Objetos seleccionados
	private int puntoSeleccionado = -1;
	private int poligonoSeleccionado = -1;
	
	// Opciones de visualizacion
	private boolean mostrarCoordenadas = false;
	private boolean mostrarNombres = false;

	// Resultado del cálculo de la distancia
	private Segmento2D resultado = null;

	public EjemploCanvas() {
		super();
		// Cambiamos el tamaño
		setPreferredSize(new Dimension(tamX, tamY));
		// Asociamos la zona de dibujo a la clase que maneja
		// los eventos del raton
		addMouseListener(new TicRaton());
	}

	public void paint(Graphics g) {
		// Cambiamos el color de fondo
		Graphics2D g2 = (Graphics2D) g;
	        g.setColor(Color.white);
	        g.fillRect(0, 0, getSize().width, getSize().height);

		// Dibujo de puntos
		for (int i = 0; i < numPuntos; i++)
		{
			puntos[i].dibujar(g, mostrarCoordenadas);
			if (mostrarNombres) puntos[i].dibujarNombre(g);
		}

		// Dibujo de poligonos
		for (int i = 0; i < numPoligonos; i++)
		{
			poligonos[i].dibujar(g, mostrarCoordenadas);
			if (mostrarNombres) poligonos[i].dibujarNombre(g);
		}
		if (poligonoTemporal != null) poligonoTemporal.dibujar(g, mostrarCoordenadas);

		// Dibujo de objetos seleccionados
		if (puntoSeleccionado != -1)
			puntos[puntoSeleccionado].dibujarSeleccionado(g, mostrarCoordenadas);
		if (poligonoSeleccionado != -1)
			poligonos[poligonoSeleccionado].dibujarSeleccionado(g, mostrarCoordenadas);
		// Dibujo del resultado si existe
		if (resultado != null) 
		{
			resultado.dibujarResultado(g);
			resultado.dibujarNombre(g);
		}
	}

	// Modifica la accion a realizar cuando se pincha sobre la zona
	// de dibujo
	public void cambiarAccion(int nuevaAccion) {
		accion = nuevaAccion;
		if (accion != D_POLIGONO) {
			poligonoTemporal = null;
			repaint();
		}
		resultado = null;
	}

	// Métodos para el cambio de las variables booleanas mostrarCoordenadas y
	// mostrarNombres
	public void cambiarCoordenadas(boolean valor) {mostrarCoordenadas = valor; repaint();}
	public void cambiarNombres(boolean valor) {mostrarNombres = valor; repaint();}
	
	// Borra el polígono temporal
	public void eliminarPoligonoTemporal() {
		poligonoTemporal = null;
		repaint();
	}
	
	// Borra los objetos geometricos dibujados
	public void borrar() {
		puntoSeleccionado = -1;
		poligonoSeleccionado = -1;
		numPuntos = 0;
		numPoligonos = 0;
		resultado = null; 
		eliminarPoligonoTemporal();
	}
	
	// CLASE PRIVADA PARA EL MANEJO DE EVENTOS DEL RATON
	private class TicRaton extends MouseAdapter {
		public void mousePressed (MouseEvent e) {
			int x = e.getX();
			int y = -e.getY();

			switch(accion) {
				// Dibujo de puntos
				case D_PUNTO:
					if (numPuntos < max) {
						puntos[numPuntos] = new Punto2D(x, y);
						String nombre = "PUNTO" + (numPuntos+1);
						puntos[numPuntos].cambiarNombre(nombre);
						numPuntos++;
						repaint();
					}
				break;
				// Seleccion de puntos
				case S_PUNTO:
                                        double minimaDistancia = java.lang.Integer.MAX_VALUE;
				        Punto2D p = new Punto2D(x,y);
				        for (int i=0; i<numPuntos;i++)
				        {
					        double distancia = puntos[i].distancia(p);
					        // Determinamos el polígono más cercano al punto
					        // en el que se pincho con el raton
					        if (distancia < minimaDistancia) {
						        minimaDistancia = distancia;
						        puntoSeleccionado = i;
						}
					}
					// Solo seleccionamos una figura si su distancia al
					// punto pinchado es menor de un umbral
					if (minimaDistancia > umbral)
						puntoSeleccionado = -1;
					repaint();
				break;
				// Dibujo de poligonos
				case D_POLIGONO:
					if (numPoligonos < max) {
						if (poligonoTemporal == null)
							poligonoTemporal = new Poligono2D();
						poligonoTemporal.insertar(new Punto2D(x, y));
						if (poligonoTemporal.esCompleto())
						{
							poligonos[numPoligonos] = new Poligono2D(poligonoTemporal);
							String nombre = "POLIG" + (numPoligonos+1);
							poligonos[numPoligonos].cambiarNombre(nombre);
							numPoligonos++;
							poligonoTemporal = null;
						}
						repaint();
					}
				break;
				// Seleccion de poligonos
				case S_POLIGONO:
					double minDistancia = java.lang.Integer.MAX_VALUE;
					Punto2D punto = new Punto2D(x,y);
					for (int i=0; i<numPoligonos;i++)
					{
						double distancia = poligonos[i].distancia(punto);
						// Determinamos el polígono más cercano al punto
						// en el que se pincho con el raton
						if (distancia < minDistancia) {
							minDistancia = distancia;
							poligonoSeleccionado = i;
						}
					}
					// Solo seleccionamos una figura si su distancia al
					// punto pinchado es menor de un umbral
					if (minDistancia > umbral)
						poligonoSeleccionado = -1;
					repaint();
				break;
				default:
					// No se hace nada
				break;
			}
		}
	}

	// Calcula la distancia y crea el objeto geométrico 'resultado' para mostrar dicha
	// distancia gráficamente
	public void calcularDistancia() {
		// Primero comprobamos que haya tanto un punto como un polígono seleccionados
		if (poligonoSeleccionado == -1 || puntoSeleccionado == -1)
		{
			JOptionPane.showMessageDialog(this, "Debe haber un punto y un poligono seleccionados", "ERROR",JOptionPane.WARNING_MESSAGE);
		}
		else try {
		
			// calculamos la distancia, así como el punto perteneciente al poligono
			// seleccionado mas cercano al punto seleccionado
			Punto2D puntoCercano = new Punto2D();
			double distancia = poligonos[poligonoSeleccionado].puntoMasCercano(puntos[puntoSeleccionado], puntoCercano);
			// Creamos el segmento resultado entre los dos puntos anteriores, dandole
			// como nombre la distancia entre ambos
			resultado = new Segmento2D(puntos[puntoSeleccionado], puntoCercano);
			resultado.cambiarNombre(String.valueOf(distancia));
			// Mostramos el resultado por la salida estandar
			System.out.println("Distancia de " + puntos[puntoSeleccionado].obtenerNombre() + " a " + poligonos[poligonoSeleccionado].obtenerNombre() + " : " + distancia);
			// y redibujamos la zona de dibujo para que se muestre el resultado
			repaint();
		} catch (GeomException ge) {System.out.println(ge); }
	}
}
