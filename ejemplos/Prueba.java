// Importamos la parte básica de la librería
import JavaRG.Mat;
import JavaRG.Nucleo2D.*;

public class Prueba {
	public Prueba() {
	}

	public static void main (String []args) throws GeomException {
		// Creamos el primer segmento
		float inicioX = 10, finX = 15;
		float inicioY = 11, finY = 6;
		Segmento2D segmento1 = new Segmento2D(inicioX, inicioY, finX, finY);
		segmento1.cambiarNombre("Segmento 1");
		System.out.println("Creado el primer segmento");
		System.out.println(segmento1);

		// Creamos el segundo segmento
		Punto2D inicio = new Punto2D(14,7);
		Punto2D fin = new Punto2D(17,4);
		Segmento2D segmento2 = new Segmento2D(inicio, fin);
		segmento2.cambiarNombre("Segmento 2");
		System.out.println("Creado el segundo segmento");
		System.out.println(segmento2);

		// Creamos el tercer segmento
		Segmento2D segmento3 = new Segmento2D(segmento1);
		segmento3 = segmento3.gira(Mat.grad2Rad(90));
		segmento3 = segmento3.trasladar(20, -5);
		segmento3.cambiarNombre("Segmento 3");
		System.out.println("Creado el tercer segmento");
		System.out.println(segmento3);

		// Calculamos intersecciones
		System.out.println("Interseccion de '" + segmento1.obtenerNombre() + "' y '" + segmento2.obtenerNombre() + "' : ");
		ObjetoGeometrico resultado1 = segmento1.interseccionSegmento(segmento2);
		System.out.println(resultado1);
		System.out.println("Interseccion de '" + segmento1.obtenerNombre() + "' y '" + segmento3.obtenerNombre() + "' : ");
		ObjetoGeometrico resultado2 = segmento1.interseccionSegmento(segmento3);
		System.out.println(resultado2);
		System.out.println("Interseccion de '" + segmento2.obtenerNombre() + "' y '" + segmento3.obtenerNombre() + "' : ");
		ObjetoGeometrico resultado3 = segmento2.interseccionSegmento(segmento3);
		System.out.println(resultado3);

		// Ejemplo de excepción geométrica
		try {
			Segmento2D segmento4 = new Segmento2D(inicio, inicio);
		} catch (GeomException e) {
			System.out.println(e);
		}
	}
}
	
	
