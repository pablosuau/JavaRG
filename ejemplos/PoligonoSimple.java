import JavaRG.Nucleo2D.*;
import JavaRG.Basica.*;

public class PoligonoSimple {

	Poligono2D poligono;
	
	public PoligonoSimple () {
		poligono = new Poligono2D();
	}

	public void escribeDatos() {
		if (poligono != null)
		{
			System.out.print("esSimple -> " + poligono.esSimple());
			System.out.println(" - esCompleto -> " + poligono.esCompleto());
		}
	}

	public void insertar(Punto2D punto) {
		poligono.insertar(punto);
	}
	
	public static void main (String []args) {
		PoligonoSimple polSim = new PoligonoSimple();
		
		polSim.insertar(new Punto2D(0,0));
		polSim.escribeDatos();
		polSim.insertar(new Punto2D(1,1));
		polSim.escribeDatos();
		polSim.insertar(new Punto2D(1,0));
		polSim.escribeDatos();
		polSim.insertar(new Punto2D(0,1));
		polSim.escribeDatos();
		polSim.insertar(new Punto2D(0,1)); // Cerramos el polígono
		polSim.escribeDatos();
	}
}
