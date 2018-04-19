
public class Sphere extends Circle {

	public Sphere(double radius) {
		super(radius);
	}
	
	public double getArea(){
		return 4 * (Math.PI * Math.pow(_radius, 3)); 
	}
}
