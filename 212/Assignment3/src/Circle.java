
public class Circle implements Measurable{
	public double _radius;
	
	public Circle(double radius){
		_radius = radius;	
	}
	
	public double getArea(){
		return  Math.PI * Math.pow(_radius, 2);
	}

}
