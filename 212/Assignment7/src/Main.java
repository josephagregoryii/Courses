import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Main {
	
	public static void main(String[] args){
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(100);
		
		Producer producer1 = new Producer(queue);
		Consumer consumer1 = new Consumer(queue, producer1);
		Consumer consumer2 = new Consumer(queue, producer1);
		Consumer consumer3 = new Consumer(queue, producer1);
		Consumer consumer4 = new Consumer(queue, producer1);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(producer1);
		executor.execute(consumer1);
		executor.execute(consumer2);
		executor.execute(consumer3);
		executor.execute(consumer4);
		
		executor.shutdown();
		
		try{
			boolean bool = executor.awaitTermination(30, TimeUnit.SECONDS);
			if(bool){
				System.out.println("Summary: ");
				producer1.printSummary();
				consumer1.printSummary();
				consumer2.printSummary();
				consumer3.printSummary();
				consumer4.printSummary();
			}
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
