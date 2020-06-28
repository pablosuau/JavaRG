import JavaRG.Nucleo2D.*;
import JavaRG.Mat;

public class EjemploTransformaciones {
	public EjemploTransformaciones() {
	}

	public static void main (String []args) throws GeomException {
		Punto2D comienzo = new Punto2D(0,0);
		Punto2D fin = new Punto2D(3,3);
		Segmento2D segmento = new Segmento2D(comienzo, fin);
		System.out.println("Segmento inicial:");
		System.out.println(segmento);
		System.out.println("----------");

		// Realizamos una traslación
		System.out.println("Traslacion en el eje x tx=2 y en el eje y ty=-2");
		System.out.println(segmento.trasladar(2,-2));
		System.out.println("----------");

		// Giramos el segmento tomando como centro de giro el centro
		// de gravedad del propio segmento
		Punto2D centroSegmento = segmento.centroGravedad();
		segmento.trasladar(-centroSegmento.x(), -centroSegmento.y());
		segmento.gira(Mat.grad2Rad(90));
		System.out.println("Giro de 90 grados");
		System.out.println(segmento.trasladar(centroSegmento.x(), centroSegmento.y()));
		System.out.println("----------");
		
		// Aplicamos una transformacion de espejo creando para ello la
		// matriz de transformacion adecuada
		double [][]matrizTransformacion = new double[2][2];
		matrizTransformacion[0][0] = -1;
		matrizTransformacion[0][1] = 0;
		matrizTransformacion[1][0] = 0;
		matrizTransformacion[1][1] = -1;
		System.out.println("Transformacion de espejo");
		System.out.println(segmento.transforma(matrizTransformacion));
	}
}
