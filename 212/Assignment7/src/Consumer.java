import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
	
	private LinkedBlockingQueue<String> queue;
	private static int staticID;
	private final int ID;
	private final Producer producer;
	private int counter = 0;
	Random random = new Random();
	
	public Consumer(LinkedBlockingQueue<String> queue, Producer producer){
		this.queue = queue;
		this.ID = ++staticID;
		this.producer = producer;
	}
	
	@Override
	public void run() {
		
		while(!queue.isEmpty() || !producer.done){
			try{
				Thread.sleep(random.nextInt(10));
				if (queue.poll(30,TimeUnit.MILLISECONDS) != null)
					counter++;
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(counter % 100 == 0) {
				System.out.printf("\"Consumer %d\": %d events consumed\n", ID, counter);
			}
		}
		
	}
	public void printSummary(){
		System.out.printf("\"Consumer %d\": %d events consumed\n", ID, counter);
	}

}
