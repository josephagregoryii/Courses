import java.util.Random;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args){
		Measurable Measure;
		Random random = new Random();
		int rectCount = 0;
		int boxCount = 0;
		int circCount = 0;
		int sphCount = 0;
		ArrayList<Measurable> list = new ArrayList<Measurable>();
		for (int i = 0; i < 1000; i++ ){
			int value = random.nextInt(4);
			if (value == 0){
				rectCount += 1;
				Measure = new Rectangle(nextDouble(),nextDouble());
			}
			else if (value == 1){
				boxCount += 1;
				Measure = new Box(nextDouble(),nextDouble(),nextDouble());
			}
			else if (value == 2){
				circCount =+ 1;
				Measure = new Circle(nextDouble());
			}
			else{
				sphCount += 1;
				Measure = new Sphere(nextDouble());
			}
			list.add(Measure);
		}
		System.out.println("Rectangles: " + rectCount + " Boxes: " + boxCount + " Circles: " + circCount + " Spheres: " + sphCount);
		System.out.println("Sum: " + calculateSum(list));

	}
	private static double nextDouble(){
		Random random = new Random();
		double value = Double.MIN_VALUE + random.nextDouble();
		return value;
	}
	private static double calculateSum(ArrayList<Measurable> list){
		double sum = 0;
		for (Measurable obj:list){
			sum = sum + obj.getArea();
		}
		return sum;
	}
}
