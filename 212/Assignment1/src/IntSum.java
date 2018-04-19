//Joseph Gregory CIS 212
import java.util.Scanner;
public class IntSum {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int sum = 0;
		int input = 0;
		boolean running = true;
		
		while(running){
			System.out.println("Enter a positive integer (-3 to print, -2 to reset, -1 to quit):");
			input = scan.nextInt();
			
			if(input == -3){					//prints the current sum
				System.out.println("Sum: " + sum);
			}
			else if(input == -2){				//sets sum to 0
				sum = 0;
			}
			else if(input == -1){				//print sum and quit
				System.out.println("Sum: " + sum);
				running = false;
			}
			else if(input > 0){					// add input to sum
				sum = sum + input;
			}
			else if(input == 0 || input < -3){	//ignores input if - or < -3 and continues
				continue;
			}
		}
		
	}
}