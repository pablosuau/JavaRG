import JavaRG.Nucleo2D.*;
import JavaRG.Basica.*;

public class CrearPoligonos {
	public CrearPoligonos() {
	}

	public static void main (String []args) throws GeomException {
		// Creacion de un poligono al azar de 10 lados, con altura 100 y anchura 100)
		Poligono2D pol1 = new Poligono2D(10, 100, 100);
		pol1.cambiarNombre("Poligono 1");
		System.out.println(pol1);
		// Creacion de un poligono a partir de otro pol�gono
		Poligono2D pol2 = new Poligono2D(pol1);
		pol2.cambiarNombre("Poligono 2");
		System.out.println(pol2);
		// Creacion de un poligono a partir de sus vertices
		Poligono2D pol3 = new Poligono2D();
		pol3.cambiarNombre("Poligono 3");
		// Introducimos los vertices que conformaran el poligono
		pol3.insertar(new Punto2D(10.0, 0.5));
		pol3.insertar(new Punto2D(12.0, 8.0));
		pol3.insertar(new Punto2D(11.2, 3.7));
		// Hasta aqu� el pol�gono pol3 es un pol�gono abierto (no completo)
		System.out.println(pol3);
		System.out.println(pol3.esCompleto());
		// Hacemos que el pol�gono sea un pol�gono cerrado (completo) insertando
		// de nuevo el ultimo vertice introducido
		pol3.insertar(new Punto2D(11.2, 3.7));
		System.out.println(pol3); // se observa que este ultimo vertice NO
					  // aparece dos veces
		System.out.println(pol3.esCompleto());
	}
	
}
