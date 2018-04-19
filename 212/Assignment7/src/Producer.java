import java.util.concurrent.LinkedBlockingQueue;

public class Producer implements Runnable {
	private LinkedBlockingQueue<String> queue;
	private static int staticID;
	private final int ID;
	public boolean done;
	private int counter = 0;
	
	public Producer(LinkedBlockingQueue<String> queue){
		this.queue = queue;
		this.ID = ++staticID;
		this.done = false;
	}

	@Override
	public void run() {
		for(int i = 0; i < 1000; i++){
			try{
				queue.put(Double.toString(Math.random()));
				counter++;
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(counter % 100 == 0){
				System.out.printf("\"Producer %d\": %d events producer \n", ID, counter);
			}
		}
		this.done = true;
	}
	public void printSummary(){
		System.out.printf("\"Producer %d\": %d events producer \n", ID, counter);
	}

}
