
public class Box extends Rectangle{
	public double _height;
	
	public Box(double length, double width, double height){
		super(length,width);
		_height = height;
	}
	public double getArea(){
		return (2* _height * _length) + (2* _height * _width) + (2*_width * _length);
	}

}
