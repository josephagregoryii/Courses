import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class Panel extends JPanel {
	private final static ArrayList<Point> _points = new ArrayList<>();
	public int size = 8;
	public Color color = Color.black;

	public Panel() {

		MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				Point point = new Point(e.getX(), e.getY(), size, color);
				_points.add(point);
				repaint();
			}			
			public void mouseDragged(MouseEvent e){
				Point point = new Point(e.getX(), e.getY(), size, color);
				_points.add(point);
				repaint();
			}		
		};
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for (Point point : _points){
			g.setColor(point._color);
			g.fillOval(point._x, point._y, point._size, point._size);

		}

	}
	public void clear(){
		_points.clear();
		repaint();

	}
}


