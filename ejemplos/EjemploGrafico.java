import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;


public class EjemploGrafico extends JFrame {

	// Zona de dibujo
	EjemploCanvas canvas = new EjemploCanvas();
	
	// Variables de la interfaz que necesitan ser declaradas como globales
	JCheckBox mostrarNombres = new JCheckBox("Mostrar nombres");
	JCheckBox mostrarCoordenadas = new JCheckBox("Mostrar coordenadas");
	
	// Constructor de la clase
	public EjemploGrafico() {
		// Le ponemos titulo a la ventana
		super("Ejemplo de aplicacion grafica");
		// Evitamos que el usuario cambie el tamaño
		setResizable(false);
		// Capturamos el evento de finalizacion de la aplicacion
		// al cerrar la ventana principal
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) 
			{
				System.exit(0);
			}
		});
		// Creamos el contenido de la ventana
		crearContenido();
	}

	// Metodo para crear el contenido de la ventana
	private void crearContenido() {
		// Barra de botones
		Container botones = Box.createVerticalBox();
		Button dibujarPuntos = new Button("Dibujar puntos");
		Button dibujarPoligonos = new Button("Dibujar poligonos");
		Button seleccionarPunto = new Button("Seleccionar punto");
		Button seleccionarPoligono = new Button("Seleccionar poligono");
		Button distancia = new Button("Calcular distancia");
		Button borrar = new Button("Borrar");

		// Modificamos la acción a realizar al pinchar sobre la zona
		// de dibujo cuando pulsamos alguno de los botones de dibujo
		// o selección
		dibujarPuntos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.cambiarAccion(EjemploCanvas.D_PUNTO);
			}
		});
		dibujarPoligonos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.cambiarAccion(EjemploCanvas.D_POLIGONO);
			}
		});
		seleccionarPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.cambiarAccion(EjemploCanvas.S_PUNTO);
			}
		});
		seleccionarPoligono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.cambiarAccion(EjemploCanvas.S_POLIGONO);
			}
		});
		mostrarNombres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.cambiarNombres(mostrarNombres.isSelected());
			}
		});
		mostrarCoordenadas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.cambiarCoordenadas(mostrarCoordenadas.isSelected());
			}
		});
		distancia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.eliminarPoligonoTemporal();
				canvas.cambiarAccion(EjemploCanvas.NADA);
				canvas.calcularDistancia();
			}
		});
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				canvas.borrar();
				canvas.cambiarAccion(EjemploCanvas.NADA);
			}
		});
		
		botones.add(Box.createVerticalGlue());
		botones.add(dibujarPuntos);
		botones.add(Box.createVerticalGlue());
		botones.add(dibujarPoligonos);
		botones.add(Box.createVerticalGlue());
		botones.add(seleccionarPunto);
		botones.add(Box.createVerticalGlue());
		botones.add(seleccionarPoligono);
		botones.add(Box.createVerticalGlue());
		botones.add(mostrarNombres);
		botones.add(Box.createVerticalGlue());
		botones.add(mostrarCoordenadas);
		botones.add(Box.createVerticalGlue());
		botones.add(distancia);
		botones.add(Box.createVerticalGlue());
		botones.add(borrar);

		// Panel general, que contendra los botones a la izquierda y la zona 
		// de dibujo a la derecha
		JPanel contenedor = new JPanel();
		contenedor.setLayout(new FlowLayout());
		contenedor.add(botones);
		contenedor.add(canvas);
		
		// Asginamos el panel general a la ventana principal
		this.setContentPane(contenedor);
	}
	
	// Metodo principal
	static public void main(String []args) {
		EjemploGrafico f = new EjemploGrafico();
		// Adaptamos la ventana al tamaño de los componentes que la conforman
		f.pack();
		// La mostramos
		f.setVisible(true);
	}
}	
