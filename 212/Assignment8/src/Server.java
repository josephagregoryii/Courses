
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	private static final int PORT = 6437;

	public static void main(String[] args){
		ArrayList<Integer> primesList = null;
		ServerSocket serverSocket = null;
		Socket socket = null;
		ObjectOutputStream outputStream = null;
		ObjectInputStream inputStream = null;
		try {
			serverSocket = new ServerSocket(PORT);
			socket = serverSocket.accept();

			inputStream = new ObjectInputStream(socket.getInputStream());
			try{
				primesList = primes((ArrayList<Integer>) inputStream.readObject());	
			}
			catch (ClassNotFoundException e) {
				System.out.println("Bad List");
				e.printStackTrace();
			}
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.flush();
			outputStream.writeObject(primesList);
			outputStream.flush();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		} 
		finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static ArrayList<Integer> primes(ArrayList<Integer> Objects) {
		ArrayList<Integer> primesList = new ArrayList<Integer>();
		for(Integer num : Objects){
			boolean bool = true;
			for (int i = 2; i < num; i++) {
				if (num % i == 0) {
					bool = false;
				}
			}
			if (bool == true){
				primesList.add(num);
			}
		}
		return primesList;

	}
}