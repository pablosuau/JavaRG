import JavaRG.Nucleo2D.*;
import JavaRG.Basica.*;

public class PoligonoSimple2 {

	Poligono2D poligono;
	
	public PoligonoSimple2 () {
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

	public void eliminar (int vertice) {
		try {
			poligono.eliminar(vertice);
		} catch (GeomException ge) {
			System.out.println(ge);
		}
	}
	
	public static void main (String []args) {
		PoligonoSimple2 polSim = new PoligonoSimple2();
		
		polSim.insertar(new Punto2D(0,0));
		polSim.escribeDatos();
		polSim.insertar(new Punto2D(1,1));
		polSim.escribeDatos();
		polSim.insertar(new Punto2D(1,0));
		polSim.escribeDatos();
		polSim.insertar(new Punto2D(0,1));
		polSim.escribeDatos();
		// Borramos el vértice recien introducido y comprobamos
		// si es simple de nuevo
		polSim.eliminar(3); 
		polSim.escribeDatos();
	}
}
