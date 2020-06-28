import JavaRG.Nucleo2D.*;
import JavaRG.Basica.*;
import JavaRG.Soporte.*;

public class EjemploAleatorio {
	public EjemploAleatorio() {
	}

	public static void main (String []args) throws GeomException {
		// Creamos el poligono para el cual deseamos recorrer
		// sus vertices
		Poligono2D poligono = new Poligono2D();
		poligono.insertar(new Punto2D(0,0));
		poligono.insertar(new Punto2D(1,0));
		poligono.insertar(new Punto2D(2,2));
		poligono.insertar(new Punto2D(1,3));
		poligono.insertar(new Punto2D(1,3)); // Cerramos el polígono

		// Creamos el circulador inicial, de tipo bidireccional, 
		// asociado al primer vertice del polígono (posición 0)
		Circulador c = new Circulador(poligono, InterfazCirculador.cALEATORIO, 0);


		// Avanzamos por la secuencia de tres en tres posiciones 
		for (int i=0; i<=5; i++)
		{
			try {
				Punto2D vertice = poligono.vertice(c.posicion());
				System.out.println(vertice);
			} catch (GeomException ge) {
				// Precisamente debido al uso del circulador esta excepción
				// nunca se va a producir, pues nunca indicaremos un vértice
				// fuera del rango de vértices del polígono
				System.out.println(ge);
			}
			
			c = c.avanzarAleatorio(3);
		}

	}
}
