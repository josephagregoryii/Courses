
public class Rectangle implements Measurable {
	public double _length;
	public double _width;
	
	public Rectangle(double length, double width){
		_length = length;
		_width = width;
	}
	
	public double getArea(){
		return _length * _width;
	}
	
	

}
