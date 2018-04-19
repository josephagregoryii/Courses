
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	private static final int PORT = 6437;

	public static void main(String[] args) {	
		ArrayList<Integer> list = input();
		System.out.println("Sending: " + list);
		ArrayList<Integer> primes = run(list);
		System.out.println("Receive: " + primes);

	}

	private static ArrayList<Integer> input() {
		Scanner input = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<Integer>();
		String str = "!";
		while(true){
			System.out.println("Enter an integer (“!” to send):");
			if(input.hasNext(str)){
				break;
			}
			if(input.hasNextInt()){
				int in = input.nextInt();
				list.add(in);
			}
			else{
				input.next();
				continue;
				
			}
		}
		return list;
	}


	public static ArrayList<Integer> run(ArrayList<Integer> list){
		ArrayList<Integer> _list = list;
		Socket client = null;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;	
		ArrayList<Integer> primes = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			client = new Socket(address, PORT);

			outputStream = new ObjectOutputStream(client.getOutputStream());
			outputStream.flush();
			outputStream.writeObject(_list);
			outputStream.flush();
		} 
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		try{
			outputStream = new ObjectOutputStream(client.getOutputStream());
			outputStream.flush();

			inputStream = new ObjectInputStream(client.getInputStream());
			try{
				primes = (ArrayList<Integer>) inputStream.readObject();
			}
			catch( ClassNotFoundException e){
				System.out.println("Bad List");
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				if (client != null) {
					client.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} 
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return primes;
	}
}