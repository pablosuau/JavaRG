import JavaRG.Nucleo2D.*;

public class EjemploObjeto {
	public EjemploObjeto() {
	}

	public static void main (String args[]) 
	{
		// Cualquier clase del paquete Nucleo puede ser referenciada como 
		// ObjetoGeometrico
		ObjetoGeometrico punto = new Punto2D();
		ObjetoGeometrico segmento = new Segmento2D();
		
		// La funci�n cambiarNombre est� implementada en ObjetoGeometrico
		punto.cambiarNombre("EjemploPunto");
		segmento.cambiarNombre("EjemploSegmento");

		// La funcion toString() est� sobrecargada por todas las subclases
		// de ObjetoGeometrico
		System.out.println(punto);
		System.out.println(segmento);

		// El m�todo hx() de Punto2D solo puede ser llamada desde una referencia
		// de esa clase
		Punto2D copiaPunto = (Punto2D) punto;
		System.out.println(copiaPunto.hx());
	}
}
