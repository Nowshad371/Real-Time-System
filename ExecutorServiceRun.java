package Week02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceRun {

	public static void main(String[] args) {
		ExecutorService Service= Executors.newSingleThreadExecutor();
		
		Service.execute(new Task1());
		Service.execute(new Thread(new Task2()));
		
		
		
		System.out.println("Task 3");
		
		for(int i=0;i<10;i++) {
			System.out.print(i + " ");
		}
		System.out.println("Task 3 Done");
		
		Service.shutdown();
	}

}

class Task1 extends Thread{
	
	public void run() {
		System.out.println("Task 1");
		
		for(int i=0;i<10;i++) {
			System.out.print(i + " ");
		}
		System.out.println("Task 1 Done");
	}
}

class Task2 implements Runnable{
	
	public void run() {
		System.out.println("Task 2");
		
		for(int i=0;i<10;i++) {
			System.out.print(i + " ");
		}
		System.out.println("Task 2 Done");
	}
}