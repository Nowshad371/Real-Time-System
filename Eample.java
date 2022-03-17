package Week02;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Eample {

	public static void main(String[] args) {
		
		logic l1 = new logic(1);
		
		Thread t1 = new Thread(l1);
		
		int numOfCores = Runtime.getRuntime().availableProcessors();
		
		logic l2 = new logic(2);
		
		Thread t2 = new Thread(l2);
		
		logic l3 = new logic(3);
		logic l4 = new logic(4);
		logic l5 = new logic(5);
		
		
		ExecutorService executor = Executors.newCachedThreadPool();
	
		 //executor = Executors.newFixedThreadPool(numOfCores);
		//ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++) {
			executor.submit(new logic(i));
			try {
				Thread.sleep(1000);
			}
			catch(Exception e) {
				
			}
		}
		executor.shutdown();
	}	
}

class logic implements Runnable{

	int id;
	
	public logic(int id) {
		this.id = id;
	}
	@Override
	public void run() {
		System.out.println("Logic " + id + ": hello from " + Thread.currentThread().getName()); 
		
		try {
			Thread.sleep(3000);
		}
		catch(Exception e) {
			System.out.println("Goodbye from" + Thread.currentThread().getName()); 
		}
	}
	}

