import JavaRG.Nucleo2D.*;

public class Equals {
	public Equals() {
	}
	
	public static void main(String args[]) {
		Punto2D punto1 = new Punto2D(10, 10);
		Punto2D punto2 = new Punto2D(punto1);
		Segmento2D segmento = new Segmento2D();
		
		System.out.println(punto1 == punto2);
		System.out.println(punto1.equals(punto2));
		System.out.println(punto1.equals(segmento));				
	}
}
