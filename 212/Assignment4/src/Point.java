import java.awt.Color;

public class Point {
	public int _x;
	public int _y;
	public int _size;
	public Color _color = Color.BLACK;

	public Point(int x, int y, int size, Color color) {
		_x = x;
		_y = y;
		_size = size;
		_color = color;
	}
}
